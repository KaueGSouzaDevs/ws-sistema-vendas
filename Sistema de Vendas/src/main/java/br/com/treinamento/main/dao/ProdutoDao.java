package br.com.treinamento.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.treinamento.main.connectionfactory.ConnectionFactory;
import br.com.treinamento.main.model.Produto;

public class ProdutoDao {

	public void salvarProduto(Produto produto) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		
		String sqlInsere = "INSERT INTO tb_produto(nome, preco) values (?,?)";
		
		//PreparedStatement para evitar o SQL Injection
		PreparedStatement pstm = connection.prepareStatement(sqlInsere);
		pstm.setString(1, produto.getNome());
		pstm.setBigDecimal(2, produto.getPreco());
		
		pstm.execute();
		pstm.close();
		connection.close();
		
	}

	public List<Produto> listarProdutos() throws SQLException{
		List<Produto> produtoList = new ArrayList<Produto>();
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlList = "SELECT id_produto, nome, preco FROM tb_produto";
		PreparedStatement pstm = connection.prepareStatement(sqlList);
		pstm.execute();
		ResultSet rst = pstm.getResultSet();
		while(rst.next()){
			Produto produto = new Produto();
			produto.setId(rst.getInt("id_produto"));
			produto.setNome(rst.getString("nome"));
			produto.setPreco(rst.getBigDecimal("preco"));
			produtoList.add(produto);
		}
		rst.close();
		pstm.close();
		connection.close();
		return produtoList;
	}

	public Optional<Produto> buscarPorId(Integer id) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlBusca = "SELECT id_produto, nome, preco FROM tb_produto WHERE id_produto = ?";
		PreparedStatement pstm = connection.prepareStatement(sqlBusca);
		pstm.setInt(1, id);
		pstm.execute();
		ResultSet rst = pstm.getResultSet();
		Optional<Produto> produtoOptional = Optional.empty();
		if(rst.next()){
			Produto produto = new Produto();
			produto.setId(rst.getInt("id_produto"));
			produto.setNome(rst.getString("nome"));
			produto.setPreco(rst.getBigDecimal("preco"));
		}
		rst.close();
		pstm.close();
		connection.close();
		return produtoOptional;
	}

	public void excluirProduto(Integer codigo) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlDeletar = "DELETE * FROM tb_produto WHERE id_produto = ?";
		PreparedStatement pstm = connection.prepareStatement(sqlDeletar);
		pstm.setInt(1, codigo);
		pstm.execute();
		pstm.close();
		connection.close();
	}

	public void atualizarProduto(Produto produto) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();

		String sqlUpdate = "UPDATE tb_produto set nome =? , preco = ? WHERE id_produto = ?";

		PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
		pstm.setString(1, produto.getNome());
		pstm.setBigDecimal(2,produto.getPreco());
		pstm.setInt(3, produto.getId());


		pstm.execute();
		pstm.close();
		connection.close();
	}


}