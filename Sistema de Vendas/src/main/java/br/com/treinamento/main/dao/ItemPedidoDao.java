package br.com.treinamento.main.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import br.com.treinamento.main.connectionfactory.ConnectionFactory;
import br.com.treinamento.main.dto.ResumoPedidoDTO;
import br.com.treinamento.main.model.Cliente;
import br.com.treinamento.main.model.ItemPedido;

public class ItemPedidoDao {

    public void criarItemPedido(ItemPedido itemPedido) throws SQLException {

        ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		
		String sqlInsere = "INSERT INTO tb_itens_pedido(id_pedido, id_produto, quantidade, valor_unitario, valor_total) values (?,?,?,?,?)";
		
		//PreparedStatement para evitar o SQL Injection
		PreparedStatement pstm = connection.prepareStatement(sqlInsere);
		pstm.setInt(1, itemPedido.getPedido().getId());
        pstm.setInt(2, itemPedido.getProduto().getId());
        pstm.setInt(3, itemPedido.getQuantidade());
        pstm.setBigDecimal(4, itemPedido.getValorUnitario());
        pstm.setBigDecimal(5, itemPedido.getValorTotal());
        
        pstm.execute();
		pstm.close();
		connection.close();
    }

    public ResumoPedidoDTO buscaResumoPorPedido(Integer idPedido) throws SQLException {
        ResumoPedidoDTO resumoPedidoDTO = new ResumoPedidoDTO();
        resumoPedidoDTO.setTotalItens(0);
        resumoPedidoDTO.setValorTotal(BigDecimal.ZERO);
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.abrirConexao();
        String sqlResumoPorId = "SELECT SUM(valor_total) AS valor_total, COUNT(id_item_pedido) AS qnt_itens FROM tb_itens_pedido WHERE id_pedido = ?";
        PreparedStatement pstm = connection.prepareStatement(sqlResumoPorId);
        pstm.setInt(1, idPedido);
        pstm.execute();
        ResultSet rst = pstm.getResultSet();
        if(rst.next()){
            resumoPedidoDTO.setValorTotal(rst.getBigDecimal("valor_total"));
            resumoPedidoDTO.setTotalItens(rst.getInt("qnt_itens"));
        }
        rst.close();
        pstm.close();
        connection.close();
        return resumoPedidoDTO;
    }

    public void atualizarValorTotal(Integer idPedido, BigDecimal totalPedido) throws SQLException {
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlUpdate = "UPDATE tb_pedido set total_pedido = ? WHERE id_pedido = ?";
		PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
		pstm.setBigDecimal(1, totalPedido);
        pstm.setInt(2, idPedido);
		pstm.execute();
		pstm.close();
		connection.close();
    }
}
