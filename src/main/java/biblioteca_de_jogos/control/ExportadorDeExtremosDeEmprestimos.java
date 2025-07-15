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

public class ExportadorDeExtremosDeEmprestimos {

    private static final DateTimeFormatter FORMATADOR_DE_MES_E_ANO = DateTimeFormatter.ofPattern("MM/yyyy");

    /**
     * Exporta um mapa de contagem de empréstimos por mês/ano para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "picos_vales_emprestimo.csv".
     *
     * @param contagemPorMes O mapa onde a chave é o YearMonth e o valor é a contagem de empréstimos.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(Map<YearMonth, Long> contagemPorMes) throws IOException {
        String nome = "picos_vales_emprestimo.csv";
        try (FileWriter escritor = new FileWriter(nome)) {
            escritor.append("Mes/Ano,Total de Emprestimos\n"); // Cabeçalho do CSV

            // Ordena as entradas por mês/ano antes de escrever no CSV
            contagemPorMes.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        try {
                            escritor.append(entry.getKey().format(FORMATADOR_DE_MES_E_ANO)).append(",");
                            escritor.append(String.valueOf(entry.getValue())).append("\n");
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
		String nome = "picos_vales_emprestimo.pdf";

		try (PdfWriter escritor = new PdfWriter(nome);
		PdfDocument pdf = new PdfDocument(escritor);
		Document documento = new Document(pdf)) {

			documento.add(new Paragraph("Relatório de Picos e Vales de Empréstimos"));
			documento.add(new Paragraph("\n"));

			if (contagemPorMes.isEmpty()) {
				documento.add(new Paragraph("Não há dados de empréstimos para analisar picos e vales."));
			} else {
				// Encontrar o pico e o vale
				Map.Entry<YearMonth, Long> pico = contagemPorMes.entrySet().stream()
				.max(Map.Entry.comparingByValue())
				.orElse(null);

				Map.Entry<YearMonth, Long> vale = contagemPorMes.entrySet().stream()
				.min(Map.Entry.comparingByValue())
				.orElse(null);

				documento.add(new Paragraph("Contagem de empréstimos por mês/ano:"));

				// Imprime os dados ordenados no PDF
				contagemPorMes.entrySet().stream()
					.sorted(Map.Entry.comparingByKey())
					.forEach(entry -> 
						documento.add(new Paragraph("  " + entry.getKey().format(FORMATADOR_DE_MES_E_ANO) + ": " + entry.getValue() + " empréstimos"))
					);

				documento.add(new Paragraph("\n")); // Espaçamento

				if (pico != null) {
					documento.add(new Paragraph("Pico de Empréstimos: " + pico.getKey().format(FORMATADOR_DE_MES_E_ANO) + " com " + pico.getValue() + " empréstimos."));
				}
				if (vale != null) {
					documento.add(new Paragraph("Vale de Empréstimos: " + vale.getKey().format(FORMATADOR_DE_MES_E_ANO) + " com " + vale.getValue() + " empréstimos."));
				}
			}
		} catch (IOException e) {
			throw new IOException("Erro ao gerar o arquivo PDF: " + e.getMessage(), e);
		}
	}
}
