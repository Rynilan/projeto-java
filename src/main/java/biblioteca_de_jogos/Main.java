package biblioteca_de_jogos;

import java.util.List;

import biblioteca_de_jogos.Controladores.ScreenManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/** A classe estática que representa o sistema em funcionalidade,
 * faz as classes conversarem e interage com o usuário. */
public class Main extends Application {

    public static String print_lista(List<?> lista) {
        StringBuilder sb = new StringBuilder();
        for (int indice = 0; indice < lista.size(); indice++) {
            sb.append(lista.get(indice).toString()).append("\n");
        }
        if(!sb.isEmpty()){
            return sb.toString();
        }
        return "Lista Vazia";
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/MainLayout.fxml"));
        Parent root = loader.load();


        ScreenManager.iniciarTelas();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Jogo & CIA - Sistema de Gestão");
        stage.getIcons().add(new Image("view/fxml/icon.png"));
        stage.setResizable(false);
        ScreenManager.mostrarTela("pagina_inicial");
        stage.show();
    }

    public static void main(String args[]) {

        launch(args);

    }

}
