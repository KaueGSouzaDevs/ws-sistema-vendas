package br.com.treinamento.main;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.treinamento.main.service.ClienteService;
import br.com.treinamento.main.service.PedidoService;
import br.com.treinamento.main.service.ProdutoService;
import br.com.treinamento.main.service.SistemaService;

public class Main {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {

		ProdutoService produtoService = new ProdutoService();
		ClienteService clienteService = new ClienteService();
		PedidoService pedidoService = new PedidoService();
		while (true) {

			SistemaService.montarMenu();
			System.out.print("Informe a opção: ");
			Integer opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1: {
					clienteService.cadastrarCliente();
					break;
				}
				case 2: {
					clienteService.listarClientes();
					break;
				}
				case 3: {
					clienteService.buscarCliente();
					break;
				}
				case 4: {
					clienteService.atualizarCliente();
					break;
				}
				case 5: {
					clienteService.excluirCliente();
					break;
				}
				case 6: {
					produtoService.cadastrarProduto();
					break;
				}
				case 7: {
					produtoService.listarProdutos();
					break;
				}
				case 8: {
					produtoService.buscarProduto();
					break;
				}
				case 9:{
					produtoService.atualizarProduto();
					break;
				}
				case 10:{
					produtoService.excluirProduto();
					break;
				}
				case 11: {
					pedidoService.cadastrarPedido();
					break;
				}
				// case 10: {
				// 	// PedidoService.listarPedidos();
				// 	break;
				// }
				// case 11: {
				// 	// PedidoService.buscarPedidos();
				// 	break;
				// }
				case 0: {
					System.out.println("Saindo do sistema.");
					System.exit(0);
				}
				default:
					System.err.println("Opção inválida.");
			}
		}
	}
}
