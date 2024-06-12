package com.alura.literatura.principal;

import com.alura.literatura.model.Dados;
import com.alura.literatura.model.Livro;
import com.alura.literatura.service.ConsumoApi;
import com.alura.literatura.service.ConverteDados;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";

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
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void buscarLivroPorTitulo() throws Exception {
        Dados dados = getDadosLivro();

        if (!dados.results().isEmpty()) {
            System.out.println("Dados encontrados:");
            for (Livro livro : dados.results()) {
                System.out.println(livro);
            }
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    private Dados getDadosLivro() throws Exception {
        System.out.println("Digite o nome do livro que deseja buscar: ");
        String nomeLivro = leitura.nextLine();
        String url = ENDERECO + nomeLivro.replace(" ", "%20");
        System.out.println("URL gerada: " + url);

        String json = consumo.obterDados(url);
        System.out.println("JSON recebido: " + json);

        return conversor.obterDados(json);
    }
}
