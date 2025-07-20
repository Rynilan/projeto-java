package biblioteca_de_jogos.Controladores;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;


public class PaginaPrincipalController {

    @FXML
    public Label ConsoleTextarea;

    @FXML
    public BorderPane MainLayout;

    @FXML
    public AnchorPane mainContent;

    public void log(String msg) {
        ConsoleTextarea.setText(msg + "\n");
    }

    public void ClicarSair(ActionEvent event){
        Platform.exit();
    }

    public void ClicarGerirUsuario(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_usuarios");
    }

    public void ClicarGerirJogos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_jogos");
    }

    public void ClicarEmprestimos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_emprestimos");
    }

    public void ClicarRelatorios(ActionEvent event){
        ScreenManager.mostrarTela("pagina_relatorios");
    }
}
