package biblioteca_de_jogos.Controladores.usuarios;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RemoverUsuariosController {

    @FXML
    private TextField idUsuarioField;

    private ControladorDeUsuarios usuariosControl = ControladorDeUsuarios.getInstance();


    @FXML
    private void handleRemover(ActionEvent event) {
        String idText = idUsuarioField.getText();

        if (idText.isEmpty()) {
            MensagensAvisosErros.mostrarAviso("Sem ID","Por favor, digite o ID do usuário a ser removido.");
            return;
        }

        try {
            Long id = Long.parseLong(idText);

            Usuario usuarioParaRemover = usuariosControl.buscarUsuario(id);

            if (usuarioParaRemover != null) {
                if (usuariosControl.removerUsuario(usuarioParaRemover)) {
                    MensagensAvisosErros.mostrarInfo("ID removido","Usuário com ID " + id + " removido com sucesso!");
                    limparCampo();
                } else {
                    MensagensAvisosErros.mostrarErro("Erro ao remover","Não foi possível remover o usuário com ID " + id + ".");
                }
            } else {
                MensagensAvisosErros.mostrarErro("ID não encontrado","Usuário com ID " + id + " não encontrado.");
            }
        } catch (NumberFormatException e) {
            MensagensAvisosErros.mostrarAviso("ID inválido","ID inválido. Por favor, digite um número inteiro.");
        } catch (Exception e) {
            MensagensAvisosErros.mostrarErro("Erro ao remover","Ocorreu um erro ao tentar remover o usuário: " + e.getMessage());
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
