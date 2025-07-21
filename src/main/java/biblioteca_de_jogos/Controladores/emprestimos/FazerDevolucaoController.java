package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Collections;

public class FazerDevolucaoController {

    @FXML
    public Label consoleTextarea;

    @FXML
    public TextField idEmprestimoField;

    @FXML
    public TextField observacaoField;

    ControladorDeEmprestimos emprestimosControl = ControladorDeEmprestimos.getInstance();

    public void log(String msg) {
        consoleTextarea.setText(msg + "\n");
    }

    public void clicarVoltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void clicarOk(ActionEvent event){
        try{
            Long IDemprestimo = Long.parseLong(idEmprestimoField.getText().trim());
            String descricao = observacaoField.getText().trim();

            Emprestimo emprestimo = ControladorDeEmprestimos.getInstance().buscarEmprestimo(IDemprestimo);
            if (emprestimo == null){
                log("Empréstimo não encontrado com o ID: " + IDemprestimo);
            }
            if (!descricao.isEmpty()){
                emprestimosControl.fazerDevolucao(IDemprestimo, Collections.singletonList(descricao));
                log("Devolução feita com sucesso!");
            }else{
                emprestimosControl.fazerDevolucao(IDemprestimo, null);
                log("Devolução feita com sucesso!");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }
}
