package br.com.treinamento.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import br.com.treinamento.main.connectionfactory.ConnectionFactory;
import br.com.treinamento.main.model.Cliente;

public class ClienteDao {
    
    //TODO: Salvar cliente no banco de dados
    public void salvarCliente(Cliente cliente) throws SQLException{
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.abrirConexao();
        String sqlCadastrar = "INSERT INTO tb_cliente(nome_cliente, email_cliente) values (?,?)";
        PreparedStatement pstm = connection.prepareStatement(sqlCadastrar);
        pstm.setString(1, cliente.getNome());
        pstm.setString(2, cliente.getEmail());
        pstm.execute();
        pstm.close();
        connection.close();
    }

    //TODO: Listar clientes do banco de dados
    public List<Cliente> listarClientes() throws SQLException{
        List<Cliente> clienteList = new ArrayList<Cliente>();
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.abrirConexao();
        String sqlList = "SELECT id_cliente, nome_cliente, email_cliente FROM tb_cliente";
        PreparedStatement pstm = connection.prepareStatement(sqlList);
        pstm.execute();
        ResultSet rst = pstm.getResultSet();
        while(rst.next()){
            Cliente cliente = new Cliente();
            cliente.setId(rst.getInt("id_cliente"));
            cliente.setNome(rst.getString("nome_cliente"));
            cliente.setEmail(rst.getString("email_cliente"));
            clienteList.add(cliente);
        }
        rst.close();
        pstm.close();
        connection.close();
        return clienteList;
    }

    //TODO: Buscar cliente especifico no banco de dados
    public Optional<Cliente> buscarClientePorId(Integer id) throws SQLException{
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.abrirConexao();
        String sqlBusca = "SELECT id_cliente, nome_cliente, email_cliente FROM tb_cliente WHERE id_cliente = ?";
        PreparedStatement pstm = connection.prepareStatement(sqlBusca);
        pstm.setInt(1, id);
        pstm.execute();
        ResultSet rst = pstm.getResultSet();
        Optional<Cliente> clienteOptional = Optional.empty();
        if(rst.next()){
            Cliente cliente = new Cliente();
            cliente.setId(rst.getInt("id_cliente"));
            cliente.setNome(rst.getString("nome_cliente"));
            cliente.setEmail(rst.getString("email_cliente"));
            clienteOptional = Optional.of(cliente);
        }
        rst.close();
        pstm.close();
        connection.close();
        return clienteOptional;
    }

    //TODO: Excluir cliente do banco de dados
    public void excluirCliente(Integer id) throws SQLException{
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.abrirConexao();

        String sqlDelete = "DELETE FROM tb_cliente WHERE id_cliente = ?";

        PreparedStatement pstm = connection.prepareStatement(sqlDelete);
        pstm.setInt(1, id);
        pstm.execute();
        pstm.close();
        connection.close();
    }

    //TODO: Alterar cliente do banco de dados
    public void atualizarCliente(Cliente cliente) throws SQLException{
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.abrirConexao();
        String sqlUpdate = "UPDATE tb_cliente set nome_cliente = ?, email_cliente = ? WHERE id_cliente = ?";
        PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
        pstm.setString(1, cliente.getNome());
        pstm.setString(2, cliente.getEmail());
        pstm.setInt(3, cliente.getId());
        pstm.execute();
        pstm.close();
        connection.close();
    }

}
