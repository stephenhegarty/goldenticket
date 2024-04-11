package com.goldenticket.event.controller;

import com.goldenticket.event.api.v1.EventResource;
import com.goldenticket.event.persistence.Event;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    public EventResource entityToResource(Event event) {
        return EventResource.builder()
                            .id(event.getId())
                            .title(event.getTitle())
                            .eventDate(event.getEventDate())
                            .eventType(event.getEventType())
                            .venue(event.getVenue())
                            .build();
    }

    public Event resourceToEntity(EventResource eventResource) {
        if (eventResource == null) {
            return null;
        }

        Event event = new Event(eventResource.getTitle(),
                eventResource.getEventDate(),
                eventResource.getVenue(),
                eventResource.getEventType(),
                eventResource.getOrganizer()
        );

        event.setId(eventResource.getId());
        return event;
    }
}