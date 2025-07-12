package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.control.JogosControl;
import javafx.event.ActionEvent;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.ScreenManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;

import static biblioteca_de_jogos.Main.print_lista;

public class Pagina_jogosController {

    @FXML
    public TextArea ConsoleTextarea;

    private final JogosControl jogosControl = JogosControl.getInstance();

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_Adicionar_jogo(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Adicionar_jogo");
    }

    public void Clicar_Remover_jogo(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Remover_jogo");
    }

    public void Clicar_Ver_jogos(ActionEvent event){
        log(print_lista(jogosControl.getJogos()));
    }

    public void Clicar_Disponiveis_emprestimo(ActionEvent event){
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

    public void Clicar_Buscar_editor(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_Buscar_editor_jogo");
    }

    public void Clicar_Buscar_numero_jogadores(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Buscar_numero_jogadores");
    }

    public void Clicar_Bucar_categoria(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Buscar_categoria_jogo");
    }

    public void Clicar_Buscar_tempo_partida(ActionEvent event){
        ScreenManager.mostrarTela("pagina_Buscar_tempo_partida");
    }
}
