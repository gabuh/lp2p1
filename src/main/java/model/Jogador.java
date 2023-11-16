package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Jogador extends Pessoa {
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

    public Jogador(LocalDate dataNascimento,String nome, String nacionalidade , String altura, int peso, int numeroCamisa, String posicao, String email) {
        super(dataNascimento, nome,nacionalidade, email);
        this.altura = altura;
        this.peso = peso;
        this.numeroCamisa = numeroCamisa;
        this.posicao = posicao;
    }

}
