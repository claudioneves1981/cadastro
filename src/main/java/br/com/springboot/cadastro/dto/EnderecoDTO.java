package br.com.springboot.cadastro.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String bairro;
    private String localidade;
    private String uf;
}
