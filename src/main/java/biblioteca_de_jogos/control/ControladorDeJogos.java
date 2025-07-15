package biblioteca_de_jogos.control;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.model.Jogos;

public class ControladorDeJogos {
	private Long id;
	private Jogos jogos;
	private static ControladorDeJogos instancia = null;
	
	private ControladorDeJogos() {
		this.id = -1L;
		this.jogos = Jogos.getInstance();
		ControladorDeJogos.instancia = this;
	}

	/** Método para ter instância única da classe controle. */
	public static ControladorDeJogos getInstance() {
		if (ControladorDeJogos.instancia == null) {
			ControladorDeJogos.instancia = new ControladorDeJogos();
		}
		return ControladorDeJogos.instancia;
	}

	public Jogo buscarJogo(Long id) {
		Jogo achado = null;
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getId() == id) {
				achado = jogo;
			}
		}
		return achado;
	}

	public Jogo criarJogo(String nome, String editor, String descricao, int tempoPartida, int minJogadores, int maxJogadores, int copias, Long idCategoria) {
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
			if (Objects.equals(jogo.getEditor(), editor)) {
				achados.add(jogo);
			}
		}
		return achados;
	}

	public List<Jogo> buscarPorNome(String nome) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (Objects.equals(jogo.getNome(), nome)) {
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

	public List<Jogo> buscarPorTempodeJogo(int TempodeJogo) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getTempoPartida() == TempodeJogo) {
				achados.add(jogo);
			}
		}
		return achados;
	}

	public List<Jogo> jogosDisponiveis() {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.estaDisponivel()) {
				achados.add(jogo);
			}
		}
		return achados;
	}

	public List<Jogo> getJogos() {
		return this.jogos.getJogos();
	}

	public List<Jogo> buscarPorCategoria(Long idCategoria) {
		List<Jogo> achados = new ArrayList<Jogo>();
		for (Jogo jogo: this.jogos.getJogos()) {
			if (jogo.getIdCategoria() == idCategoria) {
				achados.add(jogo);
			}
		}
		return achados;
	}
}
