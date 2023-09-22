package br.com.jproject.rocket.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CandidaturaDTO {
    public Long id;
    public CanditatoDTO candidato;
    public List<DocumentoDTO> documentos;
    public String senha;
    public LocalDateTime dataCadastro;
    public String status;
    public boolean aceita;
    public int tentativasReprovadas;
}
