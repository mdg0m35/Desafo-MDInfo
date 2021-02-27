package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import beans.PessoaBeans;
import connection.Conexao;
import forms.Principal;

/**
 * Camada de banco de dados para trabalhar com os participantes dos eventos
 */
public class PessoaDao {

	// Obter a quantidade de pessoas cadastradas
	public int qtdCadastros() {

		// Vari�vel
		int qtd = 0;

		// Conex�o com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Tentativa
		try {
			String sql = "SELECT COUNT(*) FROM pessoas";

			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				qtd = rs.getInt(1);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter a quantidade de pessoas, erro: " + e.getMessage());
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return qtd;
	}

	// Cadastrar
	public String cadastrarPessoa(PessoaBeans obj) {

		// Conex�o com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Mensagem de retorno
		String retorno = null;

		// Tentativa
		try {

			String sql = "INSERT INTO pessoas (nomePessoa, sobrenomePessoa, etapa1, etapa2) VALUES (?, ?, ?, ?)";

			PreparedStatement pstmt = con.conexao.prepareStatement(sql);
			pstmt.setString(1, obj.getNomePessoa());
			pstmt.setString(2, obj.getSobrenomePessoa());

			// Intercala os eventos
			if (qtdCadastros() % 4 == 0) {
				pstmt.setInt(3, 2);
				pstmt.setInt(4, 2);
			} else if (qtdCadastros() % 3 == 0) {
				pstmt.setInt(3, 2);
				pstmt.setInt(4, 1);
			} else if (qtdCadastros() % 2 == 0) {
				pstmt.setInt(3, 1);
				pstmt.setInt(4, 2);
			} else {
				pstmt.setInt(3, 1);
				pstmt.setInt(4, 1);
			}

			pstmt.execute();

			retorno = "Pessoa cadastrada com sucesso!";
		} catch (Exception e) {
			retorno = "Falha ao cadastrar a pessoa, erro: " + e.getMessage();
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return retorno;

	}

	// Listar todos os nomes
	public ArrayList<String> listarNomes() {

		// ArrayList
		ArrayList<String> lista = new ArrayList<String>();

		// Conex�o com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();

		// Tentativa
		try {
			String sql = "SELECT nomePessoa, sobrenomePessoa FROM pessoas";

			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				lista.add(rs.getString(1) + " " + rs.getString(2));
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter os nomes, erro: " + e.getMessage());
		} finally {
			con.finalizarConexao();
		}

		// Retorno
		return lista;

	}

}
