package io.github.guinunesdev.restify.domain.room.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RoomCreateRequest {
    @NotBlank(message = "O nome da sala é obrigatório")
    private String name;

    @NotBlank(message = "A localização da sala é obrigatória")
    private String location;
}
