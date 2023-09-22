package br.com.jproject.rocket.services;

import br.com.jproject.rocket.core.upload.UploadFileService;
import br.com.jproject.rocket.dto.CandidaturaDTO;
import br.com.jproject.rocket.model.Candidatura;
import br.com.jproject.rocket.model.Documento;
import br.com.jproject.rocket.repository.DocumentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentoService {

    private final DocumentoRepository documentoRepository;
    private final UploadFileService uploadFileService;

    public DocumentoService(DocumentoRepository documentoRepository, UploadFileService uploadFileService) {
        this.documentoRepository = documentoRepository;
        this.uploadFileService = uploadFileService;
    }
    public List<Documento> inserirDocumentos(Candidatura candidatura, CandidaturaDTO candidaturaDTO){
        return candidaturaDTO.documentos.stream().map( doc -> {
            Documento documento = new Documento();
            documento.setCandidatura(candidatura);
            documento.setDocumentoType(doc.documentoType);
            documento.setImagem(uploadFileService.uploadFile(doc.imagem, doc.name, doc.documentoType, candidatura.getId()));
            documento.setName(doc.name);
            documentoRepository.save(documento);
            return documento;
        }).collect(Collectors.toList());
    }
}
