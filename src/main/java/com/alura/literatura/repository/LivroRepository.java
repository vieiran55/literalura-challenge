package com.alura.literatura.repository;

import com.alura.literatura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Book;
import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    @Query("SELECT COUNT(b) > 0 FROM Livro b WHERE b.titulo LIKE %:titulo%")
    Boolean verifiedBDExistence(@Param("titulo") String titulo);

    @Query(value = "SELECT * FROM livros WHERE :idiomas = ANY (livros.idiomas)", nativeQuery = true)
    List<Livro> findBooksByLanguage(@Param("idiomas") String idiomas);

    @Query("SELECT b FROM Livro b ORDER BY b.downloads DESC LIMIT 10")
    List<Livro> findTop10();
}
