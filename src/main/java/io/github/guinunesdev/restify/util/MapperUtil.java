package io.github.guinunesdev.restify.util;

import io.github.guinunesdev.restify.domain.room.dto.RoomResponse;
import io.github.guinunesdev.restify.domain.room.model.Room;
import io.github.guinunesdev.restify.domain.reservation.dto.ReservationResponse;
import io.github.guinunesdev.restify.domain.reservation.model.Reservation;

public class MapperUtil {

    public static RoomResponse toRoomResponse(Room room) {
        return RoomResponse.builder()
                .id(room.getId())
                .name(room.getName())
                .location(room.getLocation())
                .build();
    }

    public static ReservationResponse toReservationResponse(Reservation r) {
        return ReservationResponse.builder()
                .id(r.getId())
                .room(toRoomResponse(r.getRoom()))
                .startTime(r.getStartTime())
                .endTime(r.getEndTime())
                .build();
    }
}
