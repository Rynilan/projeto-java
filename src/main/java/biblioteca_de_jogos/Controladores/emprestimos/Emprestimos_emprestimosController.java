package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.EmprestimosControl;
import biblioteca_de_jogos.control.UsuariosControl;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.control.JogosControl;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Emprestimos_emprestimosController {

    @FXML
    public TextArea ConsoleTextarea;

    @FXML
    public TextField IDusuarioField;

    @FXML
    public TextField IDjogoField;

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_ok(ActionEvent event){
        Long IDusuario = Long.parseLong(IDusuarioField.getText().trim());
        Long Idjogo = Long.parseLong(IDjogoField.getText().trim());

        Usuario usuario = UsuariosControl.getInstance().buscarUsuario(IDusuario);
        Jogo jogo = JogosControl.getInstance().buscarJogo(Idjogo);

        if (usuario == null){
            log("Usuário não encontrado com o ID " + IDusuario);
            return;
        }

        if (jogo == null){
            log("Jogo não encontrado com o ID " + Idjogo);
            return;
        }

        EmprestimosControl emprestimosControl = EmprestimosControl.getInstance();

        emprestimosControl.fazerEmprestimo(usuario, jogo);
        log("Empréstimo feito com sucesso!");


    }

}
