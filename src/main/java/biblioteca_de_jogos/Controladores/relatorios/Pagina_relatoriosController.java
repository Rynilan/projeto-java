package biblioteca_de_jogos.Controladores.relatorios;

import biblioteca_de_jogos.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class Pagina_relatoriosController {

    @FXML
    public TextArea ConsoleTextarea;

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_Emprestimos_periodo_relatorios(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Emprestimos_periodo_relatorios");
    }

    public void Clicar_Picos_vales_emprestimos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosPicos_vales_emprestimos");
    }

    public void Clicar_Picos_vales_popularidade(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosPicos_vales_popularidade");
    }

    public void Clicar_Relatorio_usuarios(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosUsuarios");
    }

    public void Clicar_Relatorio_jogos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosJogos");
    }

}
