package com.alura.literAlura;

import com.alura.literAlura.principal.Principal;
import com.alura.literAlura.repository.AutorRepository;
import com.alura.literAlura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	@Autowired
	private LibrosRepository repository;

	@Autowired
	private AutorRepository repositorioAut;
	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Principal principal= new Principal();
		Principal principal= new Principal(repository,repositorioAut);
		principal.muestraElMenu();
	}
}
