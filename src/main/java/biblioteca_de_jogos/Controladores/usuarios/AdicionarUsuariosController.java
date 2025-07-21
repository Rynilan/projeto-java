package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AdicionarUsuariosController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefoneField;
    @FXML
    private Label consoleTextarea;

    private ControladorDeUsuarios usuariosControl = ControladorDeUsuarios.getInstance();

    public void clicarVoltar(ActionEvent event){
        limparCampos();
        ScreenManager.voltarParaTelaAnterior();
    }

    @FXML
    private void clicarOk(ActionEvent event) {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            MensagensAvisosErros.mostrarAviso("Campos obrigatórios","Todos os campos são obrigatórios!");
            return;
        }

        try {
            Usuario novoUsuario = usuariosControl.criarUsuario(nome, email, telefone, "ativo");

            if (usuariosControl.adicionarUsuario(novoUsuario)) {
                MensagensAvisosErros.mostrarInfo("Usuário adicionado","Usuário adicionado com sucesso!");
                limparCampos();
            } else {
                MensagensAvisosErros.mostrarErro("Erro ao adicionar","Não foi possível adicionar o usuário.");
            }
        } catch (Exception e) {
            MensagensAvisosErros.mostrarErro("Erro","Ocorreu um erro: " + e.getMessage());
        }
    }

    private void limparCampos() {
        nomeField.clear();
        emailField.clear();
        telefoneField.clear();
    }
}

