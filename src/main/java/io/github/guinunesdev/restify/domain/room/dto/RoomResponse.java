package io.github.guinunesdev.restify.domain.room.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RoomResponse {
    private Long id;
    private String name;
    private String location;
}
