package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.control.UsuariosControl;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import static biblioteca_de_jogos.Main.print_lista;

public class Pagina_usuariosController {

    @FXML
    public TextArea ConsoleTextarea;

    private final UsuariosControl usuarios = UsuariosControl.getInstance();

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_Adicionar_usuario(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_adicionar_usuario");
    }

    public void Clicar_Remover_usuario(ActionEvent event){
        ScreenManager.mostrarTela("pagina_remover_usuario");
    }

    public void Clicar_Emprestimos_usuario(ActionEvent event){
        ScreenManager.mostrarTela("pagina_emprestimos_usuario");
    }

    public void Clicar_Ver_usuarios(ActionEvent event){
        log(print_lista(usuarios.getUsuarios()));
    }
}
