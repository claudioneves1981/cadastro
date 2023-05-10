package br.com.springboot.cadastro.controller;

import java.io.IOException;
import java.util.List;

import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.dto.CadastroUpdateDTO;
import br.com.springboot.cadastro.service.CadastroService;
import br.com.springboot.cadastro.utils.PdfUtil;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
@RequestMapping("cadastro")
public class CadastroController {
	
	@Autowired
	private CadastroService cadastroService;

    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "/listaadmin")
    @ResponseBody
    public ResponseEntity<Iterable<CadastroDTO>> buscarTodos(){
        return ResponseEntity.ok(cadastroService.buscarTodos());
    }
    
   @PostMapping(value = "/salvarcadastro")
   @ResponseBody
    public ResponseEntity<CadastroDTO> salvar(@RequestBody CadastroDTO cadastro){
       cadastroService.inserir(cadastro);
       return ResponseEntity.ok(cadastro);
    }
   
   @DeleteMapping(value = "/{id}")
   @ResponseBody
    public ResponseEntity<Void> delete(@PathVariable Long id){
       cadastroService.deletar(id);
       return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/imprimirregistro/{id}")
    @ResponseBody
    public ResponseEntity<CadastroDTO> imprimir(@PathVariable Long id) throws DocumentException, IOException{
        CadastroDTO cadastro = cadastroService.buscarPorId(id);
        PdfUtil.imprimeRegistro(cadastro);
        return ResponseEntity.ok()
                .body(cadastro);
    }

    @GetMapping(value = "/id/{id}")
    @ResponseBody
    public ResponseEntity<CadastroDTO> buscaruserId(@PathVariable Long id) {
        CadastroDTO cadastro = cadastroService.buscarPorId(id);
        return ResponseEntity.ok()
                .body(cadastro);

    }
   
   @PutMapping(value = "/{id}")
   @ResponseBody
    public ResponseEntity<CadastroDTO> cadastroAtualizar(@PathVariable Long id, @RequestBody CadastroDTO cadastro){
       cadastroService.atualizar(id,cadastro);
       return ResponseEntity.ok(cadastro);
    }

    @GetMapping(value="/nome/{nome}")
    @ResponseBody
    public ResponseEntity<List<CadastroDTO>> buscarPorNome(@PathVariable String nome){
        List<CadastroDTO> cadastro = cadastroService.buscaPorCadastro(nome);
        return ResponseEntity.ok()
                .body(cadastro);
    }

    @GetMapping(value = "/gerandopdf/{nome}")
    @ResponseBody
    public ResponseEntity<List<CadastroDTO>> gerandoPdf(@PathVariable String nome) throws IOException, DocumentException{
       List<CadastroDTO> cadastro = cadastroService.buscaPorCadastro(nome);
       PdfUtil.gerarRelatorio(cadastro);
       return ResponseEntity.ok()
               .body(cadastro);
   }
}
