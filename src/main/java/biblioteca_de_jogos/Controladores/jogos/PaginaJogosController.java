package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.Controladores.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class PaginaJogosController {

    @FXML
    public TextArea consoleTextarea;

    private final ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();
    private final VerJogosController verJogosController = VerJogosController.getInstance();
    private final DisponivelEmprestimoController disponivelEmprestimoController = DisponivelEmprestimoController.getInstance();

    public void log(String msg) {
        consoleTextarea.appendText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarAdicionarJogo(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Adicionar_jogo");
    }

    public void clicarRemoverJogo(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Remover_jogo");
    }

    public void clicarVerJogos(ActionEvent event){
        ScreenManager.carregarTela("pagina_ver_jogos", "/view/fxml/Jogos/VerJogos.fxml");
        verJogosController.atualizarTabela();
        ScreenManager.mostrarTela("pagina_ver_jogos");
    }

    public void clicarDisponiveisEmprestimo(ActionEvent event){
        ScreenManager.carregarTela("pagina_Disponivel_emprestimo_jogo", "/view/fxml/Jogos/DisponivelEmprestimoJogo.fxml");
        verJogosController.atualizarTabela();
        ScreenManager.mostrarTela("pagina_Disponivel_emprestimo_jogo");
    }

    public void clicarBuscarEditor(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Buscar_editor_jogo");
    }

    public void clicarBuscarNumeroJogadores(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Buscar_numero_jogadores");
    }

    public void clicarBucarCategoria(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Buscar_categoria_jogo");
    }

    public void clicarBuscarTempoPartida(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Buscar_tempo_partida");
    }
}
