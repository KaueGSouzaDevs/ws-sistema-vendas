package br.com.treinamento.main.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import br.com.treinamento.main.Main;
import br.com.treinamento.main.dao.ClienteDao;
import br.com.treinamento.main.model.Cliente;

public class ClienteService {

    //! Cadastrar clientes chamando função de clienteDao
    public void cadastrarCliente(){
        System.out.println("---------------");
        System.out.printf("\n%-15s", "Informe o nome do cliente: ");
        String nome = Main.scanner.nextLine();
        System.out.printf("\nInforme um E-mail de contato: ");
        String email = Main.scanner.nextLine();
        Cliente cliente = new Cliente(nome, email);
        try {
            salvarCliente(cliente);
            System.out.println("---------------------------\nCliente cadastrado com sucesso!\n---------------------------\nPressione enter para continuar...");
            Main.scanner.nextLine();
        } catch (SQLException e) {
			System.out.println("Erro ao salvar o cliente: " + e.getMessage());
			System.out.println("\nPressione enter pra continuar.");
			System.out.println("\n------------------------------");
			Main.scanner.nextLine();
        }
    }

    //! Salvar clientes chamando função de clienteDao
    public void salvarCliente(Cliente cliente) throws SQLException{
        ClienteDao clienteDao = new ClienteDao();
        clienteDao.salvarCliente(cliente);
    }

    //! Listar clientes chamando função de clienteDao
    public void listarClientes() throws SQLException{
        ClienteDao clienteDao = new ClienteDao();
        List<Cliente> clienteList = clienteDao.listarClientes();
        System.out.println("------------------------------------------------------------");
        System.out.printf("\n%-10s %-15s\n", " ", "Listagem de Produtos");
		System.out.printf("%-5s %-25s %-10s", "Id", "Nome", "E-mail");
        for(Cliente cliente: clienteList){
            System.out.printf("\n%-5s %-25s %-10s", cliente.getId() , cliente.getNome() , cliente.getEmail());
		}
		System.out.printf("\n%-15s %-15s", " ", "Fim da lista!\n\nPressione enter para continuar.");
		Main.scanner.nextLine();
    }

    //! Visualizar cliente por Id chamando função de clienteDao
    public void buscarCliente() throws SQLException{
        ClienteDao clienteDao = new ClienteDao();
        System.out.print("------------------------------------------------\nInforme o Id do cliente que deseja visualizar: ");
        Integer id = Main.scanner.nextInt();
        Main.scanner.nextLine();
        Optional<Cliente> clienteOptional = clienteDao.buscarClientePorId(id);
        if(clienteOptional.isEmpty()){
            System.out.println("\nId de cliente não localizado no banco de dados.");
        }else{
            Cliente cliente = clienteOptional.get();
            System.out.println("---------------");
            System.out.println("Id: "+ cliente.getId()+"\nNome: "+ cliente.getNome()+"\nE-mail: "+ cliente.getEmail());
        }
        System.err.println("-----------------------------------------------\n\nPressione enter para continuar.");
        Main.scanner.nextLine();
    }

    //! Exclui um cliente por Id chamando função de clienteDao
    public void excluirCliente() throws SQLException{
        ClienteDao clienteDao = new ClienteDao();
        System.out.print("---------------\nInforme o id do cliente que dejesa excluir: ");
        Integer codigo = Main.scanner.nextInt();
        Main.scanner.nextLine();
        Optional<Cliente> clienteOptional = clienteDao.buscarClientePorId(codigo);
        if(clienteOptional.isEmpty()){
            System.out.println("\nId do cliente não localizado.");
        }else{
            Cliente cliente = clienteOptional.get();
            System.out.println("Deseja excluir o cliente: "+ cliente.getNome()+"? (S/N)");
            System.out.print("Confirmação: ");
            String confirmacao = Main.scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("N")) {
                System.out.println("Operação cancelada.");
            }else{
                clienteDao.excluirCliente(codigo);
                System.out.println("Cliente excluido com sucesso.");
            }
        }
        System.out.println("---------------\nPressione enter para continuar.");
        Main.scanner.nextLine();
    }

    //! Altera os dados de um cliente por um id informado chamando função de clienteDao
    public void atualizarCliente() throws SQLException{
        ClienteDao clienteDao = new ClienteDao();
        System.out.print("\n---------------\nInforme o Id do cliente que deseja alterar: ");
        Integer codigo = Main.scanner.nextInt();
        Main.scanner.nextLine();
        Optional<Cliente> clienteOptional = clienteDao.buscarClientePorId(codigo);
        if (clienteOptional.isEmpty()) {
            System.out.println("\nId do cliente não localizado.");
        }else{
            Cliente cliente = clienteOptional.get();
            System.out.println("\nDeseja alterar o cliente: "+ cliente.getNome()+ "? (S/N)");
            System.out.print("\nConfirmação: ");
            String confirmacao = Main.scanner.nextLine();
            if (confirmacao.equalsIgnoreCase("N")) {
                System.out.println("\nOperação cancelada.");                
            }else{
                System.out.print("\nInforme o nome do cliente: ");
                String nome = Main.scanner.nextLine();
                cliente.setNome(nome);
                System.out.print("\nInforme o E-mail de contato do cliente: ");
                String email = Main.scanner.nextLine();
                cliente.setEmail(email);
                clienteDao.atualizarCliente(cliente);
                System.out.println("\nCliente atualizado com sucesso.");
            }
        }
        System.out.println("---------------\nPressione enter para continuar.");
        Main.scanner.nextLine();
    }

    public Optional<Cliente> buscaPorId(Integer codigo) throws SQLException {
		ClienteDao clienteDao = new ClienteDao();
		return clienteDao.buscarClientePorId(codigo);
	}
}
