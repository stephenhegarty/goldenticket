package com.goldenticket.event.service;

import com.goldenticket.event.persistence.Event;
import com.goldenticket.event.persistence.EventModels;
import com.goldenticket.event.persistence.EventService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static com.goldenticket.event.persistence.EventModels.anEvent;
import static com.goldenticket.event.utils.TestUtils.anyListOf;
import static com.goldenticket.event.utils.TestUtils.anyUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventApiServiceTest {

    @InjectMocks
    private EventApiService eventApiService;

    @Mock
    private EventService eventService;

    @Test
    void createEvent() {
        // Given
        Event expected = EventModels.anEvent().build();
        given(eventService.save(expected)).willReturn(expected);

        // When
        Event actual = eventApiService.createEvent(expected);

        // Then
        assertEquals(expected, actual);

    }

    @Test
    void createEvents() {
        // Given
        List<Event> expected = anyListOf(() -> EventModels.anEvent().build());
        given(eventService.saveAll(expected)).willReturn(expected);

        // When
        List<Event> actual = eventApiService.createEvents(expected);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void findEvent() {
        // Given
        UUID eventId = anyUuid();
        Event expected = EventModels.anEvent().withId(eventId).build();
        given(eventService.findOneById(expected.getId())).willReturn(expected);

        // When
        Event actual = eventApiService.findEvent(eventId);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void findAllEvents() {
        // Given
        List<Event> allEvents = anyListOf(() -> anEvent().withId(anyUuid()).build());

        // When
        when(eventService.findAll()).thenReturn(allEvents);

        List<Event> result = eventApiService.findAllEvents();

        // Then
        assertEquals(allEvents.size(), result.size());
    }

    @Test
    void findEventsByIds() {
        // Given
        UUID eventId1 = anyUuid();
        UUID eventId2 = anyUuid();

        List<Event> eventsToFind = Arrays.asList(
                anEvent().withId(eventId1)
                         .build(),
                anEvent().withId(eventId2)
                         .build()
        );

        List<UUID> eventIdsToFind = Arrays.asList(eventId1, eventId2);

        // When
        when(eventService.findAllByIds(eventIdsToFind)).thenReturn(eventsToFind);

        List<Event> result = eventService.findAllByIds(eventIdsToFind);

        // Then
        assertEquals(eventsToFind.size(), result.size());
    }

    @Test
    void updateEvent() {
        // Given

        // When

        // Then
    }

    @Test
    void eventExists() {
        // Given

        // When

        // Then
    }

    @Test
    void obsoleteEvent() {
        // Given

        // When

        // Then
    }
}