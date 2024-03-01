package br.com.treinamento.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.treinamento.main.connectionfactory.ConnectionFactory;
import br.com.treinamento.main.model.Cliente;

public class ClienteDao {
    
    //TODO: Salvar cliente
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

    //TODO: Listar cliente
    public List<Cliente> listarClientes() throws SQLException{
        List<Cliente> clienteList = new ArrayList<Cliente>();
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.abrirConexao();
        String sqlList = "SELECT id_cliente, nome_cliente, _email_cliente FROM tb_cliente";
        PreparedStatement pstm = connection.prepareStatement(sqlList);
        pstm.execute();
        ResultSet rst = pstm.getResultSet();
        while(rst.next()){
            Cliente cliente = new Cliente();
            cliente.setCodigo(rst.getInt("id_cliente"));
            cliente.setNome(rst.getString("nome_cliente"));
            cliente.setEmail(rst.getString("email_cliente"));
            clienteList.add(cliente);
        }
        rst.close();
        pstm.close();
        connection.close();
        return clienteList;
    }

































}
