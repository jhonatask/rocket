package br.com.jproject.rocket.mapper;

import br.com.jproject.rocket.dto.CanditatoDTO;
import br.com.jproject.rocket.model.Candidato;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface CandidatoMapperDTO {
    CanditatoDTO candidatoToCandidatoDTO(Candidato entity);
    Candidato candidatoDTOtoCandidato(CanditatoDTO entity);
}
