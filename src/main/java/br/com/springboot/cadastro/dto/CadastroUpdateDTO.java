package br.com.springboot.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroUpdateDTO {

    private Long codigo;

    private String nome;

    private String telefone1;

    private String telefone2;

    private EnderecoDTO endereco;

    private String numero;

    private String quantosmoram;

    private Boolean estuda;

    private Boolean casapropria;

}
