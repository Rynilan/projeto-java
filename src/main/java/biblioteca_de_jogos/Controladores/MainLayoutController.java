package biblioteca_de_jogos.Controladores;

import biblioteca_de_jogos.ScreenManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainLayoutController implements Initializable {

    @FXML
    public BorderPane RootLayout;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ScreenManager.setRootLayout(RootLayout);
    }

}
