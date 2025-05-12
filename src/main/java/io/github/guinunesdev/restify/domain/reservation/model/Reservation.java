package io.github.guinunesdev.restify.domain.reservation.model;

import io.github.guinunesdev.restify.domain.room.model.Room;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Sala é obrigatória")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "room_id")
    private Room room;

    @NotNull(message = "Data/hora de início é obrigatória")
    @FutureOrPresent(message = "Início deve ser agora ou no futuro")
    private LocalDateTime startTime;

    @NotNull(message = "Data/hora de término é obrigatória")
    @Future(message = "Término deve ser no futuro")
    private LocalDateTime endTime;
}
