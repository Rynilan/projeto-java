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
			"\n  id=" + this.id +
			",\n  nome='" + this.nome + '\'' +
			",\n  email='" + this.email + '\'' +
			",\n  telefone='" + this.telefone + '\'' +
			",\n  status='" + this.status + '\'' +
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
		return this.nome;
	}

	public String getEmail() {
		return this.email;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public String getStatus() {
		return this.status;
	}
}
