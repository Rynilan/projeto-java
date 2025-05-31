package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Devolucao {
    private int id;
    private LocalDate dataRealDevolucao;
    private boolean foiAtrasado;
    private int idEmprestimo;

    public Devolucao(int id, int idEmprestimo, boolean foiAtrasado) {
        this.id = id;
        this.dataRealDevolucao = null;
        this.foiAtrasado = foiAtrasado;
        this.idEmprestimo = idEmprestimo;
    }
}
