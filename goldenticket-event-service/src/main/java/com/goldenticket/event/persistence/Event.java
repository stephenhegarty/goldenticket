package com.goldenticket.event.persistence;

import jakarta.persistence.Entity;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;

@Entity
public class Event extends GoldenTicketEntity {

    private String title;
    private LocalDate eventDate;
    private String venue;
    private EventType eventType;
    private String organizer;

    public Event() {
    }

    public Event(String title, LocalDate eventDate, String venue, EventType eventType, String organizer) {
        this.title = title;
        this.eventDate = eventDate;
        this.venue = venue;
        this.eventType = eventType;
        this.organizer = organizer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public String getOrganiser() {
        return organizer;
    }

    public void setOrganiser(String organizer) {
        this.organizer = organizer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return new EqualsBuilder().append(title, event.title)
                                  .append(eventDate, event.eventDate)
                                  .append(venue, event.venue)
                                  .append(eventType, event.eventType)
                                  .append(organizer, event.organizer)
                                  .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                                          .append(title)
                                          .append(eventDate)
                                          .append(venue)
                                          .append(eventType)
                                          .append(organizer)
                                          .toHashCode();
    }
}
