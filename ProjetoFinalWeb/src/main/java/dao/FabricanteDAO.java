package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Fabricante;
import factory.ConexaoFactory;

public class FabricanteDAO {

	// METODO SALVAR
	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());

		comando.executeUpdate();

	}

	// METODO EXCLUIR
	public void excluir(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		comando.executeUpdate();
	}

	// METODO EDITAR
	public void editar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());

		comando.executeUpdate();
	}

	// METODO SELECIONAR POR CODIGO
	public Fabricante pesquisar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();

		Fabricante retorno = null;

		if (resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;
	}

	// METODO LISTAR POR FABRICANTE
	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante f = new Fabricante();
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));

			lista.add(f);
		}
		return lista;
	}

	// METODO LISTAR POR DESCRIÇÃO
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao  LIKE ?");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();

		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();

		while (resultado.next()) {
			Fabricante item = new Fabricante();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));

			lista.add(item);
		}

		return lista;
	}

////TESTE SALVAR
//	public static void main(String[] args) {
//		Fabricante f1 = new Fabricante();
//		f1.setDescricao("DESCRICAO 1");
//		
//		Fabricante f2 = new Fabricante();
//		f2.setDescricao("DESCRICAO 2");
//		
//		FabricanteDAO fdao = new FabricanteDAO();
//		
//		try {
//			fdao.salvar(f1);
//			fdao.salvar(f2);
//			System.out.println("Fabricante Salvos com sucesso!");
//		} catch (SQLException e) {
//			System.out.println("Ocorreu erro ao salvar um dos fabricantes!");
//			e.printStackTrace();
//		}
//		
//	}

////TESTE EXCLUIR
//	public static void main(String[] args) {
//		Fabricante f1 = new Fabricante();
//				f1.setCodigo(2L);
//		Fabricante f2 = new Fabricante();
//				f2.setCodigo(1L);
//				
//		FabricanteDAO fdao = new FabricanteDAO();
//		try {
//			fdao.excluir(f1);
//			fdao.excluir(f2);
//			System.out.println("Fabricante DELETADO com sucesso!");
//		} catch (SQLException e) {
//			System.out.println("Não foi possivel DELETAR um dos fabricantes!");
//			e.printStackTrace();
//		}
//		
//	}
////TESTE EDITAR
//	public static void main(String[] args) {
//		Fabricante f1 = new Fabricante();
//		f1.setCodigo(3L);
//		f1.setDescricao("DESCRICAO 3");
//		
//		FabricanteDAO fdao = new FabricanteDAO();
//		try {
//			fdao.editar(f1);
//			System.out.println("Fabricante EDITADO com sucesso!");
//		} catch (SQLException e) {
//			System.out.println("Não foi possivel EDITAR um dos fabricantes!");
//			e.printStackTrace();
//		}
//	}
////TESTE SELECIONAR 
//	public static void main(String[] args) {
//		Fabricante f1 = new Fabricante();
//		f1.setCodigo(3L);
//
//		Fabricante f2 = new Fabricante();
//		f2.setCodigo(5L);
//
//		FabricanteDAO fdao = new FabricanteDAO();
//
//		try {
//			Fabricante f3 = fdao.pesquisar(f1);
//			Fabricante f4 = fdao.pesquisar(f2);
//
//			System.out.println("RESULTADO 1:" + f3);
//			System.out.println("RESULTADO 2:" + f4);
//		} catch (SQLException e) {
//			System.out.println("ERRO ao buscar um dos fabricantes!");
//			e.printStackTrace();
//		}
//
//	}
////TESTE LISTAR ITENS
//	public static void main(String[] args) {
//		FabricanteDAO fdao = new FabricanteDAO();
//		try {
//			ArrayList<Fabricante> lista = fdao.listar();
//
//			for (Fabricante f : lista) {
//				System.out.println("Resultado: " + f);
//			}
//		} catch (SQLException e) {
//			System.out.println("Erro ao lista os Fabricantes!");
//			e.printStackTrace();
//		}
//	}

////TESTE LISTAR POR DESCRIÇÃO
//	public static void main(String[] args) {
//		Fabricante f1 = new Fabricante();
//		f1.setDescricao("2");
//		
//		FabricanteDAO fdao = new FabricanteDAO();
//		try {
//			ArrayList<Fabricante> lista = fdao.buscarPorDescricao(f1);
//
//			for (Fabricante f : lista) {
//				System.out.println("Resultado: " + f);
//			}
//		} catch (SQLException e) {
//			System.out.println("Erro ao lista por DESCRIÇÃO!");
//			e.printStackTrace();
//		}
//	}

}
