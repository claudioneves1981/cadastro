package br.com.springboot.cadastro.utils;

import br.com.springboot.cadastro.dto.CadastroDTO;
import br.com.springboot.cadastro.model.Cadastro;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfUtil {

    public static void imprimeRegistro(CadastroDTO cadastro) throws IOException, DocumentException {
        Document documento = new Document();

        //Properties properties = System.getProperties();
        String so = String.valueOf(System.getProperty("os.name"));
        String variavelDeAmbiente = "";
        String arquivo = "";

        if (so.contains("Windows")) {
            variavelDeAmbiente = System.getenv("USERPROFILE");
            arquivo = variavelDeAmbiente + "/Documents/Pis_" + cadastro.getNumeronis() + ".pdf";
        } else {
            variavelDeAmbiente = System.getenv("HOME");
            arquivo = variavelDeAmbiente + "/Documentos/Pis_" + cadastro.getNumeronis() + ".pdf";
        }


        PdfWriter.getInstance(documento, new FileOutputStream(arquivo));
        documento.open();
        documento.addHeader("Content-Disposition", "attachment; filename=Pis_" + cadastro.getNumeronis() + ".pdf");
        documento.add(new Paragraph("Dados Pessoais"));
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("ID:  " + cadastro.getCodigo()));
        documento.add(new Paragraph("Nome:  " + cadastro.getNome()));
        documento.add(new Paragraph("Data Nascimento:  " + cadastro.getDatanasc()));
        documento.add(new Paragraph("Idade:  " + cadastro.getIdade()));
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Endereco"));
        documento.add(new Paragraph("Endereco:  " + cadastro.getEndereco().getLogradouro() + " Nº" + cadastro.getNumero()));
        documento.add(new Paragraph("Bairro:  " + cadastro.getEndereco().getBairro() + " CEP" + cadastro.getEndereco().getCep()));
        documento.add(new Paragraph("Cidade:  " + cadastro.getEndereco().getLocalidade() + " Estado" + cadastro.getEndereco().getUf()));
        documento.add(new Paragraph("Telefone 1:  " + cadastro.getTelefone1()));
        documento.add(new Paragraph("Telefone 2:  " + cadastro.getTelefone2()));
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("Outros"));
        String estuda;
        if (!cadastro.getEstuda()) {
            estuda = "Não";
        } else {
            estuda = "Sim";
        }
        documento.add(new Paragraph("Estuda: " + estuda));
        documento.add(new Paragraph("Quantos Moram?: " + cadastro.getQuantosmoram()));
        String casapropria;
        if (!cadastro.getCasapropria()) {
            casapropria = "Não";
        } else {
            casapropria = "Sim";
        }
        documento.add(new Paragraph("Casa Propria: " + casapropria));
        documento.add(new Paragraph("Numero Nis: " + cadastro.getNumeronis()));
        documento.close();

        if (so.contains("Windows")) {
            Runtime.getRuntime().exec("cmd /c \"" + arquivo + "\"");
        } else {
            Runtime.getRuntime().exec("firefox " + arquivo);
        }

    }

    public static void gerarRelatorio(List<CadastroDTO> cadastro) throws IOException, DocumentException {

        Document documento = new Document(PageSize.A4.rotate());
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

    }

}
