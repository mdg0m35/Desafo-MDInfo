package controller;

import java.awt.Toolkit;

import javax.swing.JFrame;

public class ultilitario {

	public void InserirIcone(JFrame frm) {

		try {

			frm.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/iconeIncial.png"));

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
