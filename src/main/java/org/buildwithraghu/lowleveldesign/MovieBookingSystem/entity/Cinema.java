package org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity;

import java.util.ArrayList;
import java.util.List;

public class Cinema {
    public String name;
    public String location;
    public List<Room> rooms;

    public Cinema() {
        this.rooms = new ArrayList<>();
    }

    public Cinema(String name, String location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
    }

   public Cinema(String name, Room room) {
        this.name = name;
        this.location = "N/A";
        this.rooms = new ArrayList<>();
        this.rooms.add(room);
    }

    public Cinema(String name, String location, Room room) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
        this.rooms.add(room);
    }

    public void addRoom(Room room) {
        if (this.rooms == null) {
            this.rooms = new ArrayList<>();
        }
        this.rooms.add(room);
    }
}
