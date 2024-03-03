package br.com.treinamento.main.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.dao.ProdutoDao;
import br.com.treinamento.main.model.Produto;

public class ProdutoService {

	/*Cadastrar produto no banco de dados na tabela (tb_produto).*/
	public void cadastrarProduto() {
		System.out.printf("\n%-15s\n", "Cadastrar produto.");
		System.out.print("Informe nome do produto: ");
		String nome = Main.scanner.nextLine();
		System.out.printf("%-15s\n%s","---------------","Informe o preço: R$");
		String precoVar = Main.scanner.nextLine().replace(",", ".");
		BigDecimal preco = new BigDecimal(precoVar);
		Produto produto = new Produto(nome, preco);
		try {
			salvarProduto(produto);
			System.out.println(
					"-----------------------------\n\nProduto cadastrado com sucesso!\nPressione enter pra continuar.\n------------------------------");
			Main.scanner.nextLine();
		} catch (SQLException e) {
			System.out.println("Erro ao salvar produto: " + e.getMessage());
			System.out.println("\nPressione enter pra continuar.");
			System.out.println("\n------------------------------");
			Main.scanner.nextLine();
		}
	}
	
	/*Salvar produto */
	public void salvarProduto(Produto produto) throws SQLException {
		ProdutoDao produtodao = new ProdutoDao();
		produtodao.salvarProduto(produto);
	}

	/*Listar Produtos */
	public void listarProdutos() throws SQLException{
		ProdutoDao produtoDao = new ProdutoDao();
		List<Produto> produtos = produtoDao.listarProdutos();
		System.out.printf("\n%-10s %-15s\n", " ", "Listagem de Produtos");
		System.out.printf("%-15s %-20s %-10s", " ", "Id", "Nome", "Preço");
		for(Produto produto: produtos){
			System.out.printf("\n%-15s %-20s %-10s", produto.getId() , produto.getNome() , produto.getPreco());
		}
		System.out.printf("\n%-15s %-15s", " ", "Fim da lista!\n\nPressione enter para continuar.");
		Main.scanner.nextLine();
	}

	/*Visualizar produtos do banco de dados */
	public void buscarProduto() throws SQLException{
		ProdutoDao produtoDao = new ProdutoDao();
		System.out.printf("\n%-10s %-15s %-10s", " ", "Detalhar produtos", " ");
		System.out.print("\nInforme o ID do produto: ");
		Integer codigo = Main.scanner.nextInt();
		Main.scanner.nextLine();		
			Optional<Produto> protudoOptional = produtoDao.buscarProdutoPorId(codigo);
			if(protudoOptional.isEmpty()){
				System.out.println("\nProduto não encontrado");
			}else{
				Produto produto = protudoOptional.get();
				System.out.println("\nNome do Produto: "+ produto.getNome());
				System.out.println("Preço: "+ produto.getPreco());
			}
			System.out.println("Pressione enter para continuar.");
			Main.scanner.nextLine();
	}

	/*Excluir produtos do banco de dados*/
	public void excluirProduto() throws SQLException{
		ProdutoDao produtoDao = new ProdutoDao();
		System.out.println("\n-------------------------------------------");
		System.out.print("Informe o ID do produto que deseja excluir: ");
		Integer codigo = Main.scanner.nextInt();
		System.out.println("-------------------------------------------");
		Main.scanner.nextLine();
		Optional<Produto> produtoOptional = produtoDao.buscarProdutoPorId(codigo);
		if (produtoOptional.isEmpty()) {
			System.out.println("\nProduto não encontrado.");
		}else{
			Produto produto = produtoOptional.get();
			System.out.print("\nDeseja excluir o produto: " + produto.getNome() + "? (S/N).");
			System.out.print("\nConfirmação: ");
			String confirmacao = Main.scanner.nextLine();
			System.out.println("\n");
			if (confirmacao.equalsIgnoreCase("S")) {
				produtoDao.excluirProduto(codigo);
				System.out.println("\nProduto deletado com sucesso.");
			}else{
				System.out.println("E\nxclusão cancelada.");
				System.out.println("----------------------");
			}
			System.out.println("\nPressione enter para continuar");
			Main.scanner.nextLine();
		}
	}

	/*Atualizar um determidado produto */
	public void atualizarProduto() throws SQLException{
		ProdutoDao produtoDao = new ProdutoDao();
		System.out.println("\n-------------------------------------------");
		System.out.print("Informe o ID do produto que deseja atualizar: ");
		Integer codigo = Main.scanner.nextInt();
		System.out.println("-------------------------------------------\n");
		Main.scanner.nextLine();
		Optional<Produto> produtoOptional = produtoDao.buscarProdutoPorId(codigo);
		if (produtoOptional.isEmpty()) {
			System.out.println("Produto não encontrado.");
		}else{
			Produto produto = produtoOptional.get();
			System.out.print("Informe nome do produto: ");
			String nome = Main.scanner.nextLine();
			produto.setNome(nome);
			System.out.print("-----------------------------\nInforme o preço: R$");
			String precoVar = Main.scanner.nextLine().replace(",", ".");
			BigDecimal preco = new BigDecimal(precoVar);
			produto.setPreco(preco);
			produtoDao.atualizarProduto(produto);
			System.out.println("\n-----------------------------\nProduto atualizado com sucesso.");
		}
		System.out.println("\nPressione enter para continuar.");
		Main.scanner.nextLine();
	}
}
