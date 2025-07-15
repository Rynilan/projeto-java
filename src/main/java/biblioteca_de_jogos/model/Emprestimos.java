package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Emprestimo;

public class Emprestimos {
	private static Emprestimos instancia = null;
	private List<Emprestimo> emprestimos;

	private Emprestimos() {
		this.emprestimos = new ArrayList<Emprestimo>();
	}

	public static Emprestimos getInstance() {
		if (Emprestimos.instancia == null) {
			Emprestimos.instancia = new Emprestimos();
		}
		return Emprestimos.instancia;
	}

	public List<Emprestimo> getEmprestimos() {
		return this.emprestimos;
	}
}
