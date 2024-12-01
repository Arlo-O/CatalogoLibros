package com.arlo.catalogoLibros.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
    @JsonAlias("id") int idLibro,
    @JsonAlias("title") String tituloLibro,
    @JsonAlias("authors") List<Autor> autoresLibro,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") long numDescargas
    ) {
}
