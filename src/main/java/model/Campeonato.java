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
public class Campeonato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "campeonato_time", joinColumns = @JoinColumn(name = "campeonato_id"), inverseJoinColumns = @JoinColumn(name = "time_id"))
    private Set<Time> times;


    @Column
    private String campeao;
    @Column
    private String viceCampeao;

    public void addTime(Time time){
        this.times.add(time);
        time.getCampeonatos().add(this);
    }

    public void removeTime(Time time){
        this.times.remove(time);
        time.getCampeonatos().remove(this);
    }


    public Campeonato(Set<Time> times, String campeao, String viceCampeao) {
        this.times = times;
        this.campeao = campeao;
        this.viceCampeao = viceCampeao;
    }
}
