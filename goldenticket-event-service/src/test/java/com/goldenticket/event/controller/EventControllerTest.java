package com.goldenticket.event.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goldenticket.event.api.v1.EventResource;
import com.goldenticket.event.persistence.Event;
import com.goldenticket.event.service.EventApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.UUID;

import static com.goldenticket.event.api.EventEndpoints.EVENT;
import static com.goldenticket.event.api.EventEndpoints.EVENTS;
import static com.goldenticket.event.api.EventResourceModels.aWriteEventResource;
import static com.goldenticket.event.persistence.EventModels.anEvent;
import static com.goldenticket.event.utils.TestUtils.anyString;
import static com.goldenticket.event.utils.TestUtils.anyUuid;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class EventControllerTest {

    @Autowired
    private EventMapper eventMapper;

    @MockBean
    protected EventApiService eventApiService;

    @Autowired
    protected MockMvc mvc;

    @Autowired
    protected ObjectMapper objectMapper;

    //region CREATE
    @Test
    public void createEvent() throws Exception {
        // Given
        EventResource resource = aWriteEventResource().build();
        Event event = eventMapper.resourceToEntity(resource);
        event.setId(anyUuid());

        given(eventApiService.createEvent(event)).willReturn(event);

        // When
        mvc.perform(post(EVENTS)
                   .content(objectMapper.writeValueAsString(resource))
                   .contentType(MediaType.APPLICATION_JSON))

           // Then
           .andExpect(status().isCreated())
           .andReturn();
    }
    //endregion

    //region READ
    @Test
    void getEvent() throws Exception {
        // Given
        final Event event = anEvent().build();
        final UUID eventId = event.getId();

        given(eventApiService.findEvent(eventId)).willReturn(event);

        // When
        mvc.perform(get(EVENT, eventId))

           // Then
           .andExpect(content().string(objectMapper.writeValueAsString(eventMapper.entityToResource(event))))
           .andReturn();
    }

    @Test
    void getEvents() throws Exception {
        // Given
        final Event event = anEvent().build();

        given(eventApiService.findAllEvents()).willReturn(Collections.singletonList(event));

        // When
        mvc.perform(get(EVENTS))

           // Then
           .andExpect(content().string(objectMapper.writeValueAsString(eventMapper.entityToResource(event))))
           .andReturn();
    }
    //endregion

    //region UPDATE
    @Test
    void updateEvent() throws Exception {
        // Given
        String expectedString = anyString();
        EventResource resource = aWriteEventResource().build();
        Event event = eventMapper.resourceToEntity(resource);
        event.setId(anyUuid());

        given(eventApiService.findEvent(event.getId())).willReturn(event);
        resource.setTitle(expectedString);
        given(eventApiService.updateEvent(event.getId(), event)).willReturn(event);

        // When
        mvc.perform(put(EVENTS)
                   .content(objectMapper.writeValueAsString(resource))
                   .contentType(MediaType.APPLICATION_JSON))

           // Then
           .andExpect(status().isOk())
           .andExpect(content().string(objectMapper.writeValueAsString(eventMapper.entityToResource(event)
                                                                                  .getTitle()
                                                                                  .equals(expectedString))))
           .andReturn();

    }
    //endregion

    //region DELETE
    @Test
    void obsoleteEvent() throws Exception {
        //given
        Event event = anEvent().build();
        final UUID eventId = event.getId();

        given(eventApiService.findEvent(eventId)).willReturn(event);

        //when
        mvc.perform(delete(EVENT, eventId))
           //then
           .andExpect(status().isNoContent())
           .andReturn();
        verify(eventApiService).obsoleteEvent(eventId);
    }
    //endregion
}