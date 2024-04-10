package com.create.cabapplication.enums;

import lombok.Getter;


@Getter
public enum TripStatus {
    REQUESTED,
    ONGOING,
    COMPLETED,
    CANCELLED;

    public TripStatus tripStarted(){
        return ONGOING;
    }

    public TripStatus tripCompleted(){
        return COMPLETED;
    }

    public TripStatus tripCancelled(){
        return CANCELLED;
    }
}
