package com.alura.literAlura.repository;

import com.alura.literAlura.model.Autor;
import com.alura.literAlura.model.capturaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    boolean existsByNombre(String nombre);

    @Query("SELECT a.id  FROM Autor a WHERE a.nombre =:nombreB")
    Optional<Long> buscoId(String nombreB);

    @Query("SELECT a FROM Autor a WHERE :anioElegido BETWEEN a.fechaDeNacimiento AND a.fechaDeDefuncion")
    List <Autor> autorEncontrado(int anioElegido);

    @Query("SELECT titulo FROM Autor a JOIN a.libros l")
    List <capturaDTO> autoresEnLB();




}
