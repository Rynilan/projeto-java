package biblioteca_de_jogos.control;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.exceptions.PdfException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import java.util.List;

import biblioteca_de_jogos.classes.Emprestimo;

public class ExportadorDeEmprestimos {

    private static final DateTimeFormatter FORMATADOR_DE_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Exporta uma lista de empréstimos para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "emprestimos.csv".
     *
     * @param emprestimos A lista de objetos Emprestimo a ser exportada.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(List<Emprestimo> emprestimos) throws IOException {
        String nome = "emprestimos.csv";
        try (FileWriter escritor = new FileWriter(nome)) {
            // Cabeçalho do CSV
            escritor.append("ID,Data Emprestimo,Data Devolucao Real,Renovacoes,Intervalo,ID Usuario,ID Jogo,Observacoes\n");

            // Dados dos empréstimos
            for (Emprestimo emprestimo : emprestimos) {
                escritor.append(String.valueOf(emprestimo.getId())).append(",");
                escritor.append(emprestimo.getData().format(FORMATADOR_DE_DATA)).append(",");
                escritor.append(emprestimo.getDevolucao() != null ? emprestimo.getDevolucao().format(FORMATADOR_DE_DATA) : "N/A").append(",");

                escritor.append(String.valueOf(emprestimo.getRenovacoes())).append(",");
                escritor.append(String.valueOf(emprestimo.getIntervalo())).append(",");
                escritor.append(String.valueOf(emprestimo.getIdUsuario())).append(",");
                escritor.append(String.valueOf(emprestimo.getIdJogo())).append(",");
                escritor.append(emprestimo.getObservacoes() != null ? String.join(";", emprestimo.getObservacoes()) : "").append("\n");
            }
        }
    }

    /**
     * Exporta uma lista de empréstimos para um arquivo PDF.
     * O arquivo será salvo na raiz do projeto com o nome "emprestimos.pdf".
     *
     * @param emprestimos A lista de objetos Emprestimo a ser exportada.
     * @throws PdfException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarPDF(List<Emprestimo> emprestimos) throws IOException {
    String nome = "emprestimos.pdf";
    
    // Criando os objetos necessários para o PDF
    try (PdfWriter escritor = new PdfWriter(nome);
         PdfDocument pdf = new PdfDocument(escritor);
         Document documento = new Document(pdf)) {

        documento.add(new Paragraph("Relatório de Empréstimos"));
        documento.add(new Paragraph("\n")); // Adiciona um espaçamento

        for (Emprestimo emprestimo : emprestimos) {
            documento.add(new Paragraph("ID: " + emprestimo.getId()));
            
            // Certifique-se de que `getDataEmprestimo()` existe e funciona corretamente
            documento.add(new Paragraph("Data do Empréstimo: " + (emprestimo.getData() != null 
                ? emprestimo.getData().format(FORMATADOR_DE_DATA) : "Não especificada")));

            documento.add(new Paragraph("Data de Devolução Real: " + (emprestimo.getDevolucao() != null 
                ? emprestimo.getDevolucao().format(FORMATADOR_DE_DATA) : "Pendente")));
            
            documento.add(new Paragraph("Renovações: " + emprestimo.getRenovacoes()));
            documento.add(new Paragraph("Intervalo: " + emprestimo.getIntervalo()));
            documento.add(new Paragraph("ID Usuário: " + emprestimo.getIdUsuario()));
            documento.add(new Paragraph("ID Jogo: " + emprestimo.getIdJogo()));
            
            documento.add(new Paragraph("Observações: " + (emprestimo.getObservacoes() != null 
                ? String.join(", ", emprestimo.getObservacoes()) : "Nenhuma")));

            documento.add(new Paragraph("--------------------------------------------------")); // Separador
        }
    } catch (IOException e) {
        throw new IOException("Erro ao gerar o arquivo PDF: " + e.getMessage(), e);
    }
}
}
