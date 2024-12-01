package com.arlo.catalogoLibros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "id_libro_gutendex")
    private int idLibro;
    private String tituloLibro;
    private String idioma;
    private long numDescargas;

    @ManyToOne
    private Autor autorLibro;

    public Libro() {}
    public Libro(DatosLibro datosLibro) {
        this.idLibro = datosLibro.idLibro();
        this.tituloLibro = datosLibro.tituloLibro();
        this.autorLibro = datosLibro.autoresLibro().get(0);
        this.idioma = datosLibro.idiomas().get(0);
        this.numDescargas = datosLibro.numDescargas();
    }

    @Override
    public String toString() {
        return "-----" + tituloLibro + "----- \n" +
                "Autor: " + autorLibro.getName() + "\n" +
                "Idioma: " + idioma + "\n" +
                "Número de descargas:" + numDescargas + "\n";
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getTituloLibro() {
        return tituloLibro;
    }

    public void setTituloLibro(String tituloLibro) {
        this.tituloLibro = tituloLibro;
    }

    public Autor getAutorLibro() {
        return autorLibro;
    }

    public void setAutorLibro(Autor autorLibro) {
        this.autorLibro = autorLibro;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public long getNumDescargas() {
        return numDescargas;
    }

    public void setNumDescargas(long numDescargas) {
        this.numDescargas = numDescargas;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Long getId() {
        return Id;
    }
}
