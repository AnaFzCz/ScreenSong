package br.com.alura.screensong.screensong.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Generated;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;
    @Enumerated(EnumType.STRING)
    private TipoArtista tipo;

    public Artista() {

    }

    public Artista(String nome, TipoArtista tipoArtista) {
        this.nome = nome;
        this.tipo = tipoArtista;
    }

    public List<Musicas> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musicas> musicas) {
        this.musicas = musicas;
    }

    public TipoArtista getTipo() {
        return tipo;
    }

    public void setTipo(TipoArtista tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany(mappedBy = "artista", cascade= CascadeType.ALL, fetch= FetchType.EAGER)
    private List<Musicas> musicas = new ArrayList<>();

    @Override
    public String toString() {
        return "Artista='" + nome + '\'' +
                ", tipo=" + tipo +
                ", musicas=" + musicas;
    }
}
