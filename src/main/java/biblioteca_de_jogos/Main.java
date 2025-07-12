package biblioteca_de_jogos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeParseException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** A classe estática que representa o sistema em funcionalidade,
 * faz as classes conversarem e interage com o usuário. */
public class Main extends Application {

    public static void print(String string) {
        System.out.println(string);
    }

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

    public static List<String> pegarObservacoes(String s, Scanner i) {
        if (!s.equalsIgnoreCase("s")) {
            print("ok.");
            return null;
        }
        List<String> observacoes = new ArrayList<String>();
        while (true) {
            print("Diga uma observação: ");
            observacoes.add(i.nextLine());
            print("Tem mais? (s ou n)");
            String a = i.nextLine();
            if (a.equalsIgnoreCase("n")) {
                return observacoes;
            }
        }
    }

    public static int lerInt(Scanner stdin, String mensagem) {
        while (true) {
            print(mensagem);
            try {
                return Integer.parseInt(stdin.nextLine());
            } catch (NumberFormatException e) {
                print("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    public static LocalDate lerData(Scanner stdin, String mensagem) {
        while (true) {
            print(mensagem);
            try {
                return LocalDate.parse(stdin.nextLine());
            } catch (DateTimeParseException e) {
                print("Formato inválido. Use dd mm aaaa");
            }
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/fxml/MainLayout.fxml"));
        Parent root = loader.load();

        ScreenManager.iniciarTelas();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Jogo & CIA - Sistema de Gestão");
        stage.setResizable(false);
        ScreenManager.mostrarTela("pagina_principal");
        stage.show();
    }



    public static void main(String args[]) {

        launch(args);

    }

}
