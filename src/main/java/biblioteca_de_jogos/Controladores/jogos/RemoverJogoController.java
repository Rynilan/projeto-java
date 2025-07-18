package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RemoverJogoController {

    @FXML
    private TextField idJogoField;
    @FXML
    private TextArea consoleTextarea;

    private ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
    }

    @FXML
    private void handleRemover(ActionEvent event) {
        String idJogoStr = idJogoField.getText();

        if (idJogoStr.isEmpty()) {
            log("Por favor, digite o ID do jogo para remover.");
            return;
        }

        try {
            Long idJogo = Long.parseLong(idJogoStr);

            Jogo jogoParaRemover = jogosControl.buscarJogo(idJogo);

            if (jogoParaRemover != null) {
                if (jogosControl.removerJogo(jogoParaRemover)) {
                    log("Jogo '" + jogoParaRemover.getNome() + "' (ID: " + idJogo + ") removido com sucesso!");
                    idJogoField.clear();
                } else {
                    log("Não foi possível remover o jogo (ID: " + idJogo + ").");
                }
            } else {
                log("Jogo com ID " + idJogo + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            log("Erro: O ID do jogo deve ser um número válido.");
        } catch (Exception e) {
            log("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }
}
