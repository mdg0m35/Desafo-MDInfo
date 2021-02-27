package controller;

import javax.swing.JOptionPane;

import beans.EspacoCafeBeans;
import dao.EspacoCafeDao;

/**
 * Camada para controles da classe Espaço Café
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
