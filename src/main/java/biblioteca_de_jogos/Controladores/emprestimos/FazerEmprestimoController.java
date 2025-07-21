package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FazerEmprestimoController {

    @FXML
    public TextField idUsuarioField;

    @FXML
    public TextField idJogoField;


    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarOk(ActionEvent event){
        Long IDusuario = Long.parseLong(idUsuarioField.getText().trim());
        Long Idjogo = Long.parseLong(idJogoField.getText().trim());

        Usuario usuario = ControladorDeUsuarios.getInstance().buscarUsuario(IDusuario);
        Jogo jogo = ControladorDeJogos.getInstance().buscarJogo(Idjogo);

        if (usuario == null){
            MensagensAvisosErros.mostrarErro("Usuário não encontrado","Usuário não encontrado com o ID " + IDusuario);
            return;
        }

        if (jogo == null){
            MensagensAvisosErros.mostrarErro("Jogo não encontrado","Jogo não encontrado com o ID\n " + Idjogo);
            return;
        }

        ControladorDeEmprestimos emprestimosControl = ControladorDeEmprestimos.getInstance();

        emprestimosControl.fazerEmprestimo(usuario, jogo);
        MensagensAvisosErros.mostrarInfo("Empréstimo feito","Empréstimo feito com sucesso!");


    }

}
