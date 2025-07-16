package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class BuscarCategoriaController {
    @FXML
    private TextField categoriaField;
    @FXML
    private TextArea consoleTextarea;

    private final ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_ok(ActionEvent event) {
        try {
            Long categoria = Long.parseLong(categoriaField.getText().trim());

            List<Jogo> encontrados = jogosControl.buscarPorCategoria(categoria);

            if (encontrados.isEmpty()) {
                log("Nenhum jogo encontrado na categoria: " + categoria);
            } else {
                log("Jogos encontrados:");
                for (Jogo jogo : encontrados) {
                    log(jogo.toString());
                }
            }
        } catch (NumberFormatException e) {
            log("Insira um número inteiro válido para a categoria.");
        }
    }
}


