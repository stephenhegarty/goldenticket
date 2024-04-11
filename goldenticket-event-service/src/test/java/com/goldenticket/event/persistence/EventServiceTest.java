package com.goldenticket.event.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.goldenticket.event.persistence.EventModels.anEvent;
import static com.goldenticket.event.utils.TestUtils.anyListOf;
import static com.goldenticket.event.utils.TestUtils.anyUuid;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @InjectMocks
    public EventService eventService;

    @Mock
    public EventRepository eventRepository;

    //region Create
    @Test
    public void testSave() {
        // Given
        UUID eventId = anyUuid();
        Event eventToSave = anEvent().withId(eventId).build();

        Event savedEvent = anEvent().withId(eventId).build();

        given(eventRepository.save(eventToSave)).willReturn(savedEvent);

        // When
        Event result = eventService.save(eventToSave);

        // Then
        assertEquals(eventId, result.getId());
        assertEquals(savedEvent, result);
    }


    @Test
    public void testSaveAll() {
        // Given
        List<Event> eventsToSave = anyListOf(() -> anEvent().withId(anyUuid()).build());
        List<Event> savedEvents = eventsToSave.stream()
                                              .map(event -> anEvent().withId(event.getId()).build())
                                              .toList();

        // When
        when(eventRepository.saveAll(eventsToSave)).thenReturn(savedEvents);

        // Then
        assertEquals(savedEvents, eventService.saveAll(eventsToSave));
    }
    //endregion

    //region Read
    @Test
    public void exists() {
        // Given
        UUID eventId = anyUuid();
        given(eventRepository.existsById(eventId)).willReturn(true);

        // When
        boolean exists = eventService.exists(eventId);

        // Then
        assertTrue(exists);
    }

    @Test
    public void findOneById() {
        // Given
        UUID eventId = anyUuid();
        Event expectedEvent = anEvent().withId(eventId).build();

        // When
        when(eventRepository.findById(eventId)).thenReturn(Optional.of(expectedEvent));

        Event result = eventService.findOneById(eventId);

        // Then
        assertEquals(eventId, result.getId());
    }

    @Test
    public void findAll() {
        // Given
        List<Event> allEvents = anyListOf(() -> anEvent().withId(anyUuid()).build());

        // When
        when(eventRepository.findAll()).thenReturn(allEvents);

        List<Event> result = eventService.findAll();

        // Then
        assertEquals(allEvents.size(), result.size());
    }

    @Test
    public void findAllByIds() {
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
        when(eventRepository.findAllById(eventIdsToFind)).thenReturn(eventsToFind);

        List<Event> result = eventService.findAllByIds(eventIdsToFind);

        // Then
        assertEquals(eventsToFind.size(), result.size());
    }
    //endregion

    //region Update
    @Test
    public void updateEvent() {

    }
    //endregion

    //region Delete
    @Test
    public void obsoleteEvent() {
    }
    //endregion
}