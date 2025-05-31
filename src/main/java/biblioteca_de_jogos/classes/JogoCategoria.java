package biblioteca_de_jogos.classes;

public class JogoCategoria {
    private int idJogo;
    private int idCategoria;


    public JogoCategoria(int idJogo, int idCategoria) {
        this.idJogo = idJogo;
        this.idCategoria = idCategoria;
    }


    public int getIdJogo() {
        return idJogo;
    }

    public void setIdJogo(int idJogo) {
        this.idJogo = idJogo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }
}
