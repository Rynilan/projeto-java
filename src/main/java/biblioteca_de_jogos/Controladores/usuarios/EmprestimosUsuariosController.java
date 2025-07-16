package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class EmprestimosUsuariosController {

    @FXML
    private TextField idUsuarioField;
    @FXML
    private Label consoleTextarea;

    private ControladorDeUsuarios usuariosControl = ControladorDeUsuarios.getInstance();

    public void log(String msg) {
        consoleTextarea.setText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    @FXML
    private void clicarOk(ActionEvent event) {
        String id = idUsuarioField.getText().trim();
        Long ID;

        if (id.isEmpty()) {
            log("Digite o ID do usuário.");
            return;
        } else{
            ID = Long.parseLong(id);
        }

        Usuario usuario = usuariosControl.buscarUsuario(ID);
        ControladorDeEmprestimos emprestimo = ControladorDeEmprestimos.getInstance();

        if (usuario == null) {
            log("Usuário não encontrado.");
            return;
        }

        List<Emprestimo> emprestimos = emprestimo.getEmprestimosDoUsuario(usuario.getId());

        if (emprestimos == null || emprestimos.isEmpty()) {
            log("O usuário não possui empréstimos.");
            return;
        }

        log("Empréstimos de " + usuario.getNome() + ":");
        for (Emprestimo e : emprestimos) {
            log(e.toString());
        }
    }

}


