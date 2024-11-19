package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	private static final String USUARIO = "root";
	private static final String SENHA= "anhanguera";
	private static final String URL = "jdbc:mysql://127.0.0.1:3306/projetofinal";
	
	public static Connection conectar() throws SQLException{
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}
	public static void main(String[] args) {
		try {
		Connection conexao = ConexaoFactory.conectar();
		System.out.println("Conexão Realizada com Sucesso!");
		} catch(SQLException ex) {
			ex.printStackTrace();
			System.out.println("Não foi possivel realizar a Conexão!");
		}
	}
}
