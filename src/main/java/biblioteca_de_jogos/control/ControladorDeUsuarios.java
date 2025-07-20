package biblioteca_de_jogos.control;

import java.util.List;

import biblioteca_de_jogos.Controladores.usuarios.VerUsuariosController;
import biblioteca_de_jogos.classes.Usuario;
import biblioteca_de_jogos.model.Usuarios;

public class ControladorDeUsuarios {
	private Long id;
	private Usuarios usuarios;
	private static ControladorDeUsuarios instancia = null;
	
	public ControladorDeUsuarios() {
		this.id = 0L;
		this.usuarios = Usuarios.getInstance();
	}

	public static ControladorDeUsuarios getInstance() {
		if (ControladorDeUsuarios.instancia == null) {
			ControladorDeUsuarios.instancia = new ControladorDeUsuarios();
		}
		return ControladorDeUsuarios.instancia;
	}

	public Usuario buscarUsuario(Long id) {
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
		if (nome == null || nome.trim().isEmpty() ||
				email == null || email.trim().isEmpty() ||
				telefone == null || telefone.trim().isEmpty() ||
				(!status.equalsIgnoreCase("ativo") && !status.equalsIgnoreCase("inativo"))) {
			return null;
		}

		if (this.emailExiste(email)) {
			return null;
		}

		return new Usuario(this.id, nome, email, telefone, status);
	}

	public boolean adicionarUsuario(Usuario usuario) {
		if (usuario == null || this.buscarUsuario(usuario.getId()) != null ||
				this.emailExiste(usuario.getEmail())) {
			return false;
		}
		this.usuarios.getUsuarios().add(usuario);
		this.id++;
		return true;
	}

	public boolean removerUsuario(Usuario usuario) {
		boolean pode = (usuario != null && this.buscarUsuario(usuario.getId()) != null);
		if (pode) { this.usuarios.getUsuarios().remove(usuario); }
		return pode;
	}

	public boolean emailExiste(String email) {
		for (Usuario usuario : this.usuarios.getUsuarios()) {
			if (usuario.getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}

	public Usuario buscarUsuarioPorEmail(String email) {
		for (Usuario usuario : this.usuarios.getUsuarios()) {
			if (usuario.getEmail().equalsIgnoreCase(email)) {
				return usuario;
			}
		}
		return null;
	}

	public boolean usuarioEstaAtivo(Long idUsuario) {
		Usuario usuario = this.buscarUsuario(idUsuario);
		return usuario != null && usuario.getStatus().equalsIgnoreCase("ativo");
	}

	public List<Usuario> getUsuarios() {
		return this.usuarios.getUsuarios();
	}
}
