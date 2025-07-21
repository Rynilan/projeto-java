package biblioteca_de_jogos.Controladores.relatorios;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EmprestimoPeriodoController {

    @FXML
    private TextField dataInicialField;
    @FXML
    private TextField dataFinalField;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void clicarVoltar(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_relatorios");
    }

    public void clicarOk(ActionEvent event) {
        try {
            LocalDate inicio = LocalDate.parse(dataInicialField.getText(), formatter);
            LocalDate fim = LocalDate.parse(dataFinalField.getText(), formatter);

            List<Emprestimo> emprestimosFiltrados = ControladorDeEmprestimos.getInstance()
                    .getEmprestimos()
                    .stream()
                    .filter(e -> !e.getData().isBefore(inicio) && !e.getData().isAfter(fim))
                    .toList();

            MensagensAvisosErros.mostrarInfo("Empréstimos","Empréstimos entre " + inicio.format(formatter) + " e " + fim.format(formatter) + ":");

            if (emprestimosFiltrados.isEmpty()) {
                MensagensAvisosErros.mostrarErro("Nenhum empréstimo encontrado","Nenhum empréstimo encontrado no período.");
            } else {
                for (Emprestimo e : emprestimosFiltrados) {
                    System.out.println(e.toString());
                    ScreenManager.mostrarTela("pagina_Exportacao_relatoriosPicos_vales_emprestimos_periodos");
                }
            }
        } catch (DateTimeParseException e) {
            MensagensAvisosErros.mostrarAviso("Formatação inválida","Formato de data inválido. Use dd/MM/yyyy.");
        } catch (Exception e) {
            MensagensAvisosErros.mostrarErro("Erro","Erro inesperado: " + e.getMessage());
        }

    }
}


