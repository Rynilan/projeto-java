package biblioteca_de_jogos.assets;

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

public class EmprestimoExporter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Exporta uma lista de empréstimos para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "emprestimos.csv".
     *
     * @param emprestimos A lista de objetos Emprestimo a ser exportada.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(List<Emprestimo> emprestimos) throws IOException {
        String fileName = "emprestimos.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            // Cabeçalho do CSV
            writer.append("ID,Data Emprestimo,Data Devolucao Real,Renovacoes,Intervalo,ID Usuario,ID Jogo,Observacoes\n");

            // Dados dos empréstimos
            for (Emprestimo emprestimo : emprestimos) {
                writer.append(String.valueOf(emprestimo.getId())).append(",");
                writer.append(emprestimo.getData().format(DATE_FORMATTER)).append(",");
                writer.append(emprestimo.getDevolucao() != null ? emprestimo.getDevolucao().format(DATE_FORMATTER) : "N/A").append(",");

                writer.append(String.valueOf(emprestimo.getRenovacoes())).append(",");
                writer.append(String.valueOf(emprestimo.getIntervalo())).append(",");
                writer.append(String.valueOf(emprestimo.getIdUsuario())).append(",");
                writer.append(String.valueOf(emprestimo.getIdJogo())).append(",");
                writer.append(emprestimo.getObservacoes() != null ? String.join(";", emprestimo.getObservacoes()) : "").append("\n");
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
                ? emprestimo.getData().format(DATE_FORMATTER) : "Não especificada")));

            documento.add(new Paragraph("Data de Devolução Real: " + (emprestimo.getDevolucao() != null 
                ? emprestimo.getDevolucao().format(DATE_FORMATTER) : "Pendente")));
            
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
