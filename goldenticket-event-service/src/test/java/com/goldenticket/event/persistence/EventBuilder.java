package com.goldenticket.event.persistence;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class EventBuilder {

    private UUID id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private boolean obsolete;
    private String title;
    private LocalDate date;
    private String venue;
    private EventType eventType;
    private String organizer;

    public EventBuilder() {
    }

    public static EventBuilder anEvent() {
        return new EventBuilder();
    }

    public EventBuilder withId(UUID id) {
        this.id = id;
        return this;
    }

    public EventBuilder withCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public EventBuilder withUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public EventBuilder withObsolete(boolean obsolete) {
        this.obsolete = obsolete;
        return this;
    }

    public EventBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public EventBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public EventBuilder withVenue(String venue) {
        this.venue = venue;
        return this;
    }

    public EventBuilder withEventType(EventType eventType) {
        this.eventType = eventType;
        return this;
    }

    public EventBuilder setOrganiser(String organizer) {
        this.organizer = organizer;
        return this;
    }

    public Event build() {
        Event event = new Event(title, date, venue, eventType, organizer);

        event.setId(id);
        event.setCreateTime(createTime);
        event.setUpdateTime(updateTime);
        event.setObsolete(obsolete);
        return event;
    }
}
