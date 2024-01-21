package br.com.treinamento.main.service;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.model.Cliente;

public class ClienteService {

	public static void cadastrarCliente() {

		System.out.println("\nCadastro de cliente.");
		System.out.println("----------------------");

		boolean codigoValido = false;
		Integer codigo = null;

		while (!codigoValido) {

			codigo = SistemaService.validaCodigo("cliente: ");

			boolean codigoDuplicata = false;
			for (Cliente cliente : Main.clientList) {
				if (cliente.getCodigo().equals(codigo)) {
					System.err.println("Erro: Já existe um cliente com o código informado." + codigo);
					codigoDuplicata = true;
					break;
				}
			}
			if (!codigoDuplicata) {
				codigoValido = true;
			}

		}
		System.out.print("\nNome: ");
		String nome = Main.scanner.nextLine();

		System.out.print("\nE-mail: ");
		String email = Main.scanner.nextLine();

		if (codigoValido==true) {
			Main.clientList.add(new Cliente(codigo, nome, email));
		}

	}

	public static void listarClientes() {

		System.out.println("------------------------------");
		System.out.println("Listagem de Clientes");
		System.out.println("------------------------------");
		System.out.println("Código \t Nome \t E-mail");
		System.out.println("\n------------------------------\n");
		Main.clientList.forEach(c -> {
			System.out.println(c.getCodigo() + " \t " + c.getNome() + " \t " + c.getEmail());
		});
	}

	public static Cliente getCliente() {
		Cliente clientePedido = null;
		boolean clienteValido = false;
		
		
		while(!clienteValido) {
			
			//Verifica se o codigo é um numero.
			Integer codigoCliente = SistemaService.validaCodigo("cliente:");
			
			//Verifica se existe um cliente com o código informado
			for(Cliente cliente : Main.clientList) {
				if(cliente.getCodigo().equals(codigoCliente)) {
					clientePedido = cliente;
					break;
				}
			}
			if(clientePedido!=null) {
				clienteValido = true;
			}else {
				System.out.println("Cliente não encontrado.");
			}
		}
		return clientePedido;
	}
	public static void buscarCliente() {
		
		System.out.println("\n-----------------");
		System.out.println("Busca de cliente");
		System.out.println("-----------------");
		
		System.out.print("\nInforme o codigo do cliente:");
		Integer codigo = Main.scanner.nextInt();
		Main.scanner.nextLine();
		System.out.println("Código: "+ Main.clientList.get(codigo-1).getCodigo()+ "\nNome : "+Main.clientList.get(codigo-1).getNome()+"\nE-mail: "+Main.clientList.get(codigo-1).getEmail());
	}

}
