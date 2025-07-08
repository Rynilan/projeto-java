package biblioteca_de_jogos;

import biblioteca_de_jogos.control.EmprestimoExporter;
import biblioteca_de_jogos.control.EmprestimosControl;
import biblioteca_de_jogos.control.JogoExporter;
import biblioteca_de_jogos.control.JogoPopularidadeExporter;
import biblioteca_de_jogos.control.JogosControl;
import biblioteca_de_jogos.control.PicosValesEmprestimoExporter;
import biblioteca_de_jogos.control.UsuarioExporter;
import biblioteca_de_jogos.control.UsuariosControl;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import com.itextpdf.kernel.exceptions.PdfException;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** A classe estática que representa o sistema em funcionalidade,
 * faz as classes conversarem e interage com o usuário. */
public class Main extends Application {

    public static void print(String string) {
        System.out.println(string);
    }

    public static void print_lista(List lista) {
        for (int indice = 0; indice < lista.size(); indice++) {
            System.out.println(lista.get(indice));
        }
    }

    public static List<String> pegarObservacoes(String s, Scanner i) {
        if (!s.equalsIgnoreCase("s")) {
            print("ok.");
            return null;
        }
        List<String> observacoes = new ArrayList<String>();
        while (true) {
            print("Diga uma observação: ");
            observacoes.add(i.nextLine());
            print("Tem mais? (s ou n)");
            String a = i.nextLine();
            if (a.equalsIgnoreCase("n")) {
                return observacoes;
            }
        }
    }

    public static int lerInt(Scanner stdin, String mensagem) {
        while (true) {
            print(mensagem);
            try {
                return Integer.parseInt(stdin.nextLine());
            } catch (NumberFormatException e) {
                print("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    public static LocalDate lerData(Scanner stdin, String mensagem) {
        while (true) {
            print(mensagem);
            try {
                return LocalDate.parse(stdin.nextLine());
            } catch (DateTimeParseException e) {
                print("Formato inválido. Use dd mm aaaa");
            }
        }
    }

    public static void main(String args[]) {

        launch();

        UsuariosControl usuarios = UsuariosControl.getInstance();
        JogosControl jogos = JogosControl.getInstance();
        EmprestimosControl gerencia = EmprestimosControl.getInstance();

        Scanner stdin = new Scanner(System.in);
        String user = "0";

        print("Bem vindo a Jogo & CIA\n");

        do {
            print("O que você quer gerir/fazer?");
            print("0. Sair");
            print("1. Gerir Usuários");
            print("2. Gerir Jogos");
            print("3. Empréstimos");
            print("4. Ver relatórios");

            user = stdin.nextLine();
            print("");

            if (user.equals("1")) {
                print("O que quer fazer?");
                print("1. Adicionar usuário.");
                print("2. Remover usuário.");
                print("3. Ver usuários.");
                print("4. Ver empréstimos dum usuários.");

                user = stdin.nextLine();

                switch (user) {
                    case "1":
                        print("Defina o nome, email e telefone");
                        if (usuarios.adicionarUsuario(
                                usuarios.criarUsuario(
                                        stdin.nextLine(),
                                        stdin.nextLine(),
                                        stdin.nextLine(),
                                        "ativo"
                                )
                        )) { print("Usuário adicionado com sucesso.");
                        } else { print("Algum erro aconteceu."); }
                        break;

                    case "2":
                        print("Defina o id do usário a ser removido:");
                        if (usuarios.removerUsuario(usuarios.buscarUsuario(stdin.nextInt()))) {
                            print("Remoção bem sucedida");
                        } else { print("Algum erro ocorreu.");}
                        break;

                    case "3":
                        print("");
                        print_lista(usuarios.getUsuarios());
                        print("");
                        break;

                    case "4":
                        print("Diga o id do usuário em questão: ");
                        int n = stdin.nextInt();
                        print("");
                        print_lista(gerencia.getEmprestimosDoUsuario(n));
                        print("");
                        break;

                    default:
                        print("Entrada inválida");
                        break;
                }
                user = "1";
            }

            if (user.equals("2")) {
                print("O que quer fazer?");
                print("1. Adicionar jogo.");
                print("2. Remover jogo.");
                print("3. Ver jogos.");
                print("4. Ver jogos disponíveis para empréstimo.");
                print("5. Buscar por editor.");
                print("6. Buscar por numero de jogadores.");
                print("7. Buscar por categoria.");
                print("8. Buscar por tempo de partida.");

                user = stdin.nextLine();

                switch (user) {
                    case "1":
                        print("Defina o nome, editor, descrição, tempo de partida (em minutos), mínimo e máximo de jogadores, a quantidade de cópias, e o id da categoria desse jogo: ");
                        if (jogos.adicionarJogo(
                                jogos.criarJogo(
                                        stdin.nextLine(),
                                        stdin.nextLine(),
                                        stdin.nextLine(),
                                        stdin.nextInt(),
                                        stdin.nextInt(),
                                        stdin.nextInt(),
                                        stdin.nextInt(),
										stdin.nextInt()
                                )
                        )) { print("Jogo adicionado com sucesso.");
                        } else { print("Algum erro aconteceu."); }
                        break;

                    case "2":
                        print("Defina o id do jogo a ser removido:");
                        if (jogos.removerJogo(jogos.buscarJogo(stdin.nextInt()))) {
                            print("Remoção bem sucedida");
                        } else { print("Algum erro ocorreu.");}
                        break;

                    case "3":
                        print("");
                        print_lista(jogos.getJogos());
                        print("");
                        break;

                    case "4":
                        print("");
                        print_lista(gerencia.pegarDisponiveis(jogos.getJogos()));
                        print("");
                        break;

                    case "5":
                        print("defina o valor para o dado parâmetro: ");
                        String arg1 = stdin.nextLine();
                        print("");
                        print_lista(jogos.buscarPorEditor(arg1));
                        print("");
                        break;

                    case "6":
                        print("defina o valor para o dado parâmetro: ");
                        int arg2 = stdin.nextInt();
                        print("");
                        print_lista(jogos.buscarPorQuantiaDeJogadores(arg2));
                        print("");
                        break;

                    case "7":
                        print("defina o valor para o dado parâmetro: ");
                        int arg3 = stdin.nextInt();
                        print("");
                        print_lista(jogos.buscarPorCategoria(arg3));
                        print("");
                        break;

                    case "8":
                        print("Defina o valor para o dado parâmetro: ");
                        int arg4 = stdin.nextInt();
                        print("");
                        print_lista(jogos.buscarPorQuantiaDeJogadores(arg4));
                        print("");
                        break;

                    default:
                        print("Entrada inválida");
                        break;
                }
                user = "2";
            }

            if (user.equals("3")) {
                print("O que queres fazer?");
                print("1. Empréstimo.");
                print("2. Devolução.");
                print("3. Reserva.");
                print("4. Ver todos os empréstimos.");

                user = stdin.nextLine();

                switch (user) {
                    case "1":
                        print("Diga o id do usuário e do jogo:");
                        if (gerencia.fazerEmprestimo(
                                usuarios.buscarUsuario(stdin.nextInt()),
                                jogos.buscarJogo(stdin.nextInt())
                        )) { print("Empréstimo feito com sucesso.");
                        } else { print("Empréstimo deu erro.");}
                        break;

                    case "2":
                        print("Defina o id do empréstimo a ser devolvido e se há alguma observação (s ou n).");
                        int emprestimoId = stdin.nextInt();
                        List<String> observacoes = pegarObservacoes(stdin.nextLine(), stdin);
                        gerencia.fazerDevolucao(emprestimoId, observacoes);

                        Emprestimo emprestimoDevolvido = gerencia.buscarEmprestimo(emprestimoId);
                        if (emprestimoDevolvido != null) {
                            Jogo jogoDevolvido = emprestimoDevolvido.getJogo();
                            if (gerencia.temReservasParaJogo(jogoDevolvido)) {
                                int proximoUsuarioId = gerencia.proximaReservaParaJogo(jogoDevolvido);
                                if (gerencia.fazerEmprestimo(usuarios.buscarUsuario(proximoUsuarioId), jogoDevolvido)) {
                                    print("Reserva convertida em empréstimo para o usuário ID: " + proximoUsuarioId);
                                } else {
                                    print("Falha ao converter reserva em empréstimo.");
                                }
                            }
                        }
                        break;

                    case "3":
                        print("Defina o id de quem e de que jogo será reservado: ");
                        int usuarioId = stdin.nextInt();
                        int jogoId = stdin.nextInt();
                        Jogo jogoReservado = jogos.buscarJogo(jogoId);
                        if (jogoReservado != null && gerencia.reservarJogo(usuarioId, jogoReservado)) {
                            print("Reserva feita com sucesso.");
                        } else {
                            print("Reserva falhou.");
                        }
                        break;

                    case "4":
                        print("");
                        print_lista(gerencia.getEmprestimos());
                        print("");
                        break;

                    default:
                        print("Entrada inválida");
                        break;

                }
                user = "3";
            }

            if (user.equals("4")) {
                print("O que queres fazer?");
                print("1. Ver empréstimos de um período de tempo");
                print("2. Ver relatório de usuários");
                print("3. Ver relatório de jogos");
                print("4. Ver picos e vales de empréstimo");
                print("5. Ver picos e vales de popularidade");

                user = stdin.nextLine();

                switch (user) {
                    case "1":
                        print("Digite a data inicial (Formatação dd/mm/aaaa)");

                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        LocalDate inicio = LocalDate.parse(stdin.nextLine(), formatter);

                        print("Digite a data final (Formatação dd/mm/aaaa)");
                        LocalDate fim = LocalDate.parse(stdin.nextLine(), formatter);


                        List<Emprestimo> emprestimosFiltrados = gerencia.getEmprestimos().stream()
                                .filter(e -> !e.getData().isBefore(inicio) && !e.getData().isAfter(fim))
                                .collect(Collectors.toList());

                        print("Emprestimos entre " + inicio.format(formatter) + " e " + fim.format(formatter) + ":");
                        if (emprestimosFiltrados.isEmpty()) {
                            print("Nenhum empréstimo encontrado no período.");
                        } else {
                            emprestimosFiltrados.forEach(System.out::println);
                        }


                        print("Exportar para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String temp1 = stdin.nextLine();


                        switch (temp1){
                            case "1":
                                    try {
                                        EmprestimoExporter.exportarCSV(emprestimosFiltrados);
                                        print("Exportado com sucesso para CSV em 'emprestimos.csv'");
                                    } catch (IOException e) {
                                        print("Erro ao exportar para CSV: " + e.getMessage());
                                    }
                                    break;


                            case "2":
                                try {
                                    EmprestimoExporter.exportarPDF(emprestimosFiltrados);
                                    print("Exportado com sucesso para PDF em 'emprestimos.pdf'");
                                } catch (PdfException | IOException e) {
                                    print("Erro ao exportar para PDF: " + e.getMessage());
                                }
                                break;

                            case "3":
                                try {
                                    EmprestimoExporter.exportarCSV(emprestimosFiltrados);
                                    print("Exportado com sucesso para CSV em 'emprestimos.csv'");
                                    EmprestimoExporter.exportarPDF(emprestimosFiltrados);
                                    print("Exportado com sucesso para PDF em 'emprestimos.pdf'");
                                    print("Exportado com sucesso para PDF e CSV");
                                } catch (PdfException | IOException e) {
                                    print("Erro ao exportar: " + e.getMessage());
                                }
                                break;
                            case "4":
                                print("Exportação cancelada.");
                                break;

                            default:
                                print("Entrada inválida");
                                break;
                        }
                        break;

                    case "2":
                        print("Exportar usuários para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String exportOptionUsuario = stdin.nextLine();


                        List<Usuario> usuariosParaExportar = usuarios.getUsuarios();

                        switch (exportOptionUsuario) {
                            case "1":
                                try {
                                    UsuarioExporter.exportarCSV(usuariosParaExportar);
                                    print("Exportado com sucesso para CSV em 'usuarios.csv'");
                                } catch (IOException e) {
                                    print("Erro ao exportar para CSV: " + e.getMessage());
                                }
                                break;

                            case "2":
                                try {
                                    UsuarioExporter.exportarPDF(usuariosParaExportar);
                                    print("Exportado com sucesso para PDF em 'usuarios.pdf'");
                                } catch (PdfException | IOException e) {
                                    print("Erro ao exportar para PDF: " + e.getMessage());
                                }
                                break;

                            case "3":
                                try {
                                    UsuarioExporter.exportarCSV(usuariosParaExportar);
                                    UsuarioExporter.exportarPDF(usuariosParaExportar);
                                    print("Exportado com sucesso para PDF e CSV em 'usuarios.pdf' e 'usuarios.csv'"); // Mensagem mais específica
                                } catch (PdfException | IOException e) {
                                    print("Erro ao exportar: " + e.getMessage());
                                }
                                break;

                            case "4":
                                print("Exportação de usuários cancelada.");
                                break;

                            default:
                                print("Entrada inválida.");
                                break;
						}



                    case "3":

                                print("\n--- Relatório de Jogos ---");

                                List<Jogo> todosJogos = jogos.getJogos();
                                if (todosJogos.isEmpty()) {
                                    print("Nenhum jogo cadastrado.");
                                } else {
                                    print("Lista de Todos os Jogos:");

                                    todosJogos.forEach(System.out::println);
                                }

                                print("Exportar jogos para: ");
                                print("1. CSV");
                                print("2. PDF");
                                print("3. PDF e CSV");
                                print("4. Não exportar.");

                                String exportOptionJogo = stdin.nextLine();

                                switch (exportOptionJogo) {
                                    case "1":
                                        try {

                                            JogoExporter.exportarCSV(todosJogos);
                                            print("Exportado com sucesso para CSV em 'jogos.csv'");
                                        } catch (IOException e) {
                                            print("Erro ao exportar para CSV: " + e.getMessage());
                                        }
                                        break;

                                    case "2":
                                        try {

                                            JogoExporter.exportarPDF(todosJogos);
                                            print("Exportado com sucesso para PDF em 'jogos.pdf'");
                                        } catch (PdfException | IOException e) {
                                            print("Erro ao exportar para PDF: " + e.getMessage());
                                        }
                                        break;

                                    case "3":
                                        try {
                                            JogoExporter.exportarCSV(todosJogos);
                                            JogoExporter.exportarPDF(todosJogos);
                                            print("Exportado com sucesso para PDF e CSV em 'jogos.pdf' e 'jogos.csv'");
                                        } catch (PdfException | IOException e) {
                                            print("Erro ao exportar: " + e.getMessage());
                                        }
                                        break;

                                    case "4":
                                        print("Exportação de jogos cancelada.");
                                        break;

                                    default:
                                        print("Entrada inválida.");
                                        break;
                                }
                                break;

                    case "4":
                        print("\n--- Análise de Picos e Vales de Empréstimos ---");
                        Map<YearMonth, Long> contagemPorMes = gerencia.analisarPicosValesEmprestimos();

                        if (contagemPorMes.isEmpty()) {
                            print("Não há empréstimos registrados para analisar picos e vales.");
                        } else {
                            Map.Entry<YearMonth, Long> pico = contagemPorMes.entrySet().stream()
                                    .max(Map.Entry.comparingByValue())
                                    .orElse(null);

                            Map.Entry<YearMonth, Long> vale = contagemPorMes.entrySet().stream()
                                    .min(Map.Entry.comparingByValue())
                                    .orElse(null);

                            print("\nContagem de empréstimos por mês/ano:");
                            contagemPorMes.entrySet().stream()
                                    .sorted(Map.Entry.comparingByKey())
                                    .forEach(entry -> {
                                        print("  " + entry.getKey().format(DateTimeFormatter.ofPattern("MM/yyyy")) + ": " + entry.getValue() + " empréstimos");
                                    });

                            if (pico != null) {
                                print("\nPico de Empréstimos: " + pico.getKey().format(DateTimeFormatter.ofPattern("MM/yyyy")) + " com " + pico.getValue() + " empréstimos.");
                            }
                            if (vale != null) {
                                print("Vale de Empréstimos: " + vale.getKey().format(DateTimeFormatter.ofPattern("MM/yyyy")) + " com " + vale.getValue() + " empréstimos.");
                            }
                        }


                        print("Exportar análise de picos e vales para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String exportOptionPicosVales = stdin.nextLine();

                        switch (exportOptionPicosVales) {
                            case "1":
                                try {
                                    PicosValesEmprestimoExporter.exportarCSV(contagemPorMes);
                                    print("Exportado com sucesso para CSV em 'picos_vales_emprestimo.csv'");
                                } catch (IOException e) {
                                    print("Erro ao exportar para CSV: " + e.getMessage());
                                }
                                break;

                            case "2":
                                try {
                                    PicosValesEmprestimoExporter.exportarPDF(contagemPorMes);
                                    print("Exportado com sucesso para PDF em 'picos_vales_emprestimo.pdf'");
                                } catch (PdfException | IOException e) {
                                    print("Erro ao exportar para PDF: " + e.getMessage());
                                }
                                break;

                            case "3":
                                try {
                                    PicosValesEmprestimoExporter.exportarCSV(contagemPorMes);
                                    PicosValesEmprestimoExporter.exportarPDF(contagemPorMes);
                                    print("Exportado com sucesso para PDF e CSV em 'picos_vales_emprestimo.pdf' e 'picos_vales_emprestimo.csv'");
                                } catch (PdfException | IOException e) {
                                    print("Erro ao exportar: " + e.getMessage());
                                }
                                break;

                            case "4":
                                print("Exportação da análise de picos e vales cancelada.");
                                break;

                            default:
                                print("Entrada inválida.");
                                break;
                        }
                        print("");
                        break;

						default:
							print("Opção inválida. Por favor, tente novamente.");
							break;

                    case "5":
                        print("\n--- Análise de Popularidade de Jogos ---");
                        Map<Jogo, Long> popularidadeJogos = gerencia.analisarPicosValesPopularidadeJogos();

                        if (popularidadeJogos.isEmpty()) {
                            print("Não há empréstimos registrados para analisar a popularidade dos jogos.");
                        } else {
                            print("\nClassificação dos Jogos por Popularidade (Total de Empréstimos):");
                            popularidadeJogos.entrySet().stream()
                                    .sorted(Map.Entry.<Jogo, Long>comparingByValue().reversed()) // Ordena do mais popular para o menos
                                    .forEach(entry -> {
                                        print("  " + entry.getKey().getNome() + ": " + entry.getValue() + " empréstimos");
                                    });
                        }


                        print("Exportar análise de popularidade para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String exportOptionPopularidade = stdin.nextLine();

                        switch (exportOptionPopularidade) {
                            case "1":
                                try {
                                    JogoPopularidadeExporter.exportarCSV(popularidadeJogos);
                                    print("Exportado com sucesso para CSV em 'popularidade_jogos.csv'");
                                } catch (IOException e) {
                                    print("Erro ao exportar para CSV: " + e.getMessage());
                                }
                                break;

                            case "2":
                                try {
                                    JogoPopularidadeExporter.exportarPDF(popularidadeJogos);
                                    print("Exportado com sucesso para PDF em 'popularidade_jogos.pdf'");
                                } catch (PdfException | IOException e) { // Captura ambas as exceções
                                    print("Erro ao exportar para PDF: " + e.getMessage());
                                }
                                break;

                            case "3":
                                try {
                                    JogoPopularidadeExporter.exportarCSV(popularidadeJogos);
                                    JogoPopularidadeExporter.exportarPDF(popularidadeJogos);
                                    print("Exportado com sucesso para PDF e CSV em 'popularidade_jogos.pdf' e 'popularidade_jogos.csv'");
                                } catch (PdfException | IOException e) { // Captura ambas as exceções
                                    print("Erro ao exportar: " + e.getMessage());
                                }
                                break;

                            case "4":
                                print("Exportação da análise de popularidade cancelada.");
                                break;

                            default: // 'default' em minúsculas
                                print("Entrada inválida.");
                                break;
                        }
						break;
					}
                user = "4";
            }

        } while (!user.equals("0"));

        stdin.close();
        print("Obrigado pela presença.");
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Pagina_principal.fxml"));
        Scene scene = new Scene(root, 650, 450);
        stage.setTitle("Biblioteca de jogos");
        stage.setScene(scene);
        stage.show();

    }
}
