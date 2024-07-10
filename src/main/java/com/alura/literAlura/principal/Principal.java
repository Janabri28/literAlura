package com.alura.literAlura.principal;

import com.alura.literAlura.model.*;
import com.alura.literAlura.repository.AutorRepository;
import com.alura.literAlura.repository.LibrosRepository;
import com.alura.literAlura.service.*;



import java.util.*;



public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibrosRepository repositorio;

    private AutorRepository repositorioAut;

    private static final String URL_BASE = "https://gutendex.com/books/";


    public Principal(LibrosRepository repository, AutorRepository repository2) {
        this.repositorio = repository;
        this.repositorioAut = repository2;
    }


    public boolean existeLibro(String titulo)      //Metodo para saber si ya existe un libro en la base de Datos
    {
        return repositorio.existsByTitulo(titulo);
    }


    public Optional<Long> BuscandoId(String nombre)      //Metodo para saber si ya existe un libro en la base de Datos
    {
        return repositorioAut.buscoId(nombre);
    }


    public boolean existeByNombre(String nombre)      //Metodo para saber si ya existe un libro en la base de Datos
    {
        return repositorioAut.existsByNombre(nombre);
    }



    public void muestraElMenu() {

        System.out.println("\nBienvenido a literALURA. \n Elija una de las opciones posibles:\n ");


        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    \n1 - Buscar libro por título 
                    2 - Listar los libros existentes en la base de datos.
                    3 - Listar autores registrados
                    4 - Listar autores vivos en determinado año 
                    5 - Listar libros por idioma                  
                    0 - Salir
                    """;
            System.out.println(menu);



            opcion= Integer.parseInt(teclado.next());
        //    opcion = teclado.nextInt();
                     teclado.nextLine();

                switch (opcion) {
                    case 1:
                        buscarLibrosPorTitulo();
                        teclado.nextLine();
                        break;
                    case 2:
                        listarLibrosEnLaBaseDeDatos();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                  case 4:
                        buscarAutoresVivosEnDeterminadoAnio();
                        break;
                    case 5:
                        listarLibrosPorIdioma();
                        break;
                    case 0:
                        System.out.println("Cerrando la aplicación...");
                        break;
                    default:
                        System.out.println("Opción inválida");
                }

            }

    }



    private void buscarLibrosPorTitulo() {
        System.out.println("Escribe el título del libro que deseas buscar");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + "?search=" + nombreLibro.replace(" ", "+"));
        Datos datosBusqueda = conversor.obtenerDatos(json, Datos.class);
        Optional<DatosLibros> libroBuscado = datosBusqueda.resultados().stream()
                .filter(l -> l.titulo().toLowerCase().contains(nombreLibro.toLowerCase()))
                .findFirst();
        Optional<DatosAutor> listaAplanadaAutor = datosBusqueda.resultados().stream()
                .flatMap( listaInterna -> listaInterna.datosAutor().stream()).findFirst();
         if(!(listaAplanadaAutor.isPresent())){
             System.out.println("...");
         }
        if (libroBuscado.isPresent()) {
            System.out.println("Libro Encontrado en la API\n");
            consultaSiAgregoONo(libroBuscado.get(),listaAplanadaAutor.get());
        } else {
            System.out.println("Libro no encontrado en la API");
       }
   }

    private void consultaSiAgregoONo(DatosLibros libroE,DatosAutor autorLibro) {
        if (existeLibro(libroE.titulo())) {
            System.out.println("No añadiré este libro.\n" + libroE + "\nporque ya existe en la base de datos");
        } else {

            System.out.println("Añadiendo a la base de datos...\n\n");
            Autor nuevoAutor = new Autor(autorLibro);
            Optional<Long> idBuscado = BuscandoId(nuevoAutor.getNombre());
            if(idBuscado.isEmpty()){
                List<Libros> books = new ArrayList<>(); //crear lista
                Libros nuevoLibro = new Libros(libroE); //crear libro nuevo
                nuevoLibro.setAutor(nuevoAutor);       // SET en nuevo libro nuevo Autor
                books.add(nuevoLibro);               //añade el libro a la lista!
                nuevoAutor.setLibros(books);         //SET en Listalibros nuevo autor
                repositorioAut.save(nuevoAutor);     //salva autor
                repositorio.save(nuevoLibro);       // salva el libro
                System.out.println(nuevoLibro);
                System.out.println(nuevoAutor);
                System.out.println("Listo!,libro añadido con exito\n");
            }
              else {
                var el_Id= idBuscado.get();
                System.out.println("aqui va el Id: "+ el_Id);
             Optional<Autor> autorb=repositorioAut.findById(el_Id);
                 if(autorb.isPresent()) {
                     List<Libros> variable = autorb.get().getLibros(); //abrir lista de libros existente
                     Autor autorYaExiste=autorb.get();
                     System.out.println(autorYaExiste);
                     Libros nuevoLibro = new Libros(libroE);  //crear libro nuevo
                     nuevoLibro.setAutor(autorYaExiste);      //asociar el autor de la base de datos
                     variable.add(nuevoLibro);
                     nuevoAutor.setLibros(variable);
                     repositorio.save(nuevoLibro);
                    System.out.println(nuevoLibro);
                     System.out.println(nuevoAutor);
                     System.out.println("Lista actualizada!");
                 }
            }


        }


    }





    private void listarLibrosEnLaBaseDeDatos() {
        List<Libros> librosEnBD = repositorio.findAll();
        librosEnBD.forEach(e -> System.out.printf("---\nLibro: %s \nIdioma: %s  \nDescargas:  %f   \nAutor:  %s   \nFecha de Nacimiento: %s   \nFecha de Defunción:  %s\n\n",
                       e.getTitulo(), e.getIdiomas(), e.getNumeroDeDescargas(), e.getAutor().getNombre(), e.getAutor().getFechaDeNacimiento(),e.getAutor().getFechaDeDefuncion()));

    }

    private void  buscarAutoresVivosEnDeterminadoAnio() {
        System.out.println("Escribe el año en que deseas encontrar autores vivos: ");
        int anioelegido = teclado.nextInt();
        teclado.nextLine();

       List<Autor> autoresVivos= repositorioAut.autorEncontrado(anioelegido);
        autoresVivos.forEach(a ->
                System.out.printf("\nAutor: %s\n Fecha de Nacimiento: %s\n Fecha de Defunción: %s\n",a.getNombre(),
                        a.getFechaDeNacimiento(), a.getFechaDeDefuncion()));

    }


    private void listarLibrosPorIdioma() {
     List<Idioma> encontrados=repositorio.idiomasPresentes();
       encontrados.forEach(System.out::println);
    }



    private void listarAutoresRegistrados() {
        var autoresEnc=repositorioAut.findAll();
        System.out.println(autoresEnc);
    }



}
