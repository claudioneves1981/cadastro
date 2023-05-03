package br.com.springboot.cadastro.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import br.com.springboot.cadastro.model.Cadastro;
import br.com.springboot.cadastro.service.AdministrativoService;
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
@RestController("administrativo")
public class AdministrativoController {
	
	@Autowired
	private AdministrativoService administrativoService;
    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<Iterable<Administrativo>> listaUsuario(){
        return ResponseEntity.ok()
                .body(administrativoService.buscarTodos());
    }
    
   @PostMapping(value = "/salvar")
   @ResponseBody
   public ResponseEntity<Administrativo> salvar(@RequestBody Administrativo administrativo) {
       administrativoService.inserir(administrativo);
       return ResponseEntity.ok(administrativo);
   }
   
   @DeleteMapping(value = "/{iduser}")
   @ResponseBody
   public ResponseEntity<Void> delete(@PathVariable Long iduser){
        administrativoService.deletar(iduser);
        return ResponseEntity.ok().build();
    }
   
   @GetMapping(value = "/{iduser}")
   @ResponseBody
    public ResponseEntity<Administrativo> buscaruserId(@PathVariable Long iduser){
        Administrativo administrativo = administrativoService.buscarPorId(iduser);
       return ResponseEntity.ok()
               .body(administrativo);
    }
   
   @PutMapping(value = "/{iduser}")
   @ResponseBody
    public ResponseEntity<Administrativo> administrativoAtualizar(@PathVariable Long iduser, @RequestBody Administrativo administrativo){
       administrativoService.atualizar(iduser,administrativo);
       return ResponseEntity.ok(administrativo);
    }
   
   @GetMapping(value = "/{usuario}")
   @ResponseBody
    public ResponseEntity<Administrativo> buscarPorUsuario(@PathVariable String usuario){
    	Administrativo administrativo = administrativoService.buscarPorUsuario(usuario);
        return ResponseEntity.ok()
                .body(administrativo);
    }
}
