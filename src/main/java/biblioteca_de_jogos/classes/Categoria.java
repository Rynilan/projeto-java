package biblioteca_de_jogos.classes;

/** Classe que representa a categoria de um jogo. */
public class Categoria{
	
	/** Identificador único de uma categoria. */
	private int id;
	/** Nome dado à categoria. */
	private String nome;

	/** Construtor com nome e id (este dado pelo repositório. */
	public Categoria(String nome, int id) {
		this.nome = nome;
		this.id = id;
	}

	@Override
        public String toString() {
		return "Categoria{id=" + id + ", nome='" + nome + "'}";
	}
	
	/** Retorna o nome da categoria */
	public String getNome() {
		return this.nome;
	}

	/** Retorna o identificador da categoria. */
	public int getId() {
		return this.id;
	}

}
