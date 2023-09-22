package br.com.jproject.rocket.controller;

import br.com.jproject.rocket.dto.CandidaturaDTO;
import br.com.jproject.rocket.services.CandidaturaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/candidatura")
public class CandidaturaController {

     private final CandidaturaService candidaturaService;

    public CandidaturaController(CandidaturaService candidaturaService) {
        this.candidaturaService = candidaturaService;
    }

    @PostMapping
    @ApiOperation(value = "Cadastra uma candidatura")
    public ResponseEntity<CandidaturaDTO> cadastrarCandidatura(@RequestBody CandidaturaDTO candidaturaDTO){
        CandidaturaDTO novaCandidatura = candidaturaService.criarCandidatura(candidaturaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCandidatura);
    }

    @GetMapping(path = "/listar")
    @ApiOperation(value = "Lista as candidaturas sendo possivel passar filtros")
    public ResponseEntity<Page<CandidaturaDTO>> listarCandidaturas(@RequestParam(required = false) String status, Pageable pageable){
        Page<CandidaturaDTO> candidaturas = candidaturaService.listarAllCandidaturasOrFilter(status, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(candidaturas);
    }

    @GetMapping(path = "/candidato/{id}")
    @ApiOperation(value = "Lista as candidaturas pelo id")
    public ResponseEntity<List<CandidaturaDTO>> buscarPorCandidato(@PathVariable Long id){
        List<CandidaturaDTO> candidatura = candidaturaService.findByCandidato(id);
        return ResponseEntity.status(HttpStatus.OK).body(candidatura);
    }

    @GetMapping(path = "/{id}")
    @ApiOperation(value = "Busca candidatura por id")
    public ResponseEntity<CandidaturaDTO> findById(@PathVariable Long id){
        CandidaturaDTO candidatura = candidaturaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(candidatura);
    }
    @PatchMapping(path = "/aprovacao/{id}")
    @ApiOperation(value = "Aprova ou nao as candidaturas")
    public ResponseEntity<CandidaturaDTO> aprovacaoCandidaturas(@PathVariable Long id, @RequestParam boolean decisao){
        CandidaturaDTO candidatura = candidaturaService.aprovacao(id, decisao);
        return ResponseEntity.status(HttpStatus.OK).body(candidatura);
    }

}
