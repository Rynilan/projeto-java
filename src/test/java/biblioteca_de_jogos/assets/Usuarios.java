package biblioteca_de_jogos.assets;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Usuario;

public class Usuarios {
	private int id;
	private List<Usuario> usuarios;
	
	public Usuarios() {
		this.id = -1;
		this.usuarios = new ArrayList<Usuario>();
	}

	public Usuario buscarUsuario(int id) {
		Usuario achado = null;
		for (Usuario usuario: this.usuarios) {
			if (usuario.getId() == id) {
				achado = usuario;
			}
		}
		return achado;
	}

	public Usuario criarUsuario(String nome, String email, String telefone, String status) {
		Usuario usuario = null;
		if (nome != null && email != null && telefone != null && status.equalsIgnoreCase("ativo") || status.equalsIgnoreCase("inativo")) {
			this.id++;
			usuario = new Usuario(this.id, nome, email, telefone, status);
		}
		return usuario;
	}

	public boolean adicionarUsuario(Usuario usuario) {
		boolean pode = (usuario != null && this.buscarUsuario(usuario.getId()) == null);
		if (pode) { this.usuarios.add(usuario); } 
		return pode;
	}

	public boolean removerUsuario(Usuario usuario) {
		boolean pode = (usuario != null && this.buscarUsuario(usuario.getId()) != null);
		if (pode) { this.usuarios.remove(usuario); }
		return pode;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}
}
