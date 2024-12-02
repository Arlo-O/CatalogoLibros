package com.arlo.catalogoLibros.principal;

import com.arlo.catalogoLibros.model.Autor;
import com.arlo.catalogoLibros.model.DatosLibro;
import com.arlo.catalogoLibros.model.Libro;
import com.arlo.catalogoLibros.repository.AutorRepository;
import com.arlo.catalogoLibros.repository.LibroRepository;
import com.arlo.catalogoLibros.service.ConsumoAPI;
import com.arlo.catalogoLibros.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private String urlBase = "https://gutendex.com/books/?";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository repositorioLibros;
    private AutorRepository repositorioAutores;
    private Autor autorAGuardar;

    public Principal(LibroRepository repositorioLibros, AutorRepository repositorioAutores) {
        this.repositorioLibros = repositorioLibros;
        this.repositorioAutores = repositorioAutores;
    }

    public void mostarMenu() {
        var opcion = -1;
        var menu = """
                Elija la opción según su número:
                1. Buscar libro por titulo.
                2. Listar libros registrados.
                3. Listar autores registrados.
                4. Listar autores vivos en un año.
                5. Listar libros por idioma.
                
                0. Salir.
                """;
        while (opcion != 0) {
            System.out.println(menu);
            try {
                opcion = teclado.nextInt();
                teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibroNombre();
                        break;
                    case 2:
                        listarLibrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivos();
                        break;
                    case 5:
                        listarLibrosIdioma();
                        break;
                    case 0:
                        System.out.println("Gracias, cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Digite una opción valida");
                }
            } catch (Exception e) {
                System.out.println("Ha digitado una entrada invalida...");
                teclado.nextLine();
            }
        }
    }

    private void listarLibrosIdioma() {
        List<String> idiomasIcluidos = Arrays.asList("en", "es", "fr", "pt");
        var idiomasMenu = """
                Ingrese el idioma para buscar los libros registrados.
                en - Inglés
                fr - Francés
                es - Español
                pt - Portugués
                """;
        System.out.println(idiomasMenu);
        String idiomaElegido = teclado.nextLine();
        boolean estaIncluido = idiomasIcluidos.stream()
                .anyMatch(idioma -> idioma.equals(idiomaElegido));
        if (estaIncluido) {
            var librosIdioma = repositorioLibros.librosIdiomaEspecifico(idiomaElegido);
            if (librosIdioma.isEmpty()) {
                System.out.println("No encontramos libros en nuestra base de datos para ese idioma");
            } else {
                librosIdioma.forEach(System.out::println);
            }
        } else {
            System.out.println("No digito un idioma válido, será redirigido al menú principal...");
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Digite el año a consultar autores vivos:");
        while (true) {
            try {
                var yearAutoresVivos = teclado.nextInt();
                var autoresEncontrados = repositorioAutores.autoresVivosPorYearElegido(yearAutoresVivos);

                if (autoresEncontrados.isEmpty()) {
                    System.out.println("No se encontró ningún autor vivo para ese año.");
                } else {
                    autoresEncontrados.stream()
                            .sorted((a1, a2) -> Integer.compare(a1.getBirth_year(), a2.getBirth_year()))
                            .forEach(System.out::println);
                }
                break;
            } catch (Exception e) {
                System.out.println("Entrada invalida, digite un valor valido");
                teclado.next();
            }
        }
    }

    private void listarAutoresRegistrados() {
        var autoresResgistrados = repositorioAutores.autoresResgistrados();
        if (autoresResgistrados.isEmpty()) {
            System.out.println("Actualmente no ha registrado ningún autor, realice una consulta e intente de nuevo...");
        } else {
            autoresResgistrados.stream()
                    .sorted((a1, a2) -> Integer.compare(a1.getBirth_year(), a2.getBirth_year()))
                    .forEach(System.out::println);
        }
    }

    private void listarLibrosRegistrados() {
        var librosResgistrados = repositorioLibros.librosResgistrados();
        if (librosResgistrados.isEmpty()) {
            System.out.println("Actualmente no ha registrado ningún libro, realice una consulta e intente de nuevo...");
        } else {
            librosResgistrados.stream()
                    .sorted(Comparator.comparing(Libro::getNumDescargas)
                            .reversed()
                            .thenComparing(Comparator.comparing(Libro::getIdLibro)))
                    .forEach(System.out::println);
        }
    }

    private void buscarLibroNombre() {
        System.out.println("Digite el nombre del libro a buscar (en su idioma original): ");
        var libroBuscar = teclado.nextLine();
        Libro libroBuscado = busquedaApi("search=" + libroBuscar.replace(" ", "%20"));
        Optional<Autor> autorExitente = repositorioAutores.findByName(libroBuscado.getAutorLibro().getName());

        if (autorExitente.isPresent()) {
            autorAGuardar = autorExitente.get();
        } else {
            autorAGuardar = libroBuscado.getAutorLibro();
            repositorioAutores.save(autorAGuardar);
        }

        libroBuscado.setAutorLibro(autorAGuardar);
        Optional<Libro> libroExistente = repositorioLibros.findByIdLibro(libroBuscado.getIdLibro());

        if (libroExistente.isPresent()) {
            System.out.println("El libro ya existe en la base de datos.");
        } else {
            repositorioLibros.save(libroBuscado); // Guardar nuevo libro
            System.out.println("Libro guardado correctamente: " + libroBuscado);
        }
    }

    private Libro busquedaApi(String consultaApi) {
        var json = consumoApi.conectarAPI(urlBase + consultaApi);
        List<DatosLibro> librosDatados = conversor.obtenerDatos(json, DatosLibro.class);
        Libro libroBuscado = new Libro(librosDatados.get(0));
        return libroBuscado;
    }
}
