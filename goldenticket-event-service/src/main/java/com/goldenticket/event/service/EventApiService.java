package com.goldenticket.event.service;

import com.goldenticket.event.persistence.Event;
import com.goldenticket.event.persistence.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EventApiService {

    private final EventService eventService;

    @Autowired
    public EventApiService(EventService eventService) {
        this.eventService = eventService;
    }

    public Event createEvent(Event event) {
        return eventService.save(event);
    }

    public List<Event> createEvents(List<Event> events) {
        return eventService.saveAll(events);
    }

    public Event findEvent(UUID eventId) {
        return eventService.findOneById(eventId);
    }

    public List<Event> findAllEvents() {
        return eventService.findAll();
    }

    public List<Event> findEventsByIds(List<UUID> eventIds) {
        return eventService.findAllByIds(eventIds);
    }

    public Event updateEvent(UUID eventId, Event updatedEvent) {
        return eventService.updateEvent(eventId, updatedEvent);
    }

    public boolean eventExists(UUID eventId) {
        return eventService.exists(eventId);
    }

    public void obsoleteEvent(UUID eventId) {
        eventService.obsoleteEvent(eventId);
    }
}
