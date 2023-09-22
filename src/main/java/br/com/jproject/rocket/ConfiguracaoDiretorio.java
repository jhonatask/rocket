package br.com.jproject.rocket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ConfiguracaoDiretorio {

    @Value("${caminho.documentos.diretorio-upload}")
    private  String diretorioUploadDocumentos;

}
