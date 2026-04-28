package org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity;

import lombok.Data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Layout {
    private final int rows;
    private final int columns;
    private final Map<String, Seat> seatsByNumber;
    private final Map<Integer, Map<Integer, Seat>> seatsByPosition;

    public Layout(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seatsByNumber = new HashMap<>();
        this.seatsByPosition = new HashMap<>();
        initLayout();
    }

    private void initLayout() {
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                String seatNumber = i + "-" + j;
                addSeat(seatNumber, i, j, new Seat(seatNumber, null));
            }
        }
    }

    public void addSeat(String seatNumber, int row, int column, Seat seat) {
        seatsByNumber.put(seatNumber, seat);
        seatsByPosition.computeIfAbsent(row, k -> new HashMap<>()).put(column, seat);
    }

    public Seat getSeatByNumber(String seatNumber) {
        return seatsByNumber.get(seatNumber);
    }

    public Seat getSeatsByPosition(int row, int column) {
        return seatsByPosition.getOrDefault(row, Collections.emptyMap()).get(column);
    }

    public List<Seat> getAllSeats() {
        return List.copyOf(seatsByNumber.values());
    }
}
