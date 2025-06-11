# 📘 API REST de Gestión de Estudiantes - Programación Funcional con Vavr

Este proyecto es una API REST desarrollada con **Spring Boot** para la gestión de estudiantes, aplicando **Programación Funcional** utilizando la librería **Vavr**. Permite registrar, listar, actualizar y eliminar estudiantes, así como obtener reportes personalizados por notas y ordenamientos específicos.

## 🚀 Características principales

- Registrar estudiantes con nombre, apellido y sus 4 notas.
- Cálculo automático de la nota final en base a las 4 notas ingresadas.
- Listado de estudiantes ordenado por:
  - Nombre (ascendente)
  - Apellido (ascendente)
  - Nota final (Top 5 mejores y peores)
  - Nota1, Nota2, Nota3, Nota4 (Top 5 por cada una)
- Obtener estudiantes aprobados y desaprobados según la nota mínima definida.
- Programación funcional con `Vavr` para el manejo de respuestas (`Option`, `Try`, etc.).
- Validación de datos mediante DTOs (solo se puede registrar nombre, apellido y notas del 1 al 4).

## 🛠️ Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Lombok
- Vavr (programación funcional)
- H2 / MySQL (según configuración)
- Maven o Gradle (según elección)
