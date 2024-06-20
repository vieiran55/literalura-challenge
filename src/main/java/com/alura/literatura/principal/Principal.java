package com.alura.literatura.principal;

//import com.alura.literatura.repository.LivroRepository;
import com.alura.literatura.model.*;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LivroRepository;
import com.alura.literatura.service.ConsumoApi;
import com.alura.literatura.service.ConverteDados;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String ENDERECO = "https://gutendex.com/books?search=";
    private List<Dados> dadosEmArray = new ArrayList<>();
    private List<Livro> dadosLivroList = new ArrayList<Livro>();
    
    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    @Autowired
    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }


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
                    6 - Listar TOP10 livros
                    7 - Mostrar as estatisticas de livros da base de dados

                    0 - Sair

                    ***********************************************
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1 -> buscarLivroPorTitulo();
                case 2 -> listarLivrosBuscados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosEmDeterminadoAno();
                case 5 -> listarLivrosPorIdioma();
                case 6 -> listarTop10();
                case 7 -> mostrarDbEstatisticas();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void mostrarDbEstatisticas() {
        List<Livro> books = livroRepository.findAll();

        if (!books.isEmpty()) {
            IntSummaryStatistics est = books.stream()
                    .filter(b -> b.getDownloads() > 0)
                    .collect(Collectors.summarizingInt(Livro::getDownloads));

            System.out.println("\n----- Database statistics -----");
            System.out.println("Média de downloads: " + est.getAverage());
            System.out.println("Max downloads: " + est.getMax());
            System.out.println("Min downloads: " + est.getMin());
            System.out.println("Livros registraos: " + est.getCount());
        } else {
            System.out.println("\nNão encontrado, tente novamente");
        }
    }

    private void listarTop10() {
        List<Livro> books = livroRepository.findTop10();

        if (!books.isEmpty()) {
            System.out.println("\n----- Top 10 livros baixados -----");
            books.forEach(b -> System.out.println(b.getTitulo()));
        } else {
            System.out.println("\nNão encontrado, tente novamente.");
        }
    }

    private void listarLivrosPorIdioma() {
        var option = -1;
        String language = "";

        System.out.println("\nSelecione um idioma para consulta:");
        var languagesMenu = """
               \n
               1 - Inglês
               2 - Francês
               3 - Alemão
               4 - Português
               5 - Espanhol
               """;

        System.out.println(languagesMenu);

        if (leitura.hasNextInt()) {
            option = leitura.nextInt();

            switch (option) {
                case 1:
                    language = "en";
                    break;
                case 2:
                    language = "fr";
                    break;
                case 3:
                    language = "de";
                    break;
                case 4:
                    language = "pt";
                    break;
                case 5:
                    language = "es";
                    break;
                default:
                    System.out.println("\nOpção Inválida");
            }

            System.out.println("\nLivros Registrados:");
            List<Livro> books = livroRepository.findBooksByLanguage(language);

            if (!books.isEmpty()) {
                books.forEach(System.out::println);
            } else {
                System.out.println("\nNão encontrado, tente outro idioma\"");
            }

        } else {
            System.out.println("\nEntrada invalida");
            leitura.next();
        }
    }

    private void listarAutoresVivosEmDeterminadoAno() {
        System.out.println("\nDigite o ano que deseja consultar: ");

        if (leitura.hasNextInt()) {
            var year = leitura.nextInt();
            List<Autor> authors = autorRepository.findAuthorsAlive(year);

            if (!authors.isEmpty()) {
                System.out.println("\n----- Autores registrados vivos em " + year + " -----");
                authors.forEach(System.out::println);
            } else {
                System.out.println("\nNão encontrado, tente outro ano");
            }

        } else {
            System.out.println("\nEntrada invalida");
            leitura.next();
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> authors = autorRepository.findAll();

        if(!authors.isEmpty()) {
            System.out.println("\n----- Autores Registrados -----");
            authors.forEach(System.out::println);
        } else {
            System.out.println("\nNão existem autores registrados");
        }
    }

    private void listarLivrosBuscados() {
        List<Livro> livros = livroRepository.findAll();

        if(!livros.isEmpty()) {
            System.out.println("\n----- Livros Registrados -----");
            livros.forEach(System.out::println);
        } else {
            System.out.println("\nNão existem livros registrados");
        }
    }

    @Transactional
    private void buscarLivroPorTitulo() {
//        dadosEmArray.add(obterLivroWeb());
//        System.out.println(obterLivroWeb());

        System.out.println("\nDigite o nome do livro que deseja buscar: ");
        var entradaLivro = leitura.nextLine();

        if (!entradaLivro.isBlank() && !isANumber(entradaLivro)){

            var json = consumo.obterDados(ENDERECO + entradaLivro.replace(" ", "%20"));
            Dados dados = conversor.obterDados(json, Dados.class);
            Optional<DadosLivro> livroBuscado = dados.results().stream()
                    .filter(b -> b.titulo().toLowerCase().contains(b.titulo().toLowerCase()))
                    .findFirst();
            System.out.println(livroBuscado);

            if (livroBuscado.isPresent()) {
                DadosLivro dadosLivro = livroBuscado.get();

                if (!verifiedBookExistence(dadosLivro)) {
                    Livro livro = new Livro(dadosLivro);
                    DadosAutor dadosAutor = dadosLivro.autor().get(0);
                    Optional<Autor> optionalAutor = autorRepository.findByName(dadosAutor.nome());

                    if (optionalAutor.isPresent()) {
                        Autor autorExistente = optionalAutor.get();
                        livro.setAutor(autorExistente);
                        autorExistente.getBooks().add(livro);
                        autorRepository.save(autorExistente);
                    } else {
                        Autor novoAutor = new Autor(dadosAutor);
                        livro.setAutor(novoAutor);
                        autorRepository.save(novoAutor);
                    }

                    livroRepository.save(livro);

                } else {
                    System.out.println("\nLivro ja foi adicionado no Banco de Dados");
                }

            } else {

                System.out.println("\nLivro nao encontrado");
            }
        }

    }

    private boolean verifiedBookExistence(DadosLivro dadosLivro) {
        Livro livro = new Livro(dadosLivro);
        return livroRepository.verifiedBDExistence(livro.getTitulo());
    }

    private boolean isANumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
