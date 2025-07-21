package biblioteca_de_jogos.Controladores.emprestimos;

import biblioteca_de_jogos.Controladores.ScreenManager;
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
import javafx.scene.text.Text;

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

    private void redimensionarColunasEmprestimos(TableView<Emprestimo> tableView) {
        for (TableColumn<Emprestimo, ?> column : tableView.getColumns()) {
            double maxContentWidth = 0;

            Text headerText = new Text(column.getText());
            maxContentWidth = Math.max(maxContentWidth, headerText.getLayoutBounds().getWidth());
            for (Emprestimo item : tableView.getItems()) {
                Object cellValue = column.getCellData(item);
                if (cellValue != null) {
                    Text cellText = new Text(cellValue.toString());
                    maxContentWidth = Math.max(maxContentWidth, cellText.getLayoutBounds().getWidth());
                }
            }
            double padding = 20;
            column.setPrefWidth(Math.max(column.getMinWidth(), maxContentWidth + padding));
        }
    }

    private void redimensionarColunasReservas(TableView<Reserva> tableView) {
        for (TableColumn<Reserva, ?> column : tableView.getColumns()) {
            double maxContentWidth = 0;

            Text headerText = new Text(column.getText());
            maxContentWidth = Math.max(maxContentWidth, headerText.getLayoutBounds().getWidth());
            for (Reserva item : tableView.getItems()) {
                Object cellValue = column.getCellData(item);
                if (cellValue != null) {
                    Text cellText = new Text(cellValue.toString());
                    maxContentWidth = Math.max(maxContentWidth, cellText.getLayoutBounds().getWidth());
                }
            }
            double padding = 20;
            column.setPrefWidth(Math.max(column.getMinWidth(), maxContentWidth + padding));
        }
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

        colIdEmprestimo.setMinWidth(50);
        colDataEmprestimo.setMinWidth(50);
        colDataDevolucao.setMinWidth(50);
        colRenovacoes.setMinWidth(50);
        colIntervalo.setMinWidth(50);
        colIdUsuarioEmprestimo.setMinWidth(50);
        colIdJogoEmprestimo.setMinWidth(50);
        colObsEmprestimo.setMinWidth(50);
        tabelaEmprestimos.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tabelaEmprestimos.setItems(listaEmprestimos);
        redimensionarColunasEmprestimos(tabelaEmprestimos);

        colIdReserva.setCellValueFactory(new PropertyValueFactory<>("id"));
        colIdUsuarioReserva.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        colIdJogoReserva.setCellValueFactory(new PropertyValueFactory<>("idJogo"));
        colNotificado.setCellValueFactory(new PropertyValueFactory<>("notificado"));
        colDataReserva.setCellValueFactory(new PropertyValueFactory<>("dataReserva"));

        colIdReserva.setMinWidth(50);
        colIdUsuarioReserva.setMinWidth(50);
        colIdJogoReserva.setMinWidth(50);
        colNotificado.setMinWidth(50);
        colDataReserva.setMinWidth(50);
        tabelaReservas.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        tabelaReservas.setItems(listaReservas);
        redimensionarColunasReservas(tabelaReservas);
    }

    public void atualizarTabelas() {
        Platform.runLater(() -> {
            listaEmprestimos.clear();
            listaEmprestimos.addAll(emprestimosControl.getEmprestimos());
            if (tabelaEmprestimos != null) {
                tabelaEmprestimos.refresh();
                redimensionarColunasEmprestimos(tabelaEmprestimos);
            }

            listaReservas.clear();
            listaReservas.addAll(reservasControl.getTodasReservas());
            if (tabelaReservas != null) {
                tabelaReservas.refresh();
                redimensionarColunasReservas(tabelaReservas);
            }
        });
    }

    @FXML
    public void clicarVoltar(ActionEvent event) {
        ScreenManager.voltarParaTelaAnterior();
    }
}
