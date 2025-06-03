package br.com.alura.screensong.screensong;

import br.com.alura.screensong.screensong.principal.Principal;
import br.com.alura.screensong.screensong.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensongApplication implements CommandLineRunner {
    @Autowired
    private ArtistaRepository repositorio;

    public static void main(String[] args) {
        SpringApplication.run(ScreensongApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal(repositorio);
        principal.exibeMenu();
    }
}
