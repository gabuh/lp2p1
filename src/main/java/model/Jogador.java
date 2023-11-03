package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Jogador extends Pessoa{
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
}
