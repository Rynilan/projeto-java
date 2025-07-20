package biblioteca_de_jogos.classes;

public class Jogo {
    private Long id;
    private String nome;
    private String editor;
    private String descricao;
    private int tempoPartida;
    private int minJogadores;
    private int maxJogadores;
    private int qtdCopias;
	private Long idCategoria;


    public Jogo(Long id, String nome, String editor, String descricao, int tempoPartida,
                int minJogadores, int maxJogadores, int qtdCopias, Long idCategoria) {
        this.id = id;
        this.nome = nome;
        this.editor = editor;
        this.descricao = descricao;
        this.tempoPartida = tempoPartida;
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.qtdCopias = qtdCopias;
		this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
     return "Jogo{" +
           "id=" + this.id +
           ", nome='" + this.nome + '\'' +
           ", editor='" + this.editor + '\'' +
           ", descricao='" + this.descricao + '\'' +
           ", tempoPartida=" + this.tempoPartida + " min" +
           ", minJogadores=" + this.minJogadores +
           ", maxJogadores=" + this.maxJogadores +
           ", qtdCopias=" + this.qtdCopias +
           ", idCategoria=" + this.idCategoria +
           ", disponivel=" + (this.estaDisponivel() ? "Sim" : "Não") +
           '}';
}

	
    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEditor() {
        return this.editor;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public int getTempoPartida() {
        return this.tempoPartida;
    }

    public int getMinJogadores() {
        return this.minJogadores;
    }

    public int getMaxJogadores() {
        return this.maxJogadores;
    }

    public int getQtdCopias() {
        return this.qtdCopias;
    }

    public void setQtdCopias(int qtdCopias) {
        this.qtdCopias = qtdCopias;
    }

    public boolean estaDisponivel() {
        return this.qtdCopias > 0;
    }

    public String getDisponivel() {
        return this.estaDisponivel() ? "Sim" : "Não";
    }

	public Long getIdCategoria() {
		return this.idCategoria;
	}
    
}
