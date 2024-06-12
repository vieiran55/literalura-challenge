package com.alura.literatura.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "autores")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonProperty("birth_year")
    private Integer dataNascimento;
    @JsonProperty("death_year")
    private Integer dataMorte;

    @OneToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor() {
        // Construtor padrão
    }

    public Autor(DadosAutor dadosAutor) {
        this.name = name;
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataMorte = dadosAutor.dataMorte();
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString() {
        return "Autor{" +
                ", name='" + name + '\'' +
                ", dataNascimento=" + dataNascimento +
                ", dataMorte=" + dataMorte +
                '}';
    }
}
