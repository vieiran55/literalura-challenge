package com.alura.literatura.principal;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu(){

        var opcao = -1;
        while (opcao != 0){
            var menu =  """
                    ****************** LiterAlura *****************
                    
                    1 - buscar livro pelo titulo
                    2 - listar livros registrados
                    3 - listar autores registrados
                    4 - listar autores vivos em um determinado ano
                    5 - listar livros em um determinado idioma
                    
                    0 - sair
                    
                    ***********************************************
                    """;

        System.out.println(menu);
        opcao = leitura.nextInt();
        leitura.nextLine();

        switch (opcao){
            case 1:
                buscarLivroPorTitulo();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida");
        }
        }


    }

    private void buscarLivroPorTitulo() {
        System.out.println("Ok, Legal Legal");
    }
}
