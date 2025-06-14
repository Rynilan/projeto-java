package biblioteca_de_jogos.assets;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.time.YearMonth;
import java.util.stream.Collectors;

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



	public Map<Jogo, Long> analisarPicosValesPopularidadeJogos(Jogos jogos) {
		// Mapeia cada Jogo (resolvendo o ID para o objeto Jogo completo) para a contagem de empréstimos
		Map<Integer, Long> contagemPorJogoId = emprestimos.stream()
				.collect(
						Collectors.groupingBy(
								Emprestimo::getIdJogo, // Agrupa pelo ID do jogo
								Collectors.counting()   // Conta os empréstimos por ID de jogo
						)
				);

		// Converte o mapa de ID para Jogo, usando o jogosManager para buscar o objeto Jogo
		Map<Jogo, Long> popularidadeJogos = new LinkedHashMap<>(); // LinkedHashMap para manter a ordem (opcional)
		contagemPorJogoId.entrySet().stream()
				.sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()) // Ordena do mais popular para o menos
				.forEach(entry -> {
					Jogo jogo = jogos.buscarJogo(entry.getKey());
					if (jogo != null) {
						popularidadeJogos.put(jogo, entry.getValue());
					}
				});

		return popularidadeJogos;
	}


	public Map<YearMonth, Long> analisarPicosValesEmprestimos() {
		// Mapeia cada mês/ano para a contagem de empréstimos
		Map<YearMonth, Long> contagemPorMes = emprestimos.stream()
				.collect(
						java.util.stream.Collectors.groupingBy(
								emprestimo -> YearMonth.from(emprestimo.getData()),
								java.util.stream.Collectors.counting()
						)
				);

		return contagemPorMes;
	}

	public List<Reserva> pegarReservasDeUsuario(int idUsuario) {
		return reservas.reservasDeUsuario(idUsuario);
	}

	public List<Emprestimo> getEmprestimosDoUsuario(int idUsuario) {
		List<Emprestimo> encontrados = new ArrayList<Emprestimo>();
		for (Emprestimo emprestimo: this.emprestimos) {
			if (emprestimo.getIdUsuario() == idUsuario) {
				encontrados.add(emprestimo);
			}
		}
		return encontrados;
	}

	public List<Emprestimo> getEmprestimos() {
		return this.emprestimos;
	}
}
