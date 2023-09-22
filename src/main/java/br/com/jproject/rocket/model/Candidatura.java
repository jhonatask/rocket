package br.com.jproject.rocket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Candidatura {
    public static final String APROVADA = "A";
    public static final String PENDENTE = "P";
    public static final String REPROVADO = "R";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "canditato_id")
    private Candidato candidato;
    @OneToMany(mappedBy = "candidatura")
    private List<Documento> documentos;
    private String senha;
    private LocalDateTime dataCadastro;
    private boolean aceita;
    private String status;
    private int tentativasReprovadas;
}
