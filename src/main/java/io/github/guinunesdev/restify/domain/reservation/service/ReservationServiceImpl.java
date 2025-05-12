package io.github.guinunesdev.restify.domain.reservation.service;

import io.github.guinunesdev.restify.domain.reservation.dto.ReservationCreateRequest;
import io.github.guinunesdev.restify.domain.reservation.dto.ReservationResponse;
import io.github.guinunesdev.restify.domain.reservation.model.Reservation;
import io.github.guinunesdev.restify.domain.reservation.repository.ReservationRepository;
import io.github.guinunesdev.restify.domain.room.model.Room;
import io.github.guinunesdev.restify.domain.room.repository.RoomRepository;
import io.github.guinunesdev.restify.exception.NotFoundException;
import io.github.guinunesdev.restify.exception.ConflictException;
import io.github.guinunesdev.restify.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final RoomRepository roomRepo;
    private final ReservationRepository reservationRepo;

    public ReservationServiceImpl(RoomRepository roomRepo,
                                  ReservationRepository reservationRepo) {
        this.roomRepo = roomRepo;
        this.reservationRepo = reservationRepo;
    }

    @Override
    public ReservationResponse create(ReservationCreateRequest req) {
        Room room = roomRepo.findById(req.getRoomId())
                .orElseThrow(() -> new NotFoundException("Sala não encontrada"));

        Reservation entity = Reservation.builder()
                .room(room)
                .startTime(req.getStartTime())
                .endTime(req.getEndTime())
                .build();

        var conflicts = reservationRepo.findConflicts(
                room.getId(), entity.getStartTime(), entity.getEndTime());
        if (!conflicts.isEmpty()) {
            throw new ConflictException("Intervalo de horário já reservado para esta sala.");
        }

        Reservation saved = reservationRepo.save(entity);
        return MapperUtil.toReservationResponse(saved);
    }

    @Override
    public List<ReservationResponse> listAll() {
        return reservationRepo.findAll().stream()
                .map(MapperUtil::toReservationResponse)
                .toList();
    }
}
