package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Devolucao {
    private int id;
    private LocalDate dataRealDevolucao;
    private boolean foiAtrasado;
    private int idEmprestimo;

    public Devolucao(int id, int idEmprestimo, boolean foiAtrasado) {
        this.id = id;
        this.dataRealDevolucao = LocalDate.now();;
        this.foiAtrasado = foiAtrasado;
        this.idEmprestimo = idEmprestimo;
    }

    public String toString() {
        return "Devolucao{" +
               "id=" + id +
               ", dataRealDevolucao=" + dataRealDevolucao +
               ", foiAtrasado=" + foiAtrasado +
               ", idEmprestimo=" + idEmprestimo +
               '}';
    }
    
}
