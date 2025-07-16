package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class BuscarEditorController {
    @FXML
    private TextField editorField;
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
        String editor = editorField.getText().trim();

        if (editor.isEmpty()) {
            log("Por favor, preencha o campo.");
            return;
        }

        List<Jogo> encontrados = jogosControl.buscarPorEditor(editor);
        if (encontrados.isEmpty()) {
            log("Nenhum jogo encontrado com o editor: " + editor);
        } else {
            log("Jogos encontrados:");
            for (Jogo jogo : encontrados) {
                log(jogo.toString());
            }
        }
    }
}

