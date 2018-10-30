package bib.ui;

import java.util.Scanner;

public class InterfaceTexto {
	private Scanner entrada;
	private Estado estadoAtual;
	
	private static final int OP_CLIENTE = 1;
	private static final int OP_CONTA = 2;
	private static final int OP_SAIR = 0;
	private static final int OP_ADICIONAR = 1;
	private static final int OP_EDITAR = 2;
	private static final int OP_EXCLUIR = 3;
	private static final int OP_LISTAR = 4;
	
	private enum Estado {PRINCIPAL, CLIENTE, CONTA};
	
	public InterfaceTexto() {
		entrada = new Scanner(System.in);
		estadoAtual = Estado.PRINCIPAL;
	}
	
	private void imprimeMenu() {
		//System.out.println("Contas Bancárias");
		System.out.println();
		
		switch (estadoAtual) {
		case CLIENTE:
			imprimeMenuCliente();
			break;
		case CONTA:
			imprimeMenuConta();
			break;
		default:
			imprimeMenuPrincipal();
		}
		System.out.println();
		System.out.println("0 - Sair");
		
		System.out.println();
		System.out.print("Escolha uma op��o: ");	
	}
	
	private int leOpcao() {
		int opcao = entrada.nextInt();
		entrada.nextLine();
		return opcao;
	}
	
	private void imprimeMenuPrincipal() {
		System.out.println("1 - Administrar Autores");
		System.out.println("2 - Administrar Livros");
	}
	
	private void imprimeMenuCliente() {
		System.out.println("1 - Adicionar autor");
		System.out.println("2 - Editar autor");
		System.out.println("3 - Excluir autor");
		System.out.println("4 - Listar autores");
	}
	
	private void imprimeMenuConta() {
		System.out.println("1 - Adicionar livro");
		System.out.println("2 - Editar livro");
		System.out.println("3 - Excluir livro");
		System.out.println("4 - Listar livros");
	}
		
	public void executa() {
		InterfaceModeloTexto subMenu;
		
		imprimeMenu();
		int opcao = leOpcao();
		
		while (opcao != OP_SAIR) {
			if (estadoAtual == Estado.PRINCIPAL) {
				estadoAtual = opcao == OP_CLIENTE ? Estado.CLIENTE : Estado.CONTA;
			} else {
				subMenu = estadoAtual == Estado.CLIENTE ? 
						new InterfaceAutorMenu() : new InterfaceLivroMenu();
				
				switch (opcao) {
				case OP_ADICIONAR:
					subMenu.adicionar();
					break;
				case OP_EDITAR:
					subMenu.editar();
					break;
				case OP_EXCLUIR:
					subMenu.excluir();
					break;
				case OP_LISTAR:
					subMenu.listarTodos();
					break;
				default:
					System.out.println("Opera��o inv�lida. Tente novamente!");
				}
			}
			
			imprimeMenu();
			opcao = leOpcao();
		}
		
	}
	
}
