package biblioteca_de_jogos.control;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Penalidade;
import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.model.Penalidades;


public class ControladorDePenalidades {

	private static ControladorDePenalidades instancia = null;
	private Penalidades penalidades;
	private Long id;

	private ControladorDePenalidades() {
		this.penalidades = Penalidades.getInstance();
		this.id = -1L;
	}

	public static ControladorDePenalidades getInstance() {
		if (ControladorDePenalidades.instancia == null) {
			ControladorDePenalidades.instancia = new ControladorDePenalidades();
		}
		return ControladorDePenalidades.instancia;
	}

	public void criarPenalidade(Emprestimo emprestimo) {
		this.id++;
		this.penalidades.getPenalidades().add(
			new Penalidade(this.id, String.format("Usuário nº%d atraso da entrega de um jogo.", emprestimo.getIdUsuario()), "bloqueio", emprestimo.getIdUsuario(), emprestimo)
		);
	}

	public List<Penalidade> penalidadesDeUsuario(Long idUsuario) {
		List<Penalidade> achadas = new ArrayList<Penalidade>();
		for (Penalidade penalidade: this.penalidades.getPenalidades()) {
			if (penalidade.getIdUsuario() == idUsuario) {
				achadas.add(penalidade);
			}
		}
		return achadas;
	}
}
