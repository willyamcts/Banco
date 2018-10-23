package banco.modelo;

public class Pessoa {
	private int id;
	private String titulo;
	private String editora;
	private int anoPublicacao;
	private Autor autor;
	
	public Pessoa() { }
	
	public Pessoa(int id, String titulo, String editora, int anoPublicacao, Autor autor) {
		this.id = id;
		this.titulo = titulo;
		this.editora = editora;
		this.anoPublicacao = anoPublicacao;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return titulo;
	}

	public void setNome(String titulo) {
		this.titulo = titulo;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public int getCpf() {
		return anoPublicacao;
	}

	public void setCpf(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}

	
	@Override
	public String toString() {
		return "titulo: " + getNome() 
				+ "\nendere�o: " + getEditora()
				+ "\nCPF: " 
						+ String.valueOf(getCpf())
						.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", 
								"$1.$2.$3-$4");

	}
	
	
	
}
