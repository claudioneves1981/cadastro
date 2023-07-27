package br.com.springboot.cadastro.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Data
@Builder
public class AdministrativoDTO {

    private Long codigo;

    private String nome;

    private String usuario;

    private String senha;

    private List<String> roles;

}
