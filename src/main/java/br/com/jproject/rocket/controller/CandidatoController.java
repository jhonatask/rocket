package br.com.jproject.rocket.controller;

import br.com.jproject.rocket.dto.CanditatoDTO;
import br.com.jproject.rocket.services.CandidatoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/candidato")
public class CandidatoController {

    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping
    @ApiOperation(value = "Cadastra novo usuario")
    public ResponseEntity<CanditatoDTO> novoCandidato(@RequestBody CanditatoDTO canditatoDTO){
        CanditatoDTO newCandidato =  candidatoService.createCandidato(canditatoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCandidato);
    }
}
