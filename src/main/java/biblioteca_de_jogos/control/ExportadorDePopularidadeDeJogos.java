package biblioteca_de_jogos.control;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import biblioteca_de_jogos.classes.Jogo;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.exceptions.PdfException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class ExportadorDePopularidadeDeJogos {

    /**
     * Exporta um mapa de popularidade de jogos para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "popularidade_jogos.csv".
     *
     * @param popularidadeJogos O mapa onde a chave é o objeto Jogo e o valor é a contagem de empréstimos.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(Map<Jogo, Long> popularidadeJogos) throws IOException {
        String nome = "popularidade_jogos.csv";
        try (FileWriter escritor = new FileWriter(nome)) {
            escritor.append("ID,Nome do Jogo,Editora,Total de Emprestimos\n");


            popularidadeJogos.entrySet().stream()
                    .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
                    .forEach(entry -> {
                        Jogo jogo = entry.getKey();
                        Long contagem = entry.getValue();
                        try {
                            escritor.append(String.valueOf(jogo.getId())).append(",");
                            escritor.append(escapeCsv(jogo.getNome())).append(",");
                            escritor.append(escapeCsv(jogo.getEditor())).append(",");
                            escritor.append(String.valueOf(contagem)).append("\n");
                        } catch (IOException e) {
                            throw new RuntimeException("Erro ao escrever CSV de popularidade: " + e.getMessage(), e);
                        }
                    });
        }
    }

    /**
     * Exporta um mapa de popularidade de jogos para um arquivo PDF.
     * O arquivo será salvo na raiz do projeto com o nome "popularidade_jogos.pdf".
     *
     * @param popularidadeJogos O mapa onde a chave é o objeto Jogo e o valor é a contagem de empréstimos.
     * @throws PdfException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
	public static void exportarPDF(Map<Jogo, Long> popularidadeJogos) throws IOException {
		String nome = "popularidade_jogos.pdf";

		try (PdfWriter escritor = new PdfWriter(nome);
		PdfDocument pdf = new PdfDocument(escritor);
		Document documento = new Document(pdf)) {

			documento.add(new Paragraph("Relatório de Popularidade dos Jogos"));
			documento.add(new Paragraph("\n"));

			if (popularidadeJogos.isEmpty()) {
				documento.add(new Paragraph("Não há dados de popularidade de jogos para o relatório."));
			} else {
				documento.add(new Paragraph("Classificação dos Jogos por Popularidade (Empréstimos):"));
				documento.add(new Paragraph("\n"));

				popularidadeJogos.entrySet().stream()
					.sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
					.forEach(entry -> {
						Jogo jogo = entry.getKey();
						Long contagem = entry.getValue();

						documento.add(new Paragraph("--------------------------------------------------"));
						documento.add(new Paragraph("Jogo: " + jogo.getNome() + " (ID: " + jogo.getId() + ")"));
						documento.add(new Paragraph("Editora: " + jogo.getEditor()));
						documento.add(new Paragraph("Total de Empréstimos: " + contagem));
					});

				documento.add(new Paragraph("--------------------------------------------------"));
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
