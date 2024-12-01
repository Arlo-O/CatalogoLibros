package com.arlo.catalogoLibros.repository;

import com.arlo.catalogoLibros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.birth_year <= :yearElegido AND (a.death_year IS NULL OR a.death_year > :yearElegido)")
    List<Autor> autoresVivosPorYearElegido(Integer yearElegido);

    @Query("SELECT a FROM Autor a")
    List<Autor> autoresResgistrados();
}
