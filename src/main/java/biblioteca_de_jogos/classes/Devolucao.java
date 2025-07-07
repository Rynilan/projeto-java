package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Devolucao {
    private Long id;
    private LocalDate dataRealDevolucao;
    private boolean foiAtrasado;
    private Long idEmprestimo;

    public Devolucao(Long id, Long idEmprestimo, boolean foiAtrasado) {
        this.id = id;
        this.dataRealDevolucao = LocalDate.now();;
        this.foiAtrasado = foiAtrasado;
        this.idEmprestimo = idEmprestimo;
    }

    @Override
    public String toString() {
        return "Devolucao{" +
               "id=" + id +
               ", dataRealDevolucao=" + dataRealDevolucao +
               ", foiAtrasado=" + foiAtrasado +
               ", idEmprestimo=" + idEmprestimo +
               '}';
    }
    
}
