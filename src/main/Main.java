package main;

import java.awt.EventQueue;


import connection.Conexao;
import forms.Principal;
/**
* <h1>Sistema para gerenciar eventos</h1>
* <p>Aplica��o Java SE para gerenciar eventos. Nela � poss�vel criar locais para coffee-break, eventos, capacidade m�xima e cadastramento de participantes.</p>
* @author  Mdgomes
* @version 1.0
* @since   2021-02-21
*/
public class Main {

	 /**
	   * Esse � o m�todo principal, nele o formul�rio � exibido.
	   * @param sem par�metros.
	   * @return sem retorno.
	   * @exception Exception em caso de erro.
	   * @see Exception
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
					
					Conexao c = new Conexao();
					c.realizarConexao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
