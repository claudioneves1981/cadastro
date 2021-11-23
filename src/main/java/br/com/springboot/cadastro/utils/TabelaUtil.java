package br.com.springboot.cadastro.utils;

import br.com.springboot.cadastro.model.Cadastro;
import com.itextpdf.text.pdf.PdfPTable;

import java.util.List;

public class TabelaUtil {

    public static void fillTable(PdfPTable tabela, List<Cadastro> cadastro) {
        tabela.addCell("ID");
        tabela.addCell("Nome");
        tabela.addCell("Nasc");
        tabela.addCell("Idade");
        tabela.addCell("End.");
        tabela.addCell("Num.");
        tabela.addCell("Bairro");
        tabela.addCell("CEP");
        tabela.addCell("Cidade");
        tabela.addCell("Tel 1");
        tabela.addCell("Tel 2");
        tabela.addCell("Quantos Moram");
        tabela.addCell("Estuda");
        tabela.addCell("Casa Propria");
        tabela.addCell("Nis");

        // for(int i = 0; i < tabela.size(); i++){
        ///    tabela.addCell(col[i]);
        // }

        for (
                Cadastro value : cadastro) {
            tabela.addCell(String.valueOf(value.getCodigo()));
            tabela.addCell(value.getNome());
            tabela.addCell(String.valueOf(value.getDatanasc()));
            tabela.addCell(value.getIdade());
            tabela.addCell(value.getEndereco());
            tabela.addCell(value.getNumero());
            tabela.addCell(value.getBairro());
            tabela.addCell(value.getCep());
            tabela.addCell(value.getCidade());
            tabela.addCell(value.getTelefone1());
            tabela.addCell(value.getTelefone2());
            tabela.addCell(value.getQuantosmoram());
            if (value.getEstuda()) {
                tabela.addCell("Sim");
            } else {
                tabela.addCell("Não");
            }
            if (value.getCasapropria()) {
                tabela.addCell("Sim");
            } else {
                tabela.addCell("Não");
            }
            tabela.addCell(value.getNumeronis());

        }
    }
}
