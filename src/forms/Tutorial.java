package forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ultilitario;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class Tutorial extends JFrame {

	private JPanel contentPane;

	ultilitario u = new ultilitario();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tutorial frame = new Tutorial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tutorial() {
		u.InserirIcone(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 883, 396);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel tutorialF = new JLabel("New label");
		tutorialF.setIcon(new ImageIcon(Tutorial.class.getResource("/Imagens/fundoBV.png")));
		tutorialF.setBounds(0, 0, 869, 359);
		contentPane.add(tutorialF);
	}

}
