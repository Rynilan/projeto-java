package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import static biblioteca_de_jogos.Main.print_lista;

public class PaginaUsuariosController {

    @FXML
    public Label ConsoleTextarea;

    private final ControladorDeUsuarios usuarios = ControladorDeUsuarios.getInstance();

    public void log(String msg) {
        ConsoleTextarea.setText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarAdicionarUsuario(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_adicionar_usuario");
    }

    public void clicarRemoverUsuario(ActionEvent event){
        ScreenManager.mostrarTela("pagina_remover_usuario");
    }

    public void clicarEmprestimosUsuario(ActionEvent event){
        ScreenManager.mostrarTela("pagina_emprestimos_usuario");
    }

    public void clicarVerUsuarios(ActionEvent event){
        log(print_lista(usuarios.getUsuarios()));
    }
}
