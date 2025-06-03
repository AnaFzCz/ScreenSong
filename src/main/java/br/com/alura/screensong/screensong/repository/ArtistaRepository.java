package br.com.alura.screensong.screensong.repository;

import br.com.alura.screensong.screensong.model.Artista;
import br.com.alura.screensong.screensong.model.Musicas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String nome);

   @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a.nome ILIKE %:nome%")

    List<Musicas> buscarMusicaPorArtista(String nome);
}
