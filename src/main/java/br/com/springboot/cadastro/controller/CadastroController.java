package br.com.springboot.cadastro.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import br.com.springboot.cadastro.model.Endereco;
import br.com.springboot.cadastro.repository.EnderecoRepository;
import br.com.springboot.cadastro.service.ViaCepService;
import br.com.springboot.cadastro.utils.PdfUtil;
import br.com.springboot.cadastro.utils.TabelaUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ViaCepService viaCepService;
    /**
     *
     * @return greeting text
     */
    
    @GetMapping(value = "cadastro/listatodos")
    @ResponseBody
    public ResponseEntity<List<Cadastro>> listaUsuario() {
    	List<Cadastro> cadastro = cadastroRepository.findAll();
        return ResponseEntity.ok()
                .body(cadastro);
    }
    
   @PostMapping(value = "cadastro/salvar")
   @ResponseBody
    public ResponseEntity<String> salvar(@RequestBody Cadastro cadastro){
       List<Cadastro> cadastros = cadastroRepository.findAll();
       for (Cadastro cad : cadastros) {
           if (cad.equals(cadastro)) {
               return ResponseEntity.ok()
                       .body("Cadastro Duplicado, Usuario ja existe");
           }
       }
       cadastroRepository.save(cadastro);
       return ResponseEntity.ok()
               .body("Cadastro Salvo com Sucesso!!!");
    }
   
   @DeleteMapping(value = "cadastro/{iduser}")
   @ResponseBody
    public void delete(@PathVariable Long iduser){
    	cadastroRepository.deleteById(iduser);
    }

    @GetMapping(value = "cadastro/imprimirregistro/{iduser}")
    @ResponseBody
    public ResponseEntity<Cadastro> imprimir(@PathVariable Long iduser) throws DocumentException, IOException{
        Cadastro cadastro = cadastroRepository.findById(iduser).get();
        PdfUtil.imprimeRegistro(cadastro);
        return ResponseEntity.ok()
                .body(cadastro);
    }

    @GetMapping(value = "cadastro/{iduser}")
    @ResponseBody
    public ResponseEntity<Cadastro> buscaruserId(@PathVariable Long iduser) {
        Cadastro cadastro = cadastroRepository.findById(iduser).get();
        return ResponseEntity.ok()
                .body(cadastro);

    }
   
   @PutMapping(value = "cadastro/{iduser}")
   @ResponseBody
    public void cadastroAtualizar(@PathVariable Long iduser, @RequestBody Cadastro cadastro){
       Optional<Cadastro> cadastroBd = cadastroRepository.findById(iduser);
       if(cadastroBd.isPresent()) {
           salvarClienteComCep(cadastro);
       }
    }

    @GetMapping(value="cadastro/{nome}")
    @ResponseBody
    public ResponseEntity<Cadastro> buscarPorCadastro(@PathVariable String nome){
        Cadastro cadastro = cadastroRepository.buscarPorNome(nome);
        return ResponseEntity.ok()
                .body(cadastro);
    }

    @GetMapping(value = "gerandopdf/{nome}")
    @ResponseBody
    public ResponseEntity<List<Cadastro>> gerandoPdf(@PathVariable String nome) throws IOException, DocumentException{
       List <Cadastro> cadastro = cadastroRepository.buscarPorCadastro(nome.trim().toUpperCase());
       PdfUtil.gerarRelatorio(cadastro);
       return ResponseEntity.ok()
               .body(cadastro);
   }

    private void salvarClienteComCep(Cadastro cadastro){
        String cep = cadastro.getEndereco().getCep();
        Endereco endereco =  enderecoRepository.findById(cep).orElseGet(()->{
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cadastro.setEndereco(endereco);
        cadastroRepository.save(cadastro);
    }
   
}
