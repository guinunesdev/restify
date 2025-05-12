package io.github.guinunesdev.restify.domain.room.service;

import io.github.guinunesdev.restify.domain.room.dto.RoomCreateRequest;
import io.github.guinunesdev.restify.domain.room.dto.RoomResponse;
import io.github.guinunesdev.restify.domain.room.model.Room;
import io.github.guinunesdev.restify.domain.room.repository.RoomRepository;
import io.github.guinunesdev.restify.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository repo;

    public RoomServiceImpl(RoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public RoomResponse create(RoomCreateRequest request) {
        var entity = Room.builder()
                .name(request.getName())
                .location(request.getLocation())
                .build();
        var saved = repo.save(entity);
        return MapperUtil.toRoomResponse(saved);
    }

    @Override
    public List<RoomResponse> findAll() {
        var rooms = repo.findAll();
        return rooms.stream()
                .map(MapperUtil::toRoomResponse)
                .toList();
    }
}
