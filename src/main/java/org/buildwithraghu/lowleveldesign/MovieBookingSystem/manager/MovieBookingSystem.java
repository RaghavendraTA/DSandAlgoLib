package org.buildwithraghu.lowleveldesign.MovieBookingSystem.manager;

import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.*;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MovieBookingSystem {

    private final List<Movie> movies;
    private final List<Cinema> cinemas;
    private final ScreeningManager screeningManager;
    private final SeatLockManager seatLockManager;

    public MovieBookingSystem() {
        this.movies = new ArrayList<>();
        this.cinemas = new ArrayList<>();
        this.screeningManager = new ScreeningManager();
        this.seatLockManager = new SeatLockManager(Duration.ofSeconds(30));
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    public void addCinema(Cinema cinema) {
        this.cinemas.add(cinema);
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningManager.addScreening(movie, screening);
    }

    public Order bookTicket(Screening screening, Seat seat) {
        // Check if seat is already booked
        if (screeningManager.getTicketsForScreening(screening).stream()
                .anyMatch(t -> t.getSeat().equals(seat))) {
            throw new RuntimeException("Seat " + seat.getSeatNumber() + " is already booked!");
        }

        // Acquire lock on the seat
        if (!seatLockManager.lockSeat(screening, seat, "user-" + System.currentTimeMillis())) {
            throw new RuntimeException("Could not acquire lock for seat " + seat.getSeatNumber() + ". Try again later.");
        }

        try {
            // Check again after acquiring lock (double-check pattern)
            if (screeningManager.getTicketsForScreening(screening).stream()
                    .anyMatch(t -> t.getSeat().equals(seat))) {
                seatLockManager.unlockSeat(screening, seat);
                throw new RuntimeException("Seat " + seat.getSeatNumber() + " was booked by another user.");
            }

            // Book the ticket
            BigDecimal price = seat.getPricingStrategy().getPrice();
            Ticket ticket = new Ticket(screening, seat, price);
            screeningManager.addTicket(screening, ticket);
            Order order = new Order(java.time.LocalDateTime.now());
            order.addTicket(ticket);
            return order;
        } finally {
            // Always release the lock
            seatLockManager.unlockSeat(screening, seat);
        }
    }

    public List<Screening> getScreeningsFromMovie(Movie movie) {
        return screeningManager.getScreeningsForMovie(movie);
    }

    public List<Seat> getAvailableSeats(Screening screening) {
        return screeningManager.getAvailableSeats(screening);
    }

    public int getTicketCount(Screening screening) {
        return screeningManager.getTicketsForScreening(screening).size();
    }

    public List<Ticket> getTicketsForScreening(Screening screening) {
        return screeningManager.getTicketsForScreening(screening);
    }
}
