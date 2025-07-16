package biblioteca_de_jogos.Controladores.relatorios;

import biblioteca_de_jogos.ScreenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class PaginaRelatoriosController {

    @FXML
    public Label consoleTextarea;

    public void log(String msg) {
        consoleTextarea.setText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarEmprestimosPeriodoRelatorios(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Emprestimos_periodo_relatorios");
    }

    public void clicarPicosValesEmprestimos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosPicos_vales_emprestimos");
    }

    public void clicarPicosValesPopularidade(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosPicos_vales_popularidade");
    }

    public void clicarRelatorioUsuarios(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosUsuarios");
    }

    public void clicarRelatorioJogos(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Exportacao_relatoriosJogos");
    }

}
