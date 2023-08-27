package br.com.springboot.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdministrativoDTO {

    private Long codigo;

    private String nome;

    private String usuario;

    private String senha;

    private List<String> roles;

}
