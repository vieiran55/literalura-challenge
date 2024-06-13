package com.alura.literatura;

import com.alura.literatura.model.Dados;
import com.alura.literatura.model.DadosAutor;
import com.alura.literatura.model.DadosLivro;
import com.alura.literatura.principal.Principal;
//import com.alura.literatura.repository.LivroRepository;
import com.alura.literatura.service.ConsumoApi;
import com.alura.literatura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {


	@Autowired
	//private LivroRepository repositorio;

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		


//		ConverteDados conversor = new ConverteDados();
//
//		Dados dados = conversor.obterDados(json, Dados.class);
//		System.out.println(dados);

		Principal principal = new Principal();
		principal.exibeMenu();
//		var consumoApi = new ConsumoApi();
//		var json = consumoApi.obterDados("https://gutendex.com/books?search=dom%20casmurro");
//		Dados dados = new Dados(json);

//		System.out.println(json);
	}
}
