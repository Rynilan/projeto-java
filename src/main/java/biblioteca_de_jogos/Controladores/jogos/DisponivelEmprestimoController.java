package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

public class DisponivelEmprestimoController {

    @FXML
    private TextArea consoleTextarea;

    private final ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarOk(ActionEvent event) {
        List<Jogo> disponiveis = jogosControl.jogosDisponiveis();

        if (disponiveis.isEmpty()) {
            log("Nenhum jogo disponível para empréstimo.");
        } else {
            log("Jogos disponíveis para empréstimo:");
            for (Jogo jogo : disponiveis) {
                log(jogo.toString());
            }
        }
    }
}

