package br.com.treinamento.main.service;

import java.sql.SQLException;
import java.util.Optional;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.dao.PedidoDao;
import br.com.treinamento.main.dto.ResumoPedidoDTO;
import br.com.treinamento.main.model.Cliente;
import br.com.treinamento.main.model.ItemPedido;
import br.com.treinamento.main.model.Pedido;
import br.com.treinamento.main.model.Produto;

public class PedidoService {

    // public void gerarPedido() throws SQLException {

    // List<ItemPedido> itensList = new ArrayList<ItemPedido>();
    // ProdutoDao produtoDao = new ProdutoDao();
    // ClienteDao clienteDao = new ClienteDao();

    // System.out.println("---------------------------------------------");
    // System.out.printf("%-15s%-15s\n", " ", "Cadastro de Pedido");
    // System.out.println("---------------------------------------------");
    // System.out.print("Informe o Id do cliente: ");
    // Integer idCliente = Main.scanner.nextInt();
    // Main.scanner.nextLine();
    // String confirmacao = "";
    // if(clienteDao.buscarClientePorId(idCliente).isEmpty()) {
    // System.out.println("Cliente não encontrada.");
    // System.out.print("Pressione ENTER para continuar...");
    // Main.scanner.nextLine();
    // } else{
    // do{
    // System.out.print("Informe o Id do Produto que deseja: ");
    // Integer idProduto = Main.scanner.nextInt();
    // Main.scanner.nextLine();
    // while(produtoDao.buscarProdutoPorId(idProduto).isEmpty()) {
    // System.out.println("Produto não encontrado, tente novamente...");
    // System.out.print("Informe o Id do Produto que deseja: ");
    // idProduto = Main.scanner.nextInt();
    // Main.scanner.nextLine();
    // }
    // System.out.print("Informe a quantidade desejada: ");
    // Integer qnt = Main.scanner.nextInt();
    // Main.scanner.nextLine();
    // System.out.print("\n Deseja adicionar mais itens ao pedido (S/N)? ");
    // confirmacao = Main.scanner.nextLine();
    // Produto produto = new Produto();
    // produto.setId(idProduto);
    // produto.setNome(produtoDao.buscarProdutoPorId(idProduto).get().getNome());
    // produto.setPreco(produtoDao.buscarProdutoPorId(idProduto).get().getPreco());
    // ItemPedido itemPedido = new ItemPedido(produto, idCliente, qnt);
    // itensList.add(itemPedido);
    // System.out.println(produto);
    // } while(confirmacao.equalsIgnoreCase("S"));
    // }
    // }

    public void gerarPedido() {
        SistemaService.limparTela();
        ClienteService clienteService = new ClienteService();
        ProdutoService produtoService = new ProdutoService();
        ItemPedidoService itemPedidoService = new ItemPedidoService();
        try {
            StringBuilder header = new StringBuilder();
            header.append("---------------------------------------------");
            System.out.println(header);
            System.out.print("Informe o código do cliente: ");
            Integer codigoCliente = Main.scanner.nextInt();
            Main.scanner.nextLine();
            Optional<Cliente> clienteOptional = clienteService.buscaPorId(codigoCliente);
            if (clienteOptional.isEmpty()) {
                System.out.println("Cliente não encontrado.");
            } else {
                Cliente cliente = clienteOptional.get();
                Pedido pedido = new Pedido(cliente);
                Integer pedidoGerado = cadastrarPedido(pedido);

                if (pedidoGerado != null) {
                    pedido.setId(pedidoGerado);
                    System.out.println("---------------------------------------------");
                    System.out.println("\nPedido gerado com sucesso para o cliente: " + pedido.getCliente().getNome());
                    System.out.println("Número do pedido: " + pedido.getId());
                    System.out.println("\n---------------------------------------------");

                    while (true) {
                        System.out.print("\nDeseja adicionar um produto no pedido? (S/N): ");
                        String opcao = Main.scanner.nextLine();

                        if (opcao.equalsIgnoreCase("S")) {

                            System.out.print("\nInforme o código do produto: ");
                            Integer codigoProduto = Main.scanner.nextInt();
                            Main.scanner.nextLine();
                            Optional<Produto> produtoOptional = produtoService.buscaPorId(codigoProduto);

                            // !VERIFICAÇÃO PRA SABER SE EXISTE PRODUTO

                            if (produtoOptional.isEmpty()) {
                                System.out.println("\nProduto não encontrado!");
                            }else{
                                Produto produto = produtoOptional.get();
                                System.out.print("Informe a quantidade desejada: ");
                                Integer quantidade = Main.scanner.nextInt();
                                Main.scanner.nextLine();
                                ItemPedido itemPedido = new ItemPedido(pedido, produto, quantidade);
                                itemPedidoService.adicionarItemPedido(itemPedido);
                            }
                            
                        } else if (opcao.equalsIgnoreCase("n")) {
                            ResumoPedidoDTO resumo = itemPedidoService.getTotais(pedido.getId());
                            pedido.setTotalPedido(resumo.getValorTotal());
                            
                            
                            // !Atualizar total de pedidos no banco de dados
                            
                            itemPedidoService.atualizaTotalPedido(pedido.getId(), pedido.getTotalPedido());

                            // !Imprime as informações
                            System.out.println("\nPedido finalizado com sucesso!");
                            System.out.println("---------------------------------------------");
                            System.out.println("Id do pedido: " + pedido.getId());
                            System.out.println("Valor total do pedido: " + pedido.getTotalPedido());
                            System.out.println("Quantidade de itens: " + resumo.getTotalItens());
                            System.out.println("---------------------------------------------");
                            break;
                        } else {
                            System.out.println("Opção inválida!");
                        }

                    }

                }
            }

            System.out.print("\nPressione enter para voltar ao menu...");
            Main.scanner.nextLine();

        } catch (

        Exception e) {
        }
    }

    private Integer cadastrarPedido(Pedido pedido) {
        PedidoDao pedidoDao = new PedidoDao();
        Integer numeroPedidoGerado = null;
        try {
            numeroPedidoGerado = pedidoDao.criarPedido(pedido);
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar pedido: " + e.getMessage());
        }
        return numeroPedidoGerado;
    }

}
