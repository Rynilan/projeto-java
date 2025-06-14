import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;

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
                writer.append(emprestimo.getDataEmprestimo().format(DATE_FORMATTER)).append(",");
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
     * @throws DocumentException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarPDF(List<Emprestimo> emprestimos) throws DocumentException, IOException {
        String fileName = "emprestimos.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        document.add(new Paragraph("Relatório de Empréstimos"));
        document.add(new Paragraph("\n")); // Adiciona um espaçamento

        for (Emprestimo emprestimo : emprestimos) {
            document.add(new Paragraph("ID: " + emprestimo.getId()));
            document.add(new Paragraph("Data do Empréstimo: " + emprestimo.getDataEmprestimo().format(DATE_FORMATTER)));
            document.add(new Paragraph("Data de Devolução Real: " + (emprestimo.getDevolucao() != null ? emprestimo.getDevolucao().format(DATE_FORMATTER) : "Pendente")));

            document.add(new Paragraph("Renovações: " + emprestimo.getRenovacoes()));
            document.add(new Paragraph("Intervalo: " + emprestimo.getIntervalo()));
            document.add(new Paragraph("ID Usuário: " + emprestimo.getIdUsuario()));
            document.add(new Paragraph("ID Jogo: " + emprestimo.getIdJogo()));
            document.add(new Paragraph("Observações: " + (emprestimo.getObservacoes() != null ? String.join(", ", emprestimo.getObservacoes()) : "Nenhuma")));
            document.add(new Paragraph("--------------------------------------------------")); // Separador
        }

        document.close();
    }
}