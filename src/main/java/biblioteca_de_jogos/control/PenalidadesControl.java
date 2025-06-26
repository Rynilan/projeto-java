package biblioteca_de_jogos.control;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Penalidade;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.model.Penalidades;


public class PenalidadesControl {

	private static PenalidadesControl self = null;
	private Penalidades penalidades;
	private int id;

	private PenalidadesControl() {
		this.penalidades = Penalidades.getInstance();
		this.id = -1;
	}

	public static PenalidadesControl getInstance() {
		if (PenalidadesControl.self == null) {
			PenalidadesControl.self = new PenalidadesControl();
		}
		return PenalidadesControl.self;
	}

	public void criarPenalidade(Emprestimo emprestimo) {
		this.id++;
		this.penalidades.getPenalidades().add(
			new Penalidade(this.id, String.format("Usuário nº%i atraso da entrega de um jogo.", emprestimo.getIdUsuario()), "bloqueio", emprestimo.getIdUsuario(), emprestimo)
		);
	}

	public List<Penalidade> penalidadesDeUsuario(int idUsuario) {
		List<Penalidade> achadas = new ArrayList<Penalidade>();
		for (Penalidade penalidade: this.penalidades.getPenalidades()) {
			if (penalidade.getIdUsuario() == idUsuario) {
				achadas.add(penalidade);
			}
		}
		return achadas;
	}
}
