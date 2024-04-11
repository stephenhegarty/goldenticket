package com.goldenticket.event.api.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.goldenticket.event.persistence.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventResource extends IdResource {

    private String title;
    private LocalDate eventDate;
    private String venue;
    private EventType eventType;
    private String organizer;

    @Builder
    public EventResource(UUID id,
                         String title,
                         LocalDate eventDate,
                         String venue,
                         EventType eventType,
                         String organizer) {
        super(id);
        this.title = title;
        this.eventDate = eventDate;
        this.venue = venue;
        this.eventType = eventType;
        this.organizer = organizer;
    }
}