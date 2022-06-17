package com.jpmc.theater;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Theater {

    LocalDateProvider provider;
    private final List<Showing> schedule;

    public Theater(LocalDateProvider provider, List<Showing> schedule) {
        this.provider = provider;
        this.schedule = schedule;
    }

    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (RuntimeException ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public void printScheduleText() {
        System.out.println("As text for date: " + provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println(s.getSequenceOfTheDay() + ": " + s.getStartTime() + " " + s.getMovie().getTitle() + " " + humanReadableFormat(s.getMovie().getRunningTime()) + " $" + s.getPrice())
        );
        System.out.println("===================================================");
    }

    public void printScheduleJSON() {
        System.out.println("As JSON for date: " + provider.currentDate());
        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.println("{\n  SEQ: " + s.getSequenceOfTheDay() + ",\n  TITLE: " + s.getMovie().getTitle() + ",\n  TIME: " + s.getStartTime() + ",\n},")
        );
        System.out.println("===================================================");
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        }
        else {
            return "s";
        }
    }
}
