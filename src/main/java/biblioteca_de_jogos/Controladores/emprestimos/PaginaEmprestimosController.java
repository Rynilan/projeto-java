package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.Controladores.jogos.DisponivelEmprestimoController;
import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import biblioteca_de_jogos.control.ControladorDeReservas;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.Controladores.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.List;

import static biblioteca_de_jogos.Main.print_lista;

public class PaginaEmprestimosController {

    @FXML
    public Label consoleTextarea;
    private final VerEmprestimosEReservasController verEmprestimosEReservasController = VerEmprestimosEReservasController.getInstance();

    public void log(String msg) {
        consoleTextarea.setText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarEmprestimos(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Emprestimo_emprestimos");
    }

    public void clicarDevolucao(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Devolucao_emprestimos");
    }

    public void clicarVerEmprestimosReservas(ActionEvent event){
        verEmprestimosEReservasController.atualizarTabelas();
        ScreenManager.carregarTela("pagina_ver_emprestimos_emprestimos", "/view/fxml/Emprestimos/VerEmprestimosEReservas.fxml");
        ScreenManager.mostrarTela("pagina_ver_emprestimos_emprestimos");
    }

    public void clicarReserva(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Reserva_emprestimos");
    }

}
