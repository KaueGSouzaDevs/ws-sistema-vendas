package br.com.treinamento.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.treinamento.main.model.Cliente;
import br.com.treinamento.main.model.ItemPedido;
import br.com.treinamento.main.model.Pedido;
import br.com.treinamento.main.model.Produto;
import br.com.treinamento.main.service.ProdutoService;
import br.com.treinamento.main.service.SistemaService;

public class Main {

	public static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) throws SQLException {

		ProdutoService produtoService = new ProdutoService();

		while (true) {

			SistemaService.montarMenu();
			System.out.print("Informe a opção: ");
			Integer opcao = scanner.nextInt();
			scanner.nextLine();

			switch (opcao) {
				case 1: {
					// ClienteService.cadastrarCliente();
					break;
				}
				case 2: {
					// ClienteService.listarClientes();
					break;
				}
				case 3: {
					// ClienteService.buscarCliente();
					break;
				}
				case 4: {
					produtoService.cadastrarProduto();
					break;
				}
				case 5: {
					produtoService.listarProdutos();
					break;
				}
				case 6: {
					produtoService.buscarProduto();
					break;
				}
				case 7:{
					produtoService.excluirProduto();
				}
				case 8:{
					produtoService.atualizarProduto();
				}
				case 9: {
					// PedidoService.cadastroPedido();
					break;
				}
				case 10: {
					// PedidoService.listarPedidos();
					break;
				}
				case 11: {
					// PedidoService.buscarPedidos();
					break;
				}
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
