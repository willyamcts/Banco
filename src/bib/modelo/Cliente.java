package bib.modelo;


public class Cliente extends Autor implements Imprimivel {

	public Cliente() { super(); }
	
	public Cliente(int id, String nome, long cpf) {
		super(id, nome, cpf);
	}

	/*
	public double getRendaMensal() {
		return rendaMensal;
	}

	public void setRendaMensal(double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}
	*/


	/*
	@Override
	public String toString() {
		return super.toString()
				+ String.format("\nRenda mensal: R$ %.2f", 
						getRendaMensal());
	}
	*/

	@Override
	public String imprimeEmLista() {
		return String.format("%d\t%s\t%d", getId(), getNome(), getCpf());
	}

	@Override
	public String[] getColunas() {
		String[] colunas = {"id", "Nome", "CPF"};
		return colunas;
	}
	
	
	
}
