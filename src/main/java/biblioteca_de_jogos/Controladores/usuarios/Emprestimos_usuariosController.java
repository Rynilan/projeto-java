package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.UsuariosControl;
import biblioteca_de_jogos.control.EmprestimosControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.List;

public class Emprestimos_usuariosController {

    @FXML
    private TextField IdUsuarioField;
    @FXML
    private TextArea ConsoleTextarea;

    private UsuariosControl usuariosControl = UsuariosControl.getInstance();

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }

    @FXML
    private void Clicar_ok(ActionEvent event) {
        String id = IdUsuarioField.getText().trim();
        int ID;

        if (id.isEmpty()) {
            log("Digite o ID do usuário.");
            return;
        } else{
            ID = Integer.parseInt(id);
        }

        Usuario usuario = usuariosControl.buscarUsuario(ID);
        EmprestimosControl emprestimo = EmprestimosControl.getInstance();

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


