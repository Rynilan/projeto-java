package biblioteca_de_jogos.model;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Usuario;

public class Usuarios {
	private List<Usuario> usuarios;
	private static Usuarios self;

	private Usuarios() {
		usuarios = new ArrayList<Usuario>();
	}

	public static Usuarios getInstance() {
		if (Usuarios.self == null) {
			Usuarios.self = new Usuarios();
		}
		return Usuarios.self;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
}
