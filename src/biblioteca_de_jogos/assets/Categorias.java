package biblioteca_de_jogos.assets;

import java.util.ArrayList;
import java.util.List;

import biblioteca_de_jogos.classes.Categoria;

/** Classe repositório que irá conter todas as categorias dos jogos. */
public class Categorias {

	/** A lista que contém todas as categorias. */
	private List<Categoria> categorias;

	/** Construtor que inicia o ArrayList vazio. */
	public Categorias() {
		this.categorias = new ArrayList<Categoria>();
	}

	/** Cria uma categoria se não houver uma com mesmo nome. */
	public boolean criarCategoria(String nome) {
		boolean retorno = (nome != null && buscarCategoria(nome) == null);
		if (retorno) {
			this.categorias.add(
				new Categoria(nome)
			);
		}
		return retorno;
	}

	/** Procura uma categoria baseado no seu nome. */
	public Categoria buscarCategoria(String nome) {
		Categoria retorno = null;
		for(Categoria categoria: this.categorias) {
			if (categoria.getNome().equalsIgnoreCase(nome)) {
				retorno = categoria;
			}
		}
		return retorno;
	}

	/** Remove uma categoria baseado no seu nome. */
	public boolean removerCategoria(String nome) {
		Categoria categoria = buscarCategoria(nome);
		boolean retorno = (nome != null && categoria != null);
		if (retorno) {
			this.categorias.remove(categoria);
		}
		return retorno;
	}

	public List<Categoria> getCategorias() {
		return this.categorias;
	}
}
