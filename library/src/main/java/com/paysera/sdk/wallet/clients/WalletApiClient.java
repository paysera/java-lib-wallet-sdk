package com.paysera.sdk.wallet.clients;

import com.paysera.sdk.wallet.entities.*;
import com.paysera.sdk.wallet.entities.card.Card;
import com.paysera.sdk.wallet.entities.client.Client;
import com.paysera.sdk.wallet.entities.confirmations.Confirmation;
import com.paysera.sdk.wallet.entities.locations.Location;
import com.paysera.sdk.wallet.entities.locations.LocationCategory;
import com.paysera.sdk.wallet.entities.requests.*;
import com.paysera.sdk.wallet.entities.transfer.Transfer;

import java.util.List;
import java.util.Map;

import com.paysera.sdk.wallet.entities.transfer.TransferPassword;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WalletApiClient {
    // Misc endpoints
    @GET("server")
    Call<ServerInformation> getServerInformation();

    @GET("configuration")
    Call<ServerConfiguration> getServerConfiguration();

    @GET("currencies")
    Call<List<String>> getCurrencies();

    // Client endpoint
    @POST("client")
    Call<Client> createClient(
        @Body Client client
    );

    @GET("client/locations")
    Call<List<Location>> getClientLocations();

    // User endpoint
    @GET("user/me")
    Call<User> getUser();

    @GET("user/{id}")
    Call<User> getUser(
        @Path("id") Integer userId
    );

    @GET("user/me/projects")
    Call<List<Project>> getUserProjects();

    @GET("user/{id}/projects")
    Call<List<Project>> getUserProjects(
        @Path("id") Integer userId
    );

    @POST("user")
    Call<User> createUser(
        @Body UserRegistrationRequest userRegistrationRequestRegistration
    );

    @POST("user/password/reset")
    Call<User> requestResetPassword(
        @Body ResetPasswordRequest resetPasswordRequest
    );

    @PUT("user/{userId}/password")
    Call<User> resetPassword(
        @Path("userId") Integer id,
        @Body ResetPasswordConfirmRequest userPasswordResetConfirm
    );

    @PUT("user/{userId}/password")
    Call<User> changePassword(
        @Path("userId") Integer id,
        @Body ChangePasswordRequest changePasswordRequest
    );

    @GET("user")
    Call<User> getUser(
        @Query("email") String email,
        @Query("phone") String phone,
        @Query("person_code") String personCode,
        @Query("nationality") String nationality
    );

    @PUT("user/{userId}/phone/confirm")
    Call<User> confirmPhone(
        @Path("userId") Integer userId,
        @Body ConfirmPhoneRequest confirmPhoneRequest
    );

    @PUT("user/me/email/confirm")
    Call<User> confirmEmail(
        @Body ConfirmEmailRequest confirmEmailRequest
    );

    @GET("user/me/identification-requests")
    Call<MetadataAwareResponse<IdentificationRequest>> getIdentificationRequests(
        @Query("statuses[]") List<String> statuses
    );

    @GET("user/{userId}/identification-requests")
    Call<MetadataAwareResponse<IdentificationRequest>> getIdentificationRequests(
        @Path("userId") Integer userId,
        @Query("statuses[]") List<String> statuses
    );

    @POST("user/{userId}/phone")
    Call<User> assignPhoneNumber(
        @Path("userId") Integer userId,
        @Body AssignPhoneNumberRequest assignPhoneNumberRequest
    );

    @POST("user/me/email")
    Call<User> assignEmail(
        @Body AssignEmailRequest assignEmailRequest
    );

    @PUT("user/{userId}/avatar")
    Call<Void> setUserAvatar(@Path("userId") Integer userId, @Body byte[] bytes);


    @PUT("user/me/position")
    Call<UserPosition> provideUserPosition(
        @Body UserPosition provideUserPositionRequest
    );

    @GET("user/{userId}/services")
    Call<UserServiceResponse> getUserServices(
        @Path("userId") Integer userId
    );

    @PUT("user/{userId}/service/{service}")
    Call<Void> enableUserService(
        @Path("userId") Integer userId,
        @Path("service") String service
    );

    // Wallet endpoint
    @GET("wallet/{id}")
    Call<Wallet> getWallet(
        @Path("id") Integer walletId
    );

    @GET("wallet/{id}/balance")
    Call<WalletBalance> getWalletBalance(
        @Path("id") Integer walletId,
        @Query("convert_to") String convertToCurrency,
        @Query("include_convert_to_currency") Boolean includeConvertToCurrency,
        @Query("show_historical_currencies") Boolean showHistoricalCurrencies
    );

    @DELETE("wallet/{id}/description")
    Call<Void> deleteWalletDescription(
        @Path("id") Integer id
    );

    @PUT("wallet/{walletId}")
    Call<Wallet> changeWalletDescription(
        @Path("walletId") Integer id,
        @Body ChangeWalletDescriptionRequest changeWalletDescriptionRequest
    );

    @GET("wallet")
    Call<Wallet> getWallet(
        @Query("account_number") String accountNumber,
        @Query("phone") String phone,
        @Query("email") String email,
        @Query("user_id") Integer userId
    );

    @GET("wallets")
    Call<Map<String, Wallet>> getWallets(
        @Query("email") String emailList,
        @Query("phone") String phoneList,
        @Query("email_hash") String emailHashList,
        @Query("phone_hash") String phoneHashList
    );

    @GET("wallet/{id}/statements")
    Call<MetadataAwareResponse<Statement>> getStatements(
        @Path("id") Integer walletId,
        @Query("currency") String currencies,
        @Query("direction") String direction,
        @Query("text") String text,
        @Query("from") Long from,
        @Query("to") Long to,
        @Query("limit") Integer limit,
        @Query("offset") Integer offset,
        @Query("after") String after,
        @Query("before") String before,
        @Query("order_by") String orderBy,
        @Query("order_direction") String orderDirection
    );

    @GET("wallet/{id}/pending-payments")
    Call<MetadataAwareResponse<PendingPayment>> getPendingPayments(
        @Path("id") Integer walletId,
        @Query("limit") Integer limit,
        @Query("offset") Integer offset
    );

    @GET("wallet/{id}/reservation-statements")
    Call<MetadataAwareResponse<ReservationStatement>> getReservationStatements(
        @Path("id") Integer walletId,
        @Query("limit") Integer limit,
        @Query("offset") Integer offset
    );

    @DELETE("wallet/{walletId}/pending-payment/{pendingPaymentId}")
    Call<Void> cancelPendingPayment(
        @Path("walletId") Integer walletId,
        @Path("pendingPaymentId") long pendingPaymentId
    );

    // Card endpoint
    @POST("card")
    Call<Card> createCard(@Body Card card);

    @GET("cards")
    Call<MetadataAwareResponse<Card>> getCards(
        @Query("user_id") Integer userId,
        @Query("limit") Integer limit,
        @Query("offset") Integer offset
    );

    @GET("card/{id}")
    Call<Card> getCard(
        @Path("id") Integer id
    );

    @DELETE("card/{id}")
    Call<Void> deleteCard(
        @Path("id") Integer id
    );

    // Locations endpoint
    @GET("locations/pay-categories")
    Call<List<LocationCategory>> getLocationCategories(
        @Query("locale") String locale
    );

    @GET("locations")
    Call<MetadataAwareResponse<Location>> getLocations(
        @Query("locale") String locale,
        @Query("lat") Float lat,
        @Query("lng") Float lng,
        @Query("distance") Float distance,
        @Query("updated_after") Long updatedAfter,
        @Query("status") String status,
        @Query("limit") Integer limit,
        @Query("offset") Integer offset
    );

    // Transaction endpoint
    @POST("transaction/{transactionKey}/request")
    Call<TransactionRequest> createTransactionRequest(
        @Path("transactionKey") String transactionKey,
        @Body TransactionRequest transactionRequest
    );

    @POST("transaction")
    Call<Transaction> createTransaction(
        @Body Transaction transaction
    );

    @GET("transaction/{transactionKey}")
    Call<Transaction> getTransaction(
        @Path("transactionKey") String transactionKey
    );

    @GET("transactions")
    Call<MetadataAwareResponse<Transaction>> getTransactions(
        @Query("status") String status,
        @Query("limit") Integer limit,
        @Query("offset") Integer offset,
        @Query("from") Integer from
    );

    @PUT("transaction/{transactionKey}/confirm")
    Call<Transaction> confirmTransaction(
        @Path("transactionKey") String transactionKey
    );

    @DELETE("transaction/{transactionKey}")
    Call<Void> cancelTransaction(
        @Path("transactionKey") String transactionKey
    );

    // Contact book endpoint
    @DELETE("contact-book/{contactBookId}/contacts")
    Call<Void> removeFromContactBook(
        @Path("contactBookId") Integer contactBookId,
        @Query("email") String emailList,
        @Query("phone") String phoneList,
        @Query("email_hash") String emailHashList,
        @Query("phone_hash") String phoneHashList
    );

    // Subscriber endpoint
    @DELETE("subscriber/{subscriberId}")
    Call<Void> unregisterSubscriber(
        @Path("subscriberId") Integer subscriberId
    );

    @DELETE("subscribers")
    Call<Void> unregisterSubscribers();

    // Transfers endpoint
    @POST("transfers")
    Call<Transfer> createTransfer(
        @Body Transfer transfer
    );

    @PUT("transfers/{transferId}/reserve")
    Call<Transfer> reserveTransferById(
        @Path("transferId") Integer transferId
    );

    @PUT("transfers/{transferId}/sign")
    Call<Transfer> signTransferById(
        @Path("transferId") Integer transferId
    );

    @DELETE("transfers/{transferId}")
    Call<Transfer> deleteTransferById(
        @Path("transferId") Integer transferId
    );

    @GET("transfers/{transferId}")
    Call<Transfer> getTransferById(
        @Path("transferId") Integer transferId
    );

    @GET("transfers")
    Call<MetadataAwareResponse<Transfer>> getTransfers(
        @Query("credit_account_number") String creditAccountNumber,
        @Query("statuses[]") List<String> statuses
    );

    @PUT("transfers/{transferId}/provide-password")
    Call<Transfer> provideTransferPassword(
        @Path("transferId") Long transferId,
        @Body TransferPassword password
    );

    @POST("jwt/tokens")
    Call<JWTTokenResponse> getJWTToken(@Body JWTScope scope);

    @GET("confirmations/me")
    Call<MetadataAwareResponse<Confirmation>> getConfirmations(
        @Query("limit") Integer limit,
        @Query("offset") Integer offset,
        @Query("order_by") String orderBy,
        @Query("order_direction") String orderDirection,
        @Query("status") String status
    );

    @GET("confirmations/{identifier}")
    Call<Confirmation> getConfirmation(
        @Path("identifier") String identifier
    );

    @PUT("confirmations/{identifier}/confirm")
    Call<Confirmation> acceptConfirmation(
        @Path("identifier") String identifier
    );

    @PUT("confirmations/{identifier}/reject")
    Call<Confirmation> rejectConfirmation(
        @Path("identifier") String identifier
    );

    @POST("generator/code")
    Call<Void> generateCode(@Body GenerateCodeRequest generateCodeRequest);

    @PUT("transaction/{transactionKey}/reserve")
    Call<Void> reserveTransaction(
        @Path("transactionKey") String transactionKey,
        @Body ReserveTransactionRequest reserveTransactionRequest
    );
}
