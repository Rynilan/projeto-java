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

        tabelaUsuarios.setItems(listaUsuarios);
    }
}
