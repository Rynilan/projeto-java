package biblioteca_de_jogos.classes;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.DocumentException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.Map;
import java.util.Comparator;
import java.util.stream.Collectors;

public class JogoPopularidadeExporter {

    /**
     * Exporta um mapa de popularidade de jogos para um arquivo CSV.
     * O arquivo será salvo na raiz do projeto com o nome "popularidade_jogos.csv".
     *
     * @param popularidadeJogos O mapa onde a chave é o objeto Jogo e o valor é a contagem de empréstimos.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarCSV(Map<Jogo, Long> popularidadeJogos) throws IOException {
        String fileName = "popularidade_jogos.csv";
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("ID,Nome do Jogo,Editora,Total de Emprestimos\n");


            popularidadeJogos.entrySet().stream()
                    .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
                    .forEach(entry -> {
                        Jogo jogo = entry.getKey();
                        Long contagem = entry.getValue();
                        try {
                            writer.append(String.valueOf(jogo.getId())).append(",");
                            writer.append(escapeCsv(jogo.getNome())).append(",");
                            writer.append(escapeCsv(jogo.getEditor())).append(",");
                            writer.append(String.valueOf(contagem)).append("\n");
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
     * @throws DocumentException Se ocorrer um erro durante a geração do PDF.
     * @throws IOException Se ocorrer um erro durante a escrita do arquivo.
     */
    public static void exportarPDF(Map<Jogo, Long> popularidadeJogos) throws DocumentException, IOException {
        String fileName = "popularidade_jogos.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(fileName));
        document.open();

        document.add(new Paragraph("Relatório de Popularidade dos Jogos"));
        document.add(new Paragraph("\n"));

        if (popularidadeJogos.isEmpty()) {
            document.add(new Paragraph("Não há dados de popularidade de jogos para o relatório."));
        } else {
            document.add(new Paragraph("Classificação dos Jogos por Popularidade (Empréstimos):"));
            document.add(new Paragraph("\n"));


            popularidadeJogos.entrySet().stream()
                    .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
                    .forEach(entry -> {
                        Jogo jogo = entry.getKey();
                        Long contagem = entry.getValue();
                        try {
                            document.add(new Paragraph("--------------------------------------------------"));
                            document.add(new Paragraph("Jogo: " + jogo.getNome() + " (ID: " + jogo.getId() + ")"));
                            document.add(new Paragraph("Editora: " + jogo.getEditor()));
                            document.add(new Paragraph("Total de Empréstimos: " + contagem));
                        } catch (DocumentException e) {
                            throw new RuntimeException("Erro ao adicionar parágrafo ao PDF de popularidade: " + e.getMessage(), e);
                        }
                    });
            document.add(new Paragraph("--------------------------------------------------"));
        }

        document.close();
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