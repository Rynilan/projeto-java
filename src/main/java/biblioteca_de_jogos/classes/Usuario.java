package biblioteca_de_jogos.classes;

public class Usuario {

	private Long id;
    public String nome;
    public String email;
    public String telefone;
    public String status;

	public Usuario(Long id, String nome, String email, String telefone, String status) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.status = status;
	}

    @Override
    public String toString() {
		return "Usuario {" +
			"\n  id=" + id +
			",\n  nome='" + nome + '\'' +
			",\n  email='" + email + '\'' +
			",\n  telefone='" + telefone + '\'' +
			",\n  status='" + status + '\'' +
			"\n}";
	}

	
	public Long getId() {
		return this.id;
	}

    public boolean podeEmprestar() {
        return false; // alteravel
    }

    public boolean temDevolucoesAtrasadas() {
        return false; // alteravel 
    }

    public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getStatus() {
		return status;
	}
}
