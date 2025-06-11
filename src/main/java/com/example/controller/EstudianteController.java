package com.example.controller;



import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.example.dto.EstudianteDTO;
import com.example.model.Estudiante;
import com.example.service.EstudianteService;

import java.util.*;

@RestController
@RequestMapping("/api/estudiantes")
@RequiredArgsConstructor
public class EstudianteController {

    private final EstudianteService service;

    @PostMapping
    public ResponseEntity<?> registrar(@Valid @RequestBody EstudianteDTO dto) {
        try {
            return ResponseEntity.ok(service.registrar(dto).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody EstudianteDTO dto) {
        try {
            return ResponseEntity.ok(service.actualizar(id, dto).get());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        return service.eliminar(id) ? ResponseEntity.ok("Eliminado") : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Estudiante> listar() {
        return service.listar();
    }

    @GetMapping("/nombre")
    public List<Estudiante> listarPorNombre() {
        return service.listarPorNombre();
    }

    @GetMapping("/apellido")
    public List<Estudiante> listarPorApellido() {
        return service.listarPorApellido();
    }

    @GetMapping("/aprobados")
    public List<Estudiante> aprobados(@RequestParam(defaultValue = "11") int nota) {
        return service.obtenerAprobados(nota);
    }

    @GetMapping("/desaprobados")
    public List<Estudiante> desaprobados(@RequestParam(defaultValue = "11") int nota) {
        return service.obtenerDesaprobados(nota);
    }

    @GetMapping("/top5")
    public List<Estudiante> top5() {
        return service.obtenerTop5();
    }

    @GetMapping("/mejor")
    public Estudiante mejor() {
        return service.mejorEstudiante();
    }

    @GetMapping("/peor")
    public Estudiante peor() {
        return service.peorEstudiante();
    }

    @GetMapping("/nota1")
    public List<Estudiante> nota1() {
        return service.top5PorNota1();
    }

    @GetMapping("/nota2")
    public List<Estudiante> nota2() {
        return service.top5PorNota2();
    }

    @GetMapping("/nota3")
    public List<Estudiante> nota3() {
        return service.top5PorNota3();
    }

    @GetMapping("/nota4")
    public List<Estudiante> nota4() {
        return service.top5PorNota4();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}