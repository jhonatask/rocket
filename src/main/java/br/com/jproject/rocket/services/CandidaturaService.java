package br.com.jproject.rocket.services;

import br.com.jproject.rocket.core.exceptions.CandidaturaNaoEncontradaException;
import br.com.jproject.rocket.dto.CandidaturaDTO;
import br.com.jproject.rocket.mapper.CandidaturaMapperDTO;
import br.com.jproject.rocket.model.Candidato;
import br.com.jproject.rocket.model.Candidatura;
import br.com.jproject.rocket.repository.CandidaturaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidaturaService {

    private final CandidaturaRepository candidaturaRepository;
    private final CandidaturaMapperDTO candidaturaMapperDTO;
    private final CandidatoService candidatoService;

    private final DocumentoService documentoService;


    public CandidaturaService(CandidaturaRepository candidaturaRepository, CandidaturaMapperDTO candidaturaMapperDTO, CandidatoService candidatoService, DocumentoService documentoService) {
        this.candidaturaRepository = candidaturaRepository;
        this.candidaturaMapperDTO = candidaturaMapperDTO;
        this.candidatoService = candidatoService;
        this.documentoService = documentoService;
    }

    public CandidaturaDTO criarCandidatura(CandidaturaDTO candidaturaDTO) {
        Candidato candidato = candidatoService.findById(candidaturaDTO.candidato.id);
        Candidatura newCandidatura = candidaturaMapperDTO.candidaturaDTOtoCandidatura(candidaturaDTO);
        newCandidatura.setCandidato(candidato);
        newCandidatura.setSenha(candidaturaDTO.senha);
        newCandidatura.setStatus(Candidatura.PENDENTE);
        newCandidatura.setDataCadastro(LocalDateTime.now());
        newCandidatura.setTentativasReprovadas(0);
        candidaturaRepository.save(newCandidatura);
        newCandidatura.setDocumentos(documentoService.inserirDocumentos(newCandidatura, candidaturaDTO));
        candidaturaRepository.save(newCandidatura);
        return candidaturaMapperDTO.candidaturaToCandidaturaDTO(newCandidatura);
    }

    public Page<CandidaturaDTO> listarAllCandidaturasOrFilter(String status, Pageable pageable) {
        Page<Candidatura> result;
        result = ( status == null ? candidaturaRepository.findAll(pageable):  candidaturaRepository.findByStatusContains(status, pageable));
        return result.map(candidaturaMapperDTO::candidaturaToCandidaturaDTO);
    }

    public CandidaturaDTO aprovacao(Long id, Boolean decisao) {
        Optional<Candidatura> candidatura = candidaturaRepository.findById(id);
        if (candidatura.isEmpty()){
            throw new CandidaturaNaoEncontradaException("Candidatura nao encontrada");
        }
        Candidatura replaceCandidatura = candidatura.get();
        replaceCandidatura.setAceita(decisao);
        if (!decisao){
            replaceCandidatura.setTentativasReprovadas(1);
        }
        replaceCandidatura.setStatus( decisao ? Candidatura.APROVADA : Candidatura.REPROVADO);
        candidaturaRepository.save(replaceCandidatura);
        return candidaturaMapperDTO.candidaturaToCandidaturaDTO(replaceCandidatura);
    }

    public CandidaturaDTO findById(Long id) {
        Optional<Candidatura> candidatura = candidaturaRepository.findById(id);
        if (candidatura.isEmpty()){
            throw new CandidaturaNaoEncontradaException("Candidatura nao encontrada");
        }
        Candidatura result = candidatura.get();
        return candidaturaMapperDTO.candidaturaToCandidaturaDTO(result);
    }

    public List<CandidaturaDTO> findByCandidato(Long id) {
        Candidato candidato = candidatoService.findById(id);
        List<Candidatura> candidaturas = candidaturaRepository.findCandidaturaByCandidato(candidato);
        if (candidaturas == null){
            throw new CandidaturaNaoEncontradaException("Candidatura nao encontrada");
        }
        return  candidaturas.stream().map(candidaturaMapperDTO::candidaturaToCandidaturaDTO).collect(Collectors.toList());
    }
}

