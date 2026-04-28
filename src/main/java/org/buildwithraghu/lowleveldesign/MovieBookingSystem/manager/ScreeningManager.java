package org.buildwithraghu.lowleveldesign.MovieBookingSystem.manager;

import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.Movie;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.Screening;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.Seat;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.Ticket;

import java.util.*;
import java.util.stream.Collectors;

public class ScreeningManager {

    private final Map<Movie, List<Screening>> screeningsByMovie;
    private final Map<Screening, List<Ticket>> ticketsByScreening;

    public ScreeningManager() {
        this.screeningsByMovie = new HashMap<>();
        this.ticketsByScreening = new HashMap<>();
    }

    public void addScreening(Movie movie, Screening screening) {
        screeningsByMovie.computeIfAbsent(movie, m -> new ArrayList<>()).add(screening);
    }

    public void addTicket(Screening screening, Ticket ticket) {
        ticketsByScreening.computeIfAbsent(screening, s -> new ArrayList<>()).add(ticket);
    }

    public List<Screening> getScreeningsForMovie(Movie movie) {
        return screeningsByMovie.getOrDefault(movie, new ArrayList<>());
    }

    public List<Ticket> getTicketsForScreening(Screening screening) {
        return ticketsByScreening.getOrDefault(screening, new ArrayList<>());
    }

    public List<Seat> getAvailableSeats(Screening screening) {
        List<Seat> allSeats = screening.getRoom().getLayout().getAllSeats();
        Set<Seat> bookedSeats = getTicketsForScreening(screening).stream()
                .map(Ticket::getSeat)
                .collect(Collectors.toSet());

        return allSeats.stream().filter(seat -> !bookedSeats.contains(seat)).toList();
    }
}
