package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.control.EmprestimosControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.Collections;

public class Devolucao_emprestimosController {

    @FXML
    public TextArea ConsoleTextarea;

    @FXML
    public TextField IDemprestimoField;

    @FXML
    public TextField observacaoField;

    EmprestimosControl emprestimosControl = new EmprestimosControl();

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    public void Clicar_ok(ActionEvent event){
        try{
            int IDemprestimo = Integer.parseInt(IDemprestimoField.getText().trim());
            String descricao = observacaoField.getText().trim();

            Emprestimo emprestimo = EmprestimosControl.getInstance().buscarEmprestimo(IDemprestimo);
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
