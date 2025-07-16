package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdicionarJogoController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField editorField;
    @FXML
    private TextField tempoPartidaField;
    @FXML
    private TextField minMaxJogadoresField;
    @FXML
    private TextField quantidadeCopiasField;
    @FXML
    private TextField idCategoriaJogoField;
    @FXML
    private TextArea descricaoJogoArea;
    @FXML
    private TextArea ConsoleTextarea;


    private ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    @FXML
    private void Clicar_ok(ActionEvent event) {
        String nome = nomeField.getText();
        String editor = editorField.getText();
        String tempoPartidaStr = tempoPartidaField.getText();
        String minMaxJogadoresStr = minMaxJogadoresField.getText();
        String quantidadeCopiasStr = quantidadeCopiasField.getText();
        String idCategoriaJogoStr = idCategoriaJogoField.getText();
        String descricao = descricaoJogoArea.getText();

        if (nome.isEmpty() || editor.isEmpty() || tempoPartidaStr.isEmpty() ||
                minMaxJogadoresStr.isEmpty() || quantidadeCopiasStr.isEmpty() ||
                idCategoriaJogoStr.isEmpty() || descricao.isEmpty()) {
            log("Todos os campos são obrigatórios!");
            return;
        }

        try {
            int tempoPartida = Integer.parseInt(tempoPartidaStr);
            int quantidadeCopias = Integer.parseInt(quantidadeCopiasStr);
            Long idCategoriaJogo = Long.parseLong(idCategoriaJogoStr);

            if (tempoPartida <= 0) {
                log("Tempo de partida deve ser um número positivo.");
                return;
            }
            if (quantidadeCopias <= 0) {
                log("Quantidade de cópias deve ser um número positivo.");
                return;
            }

            String[] jogadores = minMaxJogadoresStr.split("-");
            if (jogadores.length != 2) {
                log("Formato inválido para 'Min Max Jogadores'. Use 'min-max' (ex: 1-4).");
                return;
            }
            int minJogadores = Integer.parseInt(jogadores[0]);
            int maxJogadores = Integer.parseInt(jogadores[1]);

            if (minJogadores <= 0 || minJogadores >= maxJogadores) {
                log("Jogadores: O número mínimo deve ser positivo e menor que o número máximo.");
                return;
            }


            Jogo novoJogo = jogosControl.criarJogo(
                    nome, editor, descricao, tempoPartida, minJogadores, maxJogadores, quantidadeCopias, idCategoriaJogo
            );

            if (novoJogo != null) {
                if (jogosControl.adicionarJogo(novoJogo)) {
                    log("Jogo adicionado com sucesso!");
                    limparCampos();
                } else {
                    log("Não foi possível adicionar o jogo. Pode ser um jogo duplicado ou erro interno.");
                }
            } else {
                log("Não foi possível criar o objeto Jogo. Verifique os valores fornecidos.");
            }
        } catch (NumberFormatException e) {
            log("Erro: Tempo de partida, quantidade de cópias, ID da categoria ou jogadores devem ser números válidos.");
        } catch (Exception e) {
            log("Ocorreu um erro inesperado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void limparCampos() {
        nomeField.clear();
        editorField.clear();
        tempoPartidaField.clear();
        minMaxJogadoresField.clear();
        quantidadeCopiasField.clear();
        idCategoriaJogoField.clear();
        descricaoJogoArea.clear();
    }

    @FXML
    public void Clicar_voltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }
}
