package controller;

import javax.swing.JOptionPane;

import beans.EventoBeans;
import dao.EventoDao;

/**
 * Camada para controles da classe Evento
 */
public class EventoController {

	// Cadastrar
	public void cadastrar(EventoBeans obj) {
		
		// Efetuar o cadastro
		EventoDao ed = new EventoDao();
		String mensagem = ed.cadastrarEvento(obj);
		
		// Exibir mensagem
		JOptionPane.showMessageDialog(null, mensagem);
	}
	
}
