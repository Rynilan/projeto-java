package biblioteca_de_jogos.control;

import java.util.List;
 
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.model.Usuarios;

public class UsuariosControl {
	private int id;
	private Usuarios usuarios;
	private static UsuariosControl self = null;
	
	public UsuariosControl() {
		this.id = -1;
		this.usuarios = Usuarios.getInstance();
	}

	public static UsuariosControl getInstance() {
		if (UsuariosControl.self == null) {
			UsuariosControl.self = new UsuariosControl();
		}
		return UsuariosControl.self;
	}

	public Usuario buscarUsuario(int id) {
		Usuario achado = null;
		for (Usuario usuario: this.usuarios.getUsuarios()) {
			if (usuario.getId() == id) {
				achado = usuario;
			}
		}
		return achado;
	}

	public Usuario buscarUsuario(String nome) {
		Usuario achado = null;
		for (Usuario usuario: this.usuarios.getUsuarios()) {
			if (usuario.getNome().equalsIgnoreCase(nome)) {
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
		if (pode) { this.usuarios.getUsuarios().add(usuario); } 
		return pode;
	}

	public boolean removerUsuario(Usuario usuario) {
		boolean pode = (usuario != null && this.buscarUsuario(usuario.getId()) != null);
		if (pode) { this.usuarios.getUsuarios().remove(usuario); }
		return pode;
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios.getUsuarios();
	}
}
