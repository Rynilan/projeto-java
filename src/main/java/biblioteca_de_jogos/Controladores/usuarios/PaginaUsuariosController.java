package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.Controladores.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class PaginaUsuariosController {

    @FXML
    public Label consoleTextarea;

    private final ControladorDeUsuarios usuarios = ControladorDeUsuarios.getInstance();

    private final VerUsuariosController verUsuariosController = VerUsuariosController.getInstance();

    public void log(String msg) {
        consoleTextarea.setText(msg + "\n");
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
        ScreenManager.carregarTela("pagina_ver_usuarios", "/view/fxml/Usuarios/VerUsuarios.fxml");
        verUsuariosController.atualizarTabela();
        ScreenManager.mostrarTela("pagina_ver_usuarios");
    }
}
