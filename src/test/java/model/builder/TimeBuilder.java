package model.builder;

import model.*;

import java.util.ArrayList;
import java.util.List;

public class TimeBuilder {
    private final List<Jogador> jogadores = new ArrayList<>();
    private final List<Partida> partidas = new ArrayList<>();
    private final List<Campeonato> campeonatos = new ArrayList<>();
    private Tecnico tecnico;
    private int vitorias;
    private int empates;
    private int derrotas;
    private String nome;

    public TimeBuilder setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
        return this;
    }

    public TimeBuilder setVitorias(int vitorias) {
        this.vitorias = vitorias;
        return this;
    }

    public TimeBuilder setEmpates(int empates) {
        this.empates = empates;
        return this;
    }

    public TimeBuilder setDerrotas(int derrotas) {
        this.derrotas = derrotas;
        return this;
    }

    public TimeBuilder setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public TimeBuilder addCampeonato(Campeonato campeonato){
        if(exist(campeonato)){
            throw new IllegalArgumentException("Campeonato Já existente.");
        }else{
            this.campeonatos.add(campeonato);
        }
        return this;
    }


    public TimeBuilder addJogador(Jogador jogador){
        if(exist(jogador)){
            throw new IllegalArgumentException("Jogador Já existente.");
        }else{
            this.jogadores.add(jogador);
        }
        return this;
    }

    public TimeBuilder addPartidas(Partida partida){
        if(exist(partida)){
            throw new IllegalArgumentException("Partida Já existente.");
        }else{
            this.partidas.add(partida);
        }
        return this;
    }


    public boolean exist(Object obj){
        if(obj instanceof Jogador){
            return jogadores.contains(obj);
        } else if (obj instanceof Campeonato) {
            return campeonatos.contains(obj);
        } else if (obj instanceof Partida) {
            return partidas.contains(obj);
        }
        return false;
    }


    public Time create(){
        return new Time(nome, tecnico, jogadores, campeonatos, vitorias, empates, derrotas,partidas);
    }

}
