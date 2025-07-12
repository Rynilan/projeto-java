package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.control.EmprestimosControl;
import biblioteca_de_jogos.control.ReservasControl;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

import static biblioteca_de_jogos.Main.print_lista;

public class Pagina_emprestimosController {

    @FXML
    public TextArea ConsoleTextarea;

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_Emprestimos(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Emprestimo_emprestimos");
    }

    public void Clicar_Devolucao(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Devolucao_emprestimos");
    }

    public void Clicar_Ver_emprestimos_reservas(ActionEvent event){
        log(print_lista(EmprestimosControl.getInstance().getEmprestimos()));
        List<Reserva> lista = ReservasControl.getInstance().getTodasReservas();
        for (Reserva r : lista){
            log(r.toString());
        }
    }

    public void Clicar_Reserva(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Reserva_emprestimos");
    }

}
