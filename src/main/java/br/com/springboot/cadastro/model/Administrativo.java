package br.com.springboot.cadastro.model;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "seq_administrativo" , sequenceName = "seq_administrativo", allocationSize = 1, initialValue = 1)
@Data
public class Administrativo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_administrativo")
	private Long codigo;

	@Column(unique = true)
	private String nome;

	@Column(unique = true)
	private String usuario;

	private String senha;

	private Boolean administrativo;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name= "tab_user_roles",joinColumns = @JoinColumn(name = "user_id"))
	@Column(name = "role_id")
	private List<String> roles = new ArrayList<>();

}
	

	