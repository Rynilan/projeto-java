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
			"\n  id=" + this.id +
			",\n  notificado=" + this.notificado +
			",\n  dataReserva=" + this.dataReserva +
			",\n  idUsuario=" + this.idUsuario +
			",\n  idJogo=" + this.idJogo +
			"\n}";
	}
	
	public Long getIdJogo() {
		return this.idJogo;
	}

	public Long getIdUsuario() {
		return this.idUsuario;
	}

	public Long getId() {
		return this.id;
	}

	public LocalDate getDataReserva() {
		return dataReserva;
	}

	public String getNotificado() {
		if (!this.notificado){
			return "Não";
		} else {
			return "Sim";
		}
	}
}
