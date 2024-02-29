package br.com.treinamento.main.connectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public Connection abrirConexao() throws SQLException{
		
		return DriverManager.getConnection("jdbc:postgresql://localhost/jdbc","root","123456");
		
	}
	
}
