package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.ScreenManager;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RemoverUsuariosController {

    @FXML
    private TextField idUsuarioField;

    @FXML
    public Label consoleTextarea;

    private ControladorDeUsuarios usuariosControl = ControladorDeUsuarios.getInstance();

    public void log(String msg) {
        consoleTextarea.setText(msg + "\n");
    }

    @FXML
    private void handleRemover(ActionEvent event) {
        String idText = idUsuarioField.getText();

        if (idText.isEmpty()) {
            log("Por favor, digite o ID do usuário a ser removido.");
            return;
        }

        try {
            Long id = Long.parseLong(idText);

            // 2. Buscar o usuário pelo ID
            Usuario usuarioParaRemover = usuariosControl.buscarUsuario(id);

            if (usuarioParaRemover != null) {
                // 3. Tentar remover o usuário
                if (usuariosControl.removerUsuario(usuarioParaRemover)) {
                    log("Usuário com ID " + id + " removido com sucesso!");
                    limparCampo();
                } else {
                    log("Não foi possível remover o usuário com ID " + id + ".");
                }
            } else {
                log("Usuário com ID " + id + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            log("ID inválido. Por favor, digite um número inteiro.");
        } catch (Exception e) {
            log("Ocorreu um erro ao tentar remover o usuário: " + e.getMessage());
        }
    }

    private void limparCampo() {
        idUsuarioField.clear();
    }

    @FXML
    private void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }
}
