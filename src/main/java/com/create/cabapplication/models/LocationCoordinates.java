package com.create.cabapplication.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationCoordinates {

    private Double x;
    private Double y;

    @Override
    public String toString() {
        return "LocationCoordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

}
