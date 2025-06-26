package biblioteca_de_jogos.control;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.exceptions.PdfException;

import biblioteca_de_jogos.classes.Jogo;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JogoExporter {

    /**
     * Exporta uma lista de jogos para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "jogos.csv".
     *
     * @param jogos A lista de objetos Jogo a ser exportada.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(List<Jogo> jogos) throws IOException {
        String fileName = "jogos.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            // Cabeçalho do CSV
            writer.append("ID,Nome,Editora,Descricao,Tempo Partida,Min Jogadores,Max Jogadores,Copias\n");

            // Dados dos jogos
            for (Jogo jogo : jogos) {
                writer.append(String.valueOf(jogo.getId())).append(",");
                writer.append(escapeCsv(jogo.getNome())).append(",");
                writer.append(escapeCsv(jogo.getEditor())).append(",");
                writer.append(escapeCsv(jogo.getDescricao())).append(",");
                writer.append(String.valueOf(jogo.getTempoPartida())).append(",");
                writer.append(String.valueOf(jogo.getMinJogadores())).append(",");
                writer.append(String.valueOf(jogo.getMaxJogadores())).append(",");
                writer.append(String.valueOf(jogo.getQtdCopias())).append("\n");
            }
        }
    }

    /**
     * Exporta uma lista de jogos para um arquivo PDF.
     * O arquivo será salvo na raiz do projeto com o nome "jogos.pdf".
     *
     * @param jogos A lista de objetos Jogo a ser exportada.
     * @throws PdfException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarPDF(List<Jogo> jogos) throws IOException {
		String fileName = "jogos.pdf";

		try (PdfWriter writer = new PdfWriter(fileName);
			 PdfDocument pdf = new PdfDocument(writer);
			 Document document = new Document(pdf)) {

			document.add(new Paragraph("Relatório de Jogos"));
			document.add(new Paragraph("\n"));

			if (jogos.isEmpty()) {
				document.add(new Paragraph("Nenhum jogo encontrado para o relatório."));
			} else {
				for (Jogo jogo : jogos) {
					document.add(new Paragraph("ID: " + jogo.getId()));
					document.add(new Paragraph("Nome: " + jogo.getNome()));
					document.add(new Paragraph("Editora: " + jogo.getEditor()));
					document.add(new Paragraph("Descrição: " + jogo.getDescricao()));
					document.add(new Paragraph("Tempo de Partida: " + jogo.getTempoPartida() + " min"));
					document.add(new Paragraph("Jogadores: " + jogo.getMinJogadores() + " - " + jogo.getMaxJogadores()));
					document.add(new Paragraph("Cópias Disponíveis: " + jogo.getQtdCopias()));
					document.add(new Paragraph("--------------------------------------------------"));
					}
			}
		} catch (IOException e) {
			throw new IOException("Erro ao gerar o arquivo PDF: " + e.getMessage(), e);
		}    
	}


    private static String escapeCsv(String value) {
        if (value == null) {
            return "";
        }
        if (value.contains(",") || value.contains("\"") || value.contains("\n")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
