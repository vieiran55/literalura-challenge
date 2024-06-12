package com.alura.literatura;

import com.alura.literatura.model.Dados;
import com.alura.literatura.principal.Principal;
import com.alura.literatura.service.ConsumoApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
//		var consumoApi = new ConsumoApi();
//		var json = consumoApi.obterDados("https://gutendex.com/books?search=dom%20casmurro");
//		Dados dados = new Dados(json);

//		System.out.println(json);
	}
}
