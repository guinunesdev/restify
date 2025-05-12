package io.github.guinunesdev.restify.domain.room.repository;

import io.github.guinunesdev.restify.domain.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}