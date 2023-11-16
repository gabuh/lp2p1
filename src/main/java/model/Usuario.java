package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Table
@MappedSuperclass
public class Usuario extends Pessoa{
    @Column
    private String senha;
    @Column
    private String username;

    public Usuario(LocalDate dataNascimento, String nome, String nacionalidade, String email, String senha, String username) {
        super(dataNascimento, nome, nacionalidade, email);
        this.senha = senha;
        this.username = username;
    }
}
