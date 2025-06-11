package com.example.service;




import io.vavr.control.Try;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.dto.EstudianteDTO;
import com.example.model.Estudiante;
import com.example.repository.EstudianteRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EstudianteService {

    private final EstudianteRepository repo;

    public Try<Estudiante> registrar(EstudianteDTO dto) {
        return Try.of(() -> {
            if (dto == null) {
            throw new IllegalArgumentException("El DTO de estudiante no puede ser nulo");
            }
            if (dto.getNombre() == null || dto.getApellido() == null) {
            throw new IllegalArgumentException("El nombre y apellido no pueden ser nulos");
            }
            int[] notas = {dto.getNota1(), dto.getNota2(), dto.getNota3(), dto.getNota4()};
            for (int nota : notas) {
            if (nota < 0 || nota > 20) {
                throw new IllegalArgumentException("Las notas deben estar entre 0 y 20");
            }
            }
            int notaFinal = (dto.getNota1() + dto.getNota2() + dto.getNota3() + dto.getNota4()) / 4;
            Estudiante e = new Estudiante();
            e.setNombre(dto.getNombre().trim());
            e.setApellido(dto.getApellido().trim());
            e.setNota1(dto.getNota1());
            e.setNota2(dto.getNota2());
            e.setNota3(dto.getNota3());
            e.setNota4(dto.getNota4());
            e.setNotaFinal(notaFinal);
            return repo.save(e);
        });
    }

    public Try<Estudiante> actualizar(Long id, EstudianteDTO dto) {
        return Try.of(() -> {
            Estudiante e = repo.findById(id).orElseThrow();
            if (dto == null) {
                throw new IllegalArgumentException("El DTO de estudiante no puede ser nulo");
            }
            if (dto.getNombre() == null || dto.getApellido() == null) {
                throw new IllegalArgumentException("El nombre y apellido no pueden ser nulos");
            }
            int[] notas = {dto.getNota1(), dto.getNota2(), dto.getNota3(), dto.getNota4()};
            for (int nota : notas) {
                if (nota < 0 || nota > 20) {
                    throw new IllegalArgumentException("Las notas deben estar entre 0 y 20");
                }
            }
            e.setNombre(dto.getNombre().trim());
            e.setApellido(dto.getApellido().trim());
            e.setNota1(dto.getNota1());
            e.setNota2(dto.getNota2());
            e.setNota3(dto.getNota3());
            e.setNota4(dto.getNota4());
            int notaFinal = (dto.getNota1() + dto.getNota2() + dto.getNota3() + dto.getNota4()) / 4;
            e.setNotaFinal(notaFinal);
            return repo.save(e);
        });
    }

    public boolean eliminar(Long id) {
        return repo.findById(id).map(est -> {
            repo.delete(est);
            return true;
        }).orElse(false);
    }

    public List<Estudiante> listar() {
        return repo.findAll();
    }

    public Optional<Estudiante> buscarPorId(Long id) {
        return repo.findById(id);
    }

    public List<Estudiante> listarPorNombre() {
        return (List<Estudiante>) repo.listarPorNombreAsc();
    }

    public List<Estudiante> listarPorApellido() {
        return (List<Estudiante>) repo.listarPorApellidoAsc();
    }

    public List<Estudiante> obtenerAprobados(int nota) {
        return (List<Estudiante>) repo.obtenerAprobados(nota);
    }

    public List<Estudiante> obtenerDesaprobados(int nota) {
        return (List<Estudiante>) repo.obtenerDesaprobados(nota);
    }

    public List<Estudiante> obtenerTop5() {
        return(List<Estudiante>) repo.obtenerTop5(PageRequest.of(0, 5));
    }

    public Estudiante mejorEstudiante() {
        return repo.mejorEstudiante(PageRequest.of(0, 1)).get(0);
    }

    public Estudiante peorEstudiante() {
        return repo.mejorEstudiante(PageRequest.of(0, 1)).get(0);
    }

    public List<Estudiante> top5PorNota1() {
        return (List<Estudiante>) repo.top5PorNota1(PageRequest.of(0, 5));
    }

    public List<Estudiante> top5PorNota2() {
        return (List<Estudiante>) repo.top5PorNota2(PageRequest.of(0, 5));
    }

    public List<Estudiante> top5PorNota3() {
        return (List<Estudiante>) repo.top5PorNota1(PageRequest.of(0, 5));
    }

    public List<Estudiante> top5PorNota4() {
        return (List<Estudiante>) repo.top5PorNota4(PageRequest.of(0, 5));
    }
}