package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Penalidade {
    private int id;
    private String descricao;
    private String tipo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private int idUsuario;
	private Emprestimo emprestimo;

	/** Construtor base */
    public Penalidade(int id, String descricao, String tipo, int idUsuario, Emprestimo emprestimo) {
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
           "id=" + id +
           ", descricao='" + descricao + '\'' +
           ", tipo='" + tipo + '\'' +
           ", dataInicio=" + dataInicio +
           ", dataFim=" + (dataFim != null ? dataFim : "Em aberto") +
           ", idUsuario=" + idUsuario +
           ", idEmprestimo=" + (emprestimo != null ? emprestimo.getId() : "Nenhum") +
           '}';
}

	
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

	public boolean terminou() {
		boolean acabou = emprestimo.atrasado();
		if (acabou) this.dataFim = LocalDate.now();
		return acabou;
	}
    
}
