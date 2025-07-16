package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

import static biblioteca_de_jogos.Main.print_lista;

public class PaginaJogosController {

    @FXML
    public TextArea consoleTextarea;

    private final ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();

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
        log(print_lista(jogosControl.getJogos()));
    }

    public void clicarDisponiveisEmprestimo(ActionEvent event){
        List<Jogo> disponiveis = jogosControl.jogosDisponiveis();
        if (disponiveis.isEmpty()) {
            log("Nenhum jogo disponível para empréstimo.");
        } else {
            log("Jogos disponíveis para empréstimo:");
            for (Jogo jogo : disponiveis) {
                log(jogo.toString());
            }
        }
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
