package io.github.guinunesdev.restify.domain.room.controller;

import io.github.guinunesdev.restify.domain.room.dto.RoomCreateRequest;
import io.github.guinunesdev.restify.domain.room.dto.RoomResponse;
import io.github.guinunesdev.restify.domain.room.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomController {

    private final RoomService service;

    public RoomController(RoomService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RoomResponse>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<RoomResponse> create(@Valid @RequestBody RoomCreateRequest req) {
        return ResponseEntity.ok(service.create(req));
    }
}
