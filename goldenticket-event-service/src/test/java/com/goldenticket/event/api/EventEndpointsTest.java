package com.goldenticket.event.api;

import org.junit.jupiter.api.Test;

import static com.goldenticket.event.api.EventEndpoints.EVENT;
import static com.goldenticket.event.api.EventEndpoints.EVENTS;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventEndpointsTest {

    @Test
    public void testEndpoints() {
        assertEquals("/events",EVENTS);
        assertEquals("/events/{uuid}", EVENT);
    }
}