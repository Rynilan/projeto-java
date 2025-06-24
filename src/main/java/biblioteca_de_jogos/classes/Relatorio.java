package biblioteca_de_jogos.classes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Relatorio {

    private int  id;
    private String tipo;
    private LocalDate dataGeracao;
	private String tipoDeRelatorio;
	private List<Emprestimo> dadosEmp;
	private List<Jogo> dadosJog;
	private List<Usuario> dadosUsu;

	public Relatorio(int id, String tipo, String tipoDeRelatorio, List<Object> dados) {
        this.id = id;
        this.tipo = tipo;
		this.tipoDeRelatorio = tipoDeRelatorio;
        this.dataGeracao = LocalDate.now();
		if (dados.get(0) instanceof Emprestimo) {
			List<Emprestimo> newDados = dados.stream().map(objeto -> (Emprestimo) objeto).collect(Collectors.toList());
			this.dadosEmp = newDados;
			this.dadosJog = null;
			this.dadosUsu = null;
		} else if (dados.get(0) instanceof Jogo) {
			List<Jogo> newDados = dados.stream().map(objeto -> (Jogo) objeto).collect(Collectors.toList());
			this.dadosJog = newDados;
			this.dadosEmp = null;
			this.dadosUsu = null;
		} else if (dados.get(0) instanceof Usuario) {
			List<Usuario> newDados = dados.stream().map(objeto -> (Usuario) objeto).collect(Collectors.toList());
			this.dadosUsu = newDados;
			this.dadosEmp = null;
			this.dadosJog = null;
		}
    }

	@Override
        public String toString() {
    String resultado = "Relatorio {" +
        "\n  id=" + id +
        ",\n  tipo='" + tipo + '\'' +
        ",\n  dataGeracao=" + dataGeracao +
        ",\n  tipoDeRelatorio='" + tipoDeRelatorio + '\'' +
        ",\n  dados=[";

    if (dadosEmp != null) {
        for (int i = 0; i < dadosEmp.size(); i++) {
            resultado += "\n    " + dadosEmp.get(i);
        }
    } else if (dadosJog != null) {
        for (int i = 0; i < dadosJog.size(); i++) {
            resultado += "\n    " + dadosJog.get(i);
        }
    } else if (dadosUsu != null) {
        for (int i = 0; i < dadosUsu.size(); i++) {
            resultado += "\n    " + dadosUsu.get(i);
        }
    }

    resultado += "\n  ]\n}";

    return resultado;
}

	
	public void exportarCSV() {}

	public void exportarPDF() {}

	
}
