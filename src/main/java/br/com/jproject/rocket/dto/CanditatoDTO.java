package br.com.jproject.rocket.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CanditatoDTO {

    public Long id;
    public String nomeCompleto;
    public LocalDate dataNascimento;
    public String cpf;
    public String nomeMae;
}
