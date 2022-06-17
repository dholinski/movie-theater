package com.jpmc.theater;

public class Reservation {
    private final Showing showing;
    private final Customer customer;
    private final int audienceCount;

    public Reservation(Customer customer, Showing showing, int audienceCount) {
        this.showing = showing;
        this.audienceCount = audienceCount;
        this.customer = customer;
    }

    public double totalFee() {
        return showing.getPrice() * audienceCount;
    }
}