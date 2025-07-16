package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import biblioteca_de_jogos.control.ControladorDeReservas;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

import static biblioteca_de_jogos.Main.print_lista;

public class Pagina_emprestimosController {

    @FXML
    public TextArea consoleTextarea;

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
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
        log(print_lista(ControladorDeEmprestimos.getInstance().getEmprestimos()));
        List<Reserva> lista = ControladorDeReservas.getInstance().getTodasReservas();
        for (Reserva r : lista){
            log(r.toString());
        }
    }

    public void clicarReserva(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Reserva_emprestimos");
    }

}
