package br.com.springboot.cadastro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.repository.CadastroRepository;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class CadastroController {
	
	@Autowired
	private CadastroRepository cadastroRepository;
    /**
     *
     * @param name the name to greet
     * @return greeting text
     */
    
    @GetMapping(value = "cadastrolistatodos")
    @ResponseBody
    public ResponseEntity<List<Cadastro>> listaUsuario(){
    	List<Cadastro> cadastros = cadastroRepository.findAll();
    	return new ResponseEntity<List<Cadastro>>(cadastros, HttpStatus.OK);
    
    }
    
   @PostMapping(value = "cadastrosalvar")
   @ResponseBody
    public ResponseEntity<Cadastro> salvar(@RequestBody Cadastro cadastro){
    	Cadastro prop = cadastroRepository.save(cadastro);
    	return new ResponseEntity<Cadastro>(prop, HttpStatus.CREATED);
    }
   
   @DeleteMapping(value = "cadastrodelete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	cadastroRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuario deletado com sucesso", HttpStatus.OK);
    }
   
   @GetMapping(value = "cadastrobuscaruserId")
   @ResponseBody
    public ResponseEntity<Cadastro> buscaruserId(@RequestParam(name = "iduser") Long iduser){
    	Cadastro cadastro = cadastroRepository.findById(iduser).get();
    	return new ResponseEntity<Cadastro>(cadastro, HttpStatus.OK);
    }
   
   @PutMapping(value = "cadastroatualizar")
   @ResponseBody
    public ResponseEntity<?> buscaruserId(@RequestBody Cadastro cadastro){
    	
	   if(cadastro.getCodigo() == null) {
		   return new ResponseEntity<String>("Codigo de Cadastro não foi informado para atualização", HttpStatus.OK);
	   }
	   
	   Cadastro user = cadastroRepository.saveAndFlush(cadastro);
    	return new ResponseEntity<Cadastro>(user, HttpStatus.OK);
    }
   
   @GetMapping(value = "cadastrobuscarPorNome")
   @ResponseBody
    public ResponseEntity<List<Cadastro>> buscarPorNome(@RequestParam(name = "name") String name){
    	List<Cadastro> cadastro = cadastroRepository.buscarPorNome(name.trim().toUpperCase());
    	return new ResponseEntity<List<Cadastro>>(cadastro, HttpStatus.OK);
    }
}