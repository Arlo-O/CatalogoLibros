package com.arlo.catalogoLibros;

import com.arlo.catalogoLibros.principal.Principal;
import com.arlo.catalogoLibros.repository.AutorRepository;
import com.arlo.catalogoLibros.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CatalogoLibrosApplication implements CommandLineRunner {
	@Autowired
	private LibroRepository repositorioLibros;
	@Autowired
	private AutorRepository repositorioAutores;
	public static void main(String[] args) {
		SpringApplication.run(CatalogoLibrosApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(repositorioLibros, repositorioAutores);
		principal.mostarMenu();
	}
}
