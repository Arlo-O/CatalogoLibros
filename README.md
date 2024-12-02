# Cátalogo libros Challenge Alura.

## Sobre el proyecto.

Este proyecto simula de manera básica un cátalogo de libros y sus autores, esta información es obtenida por la aplicación por medio de la conexión con la API: https://gutendex.com luego de ello se decide cual será la información más relevante para el cátalogo y se reazliza la creación de las clases que representarán a los autores y libros de los que se obtiene información, en la Documentación de la API podrá econtrar que información es proporcionada por ella, así como el formato y los párametros que se pueden utilizar para realizar las consultas a la API.
La información que se ha considerado con mayor relevancia para el proyecto se presenta a continuación:
_Para los Libros_
- Id proporcionado por Gutendex.
- Titulo del libro
- Primer idioma que presenta la API para el libro
- Número de descargas en el sitio Gutendex
- Primer Autor que proporciona la API para el libro
_Para los autores_
- Nombre del autor
- Año de nacimiento
- Año de fallecimiento

Y esta información a partir que se realizan consultas sobre distintos libros es almacenada en una base de datos, para este caso se uso como gestor de bases de datos a PostgreSQL, la interacción del usuario con el programa es por medio de la linea de comandos en donde se irán mostrando las funcionalidades disponibles apra el usuario y las indicaciones para su correcto funcionamientos.

## Funcionalidades.
- Consulta libros: El usuario digita el titulo del libro o el nombre del autor y el sistema debe de mostrarle la información vinculada a dicho libro y se registra junto con el autor en la base de datos.
- Listar libros registrados: Se accede a la base de datos y se presentan al usuario todos los libros que han sido registrados.
- Listar autores registrados: De igual manera se accede a la base de datos y se muestra la información de todos los autores registrados.
- Listar autores vivos para determinado año: El usuario digita un año y el sistema proporcionara el listado de autores registrados en la base de datos que se encuentran vivos en ese año.
- Listar libros por idioma: Se accede a la base de datos y según el idioma especificado por el usuario se presentan los libros que tiene dicho ifdioma como primer valor proporcionado por la API.
- 
## Para ejecutar el código 🚀

Asegurese de tener instalado:
* PostgreSQL
  Cree la base de datos en PostgreSQL
Edite la variables de entorno de su sistema:
Ejemplo:
Variable / Valor
 - DB_HOST / localhost
 - DB_NAME / nombre_de_la_base_de_datos
 - DB_PASSWORD / contraseña_de_su_usuario_postgres
 - DB_USER / postgres (o su propoio usuario en PGAdmin)
   _No olvide que al editar las variables de entorno para que los cambios se apliquen debe de reiniciar el sistema.
Posteriormente descargue el proyecto y ejecutelo en su entorno de desarrollo de preferencia.

## Manual de usuario básico.
Inicialmente la aplicación le mostrará un menú con los indices de las funcionalidades, digite el que sea de su preferencia (recuerda que si es la primera ejecución no tendrá ningún dato en la base de datos por tanto los listados de registros serán nulos):
1. Consultar libro por titulo:
     - Digite el nombre del titulo que desea (en su idioma original, basta con una par de palabras)
     - El sistema proporcionará a usted la información del libro si lo obtiene y caso contrario le mostrará el mensaje pertienente.
2. Listar libros registrados.
     - Se mostrarán todos los libros resgitrados y lo dirigirá de nuevo al menú principal.
3. Listar autores registrados.
     - Se mostrarán todos los autores resgitrados y lo dirigirá de nuevo al menú principal.
4. Autores vivos en un año.
     - Digite el año del cual quiere consultar los autores vivos.
     - Se presentará el listado de autores vivos para ese año con su información y se dirigirá al menú principal
5. Listar lirbos por idioma:
     . Digite una de las opciones presentadas por el programa y los libros que en ese idioma será presentados.
0. Digite 0 si desea salir de la aplicación.


## Construido con 🛠️

_Como lenguaje se uso JAVA_

* Framework Spring
* Gestor de dependencias MAVEN
* API Gutendex
Dependencias:
  * SpringBoot
  * postgresql
  * jackson
Y otras librerias y modulos propios de JAVA...

## Autores ✒️

* **Arlo Ocampo** - [Arlo-O](https://github.com/Arlo-O)
