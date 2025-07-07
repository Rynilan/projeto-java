package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.JogosControl;
import biblioteca_de_jogos.control.ReservasControl;
import biblioteca_de_jogos.control.UsuariosControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Reservas_emprestimosController {

    @FXML
    public TextArea ConsoleTextarea;

    @FXML
    public TextField IDusuarioField;

    @FXML
    public TextField IDjogoField;

    private final ReservasControl reservasControl = ReservasControl.getInstance();

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_ok(ActionEvent event){
        try{
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

            reservasControl.fazerReserva(IDusuario, Idjogo);
            log("Reserva feita com sucesso!");
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
