package br.com.springboot.cadastro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seq_administrativo" , sequenceName = "seq_administrativo", allocationSize = 1)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Administrativo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_administrativo")
	private Long codigo;

	private String nome;

	private String usuario;

	private String senha;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name= "tab_user_roles",joinColumns = @JoinColumn(name = "codigo"))
	@Column(name = "role_id")
	private List<String> roles = new ArrayList<>();
}
	

	