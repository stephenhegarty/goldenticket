package com.goldenticket.event.controller;

import com.goldenticket.event.api.client.EventApi;
import com.goldenticket.event.api.v1.EventResource;
import com.goldenticket.event.persistence.Event;
import com.goldenticket.event.service.EventApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import static com.goldenticket.event.api.EventEndpoints.EVENT;
import static com.goldenticket.event.api.EventEndpoints.UUID_TOKEN;
import static com.goldenticket.event.utils.EventUtils.buildLocationUri;
import static com.goldenticket.event.utils.EventUtils.resourceMustExist;

@RestController
public class EventController implements EventApi {

    private final EventApiService eventApiService;
    private final EventMapper eventMapper;

    @Autowired
    public EventController(EventApiService eventApiService, EventMapper eventMapper) {
        this.eventApiService = eventApiService;
        this.eventMapper = eventMapper;
    }

    @Override
    public ResponseEntity<Void> createEvent(@Valid @RequestBody EventResource request) {
        final Event event = eventApiService.createEvent(eventMapper.resourceToEntity(request));
        return ResponseEntity.created(buildLocationUri(event.getId(), EVENT)).build();
    }

    @Override
    public ResponseEntity<EventResource> getEvent(@PathVariable(UUID_TOKEN) UUID eventId) {
        final Event event = resourceMustExist(eventId, eventApiService.findEvent(eventId));
        final EventResource eventResource = eventMapper.entityToResource(event);

        return ResponseEntity.ok(eventResource);
    }

    @Override
    public ResponseEntity<List<EventResource>> getEvents() {
        final List<Event> events = eventApiService.findAllEvents();

        return ResponseEntity.ok(mapEventsToResources(events));
    }

    @Override
    public ResponseEntity<Void> updateEvent(@PathVariable(UUID_TOKEN) UUID eventId,
                                            @Valid @RequestBody EventResource request) {
        resourceMustExist(eventId, eventApiService.findEvent(eventId));

        eventApiService.updateEvent(eventId, eventMapper.resourceToEntity(request));
        return ResponseEntity.ok()
                             .build();
    }

    @Override
    public ResponseEntity<Void> obsoleteEvent(UUID eventId) {
        resourceMustExist(eventId, eventApiService.findEvent(eventId));
        eventApiService.obsoleteEvent(eventId);
        return ResponseEntity.noContent()
                             .build();
    }

    private List<EventResource> mapEventsToResources(final List<Event> events) {
        return events.stream()
                     .map(eventMapper::entityToResource)
                     .toList();
    }
}
