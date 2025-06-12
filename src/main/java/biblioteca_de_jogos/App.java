package biblioteca_de_jogos;

import biblioteca_de_jogos.assets.Gerencia;
import biblioteca_de_jogos.assets.Jogos;
import biblioteca_de_jogos.assets.Usuarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeParseException;

/** A classe estática que representa o sistema em funcionalidade,
 * faz as classes conversarem e interage com o usuário. */
public class App {

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

        Usuarios usuarios = new Usuarios();
        Jogos jogos = new Jogos();
        Gerencia gerencia = new Gerencia();
        Relatorio relatorio = new Relatorio();

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

                    case default:
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
                        print("Defina o nome, editor, descrição, tempo de partida (em minutos), mínimo e máximo de jogadores e a quantidade de cópias desse jogo: ");
                        if (jogos.adicionarJogo(
                                jogos.criarJogo(
                                        stdin.nextLine(),
                                        stdin.nextLine(),
                                        stdin.nextLine(),
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
                        String arg3 = stdin.nextLine();
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

                    case default:
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
                        gerencia.fazerDevolucao(stdin.nextInt(), pegarObservacoes(stdin.nextLine(), stdin));
                        break;

                    case "3":
                        print("Defina o id de quem e de que jogo será reservado: ");
                        if (gerencia.reservarJogo(
                                stdin.nextInt(),
                                jogos.buscarJogo(stdin.nextInt())
                        )) { print("Reserva feita com sucesso.");} else {
                            print("Reserva falhou.");}
                        break;

                    case "4":
                        print("");
                        print_lista(gerencia.getEmprestimos());
                        print("");
                        break;

                    case default:
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
                        LocalDate inicio = LocalDate.parse(stdin.nextLine());
                        print("Digite a data final (Formatação dd/mm/aaaa)");
                        LocalDate fim = LocalDate.parse(stdin.nextLine());

                        print("Emprestimos entre " + inicio + "e " + fim);
                        String exportar_emprestimos = gerencia.getEmprestimos().stream().filter(e -> !e.getDataEmprestimo().isBefore(inicio) && !e.getDataEmprestimo().isAfter(fim)).forEach(System.out::println);

                        print("Exportar para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String temp1 = stdin.nextLine();

                        switch (temp1){
                            case "1":
                                exportarCSV(exportar_emprestimos);
                                print("Exportado com sucesso para CSV");
                                break;

                            case "2":
                                exportarPDF(exportar_emprestimos);
                                print("Exportado com sucesso para PDF");
                                break;

                            case "3":
                                exportarCSV(exportar_emprestimos);
                                exportarPDF(exportar_emprestimos);
                                print("Exportado com sucesso para PDF e CSV");
                                break;

                            case "4":
                                print("");
                                break;

                            case default:
                                print("Entrada inválida");
                                break;
                        }
                        break;

                    case "2":
                        print_lista(usuarios.getUsuarios());
                        String exportar_usuarios;


                        print("Exportar para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String temp2 = stdin.nextLine();

                        switch (temp2){
                            case "1":
                                exportarCSV(exportar_usuarios);
                                print("Exportado com sucesso para CSV");
                                break;

                            case "2":
                                exportarPDF(exportar_usuarios);
                                print("Exportado com sucesso para PDF");
                                break;

                            case "3":
                                exportarCSV(exportar_usuarios);
                                exportarPDF(exportar_usuarios);
                                print("Exportado com sucesso para PDF e CSV");
                                break;

                            case "4":
                                print("");
                                break;

                            case default:
                                print("Entrada inválida");
                                break;
                        }
                        break;

                    case "3":
                        print_lista(jogos.getJogos());
                        String exportar_jogos = gerencia.getJogos;
                        print("Exportar para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String temp3 = stdin.nextLine();

                        switch (temp3){
                            case "1":
                                exportarCSV(exportar_jogos);
                                print("Exportado com sucesso para CSV");
                                break;

                            case "2":
                                exportarPDF(exportar_jogos);
                                print("Exportado com sucesso para PDF");
                                break;

                            case "3":
                                exportarCSV(exportar_jogos);
                                exportarPDF(exportar_jogos);
                                print("Exportado com sucesso para PDF e CSV");
                                break;

                            case "4":
                                print("");
                                break;

                            case default:
                                print("Entrada inválida");
                                break;
                        }
                        break;

                    case "4":
                        String exportar_picos_vales_emprestimo;

                        print("Exportar para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String temp4 = stdin.nextLine();

                        switch (temp4){
                            case "1":
                                exportarCSV(exportar_picos_vales_emprestimo);
                                print("Exportado com sucesso para CSV");
                                break;

                            case "2":
                                exportarPDF(exportar_picos_vales_emprestimo);
                                print("Exportado com sucesso para PDF");
                                break;

                            case "3":
                                exportarCSV(exportar_picos_vales_emprestimo);
                                exportarPDF(exportar_picos_vales_emprestimo);
                                print("Exportado com sucesso para PDF e CSV");
                                break;

                            case "4":
                                print("");
                                break;

                            case default:
                                print("Entrada inválida");
                                break;
                        }


                        print("");
                        break;

                    case "5":
                        String exportar_picos_vales_popularidade;

                        print("Exportar para: ");
                        print("1. CSV");
                        print("2. PDF");
                        print("3. PDF e CSV");
                        print("4. Não exportar.");

                        String temp5 = stdin.nextLine();

                        switch (temp5){
                            case "1":
                                exportarCSV(exportar_picos_vales_popularidade);
                                print("Exportado com sucesso para CSV");
                                break;

                            case "2":
                                exportarPDF(exportar_picos_vales_popularidade);
                                print("Exportado com sucesso para PDF");
                                break;

                            case "3":
                                exportarCSV(exportar_picos_vales_popularidade);
                                exportarPDF(exportar_picos_vales_popularidade);
                                print("Exportado com sucesso para PDF e CSV");
                                break;

                            case "4":
                                print("");
                                break;

                            case default:
                                print("Entrada inválida");
                                break;
                        }
                        break;

                    case default:
                        print("Entrada inválida");
                        break;
                }
                user = "4";
            }

        } while (!user.equals("0"));

        stdin.close();
        print("Obrigado pela presença.");
    }

}
