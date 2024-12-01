package com.arlo.catalogoLibros.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private Integer birth_year;
    private Integer death_year;
    @OneToMany(mappedBy = "autorLibro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros;

    public Autor() {}
    public Autor(DatosAutor datosAutor) {
        this.name = datosAutor.name();
        this.birth_year = datosAutor.birth_year();
        this.death_year = datosAutor.death_year();
    }

    @Override
    public String toString() {
        return "-----" + name + "-----" + "\n" +
                "Año de nacimiento: " + birth_year + "\n" +
                "Año de muerte: " + death_year + "\n";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBirth_year() {
        return birth_year;
    }

    public void setBirth_year(int birth_year) {
        this.birth_year = birth_year;
    }

    public int getDeath_year() {
        return death_year;
    }

    public void setDeath_year(int death_year) {
        this.death_year = death_year;
    }

    public void setId(Long id) {
        this.Id = id;
    }

    public Long getId() {
        return Id;
    }
}
