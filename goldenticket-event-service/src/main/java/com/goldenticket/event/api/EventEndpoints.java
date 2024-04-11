package com.goldenticket.event.api;

public final class EventEndpoints {

    public static final String UUID_TOKEN = "uuid";
    public static final String UUID_PLACEHOLDER = "/{" + UUID_TOKEN + "}";

    //Event
    public static final String EVENTS = "/events";
    public static final String EVENT = EVENTS + UUID_PLACEHOLDER;

    public EventEndpoints() {
    }
}