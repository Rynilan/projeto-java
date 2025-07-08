package biblioteca_de_jogos.control;

import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.model.Reservas;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ReservasControl {

	private int id;
	private Queue<Reserva> reservas;
	private Reservas historico;
	private static ReservasControl self = null;

	private ReservasControl() {
		this.id = -1;
		this.reservas = new LinkedList<Reserva>();
		this.historico = Reservas.getInstance();
	}

	public static ReservasControl getInstance() {
		if (ReservasControl.self == null) {
			ReservasControl.self = new ReservasControl();
		}
		return ReservasControl.self;
	}

	public void fazerReserva(int idJogo, int idUsuario) {
		this.id++;
		this.reservas.add(
				new Reserva(this.id, idUsuario, idJogo)
		);
	}

	public boolean temReservaJogo(int idJogo) {
		boolean tem = false;
		for (Reserva reserva: this.reservas) {
			if (reserva.getIdJogo() == idJogo) {
				tem = true;
			}
		}
		return tem;
	}

	public Reserva buscarPrimeiraReservaDoJogo(int idJogo) {
		Reserva achado = null;
		for (Reserva reserva: this.reservas) {
			if (reserva.getIdJogo() == idJogo) {
				achado = reserva;
				break;
			}
		}
		return achado;
	}

	public List<Reserva> reservasDeUsuario(int idUsuario) {
		List<Reserva> achadas = new ArrayList<Reserva>();
		for (Reserva reserva: this.reservas) {
			if (reserva.getIdUsuario() == idUsuario) {
				achadas.add(reserva);
			}
		}
		return achadas;
	}

	public boolean deletarReserva(int idReserva) {
		Iterator<Reserva> iterator = reservas.iterator();
		while (iterator.hasNext()) {
			Reserva reserva = iterator.next();
			if (reserva.getId() == idReserva) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	public boolean deletarReservasDeUsuario(int idUsuario) {
		boolean removed = false;
		Iterator<Reserva> iterator = reservas.iterator();
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
		return new ArrayList<>(this.reservas);
	}
}