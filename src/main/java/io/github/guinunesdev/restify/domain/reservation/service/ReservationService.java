package io.github.guinunesdev.restify.domain.reservation.service;

import io.github.guinunesdev.restify.domain.reservation.dto.ReservationCreateRequest;
import io.github.guinunesdev.restify.domain.reservation.dto.ReservationResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReservationService {
    ReservationResponse create(ReservationCreateRequest request) throws ChangeSetPersister.NotFoundException;
    List<ReservationResponse> listAll();
}

