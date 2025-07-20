package biblioteca_de_jogos.Controladores;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class MensagensAvisosErros {

    public static void mostrarErro(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null); // sem cabeçalho
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void mostrarInfo(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }

    public static void mostrarAviso(String titulo, String mensagem) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
