package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.UsuariosControl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Adicionar_usuariosController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextArea ConsoleTextarea;

    private UsuariosControl usuariosControl = UsuariosControl.getInstance();

    public void log(String msg) {
        ConsoleTextarea.appendText(msg + "\n");
    }

    public void Clicar_voltar(ActionEvent event){
        ScreenManager.voltarParaTelaAnterior();
    }

    @FXML
    private void Clicar_ok(ActionEvent event) {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String telefone = telefoneField.getText();

        if (nome.isEmpty() || email.isEmpty() || telefone.isEmpty()) {
            log("Todos os campos são obrigatórios!");
            return;
        }

        try {
            Usuario novoUsuario = usuariosControl.criarUsuario(nome, email, telefone, "ativo");

            if (usuariosControl.adicionarUsuario(novoUsuario)) {
                log("Usuário adicionado com sucesso!");
                limparCampos();
            } else {
                log("Não foi possível adicionar o usuário.");
            }
        } catch (Exception e) {
            log("Ocorreu um erro: " + e.getMessage());
        }
    }

    private void limparCampos() {
        nomeField.clear();
        emailField.clear();
        telefoneField.clear();
    }
}

