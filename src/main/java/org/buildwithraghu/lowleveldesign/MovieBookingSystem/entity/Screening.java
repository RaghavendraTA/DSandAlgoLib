package org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Screening {

    Integer id;
    Movie movie;
    Room room;
    LocalDateTime startTime;
    LocalDateTime endTime;

    public Duration getDuration() {
        return Duration.between(startTime, endTime);
    }
}
