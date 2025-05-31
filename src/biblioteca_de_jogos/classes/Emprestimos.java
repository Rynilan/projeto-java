package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Emprestimos {
    private int id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoReal;
    private int renovacoes;
    private int idUsuario;

    public Emprestimos(int id, int idUsuario, LocalDate dataEmprestimo) {
        this.id = id;
        this.dataEmprestimo = dataEmprestimo;
        this.idUsuario = idUsuario;
        this.renovacoes = 0;
        this.dataDevolucaoReal = null;
    }

    public boolean emAtraso() {

    }

    public boolean renovar(){

    }

}
