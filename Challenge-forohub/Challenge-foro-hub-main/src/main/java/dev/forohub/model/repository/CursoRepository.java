package dev.foro.model.repository;

import dev.foro.model.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    Optional<Curso> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}
