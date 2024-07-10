package com.alura.literAlura.repository;


import com.alura.literAlura.model.Idioma;
import com.alura.literAlura.model.Libros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;


@Repository
public interface LibrosRepository extends JpaRepository<Libros,Long> {

    boolean existsByTitulo(String titulo);

    @Query("SELECT l.idiomas FROM Libros l")
    List<Idioma> idiomasPresentes();

}