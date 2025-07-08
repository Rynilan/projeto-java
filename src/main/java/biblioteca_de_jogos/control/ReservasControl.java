package biblioteca_de_jogos.control;

import biblioteca_de_jogos.classes.Reserva;
import biblioteca_de_jogos.model.Reservas;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

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

	public boolean fazerReserva(int idJogo, int idUsuario) {
		if (this.usuarioTemReservaParaJogo(idUsuario, idJogo)) {
			return false;
		}

		this.id++;
		this.reservas.add(new Reserva(this.id, idUsuario, idJogo));
		return true;
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
		for (Reserva reserva : this.reservas) {
			if (reserva.getIdJogo() == idJogo) {
				return reserva;
			}
		}
		return null;
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

	public void removerReserva(int idReserva) {
		reservas.removeIf(reserva -> reserva.getId() == idReserva);
	}

	public boolean usuarioTemReservaParaJogo(int idUsuario, int idJogo) {
		for (Reserva reserva : this.reservas) {
			if (reserva.getIdUsuario() == idUsuario && reserva.getIdJogo() == idJogo) {
				return true;
			}
		}
		return false;
	}

	public List<Reserva> getTodasReservas() {
		return new ArrayList<>(this.reservas);
	}
}


