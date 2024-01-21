package br.com.treinamento.main;

import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.treinamento.main.model.Cliente;
import br.com.treinamento.main.model.ItemPedido;
import br.com.treinamento.main.model.Pedido;
import br.com.treinamento.main.model.Produtos;
import br.com.treinamento.main.service.ClienteService;
import br.com.treinamento.main.service.PedidoService;
import br.com.treinamento.main.service.ProdutoService;
import br.com.treinamento.main.service.SistemaService;

public class Main {

	public static Scanner scanner = new Scanner(System.in);
	public static List<Cliente> clientList = new ArrayList<Cliente>();
	public static List<Produtos> produtoList = new ArrayList<Produtos>();
	public static List<ItemPedido> itemPedidoList = new ArrayList<ItemPedido>();
	public static List<Pedido> pedidoList = new ArrayList<Pedido>();
	
    public static void main( String[] args ) {
    	
    	while (true) {
    		
    		SistemaService.montarMenu();
    		
    		System.out.print("Informe a opção: ");
    		Integer opcao = scanner.nextInt();
    		scanner.nextLine();
    		
    		switch (opcao) {
			case 1:{
				ClienteService.cadastrarCliente();
				break;
			}
			case 2:{
				ClienteService.listarClientes();
				break;
			}
			case 3:{
				ClienteService.buscarCliente();
				break;
			}
			case 4:{
				ProdutoService.cadastrarProduto();
				break;
			}
			case 5:{
				ProdutoService.listarProdutos();
				break;
			}
			case 6:{
				ProdutoService.buscarProduto();
				break;
			}
			case 7:{
				PedidoService.cadastroPedido();
				break;
			}
			case 8:{
				PedidoService.listarPedidos();
				break;
			}
			case 9:{
				PedidoService.buscarPedidos();
				break;
			}
			case 0:{
				System.out.println("Saindo do sistema.");
				System.exit(0);
			}
			default:
				System.err.println("Opção inválida.");
			}
		}
    	
    	
    
    
    }
}
