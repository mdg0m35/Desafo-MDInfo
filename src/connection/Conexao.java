package connection;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JOptionPane;

public class Conexao {

	private static String URL = "jdbc:mysql://localhost/projeto";
	private static String SENHA = "";
	private static String USUARIO = "root";
	// Atributo
	public Connection conexao;

	// Método para realizar a conexão
	public Connection realizarConexao() {
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://localhost/projeto", "root", "");
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao conectar: " + e.getMessage());
		}
		return conexao;
	}

	// Método para finalizar a conexão
	public void finalizarConexao() {
		try {
			conexao.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Erro ao finalizar a conexão: " + e.getMessage());
		}
	}

}
