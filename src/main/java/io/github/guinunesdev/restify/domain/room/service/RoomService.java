package io.github.guinunesdev.restify.domain.room.service;

import io.github.guinunesdev.restify.domain.room.dto.RoomCreateRequest;
import io.github.guinunesdev.restify.domain.room.dto.RoomResponse;

import java.util.List;

public interface RoomService {
    RoomResponse create(RoomCreateRequest request);
    List<RoomResponse> findAll();
}
