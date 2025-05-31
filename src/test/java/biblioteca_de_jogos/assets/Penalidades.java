package biblioteca_de_jogos.assets;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Penalidade;
import biblioteca_de_jogos.classes.Emprestimo;


public class Penalidades {

	private List<Penalidade> penalidades;
	private int id;

	public Penalidades() {
		this.penalidades = new ArrayList<Penalidade>();
		this.id = -1;
	}

	public void criarPenalidade(Emprestimo emprestimo) {
		this.id++;
		this.penalidades.add(
			new Penalidade(this.id, String.format("Usuário nº%i atraso da entrega de um jogo.", emprestimo.getIdUsuario()), "bloqueio", emprestimo.getIdUsuario(), emprestimo)
		);
	}

	public List<Penalidade> penalidadesDeUsuario(int idUsuario) {
		List<Penalidade> achadas = new ArrayList<Penalidade>();
		for (Penalidade penalidade: this.penalidades) {
			if (penalidade.getIdUsuario() == idUsuario) {
				achadas.add(penalidade);
			}
		}
		return achadas;
	}
}
