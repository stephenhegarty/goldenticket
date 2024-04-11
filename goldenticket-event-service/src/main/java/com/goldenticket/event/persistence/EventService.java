package com.goldenticket.event.persistence;

import com.goldenticket.event.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Transactional
public class EventService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    //region Create
    @Transactional
    public Event save(Event event) {
        return  eventRepository.save(event);
    }

    public List<Event> saveAll(final Collection<Event> events) {
        return  events == null || events.isEmpty()
                ? Collections.emptyList()
                : eventRepository.saveAll(events);
    }
    //endregion

    //region Read
    @Transactional(readOnly = true)
    public boolean exists(final UUID eventId) {
        return eventId != null && eventRepository.existsById(eventId);
    }

    @Transactional(readOnly = true)
    public Event findOneById(final UUID eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Event> findAllByIds(final Collection<UUID> eventIds) {
        return isEmpty(eventIds)
                ? Collections.emptyList()
                : eventRepository.findAllById(eventIds);
    }
    //endregion

    // region Update
    public Event updateEvent(UUID eventId, Event updatedEvent) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);

        if(existingEvent.isEmpty()) {
            LOGGER.error("Event with ID: {} doesn't exist", eventId);
            throw new ResourceNotFoundException("Event with Id: " + eventId + " doesn't exist");
        }
        return eventRepository.save(updatedEvent);
    }
    // endregion

    //region Delete
    public void obsoleteEvent(UUID eventId) {
        Optional<Event> existingEvent = eventRepository.findById(eventId);

        if(existingEvent.isEmpty()) {
            LOGGER.error("Event with ID: {} doesn't exist", eventId);
            throw new ResourceNotFoundException("Event with Id: " + eventId + " doesn't exist");
        }
        existingEvent.get().setObsolete(true);
        eventRepository.save(existingEvent.get());
    }
    //endregion
}