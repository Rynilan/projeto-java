package biblioteca_de_jogos.classes;

import java.time.LocalDate;
import java.util.List;

public class Relatorio {

    private int  id;
    private String tipo;
    private LocalDate dataGeracao;
	private String tipoDeRelatorio;
	private List<Emprestimo> dadosEmp;
	private List<Jogo> dadosJog;
	private List<Usuario> dadosUsu;



	public Relatorio(int id, String tipo, String tipoDeRelatorio, List dados) {
        this.id = id;
        this.tipo = tipo;
		this.tipoDeRelatorio = tipoDeRelatorio;
        this.dataGeracao = LocalDate.now();
		switch (tipoDeRelatorio) {
			case "emp":
				this.dadosEmp = dados;
				this.dadosJog = null;
				this.dadosUsu = null;
				break;
			case "jog":
				this.dadosJog = dados;
				this.dadosEmp = null;
				this.dadosUsu = null;
				break;
			case "usu":
				this.dadosUsu = dados;
				this.dadosEmp = null;
				this.dadosJog = null;
				break;
		}
    }

	public void exportarCSV() {}

	public void exportarPDF() {}

}
