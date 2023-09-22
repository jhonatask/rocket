package br.com.jproject.rocket.core.upload;

import br.com.jproject.rocket.ConfiguracaoDiretorio;
import br.com.jproject.rocket.core.exceptions.ErrorAoSalvarImagemException;
import br.com.jproject.rocket.model.DocumentoType;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Optional;


@Service
public class UploadFileService {

    private final ConfiguracaoDiretorio  configuracaoDiretorio;

    public UploadFileService(ConfiguracaoDiretorio configuracaoDiretorio) {
        this.configuracaoDiretorio = configuracaoDiretorio;
    }

    public Optional<byte[]> getImagemEspetaculo(String path){
        String fullPath = path;
        File file = new File(fullPath);
        if (file.exists() && file.isFile() && file.canRead()) {
            try {
                byte[] bytes = Files.readAllBytes(file.toPath());
                return Optional.of(bytes);
            } catch (IOException | OutOfMemoryError | SecurityException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return Optional.empty();
    }

    public String uploadFile(String file, String name,  DocumentoType tipo, Long idCandidatura){
        String[] dados = file.split(",");
        String extension = switch (dados[0]) {
            case "data:image/jpeg;base64" -> "jpeg";
            case "data:image/png;base64" -> "png";
            default ->//should write cases for more images types
                    "jpg";
        };
        String fileName = setFileName(idCandidatura, tipo, name) + extension;
        byte[] image = Base64.getDecoder().decode(dados[1]);
        try {
            return  writeFile(image, fileName, idCandidatura);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorAoSalvarImagemException("Erro ao salvar imagem");
        }
    }
    public String writeFile(byte[] image, String fileName, Long idCandidatura) throws IOException {
        String path = configuracaoDiretorio.getDiretorioUploadDocumentos() + "/" + idCandidatura;
        File customDir = new File(path);
        boolean caminho;
        if (customDir.mkdirs()) caminho = true;
        else caminho = false;
        String finalFileName = path + "/" + fileName ;
        Files.write(Paths.get(finalFileName), image, StandardOpenOption.CREATE);
        return finalFileName;
    }
    private String setFileName(Long idCandidatura, DocumentoType tipo, String name ) {
        return  idCandidatura + "-" + tipo + "-" + name.replaceAll("\\s+", "_") + ".";
    }
}
