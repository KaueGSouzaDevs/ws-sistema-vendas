package br.com.treinamento.main.service;

import java.io.IOException;

import br.com.treinamento.main.Main;

public class SistemaService {
	
	public static void montarMenu() {
		System.out.println("\n=========================");
		System.out.println("===Sistema de Compreas===");
		System.out.println("=========================\n");
		System.out.println("1 - Cadastrar cliente.");
		System.out.println("2 - Listar clientes.");		
		System.out.println("3 - Buscar clientes.");		
		System.out.println("4 - Atualizar clientes.");		
		System.out.println("5 - Excluir clientes.");		
		System.out.println("6 - Cadastrar produto");		
		System.out.println("7 - Listar produtos.");		
		System.out.println("8 - Buscar produtos.");
		System.out.println("9 - Atualizar produtos.");
		System.out.println("10 - Excluir produtos.");
		System.out.println("11 - Criar pedido.");		
		System.out.println("12 - Listar pedidos.");		
		System.out.println("13 - Buscar pedidos.");		
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

	public static void limparTela() {
	// 	System.out.println("\n");
		
	// 	// Windows
	// 	try {
	// 		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
	// 	} catch (IOException | InterruptedException e) {
	// 		e.printStackTrace();
	// 	}
	}
	
}
