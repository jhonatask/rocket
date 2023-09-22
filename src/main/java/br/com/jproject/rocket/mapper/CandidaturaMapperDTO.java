package br.com.jproject.rocket.mapper;

import br.com.jproject.rocket.dto.CandidaturaDTO;
import br.com.jproject.rocket.dto.CanditatoDTO;
import br.com.jproject.rocket.dto.DocumentoDTO;
import br.com.jproject.rocket.model.Candidato;
import br.com.jproject.rocket.model.Candidatura;
import br.com.jproject.rocket.model.Documento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public interface CandidaturaMapperDTO {

    @Mappings({
            @Mapping(target = "documentos", expression = "java(getDocumentos(entity))"),
            @Mapping(target = "candidato", expression = "java(getCandidato(entity))"),
    })
    CandidaturaDTO candidaturaToCandidaturaDTO(Candidatura entity);
    Candidatura candidaturaDTOtoCandidatura(CandidaturaDTO entity);

    default List<DocumentoDTO> getDocumentos(Candidatura entity){
        List<Documento> documentos = entity.getDocumentos();
        return documentos.stream().map(doc ->
            DocumentoDTO.builder().id(doc.getId()).documentoType(doc.getDocumentoType()).name(doc.getName()).imagem(doc.getImagem()).build()
        ).collect(Collectors.toList());
    }

    default CanditatoDTO getCandidato(Candidatura entity){
        Candidato candidato = entity.getCandidato();
        return CanditatoDTO.builder()
                .id(candidato.getId())
                .nomeCompleto(candidato.getNomeCompleto())
                .cpf(candidato.getCpf())
                .dataNascimento(candidato.getDataNascimento())
                .nomeMae(candidato.getNomeMae())
                .build();
    }
}
