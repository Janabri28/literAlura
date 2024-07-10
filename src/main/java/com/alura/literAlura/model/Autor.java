package com.alura.literAlura.model;

import jakarta.persistence.*;


import java.util.List;


@Entity(name="Autor")
@Table(name="autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

  //  @Column(unique = true)
    private String nombre;
    private int fechaDeNacimiento;
    private int fechaDeDefuncion;


    @OneToMany(mappedBy = "author",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    private List<Libros> libros;


    public Autor() {
       super();
    }


    public Autor(DatosAutor autorLibro) {
        this.nombre=autorLibro.nombre();
        this.fechaDeNacimiento= autorLibro.fechaDeNacimiento();
        this.fechaDeDefuncion=autorLibro.fechaDeDefuncion();
    }


    public Autor(DatosAutor autorLibro, List<Libros> books) {
        this.nombre=autorLibro.nombre();
        this.fechaDeNacimiento= autorLibro.fechaDeNacimiento();
        this.fechaDeDefuncion=autorLibro.fechaDeDefuncion();
        this.libros=books;
    }

    @Override
    public String toString() {
        return "\nAutor_nombre='" + nombre + '\'' +
                ",\nfechaDeNacimiento=" + fechaDeNacimiento +
                ",\n fechaDeDefuncion=" + fechaDeDefuncion +
                " \ntitulo_libros= " + libros.stream().map(Libros::getTitulo).toList().toString()+
                ", \n";
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }


    public List<Libros> getLibros() {

        return libros;
    }

    public void setLibros(List<Libros> libros) {
        this.libros = libros;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(int fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public int getFechaDeDefuncion() {
        return fechaDeDefuncion;
    }

    public void setFechaDeDefuncion(int fechaDeDefuncion) {
        this.fechaDeDefuncion = fechaDeDefuncion;
    }



}