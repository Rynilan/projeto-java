package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.JogosControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class Buscar_tempo_partidaController {
    @FXML
    private TextField tempoField;
    @FXML
    private TextArea consoleTextarea;

    private final JogosControl jogosControl = JogosControl.getInstance();

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_ok(ActionEvent event) {
        try {
            int tempo = Integer.parseInt(tempoField.getText().trim());

            List<Jogo> encontrados = jogosControl.buscarPorTempodeJogo(tempo);

            if (encontrados.isEmpty()) {
                log("Nenhum jogo encontrado com tempo de partida: " + tempo + " minutos.");
            } else {
                log("Jogos encontrados:");
                for (Jogo jogo : encontrados) {
                    log(jogo.toString());
                }
            }
        } catch (NumberFormatException e) {
            log("Insira um valor numérico válido para o tempo de partida.");
        }
    }
}

