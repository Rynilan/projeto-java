package biblioteca_de_jogos.control;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.exceptions.PdfException;

import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class PicosValesEmprestimoExporter {

    private static final DateTimeFormatter MONTH_YEAR_FORMATTER = DateTimeFormatter.ofPattern("MM/yyyy");

    /**
     * Exporta um mapa de contagem de empréstimos por mês/ano para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "picos_vales_emprestimo.csv".
     *
     * @param contagemPorMes O mapa onde a chave é o YearMonth e o valor é a contagem de empréstimos.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(Map<YearMonth, Long> contagemPorMes) throws IOException {
        String fileName = "picos_vales_emprestimo.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Mes/Ano,Total de Emprestimos\n"); // Cabeçalho do CSV

            // Ordena as entradas por mês/ano antes de escrever no CSV
            contagemPorMes.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        try {
                            writer.append(entry.getKey().format(MONTH_YEAR_FORMATTER)).append(",");
                            writer.append(String.valueOf(entry.getValue())).append("\n");
                        } catch (IOException e) {
                            // Trata a exceção dentro do lambda se necessário, ou propaga
                            throw new RuntimeException("Erro ao escrever CSV: " + e.getMessage(), e);
                        }
                    });
        }
    }

    /**
     * Exporta um mapa de contagem de empréstimos por mês/ano para um arquivo PDF.
     * O arquivo será salvo na raiz do projeto com o nome "picos_vales_emprestimo.pdf".
     *
     * @param contagemPorMes O mapa onde a chave é o YearMonth e o valor é a contagem de empréstimos.
     * @throws PdfException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
	public static void exportarPDF(Map<YearMonth, Long> contagemPorMes) throws IOException {
		String fileName = "picos_vales_emprestimo.pdf";

		try (PdfWriter writer = new PdfWriter(fileName);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf)) {

			document.add(new Paragraph("Relatório de Picos e Vales de Empréstimos"));
			document.add(new Paragraph("\n"));

			if (contagemPorMes.isEmpty()) {
				document.add(new Paragraph("Não há dados de empréstimos para analisar picos e vales."));
			} else {
				// Encontrar o pico e o vale
				Map.Entry<YearMonth, Long> pico = contagemPorMes.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.orElse(null);

				Map.Entry<YearMonth, Long> vale = contagemPorMes.entrySet().stream()
				.min(Map.Entry.comparingByValue())
				.orElse(null);

				document.add(new Paragraph("Contagem de empréstimos por mês/ano:"));

				// Imprime os dados ordenados no PDF
				contagemPorMes.entrySet().stream()
					.sorted(Map.Entry.comparingByKey())
					.forEach(entry -> 
						document.add(new Paragraph("  " + entry.getKey().format(MONTH_YEAR_FORMATTER) + ": " + entry.getValue() + " empréstimos"))
					);

				document.add(new Paragraph("\n")); // Espaçamento

				if (pico != null) {
					document.add(new Paragraph("Pico de Empréstimos: " + pico.getKey().format(MONTH_YEAR_FORMATTER) + " com " + pico.getValue() + " empréstimos."));
				}
				if (vale != null) {
					document.add(new Paragraph("Vale de Empréstimos: " + vale.getKey().format(MONTH_YEAR_FORMATTER) + " com " + vale.getValue() + " empréstimos."));
				}
			}
		} catch (IOException e) {
			throw new IOException("Erro ao gerar o arquivo PDF: " + e.getMessage(), e);
		}
	}
}
