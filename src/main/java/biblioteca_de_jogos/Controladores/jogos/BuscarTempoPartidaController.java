package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class BuscarTempoPartidaController {

    @FXML private TextField tempoField;
    @FXML private TableView<Jogo> tabelaJogosTempoPartida;
    @FXML private TableColumn<Jogo, Integer> colId;
    @FXML private TableColumn<Jogo, String> colNome;
    @FXML private TableColumn<Jogo, String> colEditor;
    @FXML private TableColumn<Jogo, String> colDescricao;
    @FXML private TableColumn<Jogo, String> colTempo;
    @FXML private TableColumn<Jogo, Integer> colMinJogadores;
    @FXML private TableColumn<Jogo, Integer> colMaxJogadores;
    @FXML private TableColumn<Jogo, Integer> colCopias;
    @FXML private TableColumn<Jogo, Integer> colIdCategoria;
    @FXML private TableColumn<Jogo, String> colDisponivel;

    private final ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colTempo.setCellValueFactory(new PropertyValueFactory<>("tempoPartida"));
        colMinJogadores.setCellValueFactory(new PropertyValueFactory<>("minJogadores"));
        colMaxJogadores.setCellValueFactory(new PropertyValueFactory<>("maxJogadores"));
        colCopias.setCellValueFactory(new PropertyValueFactory<>("qtdCopias"));
        colIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        colDisponivel.setCellValueFactory(new PropertyValueFactory<>("disponivel"));
    }

    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    @FXML
    private void clicarOk(ActionEvent event) {
        try {
            int tempo = Integer.parseInt(tempoField.getText().trim());

            List<Jogo> encontrados = jogosControl.buscarPorTempodeJogo(tempo);

            if (encontrados.isEmpty()) {
                tabelaJogosTempoPartida.getItems().clear();
                MensagensAvisosErros.mostrarInfo("Nenhum jogo encontrado", "Nenhum jogo com tempo de partida igual a " + tempo + " minutos.");
            } else {
                tabelaJogosTempoPartida.getItems().setAll(encontrados);
            }
        } catch (NumberFormatException e) {
            MensagensAvisosErros.mostrarErro("Valor inválido", "Insira um número inteiro válido para o tempo de partida.");
        }
    }
}

