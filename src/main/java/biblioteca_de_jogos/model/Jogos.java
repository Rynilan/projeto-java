package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Jogo;

public class Jogos {
	private static Jogos self = null;
	private List<Jogo> jogos;

	private Jogos() {
		this.jogos = new ArrayList<Jogo>();
		Jogos.self = this;
	}

	public static Jogos getInstance() {
		if (Jogos.self == null) {
			Jogos.self = new Jogos();
		}
		return Jogos.self;
	}

	public List<Jogo> getJogos() {
		return this.jogos;
	}

}
