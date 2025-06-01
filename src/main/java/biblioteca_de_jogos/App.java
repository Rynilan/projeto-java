package biblioteca_de_jogos;

import biblioteca_de_jogos.assets.Gerencia;
import biblioteca_de_jogos.assets.Jogos;
import biblioteca_de_jogos.assets.Usuarios;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** A classe estática que representa o sistema em funcionalidade,
 * faz as classes conversarem e interage com o usuário. */
public class App {

	public static void print(String string) {
		System.out.println(string);
	}

	public static void print_lista(List<E> lista) {
		for (E elemento: lista) {
			System.out.println(lista);
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

	public static void main(String args[]) {

		Usuarios usuarios = new Usuarios();
		Jogos jogos = new Jogos();
		Gerencia gerencia = new Gerencia();

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
					print_lista(gerencia.getEmprestimosPorUsuario(n));
					print("");
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
					pritn("");
					print_lista(jogos.getJogos());
					pritn("");
					break;

					case "4":
					print("");
					print_lista(gerencia.pegarDisponiveis());
					print("");

					case "5":
					print("defina o valor para o dado parâmetro: ");
					String arg1 = stdin.nextLine();
					print("");
					print_lista(jogos.buscarPorEditor(arg1));
					print("");
					break;

					case "6":
					print("defina o valor para o dado parâmetro: ");
					String arg2 = stdin.nextInt();
					print("");
					print_lista(jogos.buscarPorQuantiaDeJogadores(arg2));
					print("");
					break;

					case "7":
					print("defina o valor para o dado parâmetro: ");
					String arg3 = stdin.nextInt();
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
						stdin.nextInt(),
						stdin.nextInt()
					)) { print("Empréstimo feito com sucesso."); 
					} else { print("Empréstimo deu erro.");}
					break;

					case "2":
					print("Defina o id do empréstimo a ser devolvido e se há alguma observação (s ou n).");
					gerencia.fazerDevolucao(stdin.nextLine(), pegarObservacoes(stdin.nextLine()));
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

				print("Exportar para: ");
				print("1. CSV");
				print("2. PDF");
				print("3. PDF e CSV");
				print("4. Não exportar.");

				String temp = stdin.nextLine();

				// Implementação dos relatórios.
				
				user = "4";
			}

		} while (!(user == "0"));

		stdin.close();
		stdout.println("Obrigado pela presença.");
	}

}
