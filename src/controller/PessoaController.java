package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import beans.PessoaBeans;
import dao.PessoaDao;

/**
 * Camada para controles da classe Pessoa
 */
public class PessoaController {

	// Cadastrar
	public void cadastrar(PessoaBeans obj) {

		// Efetuar o cadastro
		PessoaDao pd = new PessoaDao();
		String mensagem = pd.cadastrarPessoa(obj);

		// Exibir mensagem
		JOptionPane.showMessageDialog(null, mensagem);
	}

	// Listar dados
	public ArrayList<PessoaBeans> listar() {

		// ArrayList
		ArrayList<PessoaBeans> dados = new ArrayList<PessoaBeans>();

		// Retorno
		return dados;

	}

}
