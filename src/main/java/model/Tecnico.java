package model;

import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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


    public void setTime(Time time){
        this.time = time;
        time.setTecnico(this);
    }

    public Tecnico(String nome, String idade, String nacionalidade, String login, String senha, int vitorias, Time time) {
        super(nome, idade,nacionalidade);
        this.login = login;
        this.senha = senha;
        this.vitorias = vitorias;
        this.time = time;
    }

    public Tecnico(String nome, String idade, String nacionalidade, String login, String senha, int vitorias) {
        super(nome, idade, nacionalidade);
        this.login = login;
        this.senha = senha;
        this.vitorias = vitorias;
    }

    public Tecnico(String nome, String idade, String nacionalidade, String login, String senha) {
        super(nome, idade, nacionalidade);
        this.login = login;
        this.senha = senha;
    }
}
