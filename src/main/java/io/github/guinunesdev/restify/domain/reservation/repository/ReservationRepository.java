package io.github.guinunesdev.restify.domain.reservation.repository;

import io.github.guinunesdev.restify.domain.reservation.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("""
      SELECT r FROM Reservation r
       WHERE r.room.id = :roomId
         AND r.startTime < :endTime
         AND r.endTime > :startTime
    """)
    List<Reservation> findConflicts(
            @Param("roomId") Long roomId,
            @Param("startTime") LocalDateTime startTime,
            @Param("endTime") LocalDateTime endTime
    );
}
