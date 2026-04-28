package org.buildwithraghu.lowleveldesign.MovieBookingSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    String title;
    String genre;
    int durationMinutes;
}
