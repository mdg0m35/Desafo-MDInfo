package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.EventoBeans;
import connection.Conexao;

/**
 * Camada de banco de dados para trabalhar com os eventos
 */
public class EventoDao {
	
	// Somar a lotação dos eventos
	public int qtdPessoasEventosLivre() {
		
		// Contador
		int contador = 0, soma = 0;
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Tentativa
		try {
			String sql1 = "SELECT SUM(lotacaoEvento) FROM eventos";
			Statement stmt1 = con.conexao.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sql1);
			while(rs1.next()) {
				soma = rs1.getInt(1);
			}
			
			String sql2 = "SELECT COUNT(idPessoa) FROM pessoas";
			Statement stmt2 = con.conexao.createStatement();
			ResultSet rs2 = stmt2.executeQuery(sql2);
			while(rs2.next()) {
				contador = rs2.getInt(1);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter contabilizar os participantes para o evento, erro: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return soma - contador;
		
	}
	
	// Obter todos os eventos cadastrados
	public ArrayList<String> listarEventos() {
		
		// Variável
		ArrayList<String> lista = new ArrayList<String>();
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Tentativa
		try {
			String sql = "SELECT nomeEvento FROM eventos";
			
			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				lista.add(rs.getString(1));
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter a listagem de eventos, erro: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return lista;
	}
	
	// Obter a quantidade de eventos cadastrados
	public int qtdEventos() {
		
		// Variável
		int qtd = 0;
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Tentativa
		try {
			String sql = "SELECT COUNT(*) FROM eventos";
			
			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				qtd = rs.getInt(1);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter a quantidade de eventos, erro: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return qtd;
	}
	
	// Obter o local onde será o café
	private String espacoCafe() {
		
		// Variável
		String espaco = null;
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Obter a quantidade de eventos cadastrados
		int qtd = qtdEventos();
		
		// Tentativa
		try {
			
			String sql;
			
			if(qtd == 0) {
				sql = "SELECT nomeEspacoCafe FROM espacocafe WHERE idEspacoCafe = 1";
			}else {				
				sql = "SELECT nomeEspacoCafe FROM espacocafe WHERE idEspacoCafe = 2";
			}
			
			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				espaco = rs.getString(1);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter o nome do espaço café, erro: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return espaco;
		
	}
	
	// Retornar a lotação do primeiro evento
	private int lotacaoPrimeiroEvento() {
		
		// Variável
		int lotacao = 0;
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Tentativa
		try {
			
			String sql = "SELECT lotacaoEvento FROM eventos WHERE idEvento = 1";
			
			
			Statement stmt = con.conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				lotacao = rs.getInt(1);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter a lotação do evento, erro: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return lotacao;
		
	}

	// Cadastrar
	public String cadastrarEvento(EventoBeans obj) {
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Mensagem de retorno
		String retorno = null;
		
		int qtdLotacao = 0;
		
		// Validar a lotação do segundo evento
		if(qtdEventos() == 1) {
			int lotacaoPrimeiroEvento = lotacaoPrimeiroEvento();
			
			if(lotacaoPrimeiroEvento > obj.getLotacaoEvento()) {
				qtdLotacao = lotacaoPrimeiroEvento - obj.getLotacaoEvento(); 
			}else {
				qtdLotacao = obj.getLotacaoEvento() - lotacaoPrimeiroEvento; 
			}
			
		}
		
		// Tentativa
		try {
			System.out.println(qtdLotacao);
			
			if(lotacaoPrimeiroEvento() != 0 && (qtdLotacao > 1)) {
				retorno = "A diferença de lotação entre os eventos deve ser até uma pessoa";
			}else {
				String sql = "INSERT INTO eventos (nomeEvento, lotacaoEvento, espacoCafe) VALUES (?, ?, ?)";
				
				PreparedStatement pstmt = con.conexao.prepareStatement(sql);
				pstmt.setString(1, obj.getNomeEvento());
				pstmt.setInt(2, obj.getLotacaoEvento());
				pstmt.setString(3, espacoCafe());
				pstmt.execute();
				
				retorno = "Evento cadastrado com sucesso!";
			}
		}catch(Exception e) {
			retorno = "Falha ao cadastrar o evento, erro: "+e.getMessage();
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return retorno;
		
	}
	
	// Obter o id das etapas através do nome
	public int[] codigoEvento(String nome, String sobrenome) {
		
		// Vetor
		int[] idsEventos = new int[2];
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Tentativa
		try {
			
			String sql = "SELECT etapa1, etapa2 FROM pessoas WHERE nomePessoa = ? AND sobrenomePessoa = ?";
			
			
			PreparedStatement pstmt = con.conexao.prepareStatement(sql);
			pstmt.setString(1, nome);
			pstmt.setString(2, sobrenome);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				idsEventos[0] = rs.getInt(1);
				idsEventos[1] = rs.getInt(2);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter os códigos dos eventos erro: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return idsEventos;
		
	}
	
	// Obter o nome dos eventos e local do café através dos ids
	public String[] nomesEventoCafe(int etapa1, int etapa2) {
		
		// Vetor
		String[] dados = new String[4];
		
		// Conexão com banco de dados
		Conexao con = new Conexao();
		con.realizarConexao();
		
		// Tentativa
		try {
			
			// 1ª Etapa
			String sql1 = "SELECT nomeEvento, espacoCafe FROM eventos WHERE idEvento = ?";
			PreparedStatement pstmt1 = con.conexao.prepareStatement(sql1);
			pstmt1.setInt(1, etapa1);
			ResultSet rs1 = pstmt1.executeQuery();		
			while(rs1.next()) {
				dados[0] = rs1.getString(1);
				dados[1] = rs1.getString(2);
			}

			// 2ª Etapa
			String sql2 = "SELECT nomeEvento, espacoCafe FROM eventos WHERE idEvento = ?";
			PreparedStatement pstmt2 = con.conexao.prepareStatement(sql2);
			pstmt2.setInt(1, etapa2);
			ResultSet rs2 = pstmt2.executeQuery();		
			while(rs2.next()) {
				dados[2] = rs2.getString(1);
				dados[3] = rs2.getString(2);
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter os códigos dos eventos erro: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return dados;
		
	}
	
	// Listar todas as pessoas que participarão da primeira etapa do primeiro evento
	public ArrayList<String> primeiraEtapaEvento1(int codigoEtapa){
		
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
			
			while(rs.next()) {
				nomes.add(rs.getString(1));
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter os nomes da primeira etapa: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return nomes;
		
	}
	
	
	// Listar todas as pessoas que participarão da segunda etapa do primeiro evento
	public ArrayList<String> segundaEtapaEvento1(int codigoEtapa){
		
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
			
			while(rs.next()) {
				nomes.add(rs.getString(1));
			}
			
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "Falha ao obter os nomes da segunda etapa: "+e.getMessage());
		}finally {
			con.finalizarConexao();
		}
		
		// Retorno
		return nomes;
		
	}

}
