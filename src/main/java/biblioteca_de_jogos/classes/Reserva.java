package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Reserva {

	private int id;
	private boolean notificado;
	private LocalDate dataReserva;
	private int idUsuario;
	private int idJogo;

	public Reserva(int id, int idUsuario, int idJogo) {
		this.id = id;
		this.notificado = false;
		this.dataReserva = LocalDate.now();
		this.idUsuario = idUsuario;
		this.idJogo = idJogo;
	}

	@Override
        public String toString() {
		return "Reserva {" +
			"\n  id=" + id +
			",\n  notificado=" + notificado +
			",\n  dataReserva=" + dataReserva +
			",\n  idUsuario=" + idUsuario +
			",\n  idJogo=" + idJogo +
			"\n}";
	}
	
	public int getIdJogo() {
		return this.idJogo;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}
}
