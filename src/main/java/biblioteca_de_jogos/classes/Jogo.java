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
           "id=" + id +
           ", nome='" + nome + '\'' +
           ", editor='" + editor + '\'' +
           ", descricao='" + descricao + '\'' +
           ", tempoPartida=" + tempoPartida + " min" +
           ", minJogadores=" + minJogadores +
           ", maxJogadores=" + maxJogadores +
           ", qtdCopias=" + qtdCopias +
           ", idCategoria=" + idCategoria +
           ", disponivel=" + (estaDisponivel() ? "Sim" : "Não") +
           '}';
}

	
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEditor() {
        return editor;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getTempoPartida() {
        return tempoPartida;
    }

    public int getMinJogadores() {
        return minJogadores;
    }

    public int getMaxJogadores() {
        return maxJogadores;
    }

    public int getQtdCopias() {
        return qtdCopias;
    }

    public void setQtdCopias(int qtdCopias) {
        this.qtdCopias = qtdCopias;
    }

    public boolean estaDisponivel() {
        return qtdCopias > 0;
    }

	public Long getIdCategoria() {
		return this.idCategoria;
	}
    
}
