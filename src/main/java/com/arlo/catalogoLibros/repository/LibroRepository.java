package com.arlo.catalogoLibros.repository;

import com.arlo.catalogoLibros.model.Libro;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByIdLibro(int idLibro);
    @Query("SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> librosIdiomaEspecifico(String idioma);
    @Query("SELECT l FROM Libro l")
    List<Libro> librosResgistrados();
}
