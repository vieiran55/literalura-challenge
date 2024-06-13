package com.alura.literatura.principal;

//import com.alura.literatura.repository.LivroRepository;
import com.alura.literatura.model.Dados;
import com.alura.literatura.model.DadosAutor;
import com.alura.literatura.model.DadosLivro;
import com.alura.literatura.service.ConsumoApi;
import com.alura.literatura.service.ConverteDados;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private List<Dados> dadosEmArray = new ArrayList<>();

    //private LivroRepository repositorio;


    public void exibeMenu() throws Exception {
        int opcao = -1;
        while (opcao != 0) {
            String menu = """
                    ****************** LiterAlura *****************

                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma

                    0 - Sair

                    ***********************************************
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivrosBuscados();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void listarLivrosBuscados() {
        dadosEmArray.forEach(System.out::println);
    }

    private void buscarLivroPorTitulo() throws Exception {
        dadosEmArray.add(obterLivroWeb());
        System.out.println(obterLivroWeb());
    }

    private Dados obterLivroWeb(){
        var json = consumo.obterDados("https://gutendex.com/books?search=dom%20casmurro");
        Dados dados = conversor.obterDados(json, Dados.class);

//        DadosLivro livros = conversor.obterDados(json, DadosLivro.class);
//        System.out.println(livros);
//        DadosAutor autores = conversor.obterDados(json, DadosAutor.class);
//        System.out.println(autores);

        return dados;
    }

}
