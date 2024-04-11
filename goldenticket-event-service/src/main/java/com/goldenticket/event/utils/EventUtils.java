package com.goldenticket.event.utils;

import com.goldenticket.event.exception.ResourceNotFoundException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

public class EventUtils {

    public static URI buildLocationUri(UUID resourceId, String basePath) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(basePath)
                .buildAndExpand(resourceId.toString())
                .toUri();
    }

    public static <T> T resourceMustExist(UUID uuid, T resource) {
        if (resource != null) {
            return resource;
        } else {
            throw new ResourceNotFoundException("Resource not found for UUID: " + uuid);
        }
    }
}
