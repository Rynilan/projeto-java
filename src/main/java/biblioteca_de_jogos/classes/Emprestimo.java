package biblioteca_de_jogos.classes;

import java.util.List;

import java.time.LocalDate;

public class Emprestimo {
    private Long id;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoReal;
    private int renovacoes;
	private Long intervalo;
    private Long idUsuario;
    private Long idJogo;
	private List<String> observacoes;

    public Emprestimo(Long id, Long idUsuario, Long idJogo) {
        this.id = id;
        this.dataEmprestimo = LocalDate.now();
        this.idUsuario = idUsuario;
		this.idJogo = idJogo;
        this.renovacoes = 0;
        this.dataDevolucaoReal = null;
		this.intervalo = 7L;
    }

    @Override	
    public String toString() {
	String obsFormatadas = (observacoes != null && !observacoes.isEmpty())
		? String.join("; ", observacoes)
		: "Nenhuma";

	return "Emprestimo{" +
	       "id=" + id +
	       ", dataEmprestimo=" + dataEmprestimo +
	       ", dataDevolucaoReal=" + (dataDevolucaoReal != null ? dataDevolucaoReal : "Ainda não devolvido") +
	       ", renovacoes=" + renovacoes +
	       ", intervalo=" + intervalo + " dias" +
	       ", idUsuario=" + idUsuario +
	       ", idJogo=" + idJogo +
	       ", observacoes=" + obsFormatadas +
	       '}';
}
	
	public Long getIdJogo() {
		return this.idJogo;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public LocalDate getDevolucao() {
		return this.dataDevolucaoReal;
	}

	public Long getId() {
		return this.id;
	}

	public LocalDate getData() {
		return this.dataEmprestimo;
	}

	public int getRenovacoes() {
		return this.renovacoes;
	}

	public Long getIntervalo() {
		return this.intervalo;
	}

	public List<String> getObservacoes() {
		return this.observacoes;
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
