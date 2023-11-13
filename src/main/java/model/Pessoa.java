package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@MappedSuperclass
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String idade;
    @Column
    private String nome;
    @Column
    private String nacionalidade;

    public Pessoa(String idade, String nome, String nacionalidade) {
        this.idade = idade;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
    }
}
