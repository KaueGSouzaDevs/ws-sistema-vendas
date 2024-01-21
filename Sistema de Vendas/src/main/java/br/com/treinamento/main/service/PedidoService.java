package br.com.treinamento.main.service;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.model.Cliente;
import br.com.treinamento.main.model.ItemPedido;
import br.com.treinamento.main.model.Pedido;
import br.com.treinamento.main.model.Produtos;

public class PedidoService {

	public static void cadastroPedido() {
		System.out.println("\nCriar Pedido.");
		System.out.println("===============");
	
		
		Cliente clientePedido = ClienteService.getCliente();
		System.out.println("Cliente: "+clientePedido.getNome()+"\n");
		
		Pedido pedido = new Pedido(clientePedido);
		
		String resposta = "S";
		
		while (resposta.equalsIgnoreCase("S")) {
			
			System.out.println("\n");
			Produtos produtoLoop = ProdutoService.getProduto();
			System.out.println("Produto: "+ produtoLoop.getNome() + " - R$"+produtoLoop.getPreco());
			
			System.out.print("Informe a quantidade: ");
			Integer quantidade = Main.scanner.nextInt();
			Main.scanner.nextLine();
			
			ItemPedido itemLoop = new ItemPedido(produtoLoop, quantidade);
			pedido.adicionarItem(itemLoop);
			
			System.out.println("Deseja adicionar mais itens ao pedido? (S/N)");
			resposta = Main.scanner.nextLine();
	
		}				
		System.out.println("Pedido criado com sucesso!\nValor total: R$"+pedido.getTotalPedido());
		Main.pedidoList.add(pedido);
	
		System.out.println("Pressione enter para continuar...");
		Main.scanner.nextLine();
		
	}
	
	public static void listarPedidos() {
		System.out.println("---------------------------------------");
		System.out.println("----------Listagem de pedidos----------");
		System.out.println("---------------------------------------");
		System.out.println("Numero pedido \t Cliente \t Total");
		System.out.println("\n---------------------------------------\n");
		Main.pedidoList.forEach(c -> {
			System.out.println(c.getNumeroPedido() + " \t\t " + c.getCliente().getNome() + " \t\t " + c.getTotalPedido());
		});
		
	}
	public static void buscarPedidos() {
		
		System.out.println("\n-----------------");
		System.out.println("Busca de pedido");
		System.out.println("-----------------");
		
		System.out.print("\nInforme o codigo do pedido:");
		Integer codigo = Main.scanner.nextInt();
		Main.scanner.nextLine();
		System.out.println("Cliente: "+Main.pedidoList.get(codigo-1).getCliente().getNome()+"\nProdutos: "+
		Main.pedidoList.get(codigo-1).getItensPedido()+"\nValor total: R$"+Main.pedidoList.get(codigo-1).getTotalPedido());
	}
}















