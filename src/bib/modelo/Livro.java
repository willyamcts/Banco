package bib.modelo;


public class Livro implements Imprimivel {
	private int id;
	private String titulo;
	private int anoPublicacao;
	private String editora;
	private Cliente autor;
	
	public Livro() { }
	
	public Livro(int id, String titulo, int anoPublicacao, String editora, Cliente autor) {
		this.id = id;
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
		this.editora = editora;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumero() {
		return anoPublicacao;
	}

	public void setNumero(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
		
	public Cliente getCliente() {
		return autor;
	}
	
	public void setCliente(Cliente autor) {
		this.autor = autor;
	}
	
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Titulo: " + getTitulo()	
			+ "\nAno de publicação: " + getNumero()
			+ "\nEditora: " + getEditora()
			+ "\nCliente: " + getCliente();
	}

	@Override
	public String imprimeEmLista() {
		return String.format("%d\t%s\t%d\t%s\t%s", getId(), getTitulo(), getNumero(), getEditora(), 
				getCliente().getNome());
	}

	@Override
	public String[] getColunas() {
		String[] colunas = {"id", "Titulo", "Ano de publicação", "Editora", "ID Autor"};
		return colunas;
	}
	
	
	
}
