package banco.modelo;


public class Cliente extends Pessoa implements Imprimivel {
	private double rendaMensal = 1000;

	public Cliente() { super(); }
	
	public Cliente(int id, String nome, long cpf) {
	}

	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	@Override
	public String toString() {
		return super.toString()
				+ String.format("\nRenda mensal: R$ %.2f", 
						getRendaMensal());
	}

	@Override
	public String imprimeEmLista() {
		return String.format("%d\t%s\t%s\t%d\t%d\t%d%.2f", getId(), getNome(), getEditora(), getCpf(), getRendaMensal());
	}

	@Override
	public String[] getColunas() {
		String[] colunas = {"id", "Nome", "Endereço", "CPF", "RG", "Telefone", "Renda Mensal"};
		return colunas;
	}
	
	
	
}
