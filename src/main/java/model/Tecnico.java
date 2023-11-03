package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Tecnico extends Pessoa {
    @Column
    private String login;
    @Column
    private String senha;
    @Column
    private int vitorias;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id")
    private Time time;
}
