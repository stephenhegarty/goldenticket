package com.goldenticket.event.api;

import com.goldenticket.event.api.v1.EventResource;
import com.goldenticket.event.persistence.EventType;

import java.time.LocalDate;

import static com.goldenticket.event.utils.TestUtils.anyEnum;
import static com.goldenticket.event.utils.TestUtils.anyString;

public class EventResourceModels {

    public static EventResource.EventResourceBuilder aWriteEventResource() {
        return EventResource.builder()
                            .title(anyString())
                            .eventDate(LocalDate.now())
                            .venue(anyString())
                            .eventType(anyEnum(EventType.class))
                            .organizer(anyString());
    }
}
