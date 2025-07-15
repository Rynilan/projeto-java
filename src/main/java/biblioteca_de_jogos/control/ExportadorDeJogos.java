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

public class ExportadorDeJogos {

    /**
     * Exporta uma lista de jogos para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "jogos.csv".
     *
     * @param jogos A lista de objetos Jogo a ser exportada.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(List<Jogo> jogos) throws IOException {
        String nome = "jogos.csv";
        try (FileWriter escritor = new FileWriter(nome)) {
            // Cabeçalho do CSV
            escritor.append("ID,Nome,Editora,Descricao,Tempo Partida,Min Jogadores,Max Jogadores,Copias\n");

            // Dados dos jogos
            for (Jogo jogo : jogos) {
                escritor.append(String.valueOf(jogo.getId())).append(",");
                escritor.append(escapeCsv(jogo.getNome())).append(",");
                escritor.append(escapeCsv(jogo.getEditor())).append(",");
                escritor.append(escapeCsv(jogo.getDescricao())).append(",");
                escritor.append(String.valueOf(jogo.getTempoPartida())).append(",");
                escritor.append(String.valueOf(jogo.getMinJogadores())).append(",");
                escritor.append(String.valueOf(jogo.getMaxJogadores())).append(",");
                escritor.append(String.valueOf(jogo.getQtdCopias())).append("\n");
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
		String nome = "jogos.pdf";

		try (PdfWriter escritor = new PdfWriter(nome);
			 PdfDocument pdf = new PdfDocument(escritor);
			 Document documento = new Document(pdf)) {

			documento.add(new Paragraph("Relatório de Jogos"));
			documento.add(new Paragraph("\n"));

			if (jogos.isEmpty()) {
				documento.add(new Paragraph("Nenhum jogo encontrado para o relatório."));
			} else {
				for (Jogo jogo : jogos) {
					documento.add(new Paragraph("ID: " + jogo.getId()));
					documento.add(new Paragraph("Nome: " + jogo.getNome()));
					documento.add(new Paragraph("Editora: " + jogo.getEditor()));
					documento.add(new Paragraph("Descrição: " + jogo.getDescricao()));
					documento.add(new Paragraph("Tempo de Partida: " + jogo.getTempoPartida() + " min"));
					documento.add(new Paragraph("Jogadores: " + jogo.getMinJogadores() + " - " + jogo.getMaxJogadores()));
					documento.add(new Paragraph("Cópias Disponíveis: " + jogo.getQtdCopias()));
					documento.add(new Paragraph("--------------------------------------------------"));
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
