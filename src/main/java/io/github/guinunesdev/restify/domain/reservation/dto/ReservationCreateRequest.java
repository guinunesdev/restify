package io.github.guinunesdev.restify.domain.reservation.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
public class ReservationCreateRequest {

    @NotNull(message = "A sala é obrigatória")
    private Long roomId;

    @NotNull(message = "Data/hora de início é obrigatória")
    @FutureOrPresent(message = "Início deve ser agora ou no futuro")
    private LocalDateTime startTime;

    @NotNull(message = "Data/hora de término é obrigatória")
    @Future(message = "Término deve ser no futuro")
    private LocalDateTime endTime;
}
