package dev.foro.model.service;

import dev.foro.model.entity.Curso;
import dev.foro.model.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerCurso(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }


}
