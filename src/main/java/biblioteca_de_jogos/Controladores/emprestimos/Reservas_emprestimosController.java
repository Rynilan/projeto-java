package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeJogos;
import biblioteca_de_jogos.control.ControladorDeReservas;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Reservas_emprestimosController {

    @FXML
    public TextArea consoleTextarea;

    @FXML
    public TextField idUsuarioField;

    @FXML
    public TextField idJogoField;

    private final ControladorDeReservas reservasControl = ControladorDeReservas.getInstance();

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarOk(ActionEvent event){
        try{
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

            reservasControl.fazerReserva(IDusuario, Idjogo);
            log("Reserva feita com sucesso!");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
