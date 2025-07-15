package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Jogo;

public class Jogos {
	private static Jogos instancia = null;
	private List<Jogo> jogos;

	private Jogos() {
		this.jogos = new ArrayList<Jogo>();
		Jogos.instancia = this;
	}

	public static Jogos getInstance() {
		if (Jogos.instancia == null) {
			Jogos.instancia = new Jogos();
		}
		return Jogos.instancia;
	}

	public List<Jogo> getJogos() {
		return this.jogos;
	}

}
