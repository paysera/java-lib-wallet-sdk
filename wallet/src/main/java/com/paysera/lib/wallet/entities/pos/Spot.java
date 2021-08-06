package com.paysera.lib.wallet.entities.pos;

import java.util.ArrayList;
import java.util.List;

public class Spot {

    public static final String STATUS_WAITING = "waiting";
    public static final String STATUS_OPEN = "open";
    public static final String STATUS_CLOSED = "closed";

    private Long id;
    private String status;
    private String identifier;
    private PlaceInfo placeInfo;
    private List<Order> orders;

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getIdentifier() {
        return identifier;
    }

    public PlaceInfo getPlaceInfo() {
        return placeInfo;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getPendingOrders() {
        List<Order> pendingOrders = new ArrayList<>();
        for (Order order : this.orders) {
            if (order.getStatus() == Order.STATUS_PENDING) {
                pendingOrders.add(order);
            }
        }

        return pendingOrders;
    }
}
