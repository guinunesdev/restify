package io.github.guinunesdev.restify.domain.reservation.dto;

import io.github.guinunesdev.restify.domain.room.dto.RoomResponse;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReservationResponse {
    private Long id;
    private RoomResponse room;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
