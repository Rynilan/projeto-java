package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Penalidade;

public class Penalidades {
	private static Penalidades self = null;
 	private List<Penalidade> penalidades;

	private Penalidades() {
		this.penalidades = new ArrayList<Penalidade>();
	}

	public static Penalidades getInstance() {
		if (Penalidades.self == null) {
			Penalidades.self = new Penalidades();
		}
		return Penalidades.self;
	}

	public List<Penalidade> getPenalidades() {
		return this.penalidades;
	}
}
