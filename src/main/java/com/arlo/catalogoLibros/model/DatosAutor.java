package com.arlo.catalogoLibros.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosAutor(
        @JsonAlias("name")String name,
        @JsonAlias("birth_year")Integer birth_year,
        @JsonAlias("death_year")Integer death_year) {
}
