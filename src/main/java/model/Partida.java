package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private Date data;
    @ManyToMany(mappedBy = "partidas")
    private List<Time> times;



    public void addTime(Time time){
        this.times.add(time);
    }

    public void removeTime(Time time){
        this.times.remove(time);
    }


    public String gerarResultado(int a, int b, Time t1, Time t2){
        String placar1 = Integer.toString(a);
        String placar2 = Integer.toString(b);
        String resultadoT1 = t1.getNome().concat(" - " + placar1 + " X ");
        String resultadoT2 = t2.getNome().concat(" - " + placar2);

        return (resultadoT1.concat(resultadoT2));
    }
}
