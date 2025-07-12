package biblioteca_de_jogos.Controladores;

import biblioteca_de_jogos.ScreenManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import javafx.event.ActionEvent;


public class Pagina_principalController {

    @FXML
    public Label ConsoleTextarea;

    @FXML
    public BorderPane MainLayout;

    @FXML
    public AnchorPane mainContent;


    public void log(String msg) {
        ConsoleTextarea.setText(msg + "\n");
    }

    public void Clicar_sair(ActionEvent event){
        Platform.exit();
    }

    public void Clicar_teste(ActionEvent event){
        System.out.println("Teste");
    }

    public void Clicar_Gerir_usuario(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_usuarios");
    }

    public void Clicar_Gerir_jogos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_jogos");
    }

    public void Clicar_Emprestimos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_emprestimos");
    }

    public void Clicar_Relatorios(ActionEvent event){
        ScreenManager.mostrarTela("pagina_relatorios");
    }
}
