package br.com.alura.screensong.screensong.principal;

import br.com.alura.screensong.screensong.ConsultaChatGPT;
import br.com.alura.screensong.screensong.model.Artista;
import br.com.alura.screensong.screensong.model.Musicas;
import br.com.alura.screensong.screensong.model.TipoArtista;
import br.com.alura.screensong.screensong.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private final ArtistaRepository repositorio;
    private Scanner scanner = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }


    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                            ************MENU**********
                    
                            1- Cadastrar artistas
                            2- Cadastrar músicas
                            3- Listar músicas
                            4- Buscar músicas por artistas
                            5- Pesquisar dados sobre um artista
                            0- Sair
                    """;
            System.out.println(menu);
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {

                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                case 4:
                    buscarMusicaPorArtista();
                case 5:
                    pesquisarDadosDoArtista();

                case 0:
                    System.out.println("Salindo do menú....");
                    break;
                default:
                    System.out.println("Opção inválida");
            }


        }

    }

    private void cadastrarArtista() {

        var cadastraraNovo = "s";
        while (cadastraraNovo.equalsIgnoreCase("s")) {
            System.out.println("Infome o nome desse artista: ");
            var nome = scanner.nextLine();
            System.out.println("Informe o tipo desse artista: ");
            var tipo = scanner.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (s/n)");
            cadastraraNovo = scanner.nextLine();
        }
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar música de que artista?");
        var nome = scanner.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);

        if (artista.isPresent()) {
            System.out.println("Informe o titulo da musica:");
            var nomeMusica = scanner.nextLine();
            Musicas musicas = new Musicas(nomeMusica);
            musicas.setArtista(artista.get());
            artista.get().getMusicas().add(musicas);
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista nao encontrado");
        }

    }

    private void listarMusicas() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void buscarMusicaPorArtista() {
        System.out.println("Buscar musicas de que artista?");
        var nome = scanner.nextLine();
        List<Musicas> musicas = repositorio.buscarMusicaPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome = scanner.nextLine();
        var resposta = ConsultaChatGPT.obterInformacao(nome);
        System.out.println(resposta.trim());
    }


}
