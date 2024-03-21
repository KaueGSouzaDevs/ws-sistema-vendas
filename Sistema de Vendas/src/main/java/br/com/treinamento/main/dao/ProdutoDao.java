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

	//TODO: Salvar produto no banco de dados
	public void salvarProduto(Produto produto) throws SQLException {

		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		
		String sqlInsere = "INSERT INTO tb_produto(nome_produto, preco_produto) values (?,?)";
		
		//PreparedStatement para evitar o SQL Injection
		PreparedStatement pstm = connection.prepareStatement(sqlInsere);
		pstm.setString(1, produto.getNome());
		pstm.setBigDecimal(2, produto.getPreco());
		
		pstm.execute();
		pstm.close();
		connection.close();
		
	}

	//TODO: Listar produtos do banco de dados
	public List<Produto> listarProdutos() throws SQLException{
		List<Produto> produtoList = new ArrayList<Produto>();
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlList = "SELECT id_produto, nome_produto, preco_produto FROM tb_produto ORDER BY id_produto";
		PreparedStatement pstm = connection.prepareStatement(sqlList);
		pstm.execute();
		ResultSet rst = pstm.getResultSet();
		while(rst.next()){
			Produto produto = new Produto();
			produto.setId(rst.getInt("id_produto"));
			produto.setNome(rst.getString("nome_produto"));
			produto.setPreco(rst.getBigDecimal("preco_produto"));
			produtoList.add(produto);
		}
		rst.close();
		pstm.close();
		connection.close();
		return produtoList;
	}

	//TODO: Buscar produto especifico do banco de dados
	public Optional<Produto> buscarProdutoPorId(Integer id) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlBusca = "SELECT id_produto, nome_produto, preco_produto FROM tb_produto WHERE id_produto = ?";
		PreparedStatement pstm = connection.prepareStatement(sqlBusca);
		pstm.setInt(1, id);
		pstm.execute();
		ResultSet rst = pstm.getResultSet();
		Optional<Produto> produtoOptional = Optional.empty();
		if(rst.next()){
			Produto produto = new Produto();
			produto.setId(rst.getInt("id_produto"));
			produto.setNome(rst.getString("nome_produto"));
			produto.setPreco(rst.getBigDecimal("preco_produto"));
			produtoOptional = Optional.of(produto);
		}
		rst.close();
		pstm.close();
		connection.close();
		return produtoOptional;
	}

	//TODO: Excluir produto do banco de dados
	public void excluirProduto(Integer codigo) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlDeletar = "DELETE FROM tb_produto WHERE id_produto = ?";
		PreparedStatement pstm = connection.prepareStatement(sqlDeletar);
		pstm.setInt(1, codigo);
		pstm.execute();
		pstm.close();
		connection.close();
	}
    
	//TODO: Alterar produto do banco de dados
	public void atualizarProduto(Produto produto) throws SQLException{
		ConnectionFactory factory = new ConnectionFactory();
		Connection connection = factory.abrirConexao();
		String sqlUpdate = "UPDATE tb_produto set nome_produto =? , preco_produto = ? WHERE id_produto = ?";
		PreparedStatement pstm = connection.prepareStatement(sqlUpdate);
		pstm.setString(1, produto.getNome());
		pstm.setBigDecimal(2,produto.getPreco());
		pstm.setInt(3, produto.getId());
		pstm.execute();
		pstm.close();
		connection.close();
	}

}
