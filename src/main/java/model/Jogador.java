package model;

import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Jogador extends Pessoa{
    @Column
    private String altura;
    @Column
    private int peso;
    @Column
    private int numeroCamisa;
    @Column
    private String posicao;
    @Column
    private int gols;
    @Column
    private int assistencias;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id")
    private Time time;


    public void setTime(Time time) {
        time.addJogador(this);
    }

    public void setTime(){
        this.time = null;
    }

    public Jogador(String nacionalidade, String idade, String nome, String altura, int peso, int numeroCamisa, String posicao, int gols, int assistencias, Time time) {
        super(nome,idade,nacionalidade);
        this.altura = altura;
        this.peso = peso;
        this.numeroCamisa = numeroCamisa;
        this.posicao = posicao;
        this.gols = gols;
        this.assistencias = assistencias;
        this.time = time;
    }
}
