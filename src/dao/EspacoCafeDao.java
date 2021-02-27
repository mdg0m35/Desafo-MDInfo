package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.EspacoCafeBeans;
import connection.Conexao;

/**
 * Camada de banco de dados para trabalhar com o local do coffee-break
 */
public class EspacoCafeDao {

	// Obter todos os eventos cadastrados
	public ArrayList<String> listarEspacos() {

		// Variável
		ArrayList<String> lista = new ArrayList<String>();

		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Tentativa
		try {
			String sql = "SELECT nomeEspacoCafe FROM espacocafe";

			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				lista.add(rs.getString(1));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter a listagem de espaços, erro: " + e.getMessage());
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return lista;
	}

	// Obter a quantidade de espaços de café cadastrados
	public int qtdEspacoCafe() {

		// Variável
		int qtd = 0;

		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Tentativa
		try {
			String sql = "SELECT COUNT(*) FROM espacoCafe";

			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				qtd = rs.getInt(1);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null,
					"Falha ao obter a quantidade de espaços de café, erro: " + e.getMessage());
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return qtd;
	}

	// Cadastrar
	public String cadastrarEspaco(EspacoCafeBeans obj) {

		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Mensagem de retorno
		String retorno = null;

		// Tentativa
		try {
			String sql = "INSERT INTO espacocafe (nomeEspacoCafe) VALUES (?)";

			PreparedStatement pstmt = con.conexao.prepareStatement(sql);
			pstmt.setString(1, obj.getNomeEspacoCafe());
			pstmt.execute();

			retorno = "Espaço cadastrado com sucesso!";
		} catch (Exception e) {
			retorno = "Falha ao cadastrar o espaço, erro: " + e.getMessage();
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return retorno;

	}

	// Retorna as salas
	public ArrayList<String> listarSalas() {

		// ArrayList
		ArrayList<String> dados = new ArrayList<String>();

		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Tentativa
		try {
			String sql = "SELECT nomeEspacoCafe FROM espacocafe";

			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				dados.add(rs.getString(1));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao listar as salas, erro: " + e.getMessage());
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return dados;

	}

	// Listar todos os espaços para café da primeira etapa do primeiro evento
	public ArrayList<String> primeiraEtapaEspaco1(int codigoEtapa) {

		// ArrayList
		ArrayList<String> nomes = new ArrayList<String>();

		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Tentativa
		try {

			String sql = "SELECT nomePessoa FROM pessoas WHERE etapa1 = ?";

			PreparedStatement pstmt = con.conexao.prepareStatement(sql);
			pstmt.setInt(1, codigoEtapa);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				nomes.add(rs.getString(1));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter os nomes da primeira etapa: " + e.getMessage());
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return nomes;

	}

	// Listar todas as pessoas que participarão da segunda etapa do primeiro evento
	public ArrayList<String> primeiraEtapaEspaco2(int codigoEtapa) {

		// ArrayList
		ArrayList<String> nomes = new ArrayList<String>();

		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Tentativa
		try {

			String sql = "SELECT nomePessoa FROM pessoas WHERE etapa2 = ?";

			PreparedStatement pstmt = con.conexao.prepareStatement(sql);
			pstmt.setInt(1, codigoEtapa);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				nomes.add(rs.getString(1));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter os nomes da segunda etapa: " + e.getMessage());
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return nomes;

	}

}