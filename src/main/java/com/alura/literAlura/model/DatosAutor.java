package com.alura.literAlura.model;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown= true)
public record DatosAutor(
        @JsonAlias("name")String nombre,
        // @JsonAlias("birth_year") LocalDate fechaDeNacimiento,

        //@JsonAlias("death_year")LocalDate fechaDeDefuncion

        @JsonAlias("birth_year") int fechaDeNacimiento,

        @JsonAlias("death_year") int fechaDeDefuncion
)

{}




