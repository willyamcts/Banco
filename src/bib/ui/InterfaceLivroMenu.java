package bib.ui;

import java.util.List;

import bib.dao.AutorDao;
import bib.dao.LivroDao;
import bib.modelo.Cliente;
import bib.modelo.Livro;

public class InterfaceLivroMenu extends InterfaceModeloTexto {

	private LivroDao dao;
	private AutorDao clienteDao;
	
	public InterfaceLivroMenu() {
		super();
		dao = new LivroDao();
		clienteDao = new AutorDao();
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar livro");
		System.out.println();
		
		Livro novaConta = obtemDadosConta(null);	
		dao.insert(novaConta);
	}

	private Livro obtemDadosConta(Livro conta) {
		System.out.print("Insira o titulo do livro: ");
		String titulo = entrada.nextLine();
		
		System.out.print("Insira o ano da publicação: ");
		int anoPublicacao = entrada.nextInt();
		
		System.out.print("Insira a editora: ");
		String editora = entrada.nextLine();
		
		System.out.print("Insira o ID do autor: ");
		int idAutor = entrada.nextInt();
		
		Cliente autor = clienteDao.getByKey(idAutor);
		
		return new Livro(0, titulo, anoPublicacao, editora, autor);
	}

	@Override
	public void listarTodos() {
		List<Livro> contas = dao.getAll();
		
		System.out.println("Lista de livros: ");
		System.out.println();
		
		System.out.println("id\tTitulo\tAno de publicacao\tEditora\tAutor");
		
		for (Livro conta : contas) {
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
		
		Livro contaAModificar = dao.getByKey(id);
		
		Livro novaConta = obtemDadosConta(contaAModificar);
		
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
