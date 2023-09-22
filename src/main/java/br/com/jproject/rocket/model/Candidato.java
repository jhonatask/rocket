package br.com.jproject.rocket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nomeCompleto;
    @Column(nullable = false)
    private LocalDate dataNascimento;
    @Column(unique = true, nullable = false)
    private String cpf;
    @Column(nullable = false)
    private String nomeMae;
}
