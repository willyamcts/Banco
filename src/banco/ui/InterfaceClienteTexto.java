package banco.ui;

import java.util.List;

import banco.dao.ClienteDao;
import banco.modelo.Cliente;

public class InterfaceClienteTexto extends InterfaceModeloTexto {
	
	private ClienteDao dao;
	
	public InterfaceClienteTexto() {
		super();
		dao = new ClienteDao();
	}
	
	private Cliente obtemDadosLivro(Cliente cliente) {

		System.out.println("Insira o título do livro: ");
		String titulo = entrada.nextLine();
		
		System.out.println("Insira o ano de publicação: ");
		int anoPublicacao = entrada.nextInt();
		entrada.nextLine();
		
		System.out.println("Insira a editora: ");
		String editora = entrada.nextLine();
		
		System.out.println("Insira o telefone do cliente (somente nÃºmeros): ");
		Autor autor = entrada.nextLong();
		entrada.nextLine();
		
		Cliente novoLivro = new Cliente(0, titulo, anoPublicacao, editora, autor);
		
		return novoLivro;
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar livro");
		System.out.println();
		
		Cliente novoLivro = obtemDadosLivro(null);	
		dao.insert(novoLivro);
		
	}

	@Override
	public void listarTodos() {
		List<Cliente> clientes = dao.getAll();
		
		System.out.println("Lista de livros");
		System.out.println();
		
		System.out.println("id\tTitulo\tAno de Publicacao\tEditora\tAutor");
		
		for (Cliente cliente : clientes) {
			imprimeItem(cliente);
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
		
		Cliente livroAModifcar = dao.getByKey(id);
		
		Cliente novoLivro = obtemDadosLivro(livroAModifcar);
		
		novoLivro.setId(id);
		
		dao.update(novoLivro);
		
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir cliente");
		System.out.println();
		
		System.out.print("Entre o id do cliente: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
		
	}

}
