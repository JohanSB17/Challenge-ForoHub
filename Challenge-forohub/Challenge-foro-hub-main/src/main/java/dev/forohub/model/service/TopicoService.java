package dev.foro.model.service;

import dev.foro.domain.topico.DatosActualizarTopico;
import dev.foro.domain.topico.DatosCrearTopico;
import dev.foro.domain.topico.TopicoResponse;
import dev.foro.domain.topico.validacion.ValidadorDeTopico;
import dev.foro.infra.error.ValidacionDeIntegridad;
import dev.foro.model.entity.Curso;
import dev.foro.model.entity.Topico;
import dev.foro.model.entity.Usuario;
import dev.foro.model.repository.CursoRepository;
import dev.foro.model.repository.TopicoRepository;
import dev.foro.model.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository temaRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorDeTopico> validadores;

    @Transactional(readOnly = true)
    public TopicoResponse obtenerTopico(Long id) {
        return temaRepository.findById(id)
                .map(TopicoResponse::new)
                .orElse(null);
    }

    @Transactional(readOnly = true)
    public List<TopicoResponse> listarTopicos() {
        return temaRepository.findAll().stream()
                .map(TopicoResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public TopicoResponse guardarTopico(DatosCrearTopico datos) {
        validadores.forEach(validador -> validador.validar(datos));
        Curso curso = obtenerCursoPorNombre(datos.nombreCurso());
        Usuario usuario = existeUsuario(datos.usuarioId());

        Topico tema = Topico.builder()
                .titulo(datos.titulo())
                .mensaje(datos.mensaje())
                .curso(curso)
                .autor(usuario)
                .fecha(LocalDateTime.now())
                .status(true)
                .build();

        return new TopicoResponse(temaRepository.save(tema));
    }

    private Curso obtenerCursoPorNombre(String nombreCurso) {
        return cursoRepository.findByNombre(nombreCurso)
                .orElseThrow(() -> new ValidacionDeIntegridad("El curso no existe"));
    }

    private Usuario existeUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ValidacionDeIntegridad("El usuario no existe"));
    }

    @Transactional
    public TopicoResponse actualizarTopico(Long id, DatosActualizarTopico datos) {
        DatosCrearTopico temporal = new DatosCrearTopico(null, datos.titulo(), datos.mensaje(), datos.nombreCurso());
        validadores.forEach(validador -> validador.validar(temporal));
        Optional<Topico> topicoBuscado = temaRepository.findById(id);

        if(topicoBuscado.isPresent()) {
            Topico tema = topicoBuscado.get();
            if(datos.titulo() != null)
                tema.setTitulo(datos.titulo());
            if(datos.mensaje() != null)
                tema.setMensaje(datos.mensaje());
            if(datos.nombreCurso() != null) {
                Curso curso = obtenerCursoPorNombre(datos.nombreCurso());
                tema.setCurso(curso);
            }

            return new TopicoResponse(tema);
        }

        return null;
    }

    @Transactional
    public void eliminarTopico(Long id) {
        temaRepository.deleteById(id);
    }

}
