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
		setBounds(100, 100, 1085, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel tutorialF = new JLabel("New label");
		tutorialF.setIcon(new ImageIcon(Tutorial.class.getResource("/Imagens/MdInfoTutor.jpg")));
		tutorialF.setBounds(10, 10, 1058, 449);
		contentPane.add(tutorialF);
	}

}
