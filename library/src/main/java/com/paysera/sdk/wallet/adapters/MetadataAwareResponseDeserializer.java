package com.paysera.sdk.wallet.adapters;

import com.google.gson.*;
import com.paysera.sdk.wallet.entities.*;
import com.paysera.sdk.wallet.entities.card.Card;
import com.paysera.sdk.wallet.entities.confirmations.Confirmation;
import com.paysera.sdk.wallet.entities.locations.Location;
import com.paysera.sdk.wallet.entities.transfer.Transfer;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MetadataAwareResponseDeserializer implements JsonDeserializer<MetadataAwareResponse> {

    @Override
    public MetadataAwareResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        MetadataAwareResponse metadataAwareResponse = new MetadataAwareResponse<>();

        for (Map.Entry<String, JsonElement> entry : json.getAsJsonObject().entrySet()) {
            switch (entry.getKey()) {
                case "statements":
                    List<Statement> statements = createStatements(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(statements);
                    break;
                case "pending_payments":
                    List<PendingPayment> pendingPayments = createPendingPayments(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(pendingPayments);
                    break;
                case "reservation_statements":
                    List<ReservationStatement> reservationStatements = createReservationStatements(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(reservationStatements);
                    break;
                case "transfers":
                    List<Transfer> transfers = createTransfers(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(transfers);
                    break;
                case "locations":
                    List<Location> locations = createLocations(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(locations);
                case "_metadata":
                    metadataAwareResponse.setMetadata((Metadata) context.deserialize(json.getAsJsonObject().get("_metadata"), Metadata.class));
                    break;
                case "cards":
                    List<Card> cards = createCards(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(cards);
                    break;
                case "transactions":
                    List<Transaction> transactions = createTransactions(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(transactions);
                    break;
                case "identification_requests":
                    List<IdentificationRequest> identificationRequests = createIdentificationRequests(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(identificationRequests);
                    break;
                case "items":
                    List<Confirmation> confirmations = createConfirmations(context, entry.getValue().getAsJsonArray());
                    metadataAwareResponse.setItems(confirmations);
                    break;

    private List<Transaction> createTransactions(JsonDeserializationContext context, JsonArray entries) {
        List<Transaction> transactions = new ArrayList<>();
        for (JsonElement transaction : entries) {
            transactions.add((Transaction) context.deserialize(transaction, Transaction.class));
        }
        return transactions;

    }

    private List<IdentificationRequest> createIdentificationRequests(JsonDeserializationContext context, JsonArray entries) {
        List<IdentificationRequest> identificationRequests = new ArrayList<>();
        for (JsonElement identificationRequest : entries) {
            identificationRequests.add((IdentificationRequest) context.deserialize(identificationRequest, IdentificationRequest.class));
        }
        return identificationRequests;
    }

    private List<Card> createCards(JsonDeserializationContext context, JsonArray entries) {
        List<Card> cards = new ArrayList<>();
        for (JsonElement card : entries) {
            cards.add((Card) context.deserialize(card, Card.class));
        }
        return cards;
    }

    private List<Location> createLocations(JsonDeserializationContext context, JsonArray entries) {
        List<Location> locations = new ArrayList<>();
        for (JsonElement location : entries) {
            locations.add((Location) context.deserialize(location, Location.class));
        }
        return locations;
    }


    private List<Transfer> createTransfers(JsonDeserializationContext context, JsonArray entries) {
        List<Transfer> transfers = new ArrayList<>();
        for (JsonElement transfer : entries) {
            transfers.add((Transfer) context.deserialize(transfer, Transfer.class));
        }
        return transfers;
    }

    private List<PendingPayment> createPendingPayments(JsonDeserializationContext context, JsonArray entries) {
        List<PendingPayment> pendingPayments = new ArrayList<>();
        for (JsonElement pendingPayment : entries) {
            pendingPayments.add((PendingPayment) context.deserialize(pendingPayment, PendingPayment.class));
        }
        return pendingPayments;
    }

    private List<Statement> createStatements(JsonDeserializationContext context, JsonArray entries) {
        List<Statement> statements = new ArrayList<>();
        for (JsonElement statement : entries) {
            statements.add((Statement) context.deserialize(statement, Statement.class));
        }
        return statements;
    }

    private List<ReservationStatement> createReservationStatements(JsonDeserializationContext context, JsonArray entries) {
        List<ReservationStatement> reservationStatements = new ArrayList<>();
        for (JsonElement reservationStatement : entries) {
            reservationStatements.add((ReservationStatement) context.deserialize(reservationStatement, ReservationStatement.class));
        }
        return reservationStatements;
    }

    private List<Confirmation> createConfirmations(JsonDeserializationContext context, JsonArray entries) {
        List<Confirmation> confirmations = new ArrayList<>();
        for (JsonElement confirmation : entries) {
            confirmations.add((Confirmation) context.deserialize(confirmation, Confirmation.class));
        }
        return confirmations;
    }

}
