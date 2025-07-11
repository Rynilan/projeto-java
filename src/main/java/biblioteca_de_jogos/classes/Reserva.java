package biblioteca_de_jogos.classes;

import java.time.LocalDate;

public class Reserva {

	private Long id;
	private boolean notificado;
	private LocalDate dataReserva;
	private Long idUsuario;
	private Long idJogo;

	public Reserva(Long id, Long idUsuario, Long idJogo) {
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
	
	public Long getIdJogo() {
		return this.idJogo;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public int getId() {
		return this.id;
	}
}
