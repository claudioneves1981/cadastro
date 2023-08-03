package br.com.springboot.cadastro.controller;


import br.com.springboot.cadastro.dto.AdministrativoDTO;
import br.com.springboot.cadastro.service.AdministrativoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.springboot.cadastro.model.Administrativo;


/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("administrativo")
@CrossOrigin(value = "http://localhost:8080")
public class AdministrativoController {

	@Autowired
	private AdministrativoService administrativoService;
    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "/listatodos")
    @ResponseBody
    public ResponseEntity<Iterable<AdministrativoDTO>> listaUsuario(){
        return ResponseEntity.ok()
                .body(administrativoService.buscarTodos());
    }
    
   @PostMapping(value = "/salvar")
   @ResponseBody
   public ResponseEntity<AdministrativoDTO> salvar(@RequestBody AdministrativoDTO administrativo) {
       administrativoService.inserir(administrativo);
       return ResponseEntity.ok(administrativo);
   }
   
   @DeleteMapping(value = "/{iduser}")
   @ResponseBody
   public ResponseEntity<Void> delete(@PathVariable Long iduser){
        administrativoService.deletar(iduser);
        return ResponseEntity.ok().build();
    }
   
   @GetMapping(value = "/id/{iduser}")
   @ResponseBody
    public ResponseEntity<AdministrativoDTO> buscaruserId(@PathVariable Long iduser){
        AdministrativoDTO administrativo = administrativoService.buscarPorId(iduser);
       return ResponseEntity.ok()
               .body(administrativo);
    }
   
   @PutMapping(value = "/{iduser}")
   @ResponseBody
    public ResponseEntity<AdministrativoDTO> administrativoAtualizar(@PathVariable Long iduser, @RequestBody AdministrativoDTO administrativo){
       administrativoService.atualizar(iduser,administrativo);
       return ResponseEntity.ok(administrativo);
    }
   
   @GetMapping(value = "/usuario/{usuario}")
   @ResponseBody
    public ResponseEntity<AdministrativoDTO> buscarPorUsuario(@PathVariable String usuario){
    	AdministrativoDTO administrativo = administrativoService.buscarPorUsuario(usuario);
        return ResponseEntity.ok()
                .body(administrativo);
    }
}
