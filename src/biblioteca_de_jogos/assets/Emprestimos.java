package biblioteca_de_jogos.assets;

import java.util.ArrayList;
import java.util.List;

import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Penalidade;

/** Classe que irá conter todos os empréstimos. */
public class Emprestimos {
	
	/** Lista que virá conter todos os empréstimos. */
	private List<Emprestimo> emprestimos;

	/** Inicializa a lista vazia. */
	public Emprestimos() {
		this.emprestimos = new ArrayList<Emprestimo>();
	}

	/** Pega um empréstimo baseado no seu usuário e jogo. */
	public Emprestimo buscarEmmprestimo(Usuario usuario, Jogo jogo) {
		Emprestimo retorno = null;
		for(Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.compareUsuario(usuario) && emprestimo.compareJogo(jogo)) {
				retorno = emprestimo;
			}
		}
		return retorno;
	}

	/** Verifica quantos empréstimos pendentes tem um usuário. */
	public int emprestimosCom(Usuario usuario) {
		int retorno = -1;
		if (usuario != null) {
			retorno += 1;
			for (Emprestimo emprestimo: this.emprestimos) {
				if (emprestimo.getDevolucao() == null && emprestimo.compararUsuario(usuario)) {
					retorno += 1;
				}
			}
		}
		return retorno;
	}

	/** Verifica quantos empréstimos pendentes tem um jogo. */
	public int emprestimosCom(Jogo jogo) {
		int retorno = -1;
		if (jogo != null) {
			retorno += 1;
			for (Emprestimo emprestimo: this.emprestimos) {
				if (emprestimo.getDevolucao() == null && emprestimo.compararJogo(jogo)) {
					retorno += 1;
				}
			}
		}
		return retorno;
	}

	/** Registra a devolução de um jogo. */
	public boolean devolverJogo(Jogo jogo, Usuario usuario, String observacao) {
		boolean retorno;
		retorno = (jogo != null && usuario != null);
		Emprestimo emprestimo = null;
		if (retorno) {
			emprestimo = this.buscarEmmprestimo(usuario, jogo);
			retorno = (emprestimo != null);
		}
		if (retorno) {
			emprestimo.devolver(jogo, usuario, observacao);
			if (emprestimo.atrasado()) {
				Penalidade penalidade = new Penalidade();
				usuario.penalizar(penalidade);
			}
		}
		return retorno;
	}

	/** Adiciona um empréstimo à lista. */
	public boolean criarEmprerstimo(Emprestimo emprestimo) {
		boolean retorno = (emprestimo != null);
		if (retorno) {
			this.emprestimos.add(emprestimo);
		}
		return retorno;
	}

	/** Retorna uma lista de todos os empréstimos. */
	public List<Emprestimo> getEmprestimos() {
		return this.emprestimos;
	}

}
