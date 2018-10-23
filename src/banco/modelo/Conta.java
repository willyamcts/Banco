package banco.modelo;


public class Conta implements Imprimivel {
	private int id;
	private String titulo;
	private int anoPublicacao;
	private Cliente cliente;
	private String editora;
	
	public Conta() { }
	
	public Conta(int id, String titulo, int anoPublicacao, String editora, Cliente cliente) {
		this.id = id;
		this.titulo = titulo;
		this.anoPublicacao = anoPublicacao;
		this.cliente = cliente;
		this.editora = editora;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getSaldo() {
		return editora;
	}

	public void setSaldo(String editora) {
		this.editora = editora;
	}

	public String getAgencia() {
		return titulo;
	}
	
	public void setAgencia(String titulo) {
		this.titulo = titulo;
	}

	public int getNumero() {
		return anoPublicacao;
	}

	public void setNumero(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Agência: " + getAgencia()		
			+ "\nNúmero: " + getNumero()
			+ "\nCliente: " + getCliente()
			+ String.format("\nSaldo: R$ %.2f", 
					getSaldo());
	}

	@Override
	public String imprimeEmLista() {
		return String.format("%d\t%d\t%d\t%.2f\t%d\t%s", getId(), getAgencia(), getNumero(), getSaldo(), 
				getCliente().getId(), getCliente().getNome());
	}

	@Override
	public String[] getColunas() {
		String[] colunas = {"id", "Agência", "Número", "Saldo", "Id Cliente"};
		return colunas;
	}
	
	
	
}
