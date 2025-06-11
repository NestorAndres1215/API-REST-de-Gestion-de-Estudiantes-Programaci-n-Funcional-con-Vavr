package com.example.repository;



import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.model.Estudiante;

import io.vavr.collection.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    @Query("SELECT e FROM Estudiante e ORDER BY e.nombre ASC")
    List<Estudiante> listarPorNombreAsc();

    @Query("SELECT e FROM Estudiante e ORDER BY e.apellido ASC")
    List<Estudiante> listarPorApellidoAsc();

    @Query("SELECT e FROM Estudiante e WHERE e.notaFinal >= :nota")
    List<Estudiante> obtenerAprobados(@Param("nota") int nota);

    @Query("SELECT e FROM Estudiante e WHERE e.notaFinal < :nota")
    List<Estudiante> obtenerDesaprobados(@Param("nota") int nota);

    @Query("SELECT e FROM Estudiante e ORDER BY e.notaFinal DESC")
    List<Estudiante> obtenerTop5(PageRequest pageable);

    @Query("SELECT e FROM Estudiante e ORDER BY e.notaFinal DESC")
    List<Estudiante> mejorEstudiante(PageRequest pageable);

    @Query("SELECT e FROM Estudiante e ORDER BY e.notaFinal ASC")
    List<Estudiante> peorEstudiante(PageRequest pageable);

    @Query("SELECT e FROM Estudiante e ORDER BY e.nota1 DESC")
    List<Estudiante> top5PorNota1(PageRequest pageable);

    @Query("SELECT e FROM Estudiante e ORDER BY e.nota2 DESC")
    List<Estudiante> top5PorNota2(PageRequest pageable);

    @Query("SELECT e FROM Estudiante e ORDER BY e.nota3 DESC")
    List<Estudiante> top5PorNota3(Pageable pageable);

    @Query("SELECT e FROM Estudiante e ORDER BY e.nota4 DESC")
    List<Estudiante> top5PorNota4(PageRequest pageable);

}
