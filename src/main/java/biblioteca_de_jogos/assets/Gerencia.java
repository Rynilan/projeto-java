package biblioteca_de_jogos.assets;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.classes.Jogo;

public class Gerencia {
	private List<Emprestimo> emprestimos;
	private Penalidades penalidades;
	private Reservas reservas;
	private int id;

	public Gerencia() {
		this.id = -1;
		this.emprestimos = new ArrayList<Emprestimo>();
		this.reservas = new Reservas();
		this.penalidades = new Penalidades();
	}

	public Emprestimo buscarEmprestimo(int idEmprestimo) {
		Emprestimo achado = null;
		for (Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.getId() == idEmprestimo) {
				achado = emprestimo;
			}
		}
		return achado;
	}

	public int getEmprestimosPorUsuario(int idUsuario) {
		int achados = 0;
		for (Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.getIdUsuario() == idUsuario) {
				achados += 1;
			}
		}
		return achados;
	}

	public int getEmprestimosPorJogo(int idJogo) {
		int achados = 0;
		for (Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.getIdJogo() == idJogo) {
				achados += 1;
			}
		}
		return achados;
	}

	public List<Emprestimo> getEmprestimosDoUsuario(int idUsuario) {
		List<Emprestimo> achados = new ArrayList<Emprestimo>();
		for (Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.getIdUsuario() == idUsuario) {
				achados.add(emprestimo);
			}
		}
		return achados;
	}

	public List<Emprestimo> getEmprestimosDoJogo(int idJogo) {
		List<Emprestimo> achados = new ArrayList<Emprestimo>();
		for (Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.getIdJogo() == idJogo) {
				achados.add(emprestimo);
			}
		}
		return achados;
	}
	public boolean usuarioTemEmprestimoDesseJogo(int idUsuario, int idJogo) {
		boolean tem = false;
		for (Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.getIdJogo() == idJogo && emprestimo.getIdUsuario() == idUsuario) {
				tem = true;
			}
		}
		return tem;
	}

	public boolean usuarioDisponivelParaEmprestimo(Usuario usuario) {
		return (this.getEmprestimosPorUsuario(usuario.getId()) <= 3);
	}

	public boolean jogoDisponivelParaEmprestimo(Jogo jogo) {
		return (this.getEmprestimosPorJogo(jogo.getId()) < jogo.getQtdCopias());
	}

	public boolean fazerEmprestimo(Usuario usuario, Jogo jogo) {
		boolean pode = (usuario != null && jogo != null && this.jogoDisponivelParaEmprestimo(jogo) && this.usuarioDisponivelParaEmprestimo(usuario) && !usuarioTemEmprestimoDesseJogo(usuario.getId(), jogo.getId()));
		if (pode) {
			this.id++;
			this.emprestimos.add(
				new Emprestimo(this.id, usuario.getId(), jogo.getId())
			);
		}
		return pode;
	}

	public void fazerDevolucao(int idEmprestimo, List<String> observacao) {
		Emprestimo emprestimo = this.buscarEmprestimo(idEmprestimo);
		if (emprestimo.atrasado()) {
			this.penalidades.criarPenalidade(emprestimo);
		}
		emprestimo.devolver(observacao);
	}
	
	public boolean reservarJogo(int idUsuario, Jogo jogo) {
		boolean pode = !(this.jogoDisponivelParaEmprestimo(jogo));
		if (pode) {
			this.reservas.fazerReserva(jogo.getId(), idUsuario);
		}
		return pode;
	}

	public Reserva verSeTemReserva(int idEmprestimo) {
		Reserva reserva = this.reservas.buscarPrimeiraReservaDoJogo(this.buscarEmprestimo(idEmprestimo).getIdJogo());
		return reserva;
	}

	public List<Jogo> pegarDisponiveis(List<Jogo> jogos) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: jogos) {
			if (this.jogoDisponivelParaEmprestimo(jogo)) {
				achados.add(jogo);
			}
		}
		return achados;
	}

	public List<Reserva> pegarReservasDeUsuario(int idUsuario) {
		return reservas.reservasDeUsuario(idUsuario);
	}

	public List<Emprestimo> getEmprestimos() {
		return this.emprestimos;
	}
}
