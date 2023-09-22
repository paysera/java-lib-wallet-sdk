package com.paysera.lib.wallet.clients;

import bolts.Task;
import com.google.gson.JsonObject;
import com.paysera.lib.wallet.ClientServerTimeSynchronizationConfiguration;
import com.paysera.lib.wallet.entities.*;
import com.paysera.lib.wallet.entities.card.Card;
import com.paysera.lib.wallet.entities.client.Client;
import com.paysera.lib.wallet.entities.confirmations.Confirmation;
import com.paysera.lib.wallet.entities.confirmations.ConfirmationFilter;
import com.paysera.lib.wallet.entities.easypay.EasyPayCreateTransfer;
import com.paysera.lib.wallet.entities.easypay.EasyPayFees;
import com.paysera.lib.wallet.entities.easypay.EasyPayMarkUpdatedTransfersAsSeen;
import com.paysera.lib.wallet.entities.easypay.EasyPayTransfer;
import com.paysera.lib.wallet.entities.easypay.EasyPayTransferFilter;
import com.paysera.lib.wallet.entities.generator.Generator;
import com.paysera.lib.wallet.entities.identifications.IdentificationRequestFilter;
import com.paysera.lib.wallet.entities.locations.Location;
import com.paysera.lib.wallet.entities.locations.LocationCategory;
import com.paysera.lib.wallet.entities.notification.NotificationSubscriber;
import com.paysera.lib.wallet.entities.pos.Spot;
import com.paysera.lib.wallet.entities.requests.*;
import com.paysera.lib.wallet.entities.transfer.IbanToEvp;
import com.paysera.lib.wallet.entities.transfer.Transfer;
import com.paysera.lib.wallet.entities.transfer.TransferPassword;
import com.paysera.lib.wallet.filters.*;
import com.paysera.lib.wallet.helpers.DateHelper;
import com.paysera.lib.wallet.helpers.EnumHelper;
import com.paysera.lib.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.lib.wallet.helpers.StringHelper;
import com.paysera.lib.wallet.providers.TimestampProvider;
import okhttp3.RequestBody;
import org.joda.money.Money;
import retrofit2.Retrofit;
import java.util.List;
import java.util.Map;

public class WalletAsyncClient extends BaseAsyncClient {

    private WalletApiClient walletApiClient;

    public WalletAsyncClient(
        TimestampProvider timestampProvider,
        ClientServerTimeSynchronizationConfiguration clientServerTimeSynchronizationConfiguration,
        PublicWalletApiClient publicWalletApiClient,
        WalletApiClient walletApiClient,
        Retrofit retrofit,
        OkHTTPQueryStringConverter okHTTPQueryStringConverter
    ) {
        super(
            timestampProvider,
            clientServerTimeSynchronizationConfiguration,
            publicWalletApiClient,
            retrofit,
            okHTTPQueryStringConverter
        );
        this.walletApiClient = walletApiClient;
    }

    public Task<ServerConfiguration> getServerConfiguration() {
        return this.execute(this.walletApiClient.getServerConfiguration());
    }

    public Task<ServerInformation> getServerInformation() {
        return this.execute(this.walletApiClient.getServerInformation());
    }

    public Task<CurrencyConversionCalculation> calculateCurrencyConversion(CurrencyConversionCalculation request) {
        return this.execute(
            this.walletApiClient.calculateCurrencyConversion(
                request.getFromAmountDecimal(),
                request.getFromCurrency(),
                request.getToAmountDecimal(),
                request.getToCurrency(),
                request.getAccountNumber()
            )
        );
    }

    public Task<CurrencyConversionResult> convertCurrency(ConvertCurrencyCurrencyRequest request) {
        return this.execute(this.walletApiClient.convertCurrency(request));
    }

    public Task<User> getUser() {
        return this.execute(this.walletApiClient.getUser());
    }

    public Task<List<Wallet>> getUserWallets() {
        return this.execute(this.walletApiClient.getUserWallets(false));
    }

    public Task<List<Wallet>> getUserWallets(Boolean isInactiveIncluded) {
        return this.execute(this.walletApiClient.getUserWallets(isInactiveIncluded));
    }

    public Task<Questionnaire> getUserQuestionnaire(Integer userId) {
        return this.execute(this.walletApiClient.getUserQuestionnaire(userId));
    }

    public Task<JWTTokenResponse> getJWTToken(JWTScope scope) {
        return this.execute(this.walletApiClient.getJWTToken(scope));
    }

    public Task<User> getUser(Integer userId) {
        return this.execute(this.walletApiClient.getUser(userId));
    }

    public Task<List<Project>> getUserProjects() {
        return this.execute(this.walletApiClient.getUserProjects());
    }

    public Task<List<Project>> getUserProjects(String fields) {
        return this.execute(this.walletApiClient.getUserProjects(fields));
    }

    public Task<List<Project>> getUserProjects(Integer userId) {
        return this.execute(this.walletApiClient.getUserProjects(userId));
    }

    public Task<List<Location>> getClientLocations() {
        return this.execute(this.walletApiClient.getClientLocations());
    }

    public Task<Wallet> getWallet(Integer walletId) {
        return this.execute(this.walletApiClient.getWallet(walletId));
    }

    public Task<WalletBalance> getWalletBalance(GetWalletBalanceRequest getWalletBalanceRequest) {
        int walletId = getWalletBalanceRequest.getWalletId();
        final String convertToCurrency = getWalletBalanceRequest.getConvertTo();
        boolean includeConvertToCurrency = getWalletBalanceRequest.isIncludeConvertToCurrency();
        boolean showHistoricalCurrency = getWalletBalanceRequest.isShowHistoricalCurrencies();

        return this.execute(
            this.walletApiClient.getWalletBalance(
                walletId,
                convertToCurrency,
                includeConvertToCurrency,
                showHistoricalCurrency
            )
        ).continueWithTask(task -> {
                if (convertToCurrency == null) {
                    return task;
                }

                for (CurrencyBalance currencyBalance : task.getResult().getCurrencyBalances()) {
                    currencyBalance.setConvertedCurrency(convertToCurrency);
                }

                return task;
            }
        );
    }

    public Task<AuthTokenResponse> createAuthToken() {
        return execute(walletApiClient.createAuthToken());
    }

    public Task<Wallet> getWallet(WalletFilter walletFilter) {
        return execute(
            walletApiClient.getWallet(
                walletFilter.getAccountNumber(),
                walletFilter.getPhone(),
                walletFilter.getEmail(),
                walletFilter.getUserId()
            )
        );
    }

    public Task<Card> createCard(Card card) {
        return this.execute(this.walletApiClient.createCard(card));
    }

    public Task<Card> getCard(Integer cardId) {
        return this.execute(this.walletApiClient.getCard(cardId));
    }

    public Task<Void> deleteCard(Integer cardId) {
        return this.execute(this.walletApiClient.deleteCard(cardId));
    }

    public Task<MetadataAwareResponse<Card>> getCards(
        CardFilter cardFilter
    ) {
        return this.execute(
            this.walletApiClient.getCards(
                cardFilter.getUserId(),
                cardFilter.getLimit(),
                cardFilter.getOffset()
            )
        );
    }

    public Task<Void> deleteWalletDescription(Integer walletId) {
        return this.execute(this.walletApiClient.deleteWalletDescription(walletId));
    }

    public Task<Wallet> changeWalletDescription(
        Integer walletId,
        String description
    ) {
        ChangeWalletDescriptionRequest
            changeWalletDescriptionRequest = new ChangeWalletDescriptionRequest(description);
        return this.execute(this.walletApiClient.changeWalletDescription(
            walletId, changeWalletDescriptionRequest
        ));
    }

    public Task<User> assignPhoneNumber(
        Integer userId,
        String phone,
        UserPhoneConfirmationParameters parameters
    ) {
        AssignPhoneNumberRequest assignPhoneNumberRequest = new AssignPhoneNumberRequest(phone, parameters);
        return this.execute(
            this.walletApiClient.assignPhoneNumber(
                userId,
                assignPhoneNumberRequest
            )
        );
    }

    public Task<User> assignPhoneNumber(
        Integer userId,
        String phone
    ) {
        AssignPhoneNumberRequest assignPhoneNumberRequest = new AssignPhoneNumberRequest(phone);
        return this.execute(
            this.walletApiClient.assignPhoneNumber(
                userId, assignPhoneNumberRequest
            )
        );
    }

    public Task<User> assignEmail(
        String email,
        UserEmailConfirmationParameters parameters
    ) {
        AssignEmailRequest assignEmailRequest = new AssignEmailRequest(email, parameters);
        return this.execute(this.walletApiClient.assignEmail(assignEmailRequest));
    }

    public Task<User> assignEmail(
        String email
    ) {
        AssignEmailRequest assignEmailRequest = new AssignEmailRequest(email);
        return this.execute(this.walletApiClient.assignEmail(assignEmailRequest));
    }

    public Task<User> confirmPhone(
        Integer userId,
        String code
    ) {
        ConfirmPhoneRequest confirmPhoneRequestRequestBody = new ConfirmPhoneRequest(code);
        return this.execute(this.walletApiClient.confirmPhone(
            userId, confirmPhoneRequestRequestBody
        ));
    }

    public Task<User> confirmEmail(
        String code
    ) {
        ConfirmEmailRequest confirmEmailRequest = new ConfirmEmailRequest(code);
        return this.execute(this.walletApiClient.confirmEmail(confirmEmailRequest));
    }

    public Task<List<String>> getCurrencies() {
        return this.execute(this.walletApiClient.getCurrencies());
    }

    public Task<Void> setUserAvatar(Integer userId, RequestBody requestBody) {
        return this.execute(this.walletApiClient.setUserAvatar(
            userId,
            requestBody
        ));
    }

    public Task<Void> setUserAvatar(RequestBody requestBody) {
        return this.execute(this.walletApiClient.setUserAvatar(
            requestBody
        ));
    }

    public Task<Void> deleteUserAvatar(Integer userId) {
        return this.execute(this.walletApiClient.deleteUserAvatar(userId));
    }

    public Task<UserPosition> provideUserPosition(
        float lat,
        float lng,
        String type
    ) {
        UserPosition userPosition = new UserPosition(lat, lng, type);
        return this.execute(this.walletApiClient.provideUserPosition(userPosition));
    }

    public Task<UserServiceResponse> getUserServices(Integer userId) {
        return this.execute(this.walletApiClient.getUserServices(userId));
    }

    public Task<Void> enableUserService(Integer userId, String service) {
        return this.execute(this.walletApiClient.enableUserService(userId, service));
    }

    public Task<Void> cancelPendingPayment(
        Integer walletId,
        long pendingPaymentId
    ) {
        return this.execute(
            this.walletApiClient.cancelPendingPayment(
                walletId,
                pendingPaymentId
            )
        );
    }

    public Task<TransactionRequest> createTransactionRequest(
        TransactionRequest transactionRequest
    ) {
        return this.execute(
            this.walletApiClient.createTransactionRequest(
                transactionRequest.getTransactionKey(),
                transactionRequest
            )
        );
    }

    public Task<Transaction> createTransaction(Transaction transaction) {
        return this.execute(this.walletApiClient.createTransaction(transaction));
    }

    public Task<Client> createClient(Client client) {
        return this.execute(this.walletApiClient.createClient(client));
    }

    public Task<UnknownDevice> getClientStatus(Integer clientId) {
        return this.execute(this.walletApiClient.getClientStatus(clientId));
    }

    public Task<User> createUser(
        UserRegistrationRequest userRegistrationRequest
    ) {
        return this.execute(this.walletApiClient.createUser(userRegistrationRequest));
    }

    public Task<User> requestResetPassword(ResetPasswordRequest resetPasswordRequest) {
        return this.execute(this.walletApiClient.requestResetPassword(resetPasswordRequest));
    }

    public Task<User> resetPassword(
        Integer userId,
        String code,
        String password
    ) {
        ResetPasswordConfirmRequest resetPasswordConfirmRequest = new ResetPasswordConfirmRequest();
        resetPasswordConfirmRequest.setCode(code);
        resetPasswordConfirmRequest.setPassword(password);
        return this.execute(
            this.walletApiClient.resetPassword(
                userId,
                resetPasswordConfirmRequest
            )
        );
    }

    public Task<User> changePassword(
        Integer userId,
        String oldPassword,
        String newPassword
    ) {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setOldPassword(oldPassword);
        changePasswordRequest.setPassword(newPassword);
        return this.execute(
            this.walletApiClient.changePassword(
                userId,
                changePasswordRequest
            )
        );
    }

    public Task<User> getUser(UserFilter userFilter) {
        return this.execute(this.walletApiClient.getUser(
            userFilter.getEmail(),
            userFilter.getPhone(),
            userFilter.getPersonCode(),
            userFilter.getCountryCode()
        ));
    }

    public Task<Map<String, Wallet>> getWallets(WalletsFilter walletsFilter) {
        return this.execute(
            this.walletApiClient.getWallets(
                StringHelper.listToString(walletsFilter.getEmailList(), ","),
                StringHelper.listToString(walletsFilter.getPhoneList(), ","),
                StringHelper.listToString(walletsFilter.getEmailHashList(), ","),
                StringHelper.listToString(walletsFilter.getPhoneHashList(), ","),
                walletsFilter.getLimit()
            )
        );
    }

    public Task<List<LocationCategory>> getLocationCategories(String locale) {
        return this.execute(this.walletApiClient.getLocationCategories(locale));
    }

    public Task<MetadataAwareResponse<Location>> getLocations(
        LocationsFilter locationsFilter
    ) {
        return this.execute(
            this.walletApiClient.getLocations(
                locationsFilter.getLocale(),
                locationsFilter.getLat(),
                locationsFilter.getLng(),
                locationsFilter.getDistance(),
                DateHelper.convertDateToUnixTimestampSeconds(locationsFilter.getUpdatedAfter()),
                StringHelper.listToString(locationsFilter.getStatuses(), ","),
                locationsFilter.getLimit(),
                locationsFilter.getOffset()
            )
        );
    }

    public Task<MetadataAwareResponse<Statement>> getStatements(
        StatementsFilter statementsFilter
    ) {
        String orderDirectionValue = null;
        if (statementsFilter.getOrderDirection() != null) {
            orderDirectionValue = statementsFilter.getOrderDirection().toString();
        }
        return this.execute(
            this.walletApiClient.getStatements(
                statementsFilter.getWalletId(),
                StringHelper.listToString(statementsFilter.getCurrencies(), ","),
                EnumHelper.enumToString(statementsFilter.getDirection()),
                statementsFilter.getText(),
                DateHelper.convertDateToUnixTimestampSeconds(statementsFilter.getFrom()),
                DateHelper.convertDateToUnixTimestampSeconds(statementsFilter.getTo()),
                statementsFilter.getLimit(),
                statementsFilter.getOffset(),
                statementsFilter.getAfter(),
                statementsFilter.getBefore(),
                statementsFilter.getOrderBy(),
                orderDirectionValue
            )
        );
    }

    public Task<MetadataAwareResponse<PendingPayment>> getPendingPayments(
        StatementsFilter statementsFilter
    ) {
        return this.execute(this.walletApiClient.getPendingPayments(
            statementsFilter.getWalletId(),
            statementsFilter.getLimit(),
            statementsFilter.getOffset()
        ));
    }

    public Task<MetadataAwareResponse<ReservationStatement>> getReservationStatements(
        StatementsFilter statementsFilter
    ) {
        return this.execute(
            this.walletApiClient.getReservationStatements(
                statementsFilter.getWalletId(),
                statementsFilter.getLimit(), statementsFilter.getOffset()
            )
        );
    }

    public Task<ContactBook> createContactBookForUser(Integer userId) {
        return this.execute(this.walletApiClient.createContactBookForUser(userId));
    }

    public Task<ContactBook> createContactBookForCurrentUser() {
        return this.execute(this.walletApiClient.createContactBookForCurrentUser());
    }

    public Task<Void> appendContactsToContactBook(
        Integer contactBookId,
        List<String> emails,
        List<String> phones,
        List<String> emailHashes,
        List<String> phoneHashes
    ) {
        AppendContactsToContactBookRequest request = new AppendContactsToContactBookRequest();
        request.setEmails(emails);
        request.setPhones(phones);
        request.setEmailHashes(emailHashes);
        request.setPhoneHashes(phoneHashes);
        return this.execute(
            this.walletApiClient.appendContactsToContactBook(
                contactBookId,
                request
            )
        );
    }

    public Task<Void> removeFromContactBook(
        Integer contactBookId,
        List<String> emailList,
        List<String> phoneList,
        List<String> emailHashList,
        List<String> phoneHashList
    ) {
        return this.execute(
            this.walletApiClient.removeFromContactBook(
                contactBookId,
                StringHelper.listToString(emailList, ","),
                StringHelper.listToString(phoneList, ","),
                StringHelper.listToString(emailHashList, ","),
                StringHelper.listToString(phoneHashList, ","))
        );
    }

    public Task<Void> unregisterSubscriber(
        Integer subscriberId
    ) {
        return this.execute(this.walletApiClient.unregisterSubscriber(subscriberId));
    }

    public Task<Void> unregisterSubscriber() {
        return this.execute(this.walletApiClient.unregisterSubscribers());
    }

    public Task<Transfer> createTransfer(Transfer transfer) {
        return this.execute(this.walletApiClient.createTransfer(transfer));
    }

    public Task<Transfer> simulateTransfer(Transfer transfer) {
        return this.execute(this.walletApiClient.simulateTransfer(transfer));
    }

    public Task<Transfer> reserveTransfer(String transferId) {
        return this.execute(this.walletApiClient.reserveTransferById(transferId));
    }

    public Task<Transfer> signTransfer(String transferId) {
        return this.execute(this.walletApiClient.signTransferById(transferId));
    }

    public Task<Transfer> deleteTransfer(String transferId) {
        return this.execute(this.walletApiClient.deleteTransferById(transferId));
    }

    public Task<Transfer> getTransfer(String transferId) {
        return this.execute(this.walletApiClient.getTransferById(transferId));
    }

    public Task<MetadataAwareResponse<Transfer>> getTransfers(TransfersFilter filter) {
        return this.execute(
            this.walletApiClient.getTransfers(
                filter.getCreditAccountNumber(),
                filter.getStatuses(),
                filter.getOffset(),
                filter.getLimit()
            )
        );
    }

    public Task<Transfer> provideTransferPassword(Long transferId, TransferPassword password) {
        return this.execute(this.walletApiClient.provideTransferPassword(transferId, password));
    }

    public Task<MetadataAwareResponse<IdentificationRequest>> getIdentificationRequests(IdentificationRequestFilter identificationRequestFilter) {
        String orderDirectionValue = null;
        if (identificationRequestFilter.getOrderDirection() != null) {
            orderDirectionValue = identificationRequestFilter.getOrderDirection().toString();
        }
        return this.execute(
            this.walletApiClient.getIdentificationRequests(
                identificationRequestFilter.getStatuses(),
                identificationRequestFilter.getOrderBy(),
                orderDirectionValue,
                identificationRequestFilter.getLimit(),
                identificationRequestFilter.getOffset()
            )
        );
    }

    public Task<MetadataAwareResponse<IdentificationRequest>> getIdentificationRequests(Integer userId, List<String> statuses) {
        return this.execute(
            this.walletApiClient.getIdentificationRequests(
                userId,
                statuses
            )
        );
    }

    public Task<Transaction> getTransaction(String transactionKey) {
        return this.execute(this.walletApiClient.getTransaction(transactionKey));
    }

    public Task<Transaction> getTransaction(String transactionKey, List<String> fields) {
        return this.execute(
            this.walletApiClient.getTransaction(
                transactionKey,
                StringHelper.listToString(fields, ",")
            )
        );
    }

    public Task<Void> cancelTransaction(String transactionKey) {
        return this.execute(this.walletApiClient.cancelTransaction(transactionKey));
    }

    public Task<Transaction> confirmTransaction(String transactionKey) {
        return this.execute(this.walletApiClient.confirmTransaction(transactionKey));
    }

    public Task<MetadataAwareResponse<Transaction>> getTransactions(
        TransactionFilter transactionFilter
    ) {
        return this.execute(
            this.walletApiClient.getTransactions(
                transactionFilter.getProjectId(),
                transactionFilter.getLocationId(),
                transactionFilter.getStatus(),
                transactionFilter.getLimit(),
                transactionFilter.getOffset(),
                transactionFilter.getFrom()
            )
        );
    }

    public Task<MetadataAwareResponse<Confirmation>> getConfirmations(ConfirmationFilter confirmationFilter) {
        String orderDirectionValue = null;
        if (confirmationFilter.getOrderDirection() != null) {
            orderDirectionValue = confirmationFilter.getOrderDirection().toString();
        }
        return this.execute(
            this.walletApiClient.getConfirmations(
                confirmationFilter.getLimit(),
                confirmationFilter.getOffset(),
                confirmationFilter.getOrderBy(),
                orderDirectionValue,
                confirmationFilter.getStatus()
            )
        );
    }

    public Task<Confirmation> getConfirmation(String identifier) {
        return this.execute(this.walletApiClient.getConfirmation(identifier));
    }

    public Task<Confirmation> acceptConfirmation(String identifier) {
        return this.execute(this.walletApiClient.acceptConfirmation(identifier));
    }

    public Task<Confirmation> rejectConfirmation(String identifier) {
        return this.execute(this.walletApiClient.rejectConfirmation(identifier));
    }

    public Task<Void> generateCode(GenerateCodeRequest generateCodeRequest) {
        return this.execute(this.walletApiClient.generateCode(generateCodeRequest));
    }

    public Task<Void> reserveTransaction(String transactionKey, ReserveTransactionRequest reserveTransactionRequest) {
        return this.execute(
            this.walletApiClient.reserveTransaction(
                transactionKey,
                reserveTransactionRequest
            )
        );
    }

    public Task<Spot> getSpotById(long spotId, String fields) {
        return this.execute(
            this.walletApiClient.getSpotById(
                spotId,
                fields
            )
        );
    }

    public Task<Spot> checkIntoSpot(Long spotId, String fields) {
        return this.execute(
            this.walletApiClient.checkIntoSpot(
                spotId,
                fields
            )
        );
    }

    public Task<IdentificationRequest> createIdentificationRequest() {
        return this.execute(this.walletApiClient.createIdentificationRequest());
    }

    public Task<CreateDocumentIdentificationResponse> createDocumentIdentificationRequest(
        Long requestId,
        CreateDocumentIdentificationRequest request
    ) {
        return this.execute(
            this.walletApiClient.createDocumentIdentificationRequest(
                requestId,
                request
            )
        );
    }

    public Task<Void> identificationRequestFileUpload(
        Long identificationRequestId,
        Integer order,
        RequestBody requestBody
    ) {
        return this.execute(
            this.walletApiClient.identificationRequestFileUpload(
                identificationRequestId,
                order,
                requestBody
            )
        );
    }

    public Task<CreateDocumentIdentificationResponse> createAdditionalDocumentRequest(
        Long identificationDocumentId,
        CreateDocumentIdentificationRequest request
    ) {
        return this.execute(
            this.walletApiClient.createAdditionalDocumentRequest(
                identificationDocumentId,
                request
            )
        );
    }

    public Task<Void> additionalDocumentUpload(
        Long additionalDocumentId,
        RequestBody requestBody
    ) {
        return this.execute(
            this.walletApiClient.additionalDocumentUpload(
                additionalDocumentId,
                requestBody
            )
        );
    }

    public Task<Void> identificationDocumentFileUpload(
        Long identificationDocumentId,
        Integer order,
        RequestBody requestBody
    ) {
        return this.execute(
            this.walletApiClient.identificationDocumentFileUpload(
                identificationDocumentId,
                order,
                requestBody
            )
        );
    }

    public Task<IdentificationRequest> submitIdentificationRequest(Long identificationRequestId) {
        return this.execute(this.walletApiClient.submitIdentificationRequest(identificationRequestId));
    }

    public Task<Generator> getGenerator(Integer generatorId) {
        return this.execute(this.walletApiClient.getGenerator(generatorId));
    }

    public Task<Generator> createGenerator(JsonObject jsonObject) {
        return this.execute(this.walletApiClient.createGenerator(jsonObject));
    }

    public Task<NotificationSubscriber> createNotificationsSubscriber(
        NotificationSubscriber notificationSubscriber
    ) {
        return this.execute(this.walletApiClient.createNotificationsSubscriber(notificationSubscriber));
    }

    public Task<NotificationSubscriber> editNotificationsSubscriber(
        Integer subscriberId,
        NotificationSubscriber notificationSubscriber
    ) {
        return this.execute(
            this.walletApiClient.editNotificationsSubscriber(
                subscriberId,
                notificationSubscriber
            )
        );
    }

    public Task<Void> unlockRecaptcha(String unlockUrl, String response) {
        return this.execute(
            this.walletApiClient.unlockRecaptcha(
                unlockUrl,
                response
            )
        );
    }

    public Task<Void> unlockRecaptcha(
        String unlockUrl,
        String response,
        String grantType,
        String username,
        String password,
        List<String> scopes
    ) {
        return this.execute(
            this.walletApiClient.unlockRecaptcha(
                unlockUrl,
                response,
                grantType,
                username,
                password,
                StringHelper.listToString(scopes, " ")
            )
        );
    }

    public Task<Void> collectContact(ContactCollectionRequest contactCollectionRequest) {
        return this.execute(this.walletApiClient.collectContact(contactCollectionRequest));
    }

    public Task<EasyPayFees> getEasyPayFees(Money transferAmount) {
        String amount = transferAmount.getAmount().toPlainString();
        String currency = transferAmount.getCurrencyUnit().getCode();
        return this.execute(this.walletApiClient.getEasyPayFees(amount, currency));
    }

    public Task<CommonMetadataAwareResponse<EasyPayTransfer>> getEasyPayTransfers(EasyPayTransferFilter filter) {
        String orderDirectionValue = null;
        if (filter.getOrderDirection() != null) {
            orderDirectionValue = filter.getOrderDirection().toString();
        }
        return this.execute(
            this.walletApiClient.getEasyPayTransfers(
                filter.getStatus(),
                filter.getBeneficiaryUserId(),
                filter.getPayerWalletId(),
                filter.getLimit(),
                filter.getOffset(),
                filter.getOrderBy(),
                orderDirectionValue
            )
        );
    }

    public Task<CommonMetadataAwareResponse<EasyPayTransfer>> getEasyPayUpdatedTransfers(Integer payerWalletId) {
        return this.execute(this.walletApiClient.getEasyPayUpdatedTransfers(payerWalletId));
    }

    public Task<Void> markEasyPayUpdatedTransfersAsSeen(EasyPayMarkUpdatedTransfersAsSeen easyPayMarkUpdatedTransfersAsSeen) {
        return this.execute(this.walletApiClient.markUpdatedTransfersAsSeen(easyPayMarkUpdatedTransfersAsSeen));
    }

    public Task<EasyPayTransfer> createEasyPayTransfer(EasyPayCreateTransfer createEasyPayTransfer) {
        return this.execute(this.walletApiClient.createEasyPayTransfer(createEasyPayTransfer));
    }

    public Task<EasyPayTransfer> cancelEasyPayTransfer(Long easyPayTransferId) {
        return this.execute(this.walletApiClient.cancelEasyPayTransfer(easyPayTransferId));
    }

    public Task<IbanToEvp> getAccountNumberByIban(String iban) {
        return this.execute(this.walletApiClient.getAccountNumberByIban(iban));
    }
}