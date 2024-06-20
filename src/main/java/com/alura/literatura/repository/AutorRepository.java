package com.alura.literatura.repository;

import com.alura.literatura.model.Autor;
import com.alura.literatura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.nome LIKE %:nome%")
    Optional<Autor> findByName(@Param("nome") String nome);

    @Query("SELECT a FROM Autor a WHERE a.dataNascimento <= :ano AND (a.dataMorte IS NULL OR a.dataMorte >= :ano)")
    List<Autor> findAuthorsAlive(@Param("ano") int ano);
}
