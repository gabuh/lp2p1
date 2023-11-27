package model;

import dao.impl.CampeonatoDao;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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
    private Set<Jogador> jogadores;
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
    private Set<Partida> partidas;

    public Time(String nome, Tecnico tecnico, Set<Jogador> jogadores, Set<Campeonato> campeonatos, int vitorias, int empates, int derrotas, Set<Partida> partidas) {
        this.nome = nome;
        this.tecnico = tecnico;
        this.jogadores = jogadores;
        this.campeonatos = campeonatos;
        this.vitorias = vitorias;
        this.empates = empates;
        this.derrotas = derrotas;
        this.partidas = partidas;
    }

    public void AddCampeonato(Campeonato campeonato){
        this.campeonatos.add(campeonato);
        campeonato.getTimes().add(this);
    }


    public void removeCampeonato(Campeonato campeonato){
        this.campeonatos.remove(campeonato);
        campeonato.getTimes().remove(this);
    }

    public void addPartida(Partida partida){
        this.partidas.add(partida);
        partida.getTimes().add(this);
    }


    public void removePartida(Partida partida){
        this.partidas.add(partida);
        partida.getTimes().add(this);
    }


    public void addJogador(Jogador jogador){
        this.jogadores.add(jogador);
        jogador.setTime(this);
    }

    public void removeJogador(Jogador jogador){
        this.jogadores.remove(jogador);
        jogador.setTime();
    }



    public void setTecnico(Tecnico tecnico) {
        tecnico.setTime(this);
    }



}
