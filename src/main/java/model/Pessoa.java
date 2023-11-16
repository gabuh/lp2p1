package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private LocalDate dataNascimento;
    @Column
    private String nome;
    @Column
    private String nacionalidade;
    @Column
    private String email;

    public Pessoa(LocalDate dataNascimento, String nome, String nacionalidade, String email) {
        this.dataNascimento = dataNascimento;
        this.nome = nome;
        this.nacionalidade = nacionalidade;
        this.email = email;
    }
}
