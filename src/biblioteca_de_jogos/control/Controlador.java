package biblioteca_de_jogos.control;

import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.classes.Emprestimo;

import biblioteca_de_jogos.assets.Jogos;
import biblioteca_de_jogos.assets.Usuarios;
import biblioteca_de_jogos.assets.Emprestimos;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;

/** Classe que terá as regras de negócio. */
public class Controlador {

	/** Repositório de jogos. */
	private Jogos jogos;
	/** Repositório de usuários. */
	private Usuarios usuarios;
	/** Repostiório de empréstimos. */
	private Emprestimos emprestimos;

	public Controlador() {
		this.jogos = new Jogos();
		this.usuarios = new Usuarios();
		this.emprestimos = new Emprestimos();
	}

	/** Criar usário */
	public boolean criarUsuario(String email, String nome, String telefone, boolean status) {
		// Internamente usuários tem um arraylist vazio com os empréstimos que fizeram,
		// também possuem outro representando as reservas que já fizeram.
		return this.usuarios.adicionarUsuario(
			new Usuario(nome, email, telefone, status)
		);
	}

	/** Cria um jogo */
	public boolean criarJogo(
		String nome, String descricao, int tempoDePartida, String editor,
		int minimoDeJogadores, int maximoDeJogadores, int copias
	) {
		return this.jogos.adicionarJogo(
			// Jogos tem categoria.
			new Jogo(
				nome, descricao, tempoDePartida, editor,
				minimoDeJogadores, maximoDeJogadores, copias
			)
		);
	}
	
	/** Vê se há possibilidade de execução de empréstimo de um jogo 
	 * para um determinado usuário (retorna o status da execução. */
	public boolean fazerEmprestimo(Jogo jogo, Usuario usuario) {
		boolean retorno = (jogo != null && usuario != null);
		retorno = (retorno) ? (
			jogo.getCopias() > this.emprestimos.emprestimosPendentesCom(jogo) && 
			this.emprestimos.emprestimosCom(usuario) < 3
		): retorno;
		if (retorno) {
			Emprestimo emprestimo = new Emprestimo(jogo, usuario, LocalDate.now());
			usuario.adicionarEmprestimo(emprestimo);
			this.emprestimos.adicionarEmprestimo(emprestimo);
			
		}
		return retorno;
	}

}
