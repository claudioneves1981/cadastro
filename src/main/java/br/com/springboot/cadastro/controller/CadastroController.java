package br.com.springboot.cadastro.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import br.com.springboot.cadastro.utils.TabelaUtil;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @return greeting text
     */
    
    @GetMapping(value = "cadastrolistatodos")
    @ResponseBody
    public ResponseEntity<List<Cadastro>> listaUsuario() {
    	List<Cadastro> cadastro = cadastroRepository.findAll();
        return ResponseEntity.ok()
                .body(cadastro);
    }
    
   @PostMapping(value = "cadastrosalvar")
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
   
   @DeleteMapping(value = "cadastrodelete")
   @ResponseBody
    public ResponseEntity<String> delete(@RequestParam Long iduser){
    	cadastroRepository.deleteById(iduser);
       return ResponseEntity.ok()
               .body("Usuario deletado com sucesso");
    }
   
   @GetMapping(value = "cadastrobuscaruserid")
   @ResponseBody
    public ResponseEntity<Cadastro> buscaruserId(@RequestParam(name = "iduser") Long iduser) throws DocumentException, IOException {
       Document documento = new Document();
       Cadastro cadastro = cadastroRepository.findById(iduser).get();
       //Properties properties = System.getProperties();
       String so = String.valueOf(System.getProperty("os.name"));
       String variavelDeAmbiente = "";
       String arquivo = "";

       if (so.contains("Windows")){
           variavelDeAmbiente = System.getenv("USERPROFILE");
           arquivo = variavelDeAmbiente+"/Documents/Pis_"+ cadastro.getNumeronis()+".pdf";
       }else{
           variavelDeAmbiente = System.getenv("HOME");
           arquivo = variavelDeAmbiente+"/Documentos/Pis_"+ cadastro.getNumeronis()+".pdf";
       }


       PdfWriter.getInstance(documento, new FileOutputStream(arquivo));
       documento.open();
       documento.addHeader("Content-Disposition", "attachment; filename=Pis_"+ cadastro.getNumeronis()+".pdf");
       documento.add(new Paragraph("Dados Pessoais"));
       documento.add(new Paragraph(" "));
       documento.add(new Paragraph("ID:  "+cadastro.getCodigo()));
       documento.add(new Paragraph("Nome:  "+cadastro.getNome()));
       documento.add(new Paragraph("Data Nascimento:  "+cadastro.getDatanasc()));
       documento.add(new Paragraph("Idade:  "+cadastro.getIdade()));
       documento.add(new Paragraph(" "));
       documento.add(new Paragraph("Endereco"));
       documento.add(new Paragraph("Endereco:  "+cadastro.getEndereco()+" Nº"+cadastro.getNumero()));
       documento.add(new Paragraph("Bairro:  "+cadastro.getBairro()+" CEP"+cadastro.getCep()));
       documento.add(new Paragraph("Cidade:  "+cadastro.getCidade()+" Estado"+cadastro.getEstado()));
       documento.add(new Paragraph("Telefone 1:  "+cadastro.getTelefone1()));
       documento.add(new Paragraph("Telefone 2:  "+cadastro.getTelefone2()));
       documento.add(new Paragraph(" "));
       documento.add(new Paragraph("Outros"));
       String estuda;
       if (!cadastro.getEstuda()){
          estuda = "Não";
       }else{
           estuda = "Sim";
       }
       documento.add(new Paragraph("Estuda: "+estuda));
       documento.add(new Paragraph("Quantos Moram?: "+cadastro.getQuantosmoram()));
       String casapropria;
       if (!cadastro.getCasapropria()){
           casapropria = "Não";
       }else{
           casapropria = "Sim";
       }
       documento.add(new Paragraph("Casa Propria: " +casapropria));
       documento.add(new Paragraph("Numero Nis: "+cadastro.getNumeronis()));
       documento.close();

       if (so.contains("Windows")){
           Runtime.getRuntime().exec("cmd /c \""+arquivo+"\"");
       }else{
           Runtime.getRuntime().exec("firefox "+arquivo);
       }

       return ResponseEntity.ok()
               .body(cadastro);
   }
   
   @PutMapping(value = "cadastroatualizar")
   @ResponseBody
    public ResponseEntity<String> buscaruserId(@RequestBody Cadastro cadastro){
    	
	   if(cadastro.getCodigo() == null) {
           return ResponseEntity.ok()
                   .body("Codigo de Cadastro não foi informado para atualização");
	   }
	   
	   cadastroRepository.saveAndFlush(cadastro);
       return ResponseEntity.ok()
               .body("Cadastro Atualizado com sucesso!!!");
    }

    @GetMapping(value="buscarporcadastro")
    @ResponseBody
    public ResponseEntity<List<Cadastro>> buscarPorCadastro(@RequestParam(name = "name") String name){
        List<Cadastro> cadastro = cadastroRepository.buscarPorCadastro(name.trim().toUpperCase());
        return ResponseEntity.ok()
                .body(cadastro);
    }

    @GetMapping(value = "gerandopdf")
    @ResponseBody
    public ResponseEntity<List<Cadastro>> gerandoPdf(@RequestParam(name = "name") String name) throws IOException, DocumentException{
       Document documento = new Document(PageSize.A4.rotate());
       List<Cadastro> cadastro = cadastroRepository.buscarPorNome(name.trim().toUpperCase());
       float tableWidth = 100f;
       float [] colWidths = {4,15,10,4,10,5,7,9,9,13,13,11,3,3,13};
        String so = String.valueOf(System.getProperty("os.name"));
        String variavelDeAmbiente = "";
        String arquivo = "";

        if (so.contains("Windows")){
            variavelDeAmbiente = System.getenv("USERPROFILE");
            arquivo = variavelDeAmbiente+"/Documents/relatorio.pdf";
        }else{
            variavelDeAmbiente = System.getenv("HOME");
            arquivo = variavelDeAmbiente+"/Documentos/relatorio.pdf";
        }

       PdfWriter.getInstance(documento, new FileOutputStream(arquivo));
       documento.open();
       documento.addHeader("Content-Disposition", "attachment; filename=relatorio.pdf");
       documento.add(new Paragraph("Lista de Familias:"));
       PdfPTable tabela = new PdfPTable(colWidths);
       tabela.setWidthPercentage(tableWidth);
       documento.add(new Paragraph(" "));
       TabelaUtil.fillTable(tabela, cadastro);
       documento.add(tabela);
       documento.close();

        if (so.contains("Windows")){
            Runtime.getRuntime().exec("cmd /c \""+arquivo+"\"");
        }else{
            Runtime.getRuntime().exec("firefox "+arquivo);
        }

       return ResponseEntity.ok()
               .body(cadastro);
   }
   
   @GetMapping(value = "ordenarporcidade")
   @ResponseBody
   public ResponseEntity<List<Cadastro>> ordenarPorCidade(){
   	List<Cadastro> cadastro = cadastroRepository.ordenarPorCidade();
       return ResponseEntity.ok()
               .body(cadastro);
   }
   
   @GetMapping(value = "cadastrobuscarporparametros")
   @ResponseBody
   public ResponseEntity<List<Cadastro>> buscaPorParametros(@RequestParam(name= "name") String name, @RequestParam(name = "cidade") String cidade){
   	List<Cadastro> cadastro = cadastroRepository.buscaPorParametros(name.trim().toUpperCase(), cidade);
       return ResponseEntity.ok()
               .body(cadastro);
   }
   
}
