package com.alura.literAlura.model;

import jakarta.persistence.*;

import java.util.Optional;


@Entity(name="Libros")
@Table(name="libros")
public class Libros {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long Id;
    @Column(unique = true)
    private String titulo;


    @Enumerated(EnumType.STRING)
    private Idioma idiomas;

    private  Double numeroDeDescargas;
    @ManyToOne(fetch = FetchType.EAGER)
    private Autor author;


    public Libros() {
        super();
    }


    public Libros(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idiomas = Idioma.fromString(datosLibros.idiomas().toString());
        this.numeroDeDescargas = datosLibros.numeroDeDescargas();
    }


    public Autor getAutor() {

        return author;
    }

    public void setAutor(Autor autor){
        this.author = autor;


    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }


    public Idioma getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Idioma idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "\nLibros" +
                ", \ntitulo='" + titulo + '\'' +
                ", \nidiomas=" + idiomas +
                ", \nNumeroDeDescargas=" + numeroDeDescargas;
    }
/*
    public void setAutor(Optional<Long> idBuscado) {
      //  this.author=idBuscado.stream().map(n)
    }
    */

}
