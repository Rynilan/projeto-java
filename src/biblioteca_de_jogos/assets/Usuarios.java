package biblioteca_de_jogos.assets;

import java.util.List;
import java.util.ArrayList;

import biblioteca_de_jogos.classes.Usuario;

/** Classe repositório que irá conter todos os usuários cadastrados. */
public class Usuarios {
    
    /** A lista de usuários. */
    private List<Usuario> usuarios;

    /** Construtor que inicializa a lista. */
    public Usuarios() {
        this.usuarios = new ArrayList<Usuario>();
    }

    /** Pega o usuário pelo email. */
    public Usuario buscarUsuario(String email) {
        if (email == null) email = "É NULO!";
        Usuario retorno = null

        for (Usuario usuario: this.usuarios) {
            if (usuario.getEmail().equalsIgnoreCase(email)) {
                retorno = usuario;
            }
        }
        return retorno;
    }

    /** Remove o usuário baseado no seu email. */
    public boolean removerUsuario(String email) {
        if (email == null) email = "É NULO!";
        Usuario usuario = this.buscarUsuario(email);
        if (usuario == null) {
            boolean resultado = false;
        } else {
            this.usuarios.remove(usuario);
            boolean resultado = true;
        }
        return resultado;
    }

    /** Adiciona o usuário novo caso não haja nenhum com o mesmo email. */
    public boolean adicionarUsuario(Usuario usuario) {
        boolean pode = !(usuario == null || this.buscarUsuario(usuario.getNome()) != null);
        if (pode) this.usuarios.add(usuario);
        return pode;
    }

    /** Lista todos os usuários da lista. */
    public void mostrarUsuarios() {
        System.out.println();
        for (Usuario usuario: this.usuarios) {
            System.out.println(usuario);
        }
        System.out.println();
    }
}
