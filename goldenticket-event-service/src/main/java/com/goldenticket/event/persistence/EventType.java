package com.goldenticket.event.persistence;

public enum EventType {
    CONCERT("Concert"),
    SPORTS("Sports"),
    THEATER("Theater"),
    COMEDY("Comedy"),
    CONFERENCE("Conference"),
    FESTIVAL("Festival"),
    OTHER("Other");

    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
