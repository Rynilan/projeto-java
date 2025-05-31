package biblioteca_de_jogos.assets;

import biblioteca_de_jogos.classes.Reserva;

import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Reservas {
	
	private int id;
	private Queue<Reserva> reservas;
	private List<Reserva> historico;

	public Reservas() {
		this.id = -1;
		this.reservas = new LinkedList<Reserva>();
		this.historico = new ArrayList<Reserva>();
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
}
