package biblioteca_de_jogos.assets;

import biblioteca_de_jogos.classes.Jogo;

import java.util.ArrayList;
import java.util.List;


/** Classe repositório para armazenar os jogos cadastrados. */
public class Jogos {

    /** O ArrayList contendo todos os jogos cadastrados. */
    private List<Jogo> jogos;

    /** Inicializa o repositório. */
    public Jogos() {
        this jogos = new ArrayList<Jogo>();
    }

    /** Retorna o jogo com o determinado nome. */
    public Jogo buscarJogo(String nome) {
        Jogo resultado = null;
        if (nome == null) nome = "É NULO!";
        for(Jogo jogo: this.jogos) {
            if (jogo.getNome().equalsIgnoreCase(nome)) {
                resultado jogo;
            }
        }
        return resultado;
    }

    /** Remove jogo da lista baseado no seu nome. */
    public boolean removerJogo(String nome) {
        Jogo jogo = this.buscarJogo(nome);
        boolean pode = (nome != null);

        if (pode) {
            this.jogos.remove(jogo);
        }
        return pode;
    }

    /** Adiciona o dado jogo caso não haja jogo conflitante. */
    public boolean adicionarJogo(Jogo jogo) {
        boolean pode = !(jogo == null || this.buscarJogo(jogo.getNome()) != null);
       
        if (pode) {
            this.jogos.add(jogo);
        }
        return pode;
    }
    
    public void mostrarJogos() {
        System.out.println();
        for (Jogo jogo: this.jogos) {
            System.out.println(jogo);
        }
        System.out.println();
    }
}
