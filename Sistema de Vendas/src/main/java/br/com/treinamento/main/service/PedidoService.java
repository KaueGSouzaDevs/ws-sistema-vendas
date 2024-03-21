package br.com.treinamento.main.service;

import java.util.Optional;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.dao.ClienteDao;
import br.com.treinamento.main.dao.PedidoDao;
import br.com.treinamento.main.model.Cliente;
import br.com.treinamento.main.model.Pedido;

public class PedidoService {

    // public void gerarPedido() throws SQLException {

    //     List<ItemPedido> itensList = new ArrayList<ItemPedido>();
    //     ProdutoDao produtoDao = new ProdutoDao();
    //     ClienteDao clienteDao = new ClienteDao();

    //     System.out.println("---------------------------------------------");
    //     System.out.printf("%-15s%-15s\n", " ", "Cadastro de Pedido");
    //     System.out.println("---------------------------------------------");
    //     System.out.print("Informe o Id do cliente: ");
    //     Integer idCliente = Main.scanner.nextInt();
    //     Main.scanner.nextLine();
    //     String confirmacao = "";
    //     if(clienteDao.buscarClientePorId(idCliente).isEmpty()) {
    //         System.out.println("Cliente não encontrada.");
    //         System.out.print("Pressione ENTER para continuar...");
    //         Main.scanner.nextLine();
    //     } else{
    //         do{
    //             System.out.print("Informe o Id do Produto que deseja: ");
    //             Integer idProduto = Main.scanner.nextInt();
    //             Main.scanner.nextLine();
    //             while(produtoDao.buscarProdutoPorId(idProduto).isEmpty()) {
    //                 System.out.println("Produto não encontrado, tente novamente...");
    //                 System.out.print("Informe o Id do Produto que deseja: ");
    //                 idProduto = Main.scanner.nextInt();
    //                 Main.scanner.nextLine();
    //             }
    //             System.out.print("Informe a quantidade desejada: ");
    //             Integer qnt = Main.scanner.nextInt();
    //             Main.scanner.nextLine();
    //             System.out.print("\n Deseja adicionar mais itens ao pedido (S/N)? ");
    //             confirmacao = Main.scanner.nextLine();
    //             Produto produto = new Produto();
    //             produto.setId(idProduto);
    //             produto.setNome(produtoDao.buscarProdutoPorId(idProduto).get().getNome());
    //             produto.setPreco(produtoDao.buscarProdutoPorId(idProduto).get().getPreco());
    //             ItemPedido itemPedido = new ItemPedido(produto, idCliente, qnt);
    //             itensList.add(itemPedido);
    //             System.out.println(produto);
    //         } while(confirmacao.equalsIgnoreCase("S"));
    //     }
    // }

    public void gerarPedido(){
        SistemaService.limparTela();
        ClienteService clienteService = new ClienteService();
        try {
            StringBuilder header = new StringBuilder();
            header.append("11 - Gerar Pedido\n");
            header.append("---------------------------------------------");
            System.out.println(header);
            System.out.print("Informe o código do cliente");
            Integer codigoCliente = Main.scanner.nextInt();
            Main.scanner.nextLine();
            Optional<Cliente> clienteOptional = clienteService.buscaPorId(codigoCliente);
            if(clienteOptional.isEmpty()){
                System.out.println("Cliente não encontrado.");
            }else{
                Cliente cliente = clienteOptional.get();
                Pedido pedido = new Pedido(cliente);
                Integer pedidoGerado = cadastrarPedido(pedido);

                if(pedidoGerado != null){
                    pedido.setId(pedidoGerado);
                    System.out.println("Pedido gerado com sucesso para o cliente: "+pedido.getCliente().getNome());
                    System.out.println(" ! Número do pedido: "+ pedidoGerado);
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }


    private Integer cadastrarPedido(Pedido pedido) {
        PedidoDao pedidoDao = new PedidoDao();
        Integer numeroPedidoGerado = null;
        try {
            numeroPedidoGerado = pedidoDao.criarPedido(pedido);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar pedido: "+ e.getMessage());
        }
        return numeroPedidoGerado;
    }





}
