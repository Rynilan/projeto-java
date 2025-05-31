public class Jogo {
    private int id;
    private String nome;
    private String editor;
    private String descricao;
    private int tempoPartida;
    private int minJogadores;
    private int maxJogadores;
    private int qtdCopias;


    public Jogo(int id, String nome, String editor, String descricao, int tempoPartida,
                int minJogadores, int maxJogadores, int qtdCopias) {
        this.id = id;
        this.nome = nome;
        this.editor = editor;
        this.descricao = descricao;
        this.tempoPartida = tempoPartida;
        this.minJogadores = minJogadores;
        this.maxJogadores = maxJogadores;
        this.qtdCopias = qtdCopias;
    }


    public int getId() {
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
}
