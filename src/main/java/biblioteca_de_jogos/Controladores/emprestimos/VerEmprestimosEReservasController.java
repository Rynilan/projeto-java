package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.Controladores.ScreenManager;
import biblioteca_de_jogos.Controladores.jogos.VerJogosController;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.control.ControladorDeEmprestimos;
import biblioteca_de_jogos.control.ControladorDeReservas;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

import java.time.LocalDate;
import java.util.List;

public class VerEmprestimosEReservasController {

    @FXML private TableView<Emprestimo> tabelaEmprestimos;
    @FXML private TableColumn<Emprestimo, Long> colIdEmprestimo;
    @FXML private TableColumn<Emprestimo, LocalDate> colDataEmprestimo;
    @FXML private TableColumn<Emprestimo, LocalDate> colDataDevolucao;
    @FXML private TableColumn<Emprestimo, Integer> colRenovacoes;
    @FXML private TableColumn<Emprestimo, Long> colIntervalo;
    @FXML private TableColumn<Emprestimo, Long> colIdUsuarioEmprestimo;
    @FXML private TableColumn<Emprestimo, Long> colIdJogoEmprestimo;
    @FXML private TableColumn<Emprestimo, List<String>> colObsEmprestimo;


    @FXML private TableView<Reserva> tabelaReservas;
    @FXML private TableColumn<Reserva, Long> colIdReserva;
    @FXML private TableColumn<Reserva, Long> colIdUsuarioReserva;
    @FXML private TableColumn<Reserva, Long> colIdJogoReserva;
    @FXML private TableColumn<Reserva, String> colNotificado;
    @FXML private TableColumn<Reserva, LocalDate> colDataReserva;

    private final ControladorDeEmprestimos emprestimosControl = ControladorDeEmprestimos.getInstance();
    private final ControladorDeReservas reservasControl = ControladorDeReservas.getInstance();
    private final ObservableList<Emprestimo> listaEmprestimos = FXCollections.observableArrayList(emprestimosControl.getEmprestimos());
    private final ObservableList<Reserva> listaReservas = FXCollections.observableArrayList(reservasControl.getTodasReservas());

    private static VerEmprestimosEReservasController instancia = null;

    public static VerEmprestimosEReservasController getInstance() {
        if (VerEmprestimosEReservasController.instancia == null) {
            VerEmprestimosEReservasController.instancia = new VerEmprestimosEReservasController();
        }
        return VerEmprestimosEReservasController.instancia;
    }

    @FXML
    public void initialize() {
        colIdEmprestimo.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDataEmprestimo.setCellValueFactory(new PropertyValueFactory<>("data"));
        colDataDevolucao.setCellValueFactory(new PropertyValueFactory<>("devolucaoString"));
        colRenovacoes.setCellValueFactory(new PropertyValueFactory<>("renovacoes"));
        colIntervalo.setCellValueFactory(new PropertyValueFactory<>("intervalo"));
        colIdUsuarioEmprestimo.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colIdJogoEmprestimo.setCellValueFactory(new PropertyValueFactory<>("idJogo"));
        colObsEmprestimo.setCellValueFactory(new PropertyValueFactory<>("obsString"));

        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdUsuarioReserva.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colIdJogoReserva.setCellValueFactory(new PropertyValueFactory<>("idJogo"));
        colNotificado.setCellValueFactory(new PropertyValueFactory<>("notificado"));
        colDataReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));

        tabelaEmprestimos.setItems(listaEmprestimos);
        tabelaReservas.setItems(listaReservas);
    }

    public void atualizarTabelas() {
        Platform.runLater(() -> {
            listaEmprestimos.clear();
            listaEmprestimos.addAll(emprestimosControl.getEmprestimos());
            if (tabelaEmprestimos != null) {
                tabelaEmprestimos.refresh();
            }

            listaReservas.clear();
            listaReservas.addAll(reservasControl.getTodasReservas());
            if (tabelaReservas != null) {
                tabelaReservas.refresh();
            }
        });
    }

    @FXML
    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }
}
