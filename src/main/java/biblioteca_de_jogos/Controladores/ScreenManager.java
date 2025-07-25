package biblioteca_de_jogos.Controladores;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Stack;

import static biblioteca_de_jogos.Main.print_lista;

public class ScreenManager {
    public static BorderPane rootLayout;
    public static TextArea console;

    private static final HashMap<String, Parent> telas = new HashMap<>();
    private static final HashMap<String, Object> controllers = new HashMap<>();
    private static final Stack<String> historicoTelas = new Stack<>();

    public static void setRootLayout(BorderPane Layout) {
        ScreenManager.rootLayout = Layout;
    }

    public static void iniciarTelas(){
        // Páginas principais
        ScreenManager.carregarTela("pagina_inicial", "/view/fxml/PaginaInicial.fxml");
        ScreenManager.carregarTela("pagina_principal", "/view/fxml/PaginaPrincipal.fxml");
        ScreenManager.carregarTela("pagina_relatorios", "/view/fxml/Relatorios/PaginaRelatorios.fxml");
        ScreenManager.carregarTela("pagina_usuarios", "/view/fxml/Usuarios/PaginaUsuarios.fxml");
        ScreenManager.carregarTela("pagina_jogos", "/view/fxml/Jogos/PaginaJogos.fxml");
        ScreenManager.carregarTela("pagina_emprestimos", "/view/fxml/Emprestimos/PaginaEmprestimos.fxml");

        // Páginas de gerenciar usuários
        ScreenManager.carregarTela("pagina_adicionar_usuario", "/view/fxml/Usuarios/AdicionarUsuario.fxml");
        ScreenManager.carregarTela("pagina_remover_usuario", "/view/fxml/Usuarios/RemoverUsuario.fxml");
        ScreenManager.carregarTela("pagina_emprestimos_usuario", "/view/fxml/Usuarios/EmprestimosUsuarios.fxml");
        ScreenManager.carregarTela("pagina_ver_usuarios", "/view/fxml/Usuarios/VerUsuarios.fxml");

        // Páginas de gerenciar empréstimos
        ScreenManager.carregarTela("pagina_Emprestimo_emprestimos", "/view/fxml/Emprestimos/FazerEmprestimo.fxml");
        ScreenManager.carregarTela("pagina_Devolucao_emprestimos", "/view/fxml/Emprestimos/FazerDevolucao.fxml");
        ScreenManager.carregarTela("pagina_Reserva_emprestimos", "/view/fxml/Emprestimos/FazerReserva.fxml");
        ScreenManager.carregarTela("pagina_ver_emprestimos_emprestimos", "/view/fxml/Emprestimos/VerEmprestimosEReservas.fxml");

        // Páginas de gerenciar relatórios
        ScreenManager.carregarTela("pagina_Emprestimos_periodo_relatorios", "/view/fxml/Relatorios/EmprestimosPeriodoRelatorios.fxml");
        ScreenManager.carregarTela("pagina_Exportacao_relatoriosUsuarios", "/view/fxml/Relatorios/ExportacaoRelatoriosUsuarios.fxml");
        ScreenManager.carregarTela("pagina_Exportacao_relatoriosJogos", "/view/fxml/Relatorios/ExportacaoRelatoriosJogos.fxml");
        ScreenManager.carregarTela("pagina_Exportacao_relatoriosPicos_vales_emprestimos", "/view/fxml/Relatorios/ExportacaoPicosValesEmprestimos.fxml");
        ScreenManager.carregarTela("pagina_Exportacao_relatoriosPicos_vales_popularidade", "/view/fxml/Relatorios/ExportacaoPicosValesPopularidade.fxml");
        ScreenManager.carregarTela("pagina_Exportacao_relatoriosPicos_vales_emprestimos_periodos", "/view/fxml/Relatorios/ExportacaoEmprestimosPeriodo.fxml");


        // Páginas de gerenciar jogos
        ScreenManager.carregarTela("pagina_Adicionar_jogo", "/view/fxml/Jogos/AdicionarJogo.fxml");
        ScreenManager.carregarTela("pagina_Buscar_categoria_jogo", "/view/fxml/Jogos/BuscarCategoriaJogo.fxml");
        ScreenManager.carregarTela("pagina_Buscar_editor_jogo", "/view/fxml/Jogos/BuscarEditorJogo.fxml");
        ScreenManager.carregarTela("pagina_Buscar_numero_jogadores", "/view/fxml/Jogos/BuscarNumeroJogadores.fxml");
        ScreenManager.carregarTela("pagina_Buscar_tempo_partida", "/view/fxml/Jogos/BuscarTempoPartida.fxml");
        ScreenManager.carregarTela("pagina_Disponivel_emprestimo_jogo", "/view/fxml/Jogos/DisponivelEmprestimoJogo.fxml");
        ScreenManager.carregarTela("pagina_Remover_jogo", "/view/fxml/Jogos/RemoverJogo.fxml");
        ScreenManager.carregarTela("pagina_ver_jogos", "/view/fxml/Jogos/VerJogos.fxml");
        print_lista(historicoTelas);
    }

    public static void carregarTela(String nome, String caminhoFxml) {
        try {
            URL fxmlPath = ScreenManager.class.getResource(caminhoFxml);
            if (fxmlPath == null) {
                System.err.println("Caminho FXML inválido ou não encontrado: " + caminhoFxml);
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlPath);
            Parent tela = loader.load();
            telas.put(nome, tela);
            controllers.put(nome, loader.getController());
            System.out.println("Tela '" + nome + "' carregada com sucesso.");
        } catch (IOException e) {
            System.err.println("Erro ao carregar a tela '" + nome + "': " + caminhoFxml);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Erro inesperado ao carregar a tela '" + nome + "': " + caminhoFxml + ". Detalhes: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void mostrarTela(String nome) {
        Parent tela = telas.get(nome);
        if (tela != null) {
            if (rootLayout != null) {
                if(!nome.equals("pagina_Exportacao_relatoriosUsuarios")
                && !nome.equals("pagina_Exportacao_relatoriosJogos") && !nome.equals("pagina_Exportacao_relatoriosPicos_vales_emprestimos")
                && !nome.equals("pagina_Exportacao_relatoriosPicos_vales_popularidade") &&!nome.equals("pagina_Exportacao_relatoriosPicos_vales_emprestimos_periodos") &&!nome.equals("pagina_Emprestimos_periodo_relatorios"))
                    if (!historicoTelas.isEmpty() && !historicoTelas.peek().equals(nome)) {
                        historicoTelas.push(nome);
                        System.out.println("Teste1");
                    } else if (historicoTelas.isEmpty()) {
                        historicoTelas.push(nome);
                        System.out.println("Teste2");
                    }
                rootLayout.setCenter(tela);
                System.out.println("Exibindo tela: " + nome);
            } else {
                System.err.println("ScreenManager: rootLayout não foi inicializado. Não foi possível mostrar a tela '" + nome + "'.");
            }
        } else {
            System.err.println("ScreenManager: Tela '" + nome + "' não encontrada. Certifique-se de que foi carregada com 'carregarTela()'.");
        }
    }

    public static void voltarParaTelaAnterior() {
        if (!historicoTelas.isEmpty()) {
            String telaAtual = historicoTelas.pop();
            System.out.println("Removendo do histórico: " + telaAtual + ". Histórico restante: " + historicoTelas);

            if (!historicoTelas.isEmpty()) {
                String telaAnteriorNome = historicoTelas.peek();
                Parent telaAnterior = telas.get(telaAnteriorNome);
                if (telaAnterior != null && rootLayout != null) {
                    rootLayout.setCenter(telaAnterior);
                    System.out.println("Voltando para: " + telaAnteriorNome);
                } else {
                    System.err.println("ScreenManager: Tela anterior '" + telaAnteriorNome + "' não encontrada ou rootLayout é nulo.");
                }
            } else {
                System.out.println("Histórico de telas vazio. Não há mais telas para voltar. Voltando para a tela principal (se houver).");
            }
        } else {
            System.out.println("Não há telas anteriores no histórico para voltar.");
        }
    }

    public static Object getController(String nome) {
        return controllers.get(nome);
    }
}

