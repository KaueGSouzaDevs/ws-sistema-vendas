package br.com.treinamento.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.treinamento.main.connectionfactory.ConnectionFactory;
import br.com.treinamento.main.model.Pedido;

public class PedidoDao {

    public Integer criarPedido(Pedido pedido) throws SQLException {
        ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlInsere = "INSERT INTO tb_pedido(id_cliente, total_pedido) values (?,?)";
		//PreparedStatement para evitar o SQL Injection
		PreparedStatement pstm = connection.prepareStatement(sqlInsere, Statement.RETURN_GENERATED_KEYS);
		pstm.setInt(1, pedido.getCliente().getId());
        pstm.setBigDecimal(2, pedido.getTotalPedido());
        pstm.execute();
        Integer numeroPedidoGerado = null;
        ResultSet rs = pstm.getGeneratedKeys();
        if (rs.next()) {
            numeroPedidoGerado = rs.getInt(1);
        }
		pstm.close();
		connection.close();
        return numeroPedidoGerado;
    }

}
