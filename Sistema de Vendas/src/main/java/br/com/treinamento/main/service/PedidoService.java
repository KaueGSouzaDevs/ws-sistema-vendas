package br.com.treinamento.main.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.dao.ProdutoDao;
import br.com.treinamento.main.model.ItemPedido;
import br.com.treinamento.main.model.Produto;

public class PedidoService {

    public void cadastrarPedido() throws SQLException{

        List<ItemPedido> itensList = new ArrayList<ItemPedido>();

        System.out.println("---------------------------------------------");
        System.out.printf("%15-s%15-s", " ","Cadastro de Pedido");
        System.out.println("---------------------------------------------");
        System.out.print("Informe o Id do cliente: ");
        Integer idCliente = Main.scanner.nextInt();
        Main.scanner.nextLine();
        System.out.print("Informe o Id do Produto que deseja: ");
        Main.scanner.nextInt();
        Main.scanner.nextLine();
        System.out.print("Informe a quantidade desejada: ");
        Main.scanner.nextInt();
        Main.scanner.nextLine();
        String confirmacao;
        do{
            System.out.print("Informe o Id do Produto que deseja: ");
            Integer idProduto = Main.scanner.nextInt();
            Main.scanner.nextLine();
            System.out.print("Informe a quantidade desejada: ");
            Integer qnt = Main.scanner.nextInt();
            Main.scanner.nextLine();
            System.out.print("\n Deseja adicionar mais itens ao pedido (S/N)?");
            confirmacao = Main.scanner.nextLine();
            ProdutoDao produtoDao = new ProdutoDao();
            Produto produto = new Produto();
            produto.setId(idProduto);
            produto.setNome(produtoDao.buscarProdutoPorId(idProduto).get().getNome());
            produto.setPreco(produtoDao.buscarProdutoPorId(idProduto).get().getPreco());
            ItemPedido itemPedido = new ItemPedido(produto, idCliente, qnt);
            itensList.add(itemPedido);
            System.out.println(produto);
        }while (confirmacao.equalsIgnoreCase("S"));

        









    }
	
}
