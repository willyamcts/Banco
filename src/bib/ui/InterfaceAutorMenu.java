package bib.ui;

import java.util.List;

import bib.dao.AutorDao;
import bib.modelo.Cliente;

public class InterfaceAutorMenu extends InterfaceModeloTexto {
	
	private AutorDao dao;
	
	public InterfaceAutorMenu() {
		super();
		dao = new AutorDao();
	}
	
	private Cliente obtemDadosAutor(Cliente cliente) {
		
		System.out.print("Insira o nome do autor: ");
		String nome = entrada.nextLine();
		
		System.out.println("Insira o CPF do autor (somente numeros): ");
		long cpf = entrada.nextLong();
		entrada.nextLine();
		
		Cliente novoAutor = new Cliente(0, nome, cpf);
		
		return novoAutor;
	}
	
	@Override
	public void adicionar() {
		System.out.println("Adicionar autor");
		System.out.println();
		
		Cliente novoCliente = obtemDadosAutor(null);	
		dao.insert(novoCliente);
		
	}

	@Override
	public void listarTodos() {
		List<Cliente> clientes = dao.getAll();
		
		System.out.println("Lista de autores");
		System.out.println();
		
		System.out.println("id\tNome\tCPFtRenda Mensal");
		
		for (Cliente cliente : clientes) {
			imprimeItem(cliente);
		}
		
	}

	@Override
	public void editar() {
		listarTodos();
		
		System.out.println("Editar autor");
		System.out.println();
		
		System.out.print("Entre o id do autor: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		Cliente clienteAModifcar = dao.getByKey(id);
		
		Cliente novoCliente = obtemDadosAutor(clienteAModifcar);
		
		novoCliente.setId(id);
		
		dao.update(novoCliente);
		
	}

	@Override
	public void excluir() {
		listarTodos();
		
		System.out.println("Excluir autor");
		System.out.println();
		
		System.out.print("Entre o id do autor: ");
		int id = entrada.nextInt();
		entrada.nextLine();
		
		dao.delete(id);
		
	}

}
