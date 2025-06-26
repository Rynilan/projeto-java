package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Emprestimo;

public class Emprestimos {
	private static Emprestimos self = null;
	private List<Emprestimo> emprestimos;

	private Emprestimos() {
		this.emprestimos = new ArrayList<Emprestimo>();
	}

	public static Emprestimos getInstance() {
		if (Emprestimos.self == null) {
			Emprestimos.self = new Emprestimos();
		}
		return Emprestimos.self;
	}

	public List<Emprestimo> getEmprestimos() {
		return this.emprestimos;
	}
}
