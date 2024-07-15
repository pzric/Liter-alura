package com.alurachallenge.Liter_alura.repository;

import com.alurachallenge.Liter_alura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IAutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaDeNacimiento <= :fecha AND (a.fechaDeMuerte IS NULL OR a.fechaDeMuerte >= :fecha)")
    List<Autor> autoresPorFechaDeMuerte(int fecha);
}