package br.com.treinamento.main.service;

import java.math.BigDecimal;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.model.Produtos;

public class ProdutoService {

	public static void cadastrarProduto() {

		System.out.println("\nCadastro de produto.");
		System.out.println("----------------------");

		boolean codigoValido = false;
		Integer codigo = null;

		while (!codigoValido) {

			codigo = SistemaService.validaCodigo("produto: ");

			boolean codigoDuplicata = false;
			for (Produtos produto : Main.produtoList) {
				if (produto.getCodigo().equals(codigo)) {
					System.err.println("Erro: Já existe um cliente com o código informado." + codigo);
					codigoDuplicata = true;
					break;
				}
			}
			if (!codigoDuplicata) {
				codigoValido = true;
			}

		}

		System.out.print("\nNome do produto: ");
		String nome = Main.scanner.nextLine();

		System.out.print("\nPreço unitário: ");
		String precoString = Main.scanner.nextLine().replace(",", ".");
		BigDecimal preco = new BigDecimal(precoString);

		if (codigoValido==true) {
			Main.produtoList.add(new Produtos(codigo, nome, preco));
		}

	}

	public static void listarProdutos() {

		System.out.println("------------------------------");
		System.out.println("Listagem de Produtos");
		System.out.println("------------------------------");
		System.out.println("Código \t Nome \t Preço");
		System.out.println("\n------------------------------\n");
		Main.produtoList.forEach(c -> {
			System.out.println(c.getCodigo() + " \t " + c.getNome() + " \t " + c.getPreco());
		});
	}
	
	public static Produtos getProduto() {
		Produtos produtoPedido = null;
		boolean produtoValido = false;
		
		
		while(!produtoValido) {
			
			//Verifica se o codigo é um numero.
			Integer codigoProduto = SistemaService.validaCodigo("produto:");
			
			//Verifica se existe um cliente com o código informado
			for(Produtos produto : Main.produtoList) {
				if(produto.getCodigo().equals(codigoProduto)) {
					produtoPedido = produto;
					break;
				}
			}
			if(produtoPedido!=null) {
				produtoValido = true;
			}else {
				System.out.println("Cliente não encontrado.");
			}
		}
		return produtoPedido;
	}

	public static void buscarProduto() {
		System.out.println("\n-----------------");
		System.out.println("Busca de produto");
		System.out.println("-----------------");
		
		System.out.print("\nInforme o codigo do produto:");
		Integer codigo = Main.scanner.nextInt();
		Main.scanner.nextLine();
		System.out.println("Código: "+ Main.produtoList.get(codigo-1).getCodigo()+ "\nNome : "+Main.produtoList.get(codigo-1).getNome()+"\nPreço: "+Main.produtoList.get(codigo-1).getPreco());		
	}
}
