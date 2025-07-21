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
import javafx.scene.text.Text;

import java.util.List;

public class BuscarEditorController {
    @FXML private TextField editorField;
    @FXML private TableView<Jogo> tabelaJogosEditor;
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

        colId.setMinWidth(50);
        colNome.setMinWidth(50);
        colEditor.setMinWidth(50);
        colDescricao.setMinWidth(50);
        colTempo.setMinWidth(50);
        colMinJogadores.setMinWidth(50);
        colMaxJogadores.setMinWidth(50);
        colCopias.setMinWidth(50);
        colIdCategoria.setMinWidth(50);
        colDisponivel.setMinWidth(50);
        tabelaJogosEditor.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        redimensionarColunas(tabelaJogosEditor);

    }

    private void redimensionarColunas(TableView<Jogo> tableView) {
        for (TableColumn<Jogo, ?> column : tableView.getColumns()) {
            double maxContentWidth = 0;

            Text headerText = new Text(column.getText());
            maxContentWidth = Math.max(maxContentWidth, headerText.getLayoutBounds().getWidth());
            for (Jogo item : tableView.getItems()) {
                Object cellValue = column.getCellData(item);
                if (cellValue != null) {
                    Text cellText = new Text(cellValue.toString());
                    maxContentWidth = Math.max(maxContentWidth, cellText.getLayoutBounds().getWidth());
                }
            }
            double padding = 20;
            column.setPrefWidth(Math.max(column.getMinWidth(), maxContentWidth + padding));
        }
    }

    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarOk(ActionEvent event) {
        String editor = editorField.getText().trim();

        if (editor.isEmpty()) {
            MensagensAvisosErros.mostrarAviso("Campo vazio", "Por favor, preencha o campo do editor.");
            return;
        }

        List<Jogo> encontrados = jogosControl.buscarPorEditor(editor);
        if (encontrados.isEmpty()) {
            tabelaJogosEditor.getItems().clear();
            MensagensAvisosErros.mostrarInfo("Nenhum jogo encontrado", "Nenhum jogo com o editor: " + editor);
        } else {
            tabelaJogosEditor.getItems().setAll(encontrados);
            redimensionarColunas(tabelaJogosEditor);
        }
    }
}

