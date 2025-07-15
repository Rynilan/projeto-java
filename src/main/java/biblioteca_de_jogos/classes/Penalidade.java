package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Penalidade {
    private Long id;
    private String descricao;
    private String tipo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Long idUsuario;
	private Emprestimo emprestimo;

	/** Construtor base */
    public Penalidade(Long id, String descricao, String tipo, Long idUsuario, Emprestimo emprestimo) {
        this.id = id;
        this.descricao = descricao;
        this.tipo = tipo;
        this.dataInicio = LocalDate.now();
        this.dataFim = null;
        this.idUsuario = idUsuario;
		this.emprestimo = emprestimo;
    }

    @Override
    public String toString() {
    return "Penalidade{" +
           "id=" + this.id +
           ", descricao='" + this.descricao + '\'' +
           ", tipo='" + this.tipo + '\'' +
           ", dataInicio=" + this.dataInicio +
           ", dataFim=" + (this.dataFim != null ? this.dataFim : "Em aberto") +
           ", idUsuario=" + this.idUsuario +
           ", idEmprestimo=" + (this.emprestimo != null ? this.emprestimo.getId() : "Nenhum") +
           '}';
}

	
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataInicio() {
        return this.dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return this.dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

	public boolean terminou() {
		boolean acabou = this.emprestimo.atrasado();
		if (acabou) this.dataFim = LocalDate.now();
		return acabou;
	}
    
}
