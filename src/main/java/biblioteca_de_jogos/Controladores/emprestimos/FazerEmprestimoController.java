package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FazerEmprestimoController {

    @FXML
    public TextArea consoleTextarea;

    @FXML
    public TextField idUsuarioField;

    @FXML
    public TextField idJogoField;

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarOk(ActionEvent event){
        Long IDusuario = Long.parseLong(idUsuarioField.getText().trim());
        Long Idjogo = Long.parseLong(idJogoField.getText().trim());

        Usuario usuario = ControladorDeUsuarios.getInstance().buscarUsuario(IDusuario);
        Jogo jogo = ControladorDeJogos.getInstance().buscarJogo(Idjogo);

        if (usuario == null){
            log("Usuário não encontrado com o ID " + IDusuario);
            return;
        }

        if (jogo == null){
            log("Jogo não encontrado com o ID " + Idjogo);
            return;
        }

        ControladorDeEmprestimos emprestimosControl = ControladorDeEmprestimos.getInstance();

        emprestimosControl.fazerEmprestimo(usuario, jogo);
        log("Empréstimo feito com sucesso!");


    }

}
