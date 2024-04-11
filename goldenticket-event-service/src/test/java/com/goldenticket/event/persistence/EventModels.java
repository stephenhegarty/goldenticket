package com.goldenticket.event.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.goldenticket.event.utils.TestUtils.anyEnum;
import static com.goldenticket.event.utils.TestUtils.anyString;
import static com.goldenticket.event.utils.TestUtils.anyUuid;

public class EventModels {

    public static EventBuilder anEvent() {
        return EventBuilder.anEvent()
                           .withId(anyUuid())
                           .withTitle(anyString())
                           .withDate(LocalDate.now())
                           .withVenue(anyString())
                           .withEventType(anyEnum(EventType.class))
                           .setOrganiser(anyString())
                           .withObsolete(false)
                           .withCreateTime(LocalDateTime.now())
                           .withUpdateTime(LocalDateTime.now());
    }
}
