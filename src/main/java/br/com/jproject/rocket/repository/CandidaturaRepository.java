package br.com.jproject.rocket.repository;

import br.com.jproject.rocket.model.Candidato;
import br.com.jproject.rocket.model.Candidatura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    Page<Candidatura> findByStatusContains(String status, Pageable pageable);
    List<Candidatura> findCandidaturaByCandidato(Candidato candidato);
}
