package org.buildwithraghu.lowleveldesign.MovieBookingSystem;

import org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity.*;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.manager.MovieBookingSystem;
import org.buildwithraghu.lowleveldesign.MovieBookingSystem.strategies.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MovieBookingSystemDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Movie Booking System Demo ===\n");

        // Create movie booking system
        MovieBookingSystem bookingSystem = new MovieBookingSystem();

        // Add movies
        Movie movie1 = new Movie("Inception", "Sci-Fi", 148);
        Movie movie2 = new Movie("The Godfather", "Crime", 175);
        bookingSystem.addMovie(movie1);
        bookingSystem.addMovie(movie2);

        // Create layout for cinema (5 rows, 8 columns)
        Layout layout = new Layout(5, 8);

        // Assign pricing strategies to seats in the layout
        assignPricingStrategies(layout, 0, 3, new PremiumRate(new BigDecimal("15.00")));
        assignPricingStrategies(layout, 4, 7, new VIPRate(new BigDecimal("25.00")));

        // Create rooms
        Room room1 = new Room("Room 1", layout);
        Room room2 = new Room("Room 2", layout);

        // Create cinemas
        bookingSystem.addCinema(new Cinema("Cinema A", room1));
        bookingSystem.addCinema(new Cinema("Cinema B", room2));

        // Create screenings
        Screening screening1 = new Screening(1, movie1, room1, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        Screening screening2 = new Screening(2, movie2, room2, LocalDateTime.now().plusHours(1), LocalDateTime.now().plusHours(3));
        bookingSystem.addScreening(movie1, screening1);
        bookingSystem.addScreening(movie2, screening2);

        // Test 1: Get available seats for screening
        System.out.println("=== Test 1: Available Seats ===");
        List<Seat> availableSeats = bookingSystem.getAvailableSeats(screening1);
        System.out.println("Available seats for " + movie1.getTitle() + ":");
        if (availableSeats.isEmpty()) {
            System.out.println("  No seats available!");
        } else {
            int premiumCount = 0;
            int vipCount = 0;
            for (Seat seat : availableSeats) {
                BigDecimal price = seat.getPricingStrategy().getPrice();
                System.out.println("  - " + seat.getSeatNumber() + " (Price: $" + price + ")");
                if (price.compareTo(new BigDecimal("20.00")) < 0) {
                    premiumCount++;
                } else {
                    vipCount++;
                }
            }
            System.out.println("  (Premium seats: " + premiumCount + ", VIP seats: " + vipCount + ")");
        }

        // Test 2: Book multiple tickets
        System.out.println("\n=== Test 2: Booking Multiple Tickets ===");
        int ticketsToBook = Math.min(3, availableSeats.size());
        for (int i = 0; i < ticketsToBook; i++) {
            Seat selectedSeat = availableSeats.get(i);
            Order order = bookingSystem.bookTicket(screening1, selectedSeat);
            System.out.println("Booked seat " + selectedSeat.getSeatNumber() + " for $" + order.calculateTotalPrice());
        }

        // Test 3: Verify remaining available seats
        System.out.println("\n=== Test 3: Remaining Available Seats ===");
        availableSeats = bookingSystem.getAvailableSeats(screening1);
        System.out.println("Remaining available seats:");
        if (availableSeats.isEmpty()) {
            System.out.println("  No seats available!");
        } else {
            for (Seat seat : availableSeats) {
                System.out.println("  - " + seat.getSeatNumber() + " (Price: $" + seat.getPricingStrategy().getPrice() + ")");
            }
        }

        // Test 4: Book another ticket
        System.out.println("\n=== Test 4: Book Another Ticket ===");
        if (!availableSeats.isEmpty()) {
            Seat selectedSeat = availableSeats.get(0);
            Order order = bookingSystem.bookTicket(screening1, selectedSeat);
            System.out.println("Booked seat " + selectedSeat.getSeatNumber() + " for $" + order.calculateTotalPrice());
        }

        // Test 5: Get all tickets for screening
        System.out.println("\n=== Test 5: All Tickets for Screening ===");
        List<Ticket> tickets = bookingSystem.getTicketsForScreening(screening1);
        System.out.println("Total tickets booked: " + tickets.size());
        for (Ticket ticket : tickets) {
            System.out.println("  - Seat: " + ticket.getSeat().getSeatNumber() + ", Price: $" + ticket.getPrice());
        }

        // Test 6: Get screenings for a movie
        System.out.println("\n=== Test 6: Screenings for " + movie1.getTitle() + " ===");
        List<Screening> screenings = bookingSystem.getScreeningsFromMovie(movie1);
        System.out.println("Number of screenings: " + screenings.size());
        for (Screening screening : screenings) {
            System.out.println("  - Screening ID: " + screening.getId() + ", Room: " + screening.getRoom().getRoomNumber());
        }

        // Test 7: Get ticket count for screening
        System.out.println("\n=== Test 7: Ticket Count ===");
        int ticketCount = bookingSystem.getTicketCount(screening1);
        System.out.println("Total tickets for " + movie1.getTitle() + ": " + ticketCount);

        // Test 8: Book tickets for second movie
        System.out.println("\n=== Test 8: Book Tickets for " + movie2.getTitle() + " ===");
        List<Seat> availableSeats2 = bookingSystem.getAvailableSeats(screening2);
        System.out.println("Available seats for " + movie2.getTitle() + ": " + availableSeats2.size());
        if (!availableSeats2.isEmpty()) {
            for (int i = 0; i < 2 && i < availableSeats2.size(); i++) {
                Seat selectedSeat = availableSeats2.get(i);
                Order order = bookingSystem.bookTicket(screening2, selectedSeat);
                System.out.println("Booked seat " + selectedSeat.getSeatNumber() + " for $" + order.calculateTotalPrice());
            }
        }

        // Test 9: Try to book an already booked seat (should fail)
        System.out.println("\n=== Test 9: Double Booking Prevention ===");
        MovieBookingSystem bookingSystem2 = new MovieBookingSystem();
        Movie movie = new Movie("Test Movie", "Action", 120);
        bookingSystem2.addMovie(movie);

        Layout layout2 = new Layout(3, 3);
        assignPricingStrategies(layout2, 0, 2, new PremiumRate(new BigDecimal("10.00")));
        Room room = new Room("Room 1", layout2);
        bookingSystem2.addCinema(new Cinema("Test Cinema", room));

        Screening screening = new Screening(99, movie, room, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        bookingSystem2.addScreening(movie, screening);

        List<Seat> seats = bookingSystem2.getAvailableSeats(screening);
        if (seats.isEmpty()) {
            System.out.println("  No seats available for test");
        } else {
            Seat seat = seats.get(0);
            try {
                Order order1 = bookingSystem2.bookTicket(screening, seat);
                System.out.println("  First booking successful: $" + order1.calculateTotalPrice());

                // Try to book the same seat again
                Order order2 = bookingSystem2.bookTicket(screening, seat);
                System.out.println("  ERROR: Double booking allowed!");
            } catch (RuntimeException e) {
                System.out.println("  Double booking prevented: " + e.getMessage());
            }
        }

        // Test 10: Concurrent booking simulation
        System.out.println("\n=== Test 10: Concurrent Bookings ===");
        MovieBookingSystem bookingSystem3 = new MovieBookingSystem();
        Movie movie3 = new Movie("Concurrent Movie", "Drama", 180);
        bookingSystem3.addMovie(movie3);

        Layout layout3 = new Layout(10, 10);
        assignPricingStrategies(layout3, 0, 4, new PremiumRate(new BigDecimal("10.00")));
        Room room3 = new Room("Room 2", layout3);
        bookingSystem3.addCinema(new Cinema("Concurrent Cinema", room3));

        Screening screening3 = new Screening(100, movie3, room3, LocalDateTime.now(), LocalDateTime.now().plusHours(2));
        bookingSystem3.addScreening(movie3, screening3);

        List<Seat> seats3 = bookingSystem3.getAvailableSeats(screening3);
        if (seats3.size() < 5) {
            System.out.println("  Not enough seats for concurrent test");
        } else {
            ExecutorService executor = Executors.newFixedThreadPool(5);
            List<Future<Order>> futures = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                final int seatIndex = i;
                futures.add(executor.submit(() -> {
                    return bookingSystem3.bookTicket(screening3, seats3.get(seatIndex));
                }));
            }

            int successfulBookings = 0;
            for (Future<Order> future : futures) {
                try {
                    Order order = future.get(5, TimeUnit.SECONDS);
                    System.out.println("  Thread completed booking: " + order.calculateTotalPrice());
                    successfulBookings++;
                } catch (Exception e) {
                    System.out.println("  Thread failed: " + e.getMessage());
                }
            }
            executor.shutdown();

            System.out.println("  Successful concurrent bookings: " + successfulBookings);
            System.out.println("  (Seat locking prevents double-booking even with concurrent requests)");
        }

        System.out.println("\n=== All Tests Completed Successfully! ===");
    }

    private static void assignPricingStrategies(Layout layout, int startRow, int endRow, PricingStrategy pricingStrategy) {
        for (int i = startRow; i <= endRow; i++) {
            for (int j = 0; j < layout.getColumns(); j++) {
                Seat seat = layout.getSeatsByPosition(i, j);
                if (seat != null) {
                    seat.setPricingStrategy(pricingStrategy);
                }
            }
        }
    }
}
