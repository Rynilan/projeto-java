package biblioteca_de_jogos.assets;

import biblioteca_de_jogos.classes.Emprestimo;
import biblioteca_de_jogos.classes.Jogo;
import biblioteca_de_jogos.classes.Usuario;

import java.util.List;
import java.util.ArrayList;

/** Classe repositório que gerencia todos os empréstimo já feito no sistema. */
public class Emprestimos {

    /** A lista com todos os empréstimos já feitos. */
    private List<Emprestimo> emprestimos;

    /** Construtor que inicializa a lista. */
    public Emprestimos() {
        this.emprestimos = new ArrayList<Emprestimo>();
    }

}
