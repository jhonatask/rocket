package br.com.jproject.rocket.services;

import br.com.jproject.rocket.core.exceptions.CandidatoNaoEncontradoException;
import br.com.jproject.rocket.core.exceptions.IdadeNaoPermitidaException;
import br.com.jproject.rocket.dto.CanditatoDTO;
import br.com.jproject.rocket.mapper.CandidatoMapperDTO;
import br.com.jproject.rocket.model.Candidato;
import br.com.jproject.rocket.repository.CandidatoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Service
public class CandidatoService {

    private final CandidatoRepository candidatoRepository;
    private final CandidatoMapperDTO candidatoMapperDTO;

    public CandidatoService(CandidatoRepository candidatoRepository, CandidatoMapperDTO candidatoMapperDTO) {
        this.candidatoRepository = candidatoRepository;
        this.candidatoMapperDTO = candidatoMapperDTO;
    }

    public CanditatoDTO createCandidato(CanditatoDTO canditato) {
        boolean idadeMinima = verificarIdadeMinima(canditato.getDataNascimento());
        if (!idadeMinima) {
            throw new IdadeNaoPermitidaException("idade mínima de 18 anos");
        }
        Candidato newcandidato = candidatoMapperDTO.candidatoDTOtoCandidato(canditato);
        newcandidato.setNomeCompleto(canditato.nomeCompleto);
        newcandidato.setCpf(canditato.cpf);
        newcandidato.setNomeMae(canditato.nomeMae);
        newcandidato.setDataNascimento(canditato.dataNascimento);
        candidatoRepository.save(newcandidato);
        return candidatoMapperDTO.candidatoToCandidatoDTO(newcandidato);
    }

    public boolean verificarIdadeMinima(LocalDate dataNascimento) {
        LocalDate dataAtual = LocalDate.now();
        int idade = Period.between(dataNascimento, dataAtual).getYears();
        int IDADEMINIMA = 18;
        return idade >= IDADEMINIMA;
    }

    public Candidato findById(Long id){
        Optional<Candidato> candidato = candidatoRepository.findById(id);
        if(candidato.isEmpty()){
            throw new CandidatoNaoEncontradoException("Candidato nao encontrado");
        }
        return candidato.get();
    }
}
