package com.jpmc.theater;

import java.time.LocalDateTime;

public class Showing {
    private final Movie movie;
    private final int sequenceOfTheDay;
    private final LocalDateTime showStartTime;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

    public double getPrice() {
        double origPrice = movie.getTicketPrice();
        double minusPrice = origPrice;
        if (sequenceOfTheDay == 1) {
            minusPrice = minusPrice - 3;
        } else if (sequenceOfTheDay == 2) {
            minusPrice = minusPrice - 2;
        } else if (showStartTime.getDayOfMonth() == 7) {
            minusPrice = minusPrice - 1;
        }

        double percentPrice = origPrice;
        int movieHour = showStartTime.getHour();
        if (movieHour >= 11 && movieHour < 16) {
            percentPrice = percentPrice * 0.75;
        } else if (movie.getIsSpecial()) {
            percentPrice = percentPrice * 0.8;
        }

        return Math.min(minusPrice, percentPrice);
    }
}
