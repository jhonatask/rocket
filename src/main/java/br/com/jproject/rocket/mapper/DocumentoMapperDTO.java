package br.com.jproject.rocket.mapper;

import br.com.jproject.rocket.dto.DocumentoDTO;
import br.com.jproject.rocket.model.Documento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;
@Component
@Mapper(componentModel = "spring")
public interface DocumentoMapperDTO {


    DocumentoDTO documentoToDocumentoDTO(Documento entity);
    Documento documentoDTOtoDocumento(DocumentoDTO entity);


}
