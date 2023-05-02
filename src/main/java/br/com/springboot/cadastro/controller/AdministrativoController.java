package br.com.springboot.cadastro.controller;

import java.util.List;
import java.util.Optional;

import br.com.springboot.cadastro.model.Cadastro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.springboot.cadastro.model.Administrativo;
import br.com.springboot.cadastro.repository.AdministrativoRepository;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class AdministrativoController {
	
	@Autowired
	private AdministrativoRepository administrativoRepository;
    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "administrativo/listatodos")
    @ResponseBody
    public ResponseEntity<List<Administrativo>> listaUsuario(){
    	List<Administrativo> administrativos = administrativoRepository.findAll();
        return ResponseEntity.ok()
                .body(administrativos);
    }
    
   @PostMapping(value = "administrativo/salvar")
   @ResponseBody
   public ResponseEntity<String> salvar(@RequestBody Administrativo administrativo) {
       List<Administrativo> administrativos = administrativoRepository.findAll();
       for (Administrativo admin : administrativos) {
			if (admin.equals(administrativo)) {
               return ResponseEntity.ok()
                       .body("Cadastro Duplicado, Usuario ja existe");
            }
       }
       administrativoRepository.save(administrativo);
       return ResponseEntity.ok()
               .body("Cadastro Salvo com Sucesso!!!");
   }
   
   @DeleteMapping(value = "administrativo/{iduser}")
   @ResponseBody
    public void delete(@PathVariable Long iduser){
    	administrativoRepository.deleteById(iduser);
    }
   
   @GetMapping(value = "administrativo/{iduser}")
   @ResponseBody
    public ResponseEntity<Administrativo> buscaruserId(@PathVariable Long iduser){
    	Administrativo administrativo = administrativoRepository.findById(iduser).get();
       return ResponseEntity.ok()
               .body(administrativo);
    }
   
   @PutMapping(value = "administrativo/{iduser}")
   @ResponseBody
    public void administrativoAtualizar(@PathVariable Long iduser, @RequestBody Administrativo administrativo){
       Optional<Administrativo> administrativoBd = administrativoRepository.findById(iduser);
       if(administrativoBd.isPresent()) {
           administrativoRepository.save(administrativo);
       }
    }
   
   @GetMapping(value = "administrativo/{usuario}")
   @ResponseBody
    public ResponseEntity<Administrativo> buscarPorUsuario(@PathVariable String usuario){
    	Administrativo administrativo = administrativoRepository.findByUsuario(usuario);
        return ResponseEntity.ok()
               .body(administrativo);
    }
   
}
