package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class EmprestimosUsuariosController {


    @FXML private TextField idUsuarioField;
    @FXML private TableView<Emprestimo> tabelaUsuarioEmprestimos;
    @FXML private TableColumn<Emprestimo, Integer> colId;
    @FXML private TableColumn<Emprestimo, String> colDataEmprestimo;
    @FXML private TableColumn<Emprestimo, String> colDataDevolucao;
    @FXML private TableColumn<Emprestimo, Integer> colRenovacoes;
    @FXML private TableColumn<Emprestimo, String> colIntervalo;
    @FXML private TableColumn<Emprestimo, Long> colIdUsuario;
    @FXML private TableColumn<Emprestimo, Long> colIdJogo;
    @FXML private TableColumn<Emprestimo, String> colObs;

    private final ControladorDeUsuarios usuariosControl = ControladorDeUsuarios.getInstance();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("data"));
        colDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("devolucao"));
        colRenovacoes.setCellValueFactory(new PropertyValueFactory<>("renovacoes"));
        colIntervalo.setCellValueFactory(new PropertyValueFactory<>("intervalo"));
        colIdUsuario.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colIdJogo.setCellValueFactory(new PropertyValueFactory<>("idJogo"));
        colObs.setCellValueFactory(new PropertyValueFactory<>("observacoes"));

    }

    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    @FXML
    private void clicarOk(ActionEvent event) {
        String id = idUsuarioField.getText().trim();
        Long ID;

        if (id.isEmpty()) {
            MensagensAvisosErros.mostrarAviso("Entrada inválida", "Digite o ID do usuário.");
            return;
        }
        try {
            ID = Long.parseLong(id);
        } catch (NumberFormatException e) {
            MensagensAvisosErros.mostrarErro("ID inválido", "Digite um número inteiro válido.");
            return;
        }
        Usuario usuario = usuariosControl.buscarUsuario(ID);
        if (usuario == null) {
            MensagensAvisosErros.mostrarErro("Usuário não encontrado", "Nenhum usuário com esse ID.");
            return;
        }
        List<Emprestimo> emprestimos = ControladorDeEmprestimos.getInstance().getEmprestimosDoUsuario(usuario.getId());

        if (emprestimos == null || emprestimos.isEmpty()) {
            MensagensAvisosErros.mostrarInfo("Sem empréstimos", "O usuário não possui empréstimos.");
            tabelaUsuarioEmprestimos.getItems().clear();
            return;
        }
        tabelaUsuarioEmprestimos.getItems().setAll(emprestimos);
    }
}


