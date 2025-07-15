package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Usuario;

public class Usuarios {
	private List<Usuario> usuarios;
	private static Usuarios instancia = null;

	private Usuarios() {
		usuarios = new ArrayList<Usuario>();
	}

	public static Usuarios getInstance() {
		if (Usuarios.instancia == null) {
			Usuarios.instancia = new Usuarios();
		}
		return Usuarios.instancia;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
}
