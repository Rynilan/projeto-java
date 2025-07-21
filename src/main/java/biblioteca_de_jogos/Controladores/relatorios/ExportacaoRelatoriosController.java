package biblioteca_de_jogos.Controladores.relatorios;

import biblioteca_de_jogos.Controladores.MensagensAvisosErros;
import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.control.ExportadorDeEmprestimos;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import biblioteca_de_jogos.control.ExportadorDeJogos;
import biblioteca_de_jogos.control.ExportadorDePopularidadeDeJogos;
import biblioteca_de_jogos.control.ControladorDeJogos;
import biblioteca_de_jogos.control.ExportadorDeExtremosDeEmprestimos;
import biblioteca_de_jogos.control.ExportadorDeUsuarios;
import biblioteca_de_jogos.control.ControladorDeUsuarios;
import com.itextpdf.kernel.exceptions.PdfException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ExportacaoRelatoriosController {
    @FXML
    public Label consoleTextarea;

    private ControladorDeUsuarios usuariosControl = ControladorDeUsuarios.getInstance();
    private ControladorDeJogos jogosControl = ControladorDeJogos.getInstance();
    private ControladorDeEmprestimos emprestimosControl = ControladorDeEmprestimos.getInstance();


    public void clicarCancelar(ActionEvent event) {
        ScreenManager.mostrarTela("pagina_relatorios");
    }

    @FXML
    public void exportarUsuariosCSV(ActionEvent event) {
        try {
            List<Usuario> usuarios = usuariosControl.getUsuarios();
            ExportadorDeUsuarios.exportarCSV(usuarios);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Usuários exportados com sucesso para CSV em 'usuarios.csv'");
        } catch (IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar usuários para CSV: " + e.getMessage());
        }
    }

    @FXML
    public void exportarUsuariosPDF(ActionEvent event) {
        try {
            List<Usuario> usuarios = usuariosControl.getUsuarios();
            ExportadorDeUsuarios.exportarPDF(usuarios);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Usuários exportados com sucesso para PDF em 'usuarios.pdf'");
        } catch (PdfException | IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar usuários para PDF: " + e.getMessage());
        }
    }

    @FXML
    public void exportarUsuariosAmbos(ActionEvent event){
        exportarUsuariosPDF(event);
        exportarUsuariosCSV(event);
    }

    @FXML
    public void exportarJogosCSV(ActionEvent event) {
        try {
            List<Jogo> jogos = jogosControl.getJogos();
            ExportadorDeJogos.exportarCSV(jogos);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Jogos exportados com sucesso para CSV em 'jogos.csv'");
        } catch (IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar jogos para CSV: " + e.getMessage());
        }
    }

    @FXML
    public void exportarJogosPDF(ActionEvent event) {
        try {
            List<Jogo> jogos = jogosControl.getJogos();
            ExportadorDeJogos.exportarPDF(jogos);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Jogos exportados com sucesso para PDF em 'jogos.pdf'");
        } catch (PdfException | IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar jogos para PDF: " + e.getMessage());
        }
    }

    @FXML
    public void exportarJogosAmbos(ActionEvent event){
        exportarJogosPDF(event);
        exportarJogosCSV(event);
    }

    @FXML
    public void exportarEmprestimosCSV(ActionEvent event) {
        try {
            List<Emprestimo> emprestimos = emprestimosControl.getEmprestimos();
            ExportadorDeEmprestimos.exportarCSV(emprestimos);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Empréstimos exportados com sucesso para CSV em 'emprestimos.csv'");
        } catch (IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar empréstimos para CSV: " + e.getMessage());
        }
    }

    @FXML
    public void exportarEmprestimosPDF(ActionEvent event) {
        try {
            List<Emprestimo> emprestimos = emprestimosControl.getEmprestimos();
            ExportadorDeEmprestimos.exportarPDF(emprestimos);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Empréstimos exportados com sucesso para PDF em 'emprestimos.pdf'");
        } catch (PdfException | IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar empréstimos para PDF: " + e.getMessage());
        }
    }

    @FXML
    public void exportarEmprestimosAmbos(ActionEvent event){
        exportarEmprestimosPDF(event);
        exportarEmprestimosCSV(event);
    }

    @FXML
    public void exportarPicosValesEmprestimosCSV(ActionEvent event) {
        try {
            Map<YearMonth, Long> picosVales = emprestimosControl.analisarPicosValesEmprestimos();
            ExportadorDeExtremosDeEmprestimos.exportarCSV(picosVales);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Picos e Vales de Empréstimos exportados com sucesso para CSV em 'picos_vales_emprestimos.csv'");
        } catch (IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar picos e vales de empréstimos para CSV: " + e.getMessage());
        }
    }

    @FXML
    public void exportarPicosValesEmprestimosPDF(ActionEvent event) {
        try {
            Map<YearMonth, Long> picosVales = emprestimosControl.analisarPicosValesEmprestimos();
            ExportadorDeExtremosDeEmprestimos.exportarPDF(picosVales);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Picos e Vales de Empréstimos exportados com sucesso para PDF em 'picos_vales_emprestimos.pdf'");
        } catch (PdfException | IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar picos e vales de empréstimos para PDF: " + e.getMessage());
        }
    }

    @FXML
    public void exportarPicosValesEmprestimosAmbos(ActionEvent event){
        exportarPicosValesEmprestimosPDF(event);
        exportarPicosValesEmprestimosCSV(event);
    }

    @FXML
    public void exportarPopularidadeJogosCSV(ActionEvent event) {
        try {
            Map<Jogo, Long> popularidadeJogos = emprestimosControl.analisarPicosValesPopularidadeJogos()
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            java.util.LinkedHashMap::new
                    ));
            ExportadorDePopularidadeDeJogos.exportarCSV(popularidadeJogos);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida","Popularidade de Jogos exportada com sucesso para CSV em 'popularidade_jogos.csv'");
        } catch (IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar popularidade de jogos para CSV: " + e.getMessage());
        }
    }

    @FXML
    public void exportarPopularidadeJogosPDF(ActionEvent event) {
        try {
            Map<Jogo, Long> popularidadeJogos = emprestimosControl.analisarPicosValesPopularidadeJogos()
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            java.util.LinkedHashMap::new
                    ));
            ExportadorDePopularidadeDeJogos.exportarPDF(popularidadeJogos);
            MensagensAvisosErros.mostrarInfo("Exportação bem sucedida", "Popularidade de Jogos exportada com sucesso para PDF em 'popularidade_jogos.pdf'");
        } catch (PdfException | IOException e) {
            MensagensAvisosErros.mostrarErro("Erro ao exportar","Erro ao exportar popularidade de jogos para PDF: " + e.getMessage());
        }
    }

    @FXML
    public void exportarPicosValesPopularidadeAmbos(ActionEvent event){
        exportarPopularidadeJogosPDF(event);
        exportarPopularidadeJogosCSV(event);
    }
}

