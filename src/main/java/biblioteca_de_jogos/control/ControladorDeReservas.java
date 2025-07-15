package biblioteca_de_jogos.control;

import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.model.Reservas;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ControladorDeReservas {

	private Long id;
	private Reservas reservas;
	private static ControladorDeReservas instancia = null;

	private ControladorDeReservas() {
		this.id = -1L;
		this.reservas = Reservas.getInstance();
	}

	public static ControladorDeReservas getInstance() {
		if (ControladorDeReservas.instancia == null) {
			ControladorDeReservas.instancia = new ControladorDeReservas();
		}
		return ControladorDeReservas.instancia;
	}

	public void fazerReserva(Long idJogo, Long idUsuario) {
		this.id++;
		this.reservas.getReservas().add(
				new Reserva(this.id, idUsuario, idJogo)
		);
	}

	public boolean temReservaJogo(Long idJogo) {
		boolean tem = false;
		for (Reserva reserva: this.reservas.getReservas()) {
			if (reserva.getIdJogo() == idJogo) {
				tem = true;
			}
		}
		return tem;
	}

	public Reserva buscarPrimeiraReservaDoJogo(Long idJogo) {
		Reserva achado = null;
		for (Reserva reserva: this.reservas.getReservas()) {
			if (reserva.getIdJogo() == idJogo) {
				achado = reserva;
				break;
			}
		}
		return achado;
	}

	public List<Reserva> reservasDeUsuario(Long idUsuario) {
		List<Reserva> achadas = new ArrayList<Reserva>();
		for (Reserva reserva: this.reservas.getReservas()) {
			if (reserva.getIdUsuario() == idUsuario) {
				achadas.add(reserva);
			}
		}
		return achadas;
	}

	public boolean deletarReserva(Long idReserva) {
		Iterator<Reserva> iterator = reservas.getReservas().iterator();
		while (iterator.hasNext()) {
			Reserva reserva = iterator.next();
			if (reserva.getId() == idReserva) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public boolean deletarReservasDeUsuario(Long idUsuario) {
		boolean removed = false;
		Iterator<Reserva> iterator = reservas.getReservas().iterator();
		while (iterator.hasNext()) {
			Reserva reserva = iterator.next();
			if (reserva.getIdUsuario() == idUsuario) {
				iterator.remove();
				removed = true;
			}
		}
		return removed;
	}

	public List<Reserva> getTodasReservas() {
		return new ArrayList<Reserva>(this.reservas.getReservas());
	}
}
