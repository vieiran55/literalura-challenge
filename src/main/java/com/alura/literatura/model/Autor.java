package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer dataNascimento;
    private Integer dataMorte;
    @OneToMany(mappedBy = "autor", fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){}

    public Autor(DadosAutor dadosAutor) {
        this.nome = dadosAutor.nome();
        this.dataMorte = dadosAutor.dataMorte();
        this.dataNascimento = dadosAutor.dataNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataMorte() {
        return dataMorte;
    }

    public void setDataMorte(Integer dataMorte) {
        this.dataMorte = dataMorte;
    }

    public List<Livro> getBooks() {
        return livros;
    }

    public void setBooks(List<Livro> livros) {
        this.livros = new ArrayList<>();
        livros.forEach(b-> {
            b.setAutor(this);
            this.livros.add(b);
        });
    }

    @Override
    public String toString() {
        List<String> livros = this.getBooks().stream().map(Livro::getTitulo).toList();
        return "Autor{" +
                "nome='" + nome + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", dataMorte=" + dataMorte +
                ", Livros=" + livros +
                '}';
    }
}
