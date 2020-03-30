package com.paysera.sdk.wallet.clients;

import bolts.Continuation;
import bolts.Task;
import com.paysera.sdk.wallet.ClientServerTimeSynchronizationConfiguration;
import com.paysera.sdk.wallet.entities.*;
import com.paysera.sdk.wallet.entities.card.Card;
import com.paysera.sdk.wallet.entities.client.Client;
import com.paysera.sdk.wallet.entities.confirmations.Confirmation;
import com.paysera.sdk.wallet.entities.locations.Location;
import com.paysera.sdk.wallet.entities.locations.LocationCategory;
import com.paysera.sdk.wallet.entities.pos.Spot;
import com.paysera.sdk.wallet.entities.requests.*;
import com.paysera.sdk.wallet.entities.transfer.Transfer;
import com.paysera.sdk.wallet.entities.transfer.TransferPassword;
import com.paysera.sdk.wallet.filters.*;
import com.paysera.sdk.wallet.filters.ConfirmationFilter;
import com.paysera.sdk.wallet.helpers.DateHelper;
import com.paysera.sdk.wallet.helpers.EnumHelper;
import com.paysera.sdk.wallet.helpers.OkHTTPQueryStringConverter;
import com.paysera.sdk.wallet.helpers.StringHelper;
import com.paysera.sdk.wallet.providers.TimestampProvider;
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
        return this.execute(this.walletApiClient.calculateCurrencyConversion(
            request.getFromAmount(),
            request.getFromCurrency(),
            request.getToAmount(),
            request.getToCurrency(),
            request.getAccountNumber()
        ));
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

        return this.execute(this.walletApiClient.getWalletBalance(walletId, convertToCurrency, includeConvertToCurrency, showHistoricalCurrency))
            .continueWithTask(new Continuation<WalletBalance, Task<WalletBalance>>() {
                @Override
                public Task<WalletBalance> then(Task<WalletBalance> task) throws Exception {
                    if (convertToCurrency == null) {
                        return task;
                    }

                    for (CurrencyBalance currencyBalance : task.getResult().getCurrencyBalances()) {
                        currencyBalance.setConvertedCurrency(convertToCurrency);
                    }

                    return task;
                }
            });
    }

    public Task<AuthTokenResponse> createAuthToken() {
        return execute(walletApiClient.createAuthToken());
    }

    public Task<Wallet> getWallet(WalletFilter walletFilter) {
        return execute(walletApiClient.getWallet(
            walletFilter.getAccountNumber(),
            walletFilter.getPhone(),
            walletFilter.getEmail(),
            walletFilter.getUserId()
        ));
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
        return this.execute(this.walletApiClient.getCards(
            cardFilter.getUserId(),
            cardFilter.getLimit(),
            cardFilter.getOffset()
        ));
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
        AssignPhoneNumberRequest
            assignPhoneNumberRequest = new AssignPhoneNumberRequest(phone, parameters);
        return this.execute(this.walletApiClient.assignPhoneNumber(
            userId, assignPhoneNumberRequest
        ));
    }

    public Task<User> assignPhoneNumber(
        Integer userId,
        String phone
    ) {
        AssignPhoneNumberRequest assignPhoneNumberRequest = new AssignPhoneNumberRequest(phone);
        return this.execute(this.walletApiClient.assignPhoneNumber(
            userId, assignPhoneNumberRequest
        ));
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
        return this.execute(this.walletApiClient.confirmEmail(
            confirmEmailRequest
        ));
    }

    public Task<List<String>> getCurrencies() {
        return this.execute(this.walletApiClient.getCurrencies());
    }

    public Task<Void> setUserAvatar(Integer userId, byte[] bytes) {
        return this.execute(this.walletApiClient.setUserAvatar(
            userId,
            bytes
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
        UserPosition userPosition = new UserPosition(
            lat,
            lng,
            type
        );
        return this.execute(this.walletApiClient.provideUserPosition(
            userPosition
        ));
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
        return this.execute(this.walletApiClient.cancelPendingPayment(
            walletId,
            pendingPaymentId
        ));
    }

    public Task<TransactionRequest> createTransactionRequest(
        TransactionRequest transactionRequest
    ) {
        return this.execute(this.walletApiClient.createTransactionRequest(
            transactionRequest.getTransactionKey(),
            transactionRequest
        ));
    }

    public Task<Transaction> createTransaction(Transaction transaction) {
        return this.execute(this.walletApiClient.createTransaction(transaction));
    }

    public Task<Client> createClient(Client client) {
        return this.execute(this.walletApiClient.createClient(client));
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
        return this.execute(this.walletApiClient.resetPassword(userId, resetPasswordConfirmRequest));
    }

    public Task<User> changePassword(
        Integer userId,
        String oldPassword,
        String newPassword
    ) {
        ChangePasswordRequest changePasswordRequest = new ChangePasswordRequest();
        changePasswordRequest.setOldPassword(oldPassword);
        changePasswordRequest.setPassword(newPassword);
        return this.execute(this.walletApiClient.changePassword(userId, changePasswordRequest));
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
        return this.execute(this.walletApiClient.getWallets(
            StringHelper.listToString(walletsFilter.getEmailList(), ","),
            StringHelper.listToString(walletsFilter.getPhoneList(), ","),
            StringHelper.listToString(walletsFilter.getEmailHashList(), ","),
            StringHelper.listToString(walletsFilter.getPhoneHashList(), ","),
            walletsFilter.getLimit()));
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
            ));
    }

    public Task<MetadataAwareResponse<Statement>> getStatements(
        StatementsFilter statementsFilter
    ) {
        return this.execute(this.walletApiClient.getStatements(
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
            EnumHelper.enumToString(statementsFilter.getOrderDirection())
        ));
    }

    public Task<MetadataAwareResponse<PendingPayment>> getPendingPayments(
        StatementsFilter statementsFilter
    ) {
        return this.execute(this.walletApiClient.getPendingPayments(
            statementsFilter.getWalletId(),
            statementsFilter.getLimit(), statementsFilter.getOffset()
        ));
    }

    public Task<MetadataAwareResponse<ReservationStatement>> getReservationStatements(
        StatementsFilter statementsFilter
    ) {
        return this.execute(this.walletApiClient.getReservationStatements(
            statementsFilter.getWalletId(),
            statementsFilter.getLimit(), statementsFilter.getOffset()
        ));
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

        return this.execute(this.walletApiClient.appendContactsToContactBook(contactBookId, request));
    }

    public Task<Void> removeFromContactBook(
        Integer contactBookId,
        List<String> emailList,
        List<String> phoneList,
        List<String> emailHashList,
        List<String> phoneHashList
    ) {
        return this.execute(this.walletApiClient.removeFromContactBook(
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
        return this.execute(this.walletApiClient.getTransfers(filter.getCreditAccountNumber(), filter.getStatuses(), filter.getOffset(), filter.getLimit()));
    }

    public Task<Transfer> provideTransferPassword(Long transferId, TransferPassword password) {
        return this.execute(this.walletApiClient.provideTransferPassword(transferId, password));
    }

    public Task<MetadataAwareResponse<IdentificationRequest>> getIdentificationRequests(List<String> statuses) {
        return this.execute(this.walletApiClient.getIdentificationRequests(statuses));
    }

    public Task<MetadataAwareResponse<IdentificationRequest>> getIdentificationRequests(Integer userId, List<String> statuses) {
        return this.execute(this.walletApiClient.getIdentificationRequests(userId, statuses));
    }

    public Task<Transaction> getTransaction(String transactionKey) {
        return this.execute(this.walletApiClient.getTransaction(transactionKey));
    }

    public Task<Transaction> getTransaction(String transactionKey, List<String> fields) {
        return this.execute(this.walletApiClient.getTransaction(transactionKey, StringHelper.listToString(fields, ",")));
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
        return this.execute(this.walletApiClient.getTransactions(
            transactionFilter.getStatus(),
            transactionFilter.getLimit(),
            transactionFilter.getOffset(),
            transactionFilter.getFrom()
        ));
    }

    public Task<MetadataAwareResponse<Confirmation>> getConfirmations(
        ConfirmationFilter confirmationFilter
    ) {
        return this.execute(this.walletApiClient.getConfirmations(
            confirmationFilter.getLimit(),
            confirmationFilter.getOffset(),
            confirmationFilter.getOrderBy(),
            confirmationFilter.getOrderDirection(),
            confirmationFilter.getStatus()
        ));
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
        return this.execute(this.walletApiClient.reserveTransaction(transactionKey, reserveTransactionRequest));
    }

    public Task<Spot> getSpotById(long spotId, String fields) {
        return this.execute(this.walletApiClient.getSpotById(spotId, fields));
    }

    public Task<Spot> checkIntoSpot(Long spotId, String fields) {
        return this.execute(this.walletApiClient.checkIntoSpot(spotId, fields));
    }
}
