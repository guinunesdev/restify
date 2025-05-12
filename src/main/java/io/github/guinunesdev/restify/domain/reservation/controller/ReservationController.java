package io.github.guinunesdev.restify.domain.reservation.controller;

import io.github.guinunesdev.restify.domain.reservation.dto.ReservationCreateRequest;
import io.github.guinunesdev.restify.domain.reservation.dto.ReservationResponse;
import io.github.guinunesdev.restify.domain.reservation.model.Reservation;
import io.github.guinunesdev.restify.domain.reservation.service.ReservationService;
import jakarta.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ReservationResponse>> getAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(
            @Valid @RequestBody ReservationCreateRequest req) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.create(req));
    }
}
