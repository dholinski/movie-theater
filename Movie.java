package com.jpmc.theater;

import java.time.Duration;
import java.util.Objects;

public class Movie {
    private final String title;
    private final String description;
    private final Duration runningTime;
    private final double ticketPrice;
    private final boolean isSpecial;

    public Movie(String title, String description, Duration runningTime, double ticketPrice, boolean isSpecial) {
        this.title = title;
        this.description = description;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.isSpecial = isSpecial;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public boolean getIsSpecial() { return isSpecial; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(description, movie.description)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(isSpecial, movie.isSpecial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, runningTime, ticketPrice, isSpecial);
    }
}