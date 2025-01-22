package dev.foro.model.repository;

import dev.foro.model.entity.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    boolean existsByTitulo(String titulo);
    boolean existsByMensaje(String mensaje);

}
