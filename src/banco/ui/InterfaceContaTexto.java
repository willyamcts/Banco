package banco.ui;

import java.util.List;

import banco.dao.ClienteDao;
import banco.dao.ContaDao;
import banco.modelo.Cliente;
import banco.modelo.Conta;

public class InterfaceContaTexto extends InterfaceModeloTexto {

	private ContaDao dao;
	private ClienteDao clienteDao;
	
	public InterfaceContaTexto() {
		super();
		dao = new ContaDao();
		clienteDao = new ClienteDao();
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar conta");
		System.out.println();
		
		Conta novaConta = obtemDadosConta(null);	
		dao.insert(novaConta);
	}

	private Conta obtemDadosConta(Conta conta) {
		System.out.print("Insira o titulo do livro: ");
		String titulo = entrada.nextLine();
		
		System.out.print("Insira o ano da publicação: ");
		int anoPublicacao = entrada.nextInt();
		
		System.out.print("Insira a editora: ");
		String editora = entrada.nextLine();
		
		System.out.print("Insira o ID do autor: ");
		int idAutor = entrada.nextInt();
		
		Cliente autor = clienteDao.getByKey(idAutor);
		
		return new Conta(0, titulo, anoPublicacao, editora, autor);
	}

	@Override
	public void listarTodos() {
		List<Conta> contas = dao.getAll();
		
		System.out.println("Lista de livros: ");
		System.out.println();
		
		System.out.println("id\tTitulo\tAno de publicacao\tEditora\tAutor");
		
		for (Conta conta : contas) {
			imprimeItem(conta);
		}
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar livro");
		System.out.println();
		
		System.out.print("Entre o id do livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Conta contaAModificar = dao.getByKey(id);
		
		Conta novaConta = obtemDadosConta(contaAModificar);
		
		novaConta.setId(id);
		
		dao.update(novaConta);
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir livro");
		System.out.println();
		
		System.out.print("Entre o id do livro: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
	}

}
