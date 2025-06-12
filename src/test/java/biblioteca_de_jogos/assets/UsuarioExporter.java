package biblioteca_de_jogos.classes;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.List;

public class UsuarioExporter {

    /**
     * Exporta uma lista de usuários para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "usuarios.csv".
     *
     * @param usuarios A lista de objetos Usuario a ser exportada.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(List<Usuario> usuarios) throws IOException {
        String fileName = "usuarios.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("ID,Nome,Email,Telefone,Status\n"); // Cabeçalho do CSV
            for (Usuario usuario : usuarios) {
                writer.append(String.valueOf(usuario.getId())).append(",");
                writer.append(escapeCsv(usuario.getNome())).append(",");
                writer.append(escapeCsv(usuario.getEmail())).append(",");
                writer.append(escapeCsv(usuario.getTelefone())).append(",");
                writer.append(escapeCsv(usuario.getStatus())).append("\n");
            }
        }
    }

    /**
     * Exporta uma lista de usuários para um arquivo PDF.
     * O arquivo será salvo na raiz do projeto com o nome "usuarios.pdf".
     *
     * @param usuarios A lista de objetos Usuario a ser exportada.
     * @throws DocumentException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarPDF(List<Usuario> usuarios) throws DocumentException, IOException {
        String fileName = "usuarios.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        document.add(new Paragraph("Relatório de Usuários"));
        document.add(new Paragraph("\n"));

        if (usuarios.isEmpty()) {
            document.add(new Paragraph("Nenhum usuário encontrado para o relatório."));
        } else {
            for (Usuario usuario : usuarios) {
                document.add(new Paragraph("ID: " + usuario.getId()));
                document.add(new Paragraph("Nome: " + usuario.getNome()));
                document.add(new Paragraph("Email: " + usuario.getEmail()));
                document.add(new Paragraph("Telefone: " + usuario.getTelefone()));
                document.add(new Paragraph("Status: " + usuario.getStatus()));
                document.add(new Paragraph("--------------------------------------------------"));
            }
        }
        document.close();
    }


    private static String escapeCsv(String value) {
        if (value == null) { return ""; }
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}