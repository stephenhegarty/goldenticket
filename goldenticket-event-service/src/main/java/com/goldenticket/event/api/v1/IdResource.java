package com.goldenticket.event.api.v1;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class IdResource {

    private UUID id;

    public IdResource() {
    }
}
