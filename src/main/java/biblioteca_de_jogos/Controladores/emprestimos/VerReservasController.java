package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeJogos;
import biblioteca_de_jogos.control.ControladorDeReservas;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import com.itextpdf.kernel.colors.Lab;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class VerReservasController {

    @FXML
    public TextField idUsuarioField;

    @FXML
    public TextField idJogoField;

    private final ControladorDeReservas reservasControl = ControladorDeReservas.getInstance();

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
                MensagensAvisosErros.mostrarErro("Usuário não encontrado","Usuário não encontrado com o ID " + IDusuario);
                return;
            }
            if (jogo == null){
                MensagensAvisosErros.mostrarErro("Jogo não encontrado","Jogo não encontrado com o ID " + Idjogo);
                return;
            }

            reservasControl.fazerReserva(IDusuario, Idjogo);
            MensagensAvisosErros.mostrarInfo("Reserva feita","Reserva feita com sucesso!");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
