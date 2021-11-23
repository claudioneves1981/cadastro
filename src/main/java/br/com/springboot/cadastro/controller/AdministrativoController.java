package br.com.springboot.cadastro.controller;

import java.util.List;

import javax.validation.Valid;
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
    
    @GetMapping(value = "administrativolistatodos")
    @ResponseBody
    public ResponseEntity<List<Administrativo>> listaUsuario(){
    	List<Administrativo> administrativos = administrativoRepository.findAll();
        return ResponseEntity.ok()
                .body(administrativos);
    }
    
   @PostMapping(value = "administrativosalvar")
   @ResponseBody
   public ResponseEntity<String> salvar(@Valid @RequestBody Administrativo administrativo) {
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
   
   @DeleteMapping(value = "administrativodelete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	administrativoRepository.deleteById(iduser);
       return ResponseEntity.ok()
               .body("Usuario deletado com sucesso");
    }
   
   @GetMapping(value = "administrativobuscaruserid")
   @ResponseBody
    public ResponseEntity<Administrativo> buscaruserId(@RequestParam(name = "iduser") Long iduser){
    	Administrativo administrativo = administrativoRepository.findById(iduser).get();

       return ResponseEntity.ok()
               .body(administrativo);
    }
   
   @PutMapping(value = "administrativoatualizar")
   @ResponseBody
    public ResponseEntity<?> buscaruserId(@RequestBody Administrativo administrativo){
    	
	   if(administrativo.getCodigo() == null) {
           return ResponseEntity.ok()
                   .body("Codigo de Administrativo não foi informado para atualização");
	   }
	   
	   Administrativo user = administrativoRepository.saveAndFlush(administrativo);

       return ResponseEntity.ok()
               .body(user);
    }
   
   @GetMapping(value = "administrativobuscarpornome")
   @ResponseBody
    public ResponseEntity<List<Administrativo>> buscarPorNome(@RequestParam(name = "name") String name){
    	List<Administrativo> administrativo = administrativoRepository.buscarPorNome(name.trim().toUpperCase());
        return ResponseEntity.ok()
               .body(administrativo);
    }
   
   @GetMapping(value = "buscarporadministrativo")
   @ResponseBody
   public ResponseEntity<List<Administrativo>> buscarPorAdministrativo(@RequestParam(name = "name") String name){
   	List<Administrativo> administrativo = administrativoRepository.buscarPorAdministrativo(name.trim().toUpperCase());
       return ResponseEntity.ok()
               .body(administrativo);
   }
   
   @PostMapping(value = "login")
   @ResponseBody
   public ResponseEntity<Boolean> loginUser(@Valid @RequestBody Administrativo administrativo) {
       List<Administrativo> administrativos = administrativoRepository.findAll();
       for (Administrativo other : administrativos) {
           if (other.getUsuario().equals(administrativo.getUsuario()) &&
                   other.getSenha().equals(administrativo.getSenha()) &&
                   other.getAdministrativo().equals(administrativo.getAdministrativo())) {
               return ResponseEntity.ok()
                       .body(true);
           }
       }
       return ResponseEntity.ok()
               .body(false);
   }
   
   @PostMapping(value = "loginadmin")
   @ResponseBody
   public ResponseEntity<Boolean> loginAdmin(@Valid @RequestBody Administrativo administrativo) {
       List<Administrativo> administrativos = administrativoRepository.findAll();
       for (Administrativo other : administrativos) {
           if (other.getUsuario().equals(administrativo.getUsuario()) &&
                   other.getSenha().equals(administrativo.getSenha()) &&
                   other.getAdministrativo().equals(administrativo.getAdministrativo())) {
               return ResponseEntity.ok()
                       .body(true);
           }
       }
       return ResponseEntity.ok()
               .body(false);
   }
   
   
}
