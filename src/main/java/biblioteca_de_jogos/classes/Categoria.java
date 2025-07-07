package biblioteca_de_jogos.classes;

public enum Categoria{
	
	TATICO(1L, "Tático", "Jogos que exigem raciocínio estratégico e tomada de decisões precisas a cada turno"),
	DEDUTIVO(2L, "Dedutivo", "Jogos que envolvem lógica, observação e dedução para descobrir informações ocultas"),
	CARTAS(3L, "Cartas", "Jogos centrados no uso de baralhos, sejam eles personalizados ou tradicionais"),
	CLASSICO(4L, "Clássico", "Jogos consagrados pelo tempo, com regras simples e amplamente conhecidos"),
	COOPERATIVO(5L, "Cooperativo", "Jogos em que os participantes trabalham em equipe contra o próprio jogo"),
	COMPETITIVO(6L, "Competitivo", "Jogos em que os jogadores competem entre si para alcançar objetivos individuais"),
	TABULEIRO(7L, "Tabuleiro", "Jogos físicos com peças que se movimentam sobre um tabuleiro"),
	NARRATIVO(8L, "Narrativo", "Jogos que contam histórias envolventes e tomam decisões que afetam a trama");

	/** Identificador único de uma categoria. */
	private final Long id;
	/** Nome dado à categoria. */
	private final String nome;
	/** Descrição breve da categoria. */
	private final String descricao;

	/** Construtor com nome e id (este dado pelo repositório. */
	private Categoria(Long id, String nome, String descricao) {
		this.nome = nome;
		this.id = id;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Categoria{id=" + id + ", nome='" + nome + "', descrição='" + descricao + "'}";
	}
	
	/** Retorna o nome da categoria */
	public String getNome() {
		return this.nome;
	}

	/** Retorna o identificador da categoria. */
	public Long getId() {
		return this.id;
	}

	public String getDescricao() {
		return this.descricao;
	}

}
