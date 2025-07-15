package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Penalidade;

public class Penalidades {
	private static Penalidades instancia = null;
 	private List<Penalidade> penalidades;

	private Penalidades() {
		this.penalidades = new ArrayList<Penalidade>();
	}

	public static Penalidades getInstance() {
		if (Penalidades.instancia == null) {
			Penalidades.instancia = new Penalidades();
		}
		return Penalidades.instancia;
	}

	public List<Penalidade> getPenalidades() {
		return this.penalidades;
	}
}
