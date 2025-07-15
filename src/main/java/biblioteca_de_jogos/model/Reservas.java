package biblioteca_de_jogos.model;

import java.util.Queue;
import java.util.LinkedList;

import biblioteca_de_jogos.classes.Reserva;

public class Reservas {
	private Queue<Reserva> reservas;
	private static Reservas instancia = null;

	private Reservas() {
		this.reservas = new LinkedList<Reserva>();
	}

	public static Reservas getInstance() {
		if (Reservas.instancia == null) {
			Reservas.instancia = new Reservas();
		}
		return Reservas.instancia;
	}

	public Queue<Reserva> getReservas() {
		return this.reservas;
	}
}
