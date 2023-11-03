package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL)
    private List<Jogador> jogadores;
    @ManyToMany(mappedBy = "times")
    private Set<Campeonato> campeonatos;
    @Column
    private int vitorias;
    @Column
    private int empates;
    @Column
    private int derrotas;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "time_partida", joinColumns = @JoinColumn(name = "time_id"), inverseJoinColumns = @JoinColumn(name = "partida_id"))
    private List<Partida> partidas;

    public Time(String nome, Tecnico tecnico, List<Jogador> jogadores, Set<Campeonato> campeonatos, int vitorias, int empates, int derrotas, List<Partida> partidas) {
        this.nome = nome;
        this.tecnico = tecnico;
        this.jogadores = jogadores;
        this.campeonatos = campeonatos;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.partidas = partidas;
    }
}
