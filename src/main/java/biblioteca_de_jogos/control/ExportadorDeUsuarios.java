package biblioteca_de_jogos.control;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.exceptions.PdfException;

import biblioteca_de_jogos.classes.Usuario;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportadorDeUsuarios {

    /**
     * Exporta uma lista de usuários para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "usuarios.csv".
     *
     * @param usuarios A lista de objetos Usuario a ser exportada.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(List<Usuario> usuarios) throws IOException {
        String nome = "usuarios.csv";
        try (FileWriter escritor = new FileWriter(nome)) {
            escritor.append("ID,Nome,Email,Telefone,Status\n"); // Cabeçalho do CSV
            for (Usuario usuario : usuarios) {
                escritor.append(String.valueOf(usuario.getId())).append(",");
                escritor.append(escapeCsv(usuario.getNome())).append(",");
                escritor.append(escapeCsv(usuario.getEmail())).append(",");
                escritor.append(escapeCsv(usuario.getTelefone())).append(",");
                escritor.append(escapeCsv(usuario.getStatus())).append("\n");
            }
        }
    }

    /**
     * Exporta uma lista de usuários para um arquivo PDF.
     * O arquivo será salvo na raiz do projeto com o nome "usuarios.pdf".
     *
     * @param usuarios A lista de objetos Usuario a ser exportada.
     * @throws PdfException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
	public static void exportarPDF(List<Usuario> usuarios) throws IOException {
		String nome = "usuarios.pdf";

		try (PdfWriter escritor = new PdfWriter(nome);
			 PdfDocument pdf = new PdfDocument(escritor);
			 Document documento = new Document(pdf)) {

			documento.add(new Paragraph("Relatório de Usuários"));
			documento.add(new Paragraph("\n"));

			if (usuarios.isEmpty()) {
				documento.add(new Paragraph("Nenhum usuário encontrado para o relatório."));
			} else {
				for (Usuario usuario : usuarios) {
					documento.add(new Paragraph("ID: " + usuario.getId()));
					documento.add(new Paragraph("Nome: " + usuario.getNome()));
					documento.add(new Paragraph("Email: " + usuario.getEmail()));
					documento.add(new Paragraph("Telefone: " + usuario.getTelefone()));
					documento.add(new Paragraph("Status: " + usuario.getStatus()));
					documento.add(new Paragraph("--------------------------------------------------"));
				}
			}
		} catch (IOException e) {
			throw new IOException("Erro ao gerar o arquivo PDF: " + e.getMessage(), e);
		}
	}

    private static String escapeCsv(String value) {
        if (value == null) { return ""; }
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
