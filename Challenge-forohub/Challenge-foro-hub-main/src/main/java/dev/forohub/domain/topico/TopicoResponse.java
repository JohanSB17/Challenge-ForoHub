package dev.foro.domain.topico;

import dev.foro.model.entity.Topico;

import java.time.LocalDateTime;

public record TopicoResponse(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion
) {
    public TopicoResponse(Topico dato) {
        this(dato.getId(), dato.getTitulo(), dato.getMensaje(), dato.getFecha());
    }
}
