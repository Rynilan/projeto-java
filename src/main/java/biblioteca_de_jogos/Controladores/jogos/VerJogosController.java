package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class VerJogosController {

    private final ControladorDeJogos jogos = ControladorDeJogos.getInstance();
    public final ObservableList<Jogo> listaJogos = FXCollections.observableArrayList(jogos.getJogos());
    private static VerJogosController instancia = null;

    @FXML private TableView<Jogo> tabelaJogos;
    @FXML private TableColumn<Jogo, Long> colId;
    @FXML private TableColumn<Jogo, String> colNome;
    @FXML private TableColumn<Jogo, String> colEditor;
    @FXML private TableColumn<Jogo, String> colDescricao;
    @FXML private TableColumn<Jogo, Integer> colTempoPartida;
    @FXML private TableColumn<Jogo, Integer> colMinJogadores;
    @FXML private TableColumn<Jogo, Integer> colMaxJogadores;
    @FXML private TableColumn<Jogo, Integer> colQtdCopias;
    @FXML private TableColumn<Jogo, Long> colIdCategoria;
    @FXML private TableColumn<Jogo, String> colDisponivel;

    public static VerJogosController getInstance() {
        if (VerJogosController.instancia == null) {
            VerJogosController.instancia = new VerJogosController();
        }
        return VerJogosController.instancia;
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void atualizarTabela() {
        Platform.runLater(() -> {
            listaJogos.clear();
            listaJogos.addAll(jogos.getJogos());
            if (tabelaJogos != null){
                tabelaJogos.refresh();
            }
        });
    }

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEditor.setCellValueFactory(new PropertyValueFactory<>("editor"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colTempoPartida.setCellValueFactory(new PropertyValueFactory<>("tempoPartida"));
        colMinJogadores.setCellValueFactory(new PropertyValueFactory<>("minJogadores"));
        colMaxJogadores.setCellValueFactory(new PropertyValueFactory<>("maxJogadores"));
        colQtdCopias.setCellValueFactory(new PropertyValueFactory<>("qtdCopias"));
        colIdCategoria.setCellValueFactory(new PropertyValueFactory<>("idCategoria"));
        colDisponivel.setCellValueFactory(new PropertyValueFactory<>("disponivel"));

        tabelaJogos.setItems(listaJogos);
    }
}