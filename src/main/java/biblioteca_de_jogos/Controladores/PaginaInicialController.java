package biblioteca_de_jogos.Controladores;

import javafx.application.Platform;
import javafx.event.ActionEvent;

public class PaginaInicialController {

    public void ClicarSair(ActionEvent event){
        Platform.exit();
    }

    public void ClicarEntrar(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_principal");
    }
}
