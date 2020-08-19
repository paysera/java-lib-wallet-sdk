package com.paysera.sdk.wallet.clients;

import bolts.Continuation;
import bolts.Task;
import com.paysera.sdk.wallet.WalletApiResponse;
import com.paysera.sdk.wallet.entities.CurrencyConversionDirection;
import com.paysera.sdk.wallet.exceptions.WalletApiException;
import com.paysera.sdk.wallet.handlers.AlwaysAsyncJsonHttpResponseHandler;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.*;

public class ApiClient {
    public static class AuthorizedRequest {
        RefreshingWalletAsyncClient refreshingWalletAsyncClient;

        private List<String> fields = new ArrayList<>();
        private Map<String, String> parameters = new HashMap<>();
        private boolean isJsonRequest;
        private boolean isImageContent;
        private boolean isBinaryContent;
        private String url;
        private BaseRequest.HttpMethod method;

        private JSONObject json;
        private byte[] content;

        /**
         * Method to call for User Session requests.
         *
         * @param url
         * @param method
         * @param handler
         */
        private void userCall(final String url, final BaseRequest.HttpMethod method, final AlwaysAsyncJsonHttpResponseHandler handler) {
            this.method = method;
            this.url = url;
            execute(handler);
        }
        /**
         * Execute the request
         * @param handler
         */
        private void execute(final AlwaysAsyncJsonHttpResponseHandler handler) {
            if (!this.fields.isEmpty()) {
                parameters.put("fields", join(",", this.fields));
            }
            RequestBody requestBody = null;
            switch (method) {
                case GET:
                    this.refreshingWalletAsyncClient.get(url, parameters)
                            .continueWith(new Continuation<WalletApiResponse, Object>() {
                                @Override
                                public Object then(Task<WalletApiResponse> task) throws Exception {
                                    handleResponseTask(task, handler);
                                    return null;
                                }
                            }, handler.getExecutor());
                    break;
                case POST:
                    try {
                        requestBody = RequestBody.create(
                                MediaType.parse("application/json; charset=utf-8"),
                                json.toString().getBytes("utf-8")
                        );
                    } catch (UnsupportedEncodingException e) {
                        /*Timber.d(e);
                        Timber.e("Api: execute POST");*/
                    }
                    this.refreshingWalletAsyncClient.post(url, requestBody)
                            .continueWith(new Continuation<WalletApiResponse, Object>() {
                                @Override
                                public Object then(Task<WalletApiResponse> task) throws Exception {
                                    handleResponseTask(task, handler);

                                    return null;
                                }
                            }, handler.getExecutor());
                    break;
                case PUT:
                    if (isJsonRequest) {
                        try {
                            requestBody = RequestBody.create(
                                    MediaType.parse("application/json; charset=utf-8"),
                                    json.toString().getBytes("utf-8")
                            );
                        } catch (UnsupportedEncodingException e) {
                            /*Timber.d(e);
                            Timber.e("Api: execute PUT");*/
                        }
                        this.refreshingWalletAsyncClient.put(url, requestBody)
                                .continueWith(new Continuation<WalletApiResponse, Object>() {
                                    @Override
                                    public Object then(Task<WalletApiResponse> task) throws Exception {
                                        handleResponseTask(task, handler);
                                        return null;
                                    }
                                }, handler.getExecutor());
                    } else if (isImageContent) {
                        requestBody = RequestBody.create(
                                MediaType.parse("image/jpeg; charset=utf-8"),
                                content
                        );
                        this.refreshingWalletAsyncClient.put(url, requestBody)
                                .continueWith(new Continuation<WalletApiResponse, Object>() {
                                    @Override
                                    public Object then(Task<WalletApiResponse> task) throws Exception {
                                        handleResponseTask(task, handler);
                                        return null;
                                    }
                                }, handler.getExecutor());
                    } else if (isBinaryContent) {
                        requestBody = RequestBody.create(
                                MediaType.parse("application/octet-stream; charset=utf-8"),
                                content
                        );
                        this.refreshingWalletAsyncClient.put(url, requestBody)
                                .continueWith(new Continuation<WalletApiResponse, Object>() {
                                    @Override
                                    public Object then(Task<WalletApiResponse> task) throws Exception {
                                        handleResponseTask(task, handler);
                                        return null;
                                    }
                                }, handler.getExecutor());
                    }
                    break;
            }

        }

        private void handleResponseTask(Task<WalletApiResponse> task, AlwaysAsyncJsonHttpResponseHandler handler) {
            if (!task.isFaulted()) {
                WalletApiResponse response = task.getResult();

                if (response.getBody() != null) {
                    try {
                        JSONObject jsonObject = new JSONObject(response.getBody());
                        handler.onSuccess(response.getCode(), null, jsonObject);
                        return;
                    } catch (JSONException e) {
                        /*Timber.d(e);
                        Timber.e("Api: handleResponseTask.success1");*/
                    }

                    try {
                        JSONArray jsonArray = new JSONArray(response.getBody());
                        handler.onSuccess(response.getCode(), null, jsonArray);
                        return;
                    } catch (JSONException e) {
                        /*Timber.d(e);
                        Timber.e("Api: handleResponseTask.success2");*/
                    }
                }

                // both shouldn't be implemented, so one call will definitely get it right
                handler.onSuccess(response.getCode(), null, (JSONObject) null);
                handler.onSuccess(response.getCode(), null, (JSONArray) null);
            } else {
                if (task.getError() instanceof WalletApiException) {
                    if (((WalletApiException) task.getError()).isNetworkError()) {
                        handler.onFailure(
                                0,
                                null,
                                task.getError(),
                                getNoConnectionJsonObject()
                        );
                        return;
                    }

                    Integer statusCode = ((WalletApiException) task.getError()).getStatusCode();
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("error", ((WalletApiException) task.getError()).getErrorCode());
                        jsonObject.put("error_description", ((WalletApiException) task.getError()).getErrorDescription());
                        handler.onFailure(
                                statusCode,
                                null,
                                task.getError(),
                                jsonObject
                        );

                        return;
                    } catch (JSONException e) {
                        /*Timber.d(e);
                        Timber.e("Api: handleResponseTask.failure");*/
                    }

                    handler.onFailure(statusCode, null, task.getError(), null);
                } else {
                    handler.onFailure(-1, null, task.getError(), null);
                }
            }
        }

        private String join(String join, List<String> strings) {
            if (strings == null || strings.size() == 0) {
                return "";
            } else if (strings.size() == 1) {
                return strings.get(0);
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(strings.get(0));
                for (int i = 1; i < strings.size(); i++) {
                    sb.append(join).append(strings.get(i));
                }
                return sb.toString();
            }
        }

        private JSONObject getNoConnectionJsonObject() {
            try {
                return new JSONObject().put("error", "no connection");
            } catch (JSONException e) {
                /*Timber.d(e);
                Timber.e("Api: getNoConnectionJsonObject");*/
                return null;
            }
        }

        public AuthorizedRequest addField(String field) {
            this.fields.add(field);

            return this;
        }

        void jsonBody(JSONObject json) {
            try {
                isJsonRequest = true;
                this.json = json;
            } catch (Exception e) {
                /*Timber.d(e);
                Timber.e("Api: jsonBody");*/
            }
        }

        void binaryBody(byte[] content) {
            this.content = content;
            isBinaryContent = true;
        }

        public void phone(String phone) {
            parameters.put("phone", phone);
        }

        public void email(String email) {
            parameters.put("email", email);
        }

        public void from(String from) {
            parameters.put("from", from);
        }

        public void fromCurrency(String fromCurrency) {
            parameters.put("from_currency", fromCurrency);
        }

        public void toCurrency(String toCurrency) {
            parameters.put("to_currency", toCurrency);
        }

        public void fromAmount(BigDecimal fromAmount) {
            parameters.put("from_amount_decimal", fromAmount.toString());
        }

        public void toAmount(BigDecimal toAmount) {
            parameters.put("to_amount_decimal", toAmount.toString());
        }

        public void accountNumber(String accountNumber) {
            parameters.put("account_number", accountNumber);
        }

        public void to(String to) {
            parameters.put("to", to);
        }

        public void limit(String limit) {
            parameters.put("limit", limit);
        }

        public void type(String type) {
            parameters.put("type", type);
        }

        public void userId(String userId) {
            parameters.put("user_id", userId);
        }

        public void offset(String offset) {
            parameters.put("offset", offset);
        }

        public void direction(String direction) {
            parameters.put("direction", direction);
        }

        public void locale(String locale) {
            parameters.put("locale", locale);
        }

        public void code(String code) {
            parameters.put("code", code);
        }

        public void description(String description) {
            parameters.put("description", description);
        }

        public void addDefaultFields() {
            this.fields.add("*");
        }
    }

    public abstract static class BaseRequest {
        protected AuthorizedRequest r;

        protected enum HttpMethod {GET, POST, PUT, DELETE}

        public BaseRequest() {
            r = new AuthorizedRequest();
        }

        public void call(final AlwaysAsyncJsonHttpResponseHandler handler) {
            try {
                r.userCall(getUrl(), getMethod(), handler);
            } catch (Exception e) {
                /*Timber.d(e);
                Timber.e("Api: BaseRequest call");*/
            }
        }

        /**
         * Set when making calls using client tokens. I.e Logging in, refreshing token, reseting passwords, etc.
         *
         * @return
         */

        public AuthorizedRequest getRequest() {
            return this.r;
        }

        protected abstract HttpMethod getMethod();
        protected abstract String getUrl();

    }

    public static class AvatarPut extends BaseRequest {

        public AvatarPut(byte[] content) {
            super();
            r.binaryBody(content);
        }

        @Override
        protected HttpMethod getMethod() {
            return HttpMethod.PUT;
        }

        @Override
        protected String getUrl() {
            return "user/me/avatar";
        }
    }

    public static class CurrencyConversion {
        public static class CalculateConversionRate extends BaseRequest {
            public CalculateConversionRate(
                    String accountNumber,
                    String fromCurrency,
                    String toCurrency,
                    BigDecimal fromAmount,
                    BigDecimal toAmount
            ) {
                super();

                r.accountNumber(accountNumber);
                r.fromCurrency(fromCurrency);
                r.toCurrency(toCurrency);

                if (toAmount.compareTo(BigDecimal.ZERO) == 0) {
                    r.fromAmount(fromAmount);
                } else {
                    r.toAmount(toAmount);
                }
            }

            @Override
            protected HttpMethod getMethod() {
                return HttpMethod.GET;
            }

            @Override
            protected String getUrl() {
                return String.format(Locale.US, "currency-conversion");
            }
        }


        public static class ConvertCurrency extends BaseRequest {
            public ConvertCurrency(
                    String accountNumber,
                    String fromCurrency,
                    String toCurrency,
                    BigDecimal fromAmount,
                    BigDecimal toAmount,
                    String direction
            ) {
                super();

                JSONObject body = new JSONObject();
                try {
                    body
                            .put("account_number", accountNumber)
                            .put("from_currency", fromCurrency)
                            .put("to_currency", toCurrency);

                    if (direction.equals(CurrencyConversionDirection.FROM.name())) {
                        body
                                .put("from_amount_decimal", fromAmount.toString())
                                .put("min_to_amount_decimal", toAmount.toString());
                    } else {
                        body
                                .put("to_amount_decimal", toAmount.toString())
                                .put("max_from_amount_decimal", fromAmount.toString());
                    }

                    r.jsonBody(body);
                } catch (JSONException e) {
                    /*Timber.d(e);
                    Timber.e("Api: ConvertCurrency");*/
                }
            }

            @Override
            protected HttpMethod getMethod() {
                return HttpMethod.POST;
            }

            @Override
            protected String getUrl() {
                return String.format(Locale.US, "currency-conversion");
            }
        }
    }

    public static class TransactionReserve extends BaseRequest {

        private final String key;

        public TransactionReserve(String key, JSONObject json) {
            super();
            this.key = key;
            r.jsonBody(json);
        }

        @Override
        protected HttpMethod getMethod() {
            return HttpMethod.PUT;
        }

        @Override
        protected String getUrl() {
            return String.format(Locale.US, "transaction/%s/reserve", key);
        }
    }

    public static class CodeGenerationValid extends BaseRequest {

        final String id;

        public CodeGenerationValid(long id) {
            super();
            this.id = String.valueOf(id);
        }

        @Override
        protected HttpMethod getMethod() {
            return HttpMethod.GET;
        }

        @Override
        protected String getUrl() {
            return String.format(Locale.US, "generator/%s", id);
        }
    }

    public static class CodeGenerationSeedData extends BaseRequest {


        public CodeGenerationSeedData(JSONObject json) {
            super();
            r.jsonBody(json);
        }

        @Override
        protected HttpMethod getMethod() {
            return HttpMethod.POST;
        }

        @Override
        protected String getUrl() {
            return "generator";
        }
    }

    /**
     * PUSH NOTIFICATIONS
     */

    public static class NotificationsSubscribe extends BaseRequest {

        public NotificationsSubscribe(JSONObject json) {
            super();
            r.jsonBody(json);
        }

        @Override
        protected HttpMethod getMethod() {
            return HttpMethod.POST;
        }

        @Override
        protected String getUrl() {
            return "subscriber";
        }
    }

    public static class NotificationsModify extends BaseRequest {
        protected final Integer id;

        public NotificationsModify(Integer id, JSONObject json) {
            super();
            this.id = id;
            r.jsonBody(json);
        }

        @Override
        protected HttpMethod getMethod() {
            return HttpMethod.PUT;
        }

        @Override
        protected String getUrl() {
            return String.format(Locale.US, "subscriber/%d", id);
        }
    }
}
