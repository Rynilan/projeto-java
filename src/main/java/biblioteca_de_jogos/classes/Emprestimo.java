package biblioteca_de_jogos.classes;

import java.util.List;
import java.time.LocalDate;

public class Emprestimo {
    private int id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoReal;
    private int renovacoes;
	private Long intervalo;
    private int idUsuario;
    private int idJogo;
	private List<String> observacoes;

    public Emprestimo(int id, int idUsuario, int idJogo) {
        this.id = id;
        this.dataEmprestimo = LocalDate.now();
        this.idUsuario = idUsuario;
		this.idJogo = idJogo;
        this.renovacoes = 0;
        this.dataDevolucaoReal = null;
		this.intervalo = 7L;
    }

	public int getIdJogo() {
		return this.idJogo;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public LocalDate getDevolucao() {
		return this.dataDevolucaoReal;
	}

	public int getId() {
		return this.id;
	}

	public void devolver(List<String> observacoes) {
		this.dataDevolucaoReal = LocalDate.now();
		this.observacoes = observacoes;
	}

	public boolean atrasado() {
		return (
			this.dataDevolucaoReal != null ||
			LocalDate.now().isAfter(this.dataEmprestimo.plusDays(this.intervalo))
		);
	}

	public boolean renovar() {
		boolean pode = (this.renovacoes < 3);
		if (pode) {
			this.renovacoes++;
			this.intervalo *= (this.renovacoes + 1);
		}
		return pode;
	}
}
