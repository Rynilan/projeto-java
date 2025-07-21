package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class VerUsuariosController {

    private final ControladorDeUsuarios usuarios = ControladorDeUsuarios.getInstance();
    public final ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList(usuarios.getUsuarios());
    private static VerUsuariosController instancia = null;

    @FXML private TableView<Usuario> tabelaUsuarios;
    @FXML private TableColumn<Usuario, Long> colId;
    @FXML private TableColumn<Usuario, String> colNome;
    @FXML private TableColumn<Usuario, String> colEmail;
    @FXML private TableColumn<Usuario, String> colTelefone;
    @FXML private TableColumn<Usuario, String> colStatus;

    public static VerUsuariosController getInstance() {
        if (VerUsuariosController.instancia == null) {
            VerUsuariosController.instancia = new VerUsuariosController();
        }
        return VerUsuariosController.instancia;
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void atualizarTabela() {
        Platform.runLater(() -> {
            listaUsuarios.clear();
            listaUsuarios.addAll(usuarios.getUsuarios());
            if (tabelaUsuarios != null){
                tabelaUsuarios.refresh();
                redimensionarColunas(tabelaUsuarios);
            }
        });
    }
    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colTelefone.setCellValueFactory(new PropertyValueFactory<>("telefone"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

        colId.setMinWidth(50);
        colNome.setMinWidth(50);
        colEmail.setMinWidth(50);
        colTelefone.setMinWidth(50);
        colStatus.setMinWidth(50);
        tabelaUsuarios.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tabelaUsuarios.setItems(listaUsuarios);
        redimensionarColunas(tabelaUsuarios);
    }

    private void redimensionarColunas(TableView<Usuario> tableView) {
        for (TableColumn<Usuario, ?> column : tableView.getColumns()) {
            double maxContentWidth = 0;

            Text headerText = new Text(column.getText());
            maxContentWidth = Math.max(maxContentWidth, headerText.getLayoutBounds().getWidth());
            for (Usuario item : tableView.getItems()) {
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
}
