package br.com.jproject.rocket.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Documento  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "candidatura_id")
    private Candidatura candidatura;
    private String name;
    @Enumerated(EnumType.STRING)
    private DocumentoType  documentoType;
    private String imagem;
}
