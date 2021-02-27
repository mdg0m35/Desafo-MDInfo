package controller;

import javax.swing.JOptionPane;

import beans.EspacoCafeBeans;
import dao.EspacoCafeDao;

/**
 * Camada para controles da classe Espa�o Caf�
 */
public class EspacoCafeController {

	// Cadastrar
	public void cadastrar(EspacoCafeBeans obj) {

		// Efetuar o cadastro
		EspacoCafeDao ed = new EspacoCafeDao();
		String mensagem = ed.cadastrarEspaco(obj);

		// Exibir mensagem
		JOptionPane.showMessageDialog(null, mensagem);
	}

}
