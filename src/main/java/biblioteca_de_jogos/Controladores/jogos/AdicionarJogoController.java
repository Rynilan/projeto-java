package biblioteca_de_jogos.Controladores.jogos;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.control.ControladorDeJogos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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



    private ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();


    @FXML
    private void clicarOk(ActionEvent event) {
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
            MensagensAvisosErros.mostrarAviso("Campos obrigatórios","Todos os campos são obrigatórios!");
            return;
        }

        try {
            int tempoPartida = Integer.parseInt(tempoPartidaStr);
            int quantidadeCopias = Integer.parseInt(quantidadeCopiasStr);
            Long idCategoriaJogo = Long.parseLong(idCategoriaJogoStr);

            if (tempoPartida <= 0) {
                MensagensAvisosErros.mostrarAviso("Tempo de partida positivo","Tempo de partida deve ser um número positivo.");
                return;
            }
            if (quantidadeCopias <= 0) {
                MensagensAvisosErros.mostrarAviso("Quantidade de cópias positivas","Quantidade de cópias deve ser um número positivo.");
                return;
            }

            String[] jogadores = minMaxJogadoresStr.split("-");
            if (jogadores.length != 2) {
                MensagensAvisosErros.mostrarAviso("Formato inválido","Formato inválido para 'Min Max Jogadores'. \n Use 'min-max' (ex: 1-4).");
                return;
            }
            int minJogadores = Integer.parseInt(jogadores[0]);
            int maxJogadores = Integer.parseInt(jogadores[1]);

            if (minJogadores <= 0 || minJogadores >= maxJogadores) {
                MensagensAvisosErros.mostrarAviso("Número de jogadores","Jogadores: O número mínimo deve ser positivo e menor que o número máximo.");
                return;
            }


            Jogo novoJogo = jogosControl.criarJogo(
                    nome, editor, descricao, tempoPartida, minJogadores, maxJogadores, quantidadeCopias, idCategoriaJogo
            );

            if (novoJogo != null) {
                if (jogosControl.adicionarJogo(novoJogo)) {
                    MensagensAvisosErros.mostrarInfo("Jogo adicionado","Jogo adicionado com sucesso!");
                    limparCampos();
                } else {
                    MensagensAvisosErros.mostrarErro("Erro ao adicionar","Não foi possível adicionar o jogo. Pode ser um jogo duplicado ou erro interno.");
                }
            } else {
                MensagensAvisosErros.mostrarErro("Erro ao criar","Não foi possível criar o objeto Jogo. Verifique os valores fornecidos.");
            }
        } catch (NumberFormatException e) {
            MensagensAvisosErros.mostrarErro("Números válidos","Erro: Tempo de partida, quantidade de cópias,\n ID da categoria ou jogadores devem ser números \nválidos.");
        } catch (Exception e) {
            MensagensAvisosErros.mostrarErro("Erro","Ocorreu um erro inesperado: " + e.getMessage());
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
    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }
}
