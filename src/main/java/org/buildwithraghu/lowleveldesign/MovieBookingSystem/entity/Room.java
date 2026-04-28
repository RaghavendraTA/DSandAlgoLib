package org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity;

import lombok.Data;

@Data
public class Room {

    String roomNumber;
    Layout layout;

    public Room(String roomNumber, Layout layout) {
        this.roomNumber = roomNumber;
        this.layout = layout;
    }
}
