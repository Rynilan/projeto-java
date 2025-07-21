package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class RemoverJogoController {

    @FXML
    private TextField idJogoField;

    private ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();

    @FXML
    private void handleRemover(ActionEvent event) {
        String idJogoStr = idJogoField.getText();

        if (idJogoStr.isEmpty()) {
            MensagensAvisosErros.mostrarAviso("Digite ID","Por favor, digite o ID do jogo para remover.");
            return;
        }

        try {
            Long idJogo = Long.parseLong(idJogoStr);

            Jogo jogoParaRemover = jogosControl.buscarJogo(idJogo);

            if (jogoParaRemover != null) {
                if (jogosControl.removerJogo(jogoParaRemover)) {
                    MensagensAvisosErros.mostrarInfo("Removido com sucesso","Jogo '" + jogoParaRemover.getNome() + "' (ID: " + idJogo + ") removido com sucesso!");
                    idJogoField.clear();
                } else {
                    MensagensAvisosErros.mostrarErro("Não foi possível remover","Não foi possível remover o jogo (ID: " + idJogo + ").");
                }
            } else {
                MensagensAvisosErros.mostrarErro("Jogo não encontrado","Jogo com ID " + idJogo + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            MensagensAvisosErros.mostrarAviso("Digite ID válido","O ID do jogo deve ser um número válido.");
        } catch (Exception e) {
            MensagensAvisosErros.mostrarErro("Erro","Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }
}
