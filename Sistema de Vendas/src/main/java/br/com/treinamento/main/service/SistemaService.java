package br.com.treinamento.main.service;

import br.com.treinamento.main.Main;

public class SistemaService {
	
	public static void montarMenu() {
		System.out.println("\n=========================");
		System.out.println("===Sistema de Compreas===");
		System.out.println("=========================");
		System.out.println("\n1 - Cadastrar cliente.");
		System.out.println("2 - Listar clientes.");		
		System.out.println("3 - Buscar clientes.");		
		System.out.println("4 - Cadastrar produto");		
		System.out.println("5 - Listar produtos.");		
		System.out.println("5 - Buscar produtos.");		
		System.out.println("7 - Criar pedido.");		
		System.out.println("8 - Listar pedidos.");		
		System.out.println("9 - Buscar pedidos.");		
		System.out.println("0 - Sair do sistema.\n");
		
	}

	public static Integer validaCodigo(String algumaCoisa) {
		Integer codigo = null;
	
		// Verifica se o codigo é um numero inteiro.
		while (codigo == null) {
	
			try {
				System.out.print("Informe o código do "+ algumaCoisa);
				codigo = Main.scanner.nextInt();
				Main.scanner.nextLine();
				break;
			} catch (Exception e) {
				System.out.println("Código inválido, informe um número inteiro.");
				Main.scanner.nextLine();
			}
		}
		return codigo;
	}

}
