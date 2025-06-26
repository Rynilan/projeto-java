package biblioteca_de_jogos.control;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.model.Jogos;

public class JogosControl {
	private int id;
	private Jogos jogos;
	private static JogosControl self = null;
	
	private JogosControl() {
		this.id = -1;
		this.jogos = Jogos.getInstance();
		JogosControl.self = this;
	}

	/** Método para ter instância única da classe controle. */
	public static JogosControl getInstance() {
		if (JogosControl.self != null) {
			JogosControl.self = new JogosControl();
		}
		return JogosControl.self;
	}

	public Jogo buscarJogo(int id) {
		Jogo achado = null;
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getId() == id) {
				achado = jogo;
			}
		}
		return achado;
	}

	public Jogo criarJogo(String nome, String editor, String descricao, int tempoPartida, int minJogadores, int maxJogadores, int copias, int idCategoria) {
		Jogo jogo = null;
		if (nome != null && editor != null && tempoPartida > 0 && minJogadores > 0 && minJogadores < maxJogadores && copias > 0) {
			jogo = new Jogo(this.id, nome, editor, descricao, tempoPartida, minJogadores, maxJogadores, copias, idCategoria);
		}
		return jogo;
	}

	public boolean adicionarJogo(Jogo jogo) {
		boolean pode = (jogo != null && this.buscarJogo(jogo.getId()) == null);
		if (pode) { this.jogos.getJogos().add(jogo); this.id++; } 
		return pode;
	}

	public boolean removerJogo(Jogo jogo) {
		boolean pode = (jogo != null && this.buscarJogo(jogo.getId()) != null);
		if (pode) { this.jogos.getJogos().remove(jogo); }
		return pode;
	}

	public List<Jogo> buscarPorEditor(String editor) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getEditor() == editor) {
				achados.add(jogo);
			}
		}
		return achados;
	}

	public List<Jogo> buscarPorNome(String nome) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getNome() == nome) {
				achados.add(jogo);
			}
		}
		return achados;
	}

	public List<Jogo> buscarPorQuantiaDeJogadores(int quantiaDeJogadores) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getMinJogadores() <= quantiaDeJogadores && jogo.getMaxJogadores() >= quantiaDeJogadores) {
				achados.add(jogo);
			}
		}
		return achados;
	}

	public List<Jogo> getJogos() {
		return this.jogos.getJogos();
	}

	public List<Jogo> buscarPorCategoria(int idCategoria) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getIdCategoria() == idCategoria) {
				achados.add(jogo);
			}
		}
		return achados;
	}
}
