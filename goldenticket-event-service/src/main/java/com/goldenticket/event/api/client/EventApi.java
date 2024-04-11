package com.goldenticket.event.api.client;

import com.goldenticket.event.api.v1.EventResource;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.UUID;

import static com.goldenticket.event.api.EventEndpoints.EVENT;
import static com.goldenticket.event.api.EventEndpoints.EVENTS;
import static com.goldenticket.event.api.EventEndpoints.UUID_TOKEN;

public interface EventApi {

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = EVENTS, method = RequestMethod.POST)
    ResponseEntity<Void> createEvent(@Valid @RequestBody EventResource request);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = EVENT, method = RequestMethod.GET)
    ResponseEntity<EventResource> getEvent(@PathVariable(UUID_TOKEN) UUID eventId);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = EVENTS, method = RequestMethod.GET)
    ResponseEntity<List<EventResource>> getEvents();

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = EVENT, method = RequestMethod.PUT)
    ResponseEntity<Void> updateEvent(@PathVariable(UUID_TOKEN) UUID eventId, @Valid @RequestBody EventResource request);

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = EVENT, method = RequestMethod.DELETE)
    ResponseEntity<Void> obsoleteEvent(@PathVariable(UUID_TOKEN) UUID eventId);
}