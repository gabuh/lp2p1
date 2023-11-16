package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table
public class Tecnico extends Usuario {

    @Column
    private int vitorias;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "time_id")
    private Time time;


    public void setTime(Time time){
        this.time = time;
        time.setTecnico(this);
    }

    public Tecnico(String nome, LocalDate dataNascimento, String nacionalidade, String email, String senha, String username, int vitorias, Time time) {
        super(dataNascimento, nome, nacionalidade, email, senha, username);
        this.vitorias = vitorias;
        this.time = time;
    }

    public Tecnico(String nome, LocalDate dataNascimento, String nacionalidade, String email, String senha, String username, int vitorias) {
        super(dataNascimento, nome, nacionalidade, email, senha, username);
        this.vitorias = vitorias;
    }

    public Tecnico(String nome, LocalDate dataNascimento, String nacionalidade, String email, String senha, String username) {
        super(dataNascimento, nome, nacionalidade, email, senha, username);;
    }
}
