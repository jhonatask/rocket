package br.com.jproject.rocket.dto;


import br.com.jproject.rocket.model.DocumentoType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentoDTO {
    public Long id;
    public String name;
    public String imagem;
    public DocumentoType documentoType;
}
