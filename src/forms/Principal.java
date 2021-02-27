package forms;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;

import beans.EspacoCafeBeans;
import beans.EventoBeans;
import beans.PessoaBeans;
import connection.Conexao;
import controller.EspacoCafeController;
import controller.EventoController;
import controller.PessoaController;
import controller.ultilitario;
import dao.EspacoCafeDao;
import dao.EventoDao;
import dao.PessoaDao;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JInternalFrame;

@SuppressWarnings("serial")

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField txtNomePessoa;
	private JTextField txtSobrenomePessoa;
	private JTextField txtNomeEvento;
	private JTextField txtLotacao;
	private JTextField txtNomeEspacoCafe;
	private JTextField txtEtapa1;
	private JTextField txtEtapa2;
	private JTextField txtCafe1;

	Conexao conn = new Conexao();
	PessoaDao pd = new PessoaDao();

	ultilitario u = new ultilitario();

	@SuppressWarnings("rawtypes")
	JComboBox cbxEvento = new JComboBox();
	EventoDao ed = new EventoDao();
	ArrayList<String> nomesEventos = ed.listarEventos();

	@SuppressWarnings("rawtypes")
	JComboBox cbxEspacoCafe = new JComboBox();
	EspacoCafeDao ecd = new EspacoCafeDao();
	ArrayList<String> nomesEspacoCafe = ecd.listarEspacos();

	@SuppressWarnings("rawtypes")
	JComboBox comboBox = new JComboBox();
	ArrayList<String> nomes;
	private JTextField txtCafe2;

	int vagas = ed.qtdPessoasEventosLivre();
	public JTable tableDados;
	private JTable tableEvento;
	private JTable tableCafe;

	@SuppressWarnings("unchecked")
	public Principal() {
		u.InserirIcone(this);

		setForeground(Color.WHITE);
		setTitle("MDInfo");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 897, 488);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 102, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		for (String nome : nomesEventos) {
			cbxEvento.addItem(nome);
		}
		for (String nome : nomesEspacoCafe) {
			cbxEspacoCafe.addItem(nome);
		}

		PessoaDao pd = new PessoaDao();
		nomes = pd.listarNomes();
		for (String nome : nomes) {
			comboBox.addItem(nome);
		}

		JInternalFrame TelaBemVindo = new JInternalFrame("Bem Vindo");
		TelaBemVindo.setIconifiable(true);
		TelaBemVindo.setBounds(10, 30, 863, 400);
		contentPane.add(TelaBemVindo);
		TelaBemVindo.getContentPane().setLayout(null);

		JButton btFechar = new JButton("Fechar");
		btFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaBemVindo.dispose();
			}
		});
		btFechar.setBounds(768, 0, 73, 16);
		TelaBemVindo.getContentPane().add(btFechar);
		btFechar.setToolTipText("Fechar Bem Vindo");
		btFechar.setForeground(Color.WHITE);
		btFechar.setBackground(Color.RED);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundoBV.png")));
		lblNewLabel.setBounds(0, 10, 851, 361);
		TelaBemVindo.getContentPane().add(lblNewLabel);
		TelaBemVindo.setVisible(true);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 864, 419);
		contentPane.add(tabbedPane);

		JPanel panel1 = new JPanel();
		panel1.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Cadastrar pessoas", null, panel1, null);
		tabbedPane.setBackgroundAt(0, Color.LIGHT_GRAY);
		panel1.setLayout(null);

		txtSobrenomePessoa = new JTextField();
		txtSobrenomePessoa.setToolTipText("Digite o Sobrenome");
		txtSobrenomePessoa.setBounds(229, 170, 488, 28);
		txtSobrenomePessoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSobrenomePessoa.setColumns(10);
		panel1.add(txtSobrenomePessoa);

		txtNomePessoa = new JTextField();
		txtNomePessoa.setToolTipText("Digite o Nome");
		txtNomePessoa.setBounds(229, 131, 488, 28);
		txtNomePessoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel1.add(txtNomePessoa);
		txtNomePessoa.setColumns(10);

		JLabel lblNomePessoa = new JLabel("Nome:");
		lblNomePessoa.setForeground(Color.BLACK);
		lblNomePessoa.setBounds(119, 136, 73, 14);
		lblNomePessoa.setFont(new Font("Arial Black", Font.PLAIN, 16));
		panel1.add(lblNomePessoa);

		JLabel lblSobrenomePessoa = new JLabel("Sobrenome:");
		lblSobrenomePessoa.setForeground(Color.BLACK);
		lblSobrenomePessoa.setBounds(119, 172, 100, 29);
		lblSobrenomePessoa.setFont(new Font("Arial Black", Font.PLAIN, 15));
		panel1.add(lblSobrenomePessoa);

		JButton btnCadastrarPessoa = new JButton("Cadastrar");
		btnCadastrarPessoa.setBounds(391, 209, 121, 28);
		btnCadastrarPessoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validar se há dois eventos cadastrados
				PessoaDao pd = new PessoaDao();
				if (txtNomePessoa.getText().equals("") && txtSobrenomePessoa.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Cadastre todos os campos");
				} else {

					// Obter dados
					String nomePessoa = txtNomePessoa.getText();
					String sobrenomePessoa = txtSobrenomePessoa.getText();

					// Criar objeto do tipo Pessoa
					PessoaBeans pb = new PessoaBeans();
					pb.setNomePessoa(txtNomePessoa.getText());
					pb.setSobrenomePessoa(txtSobrenomePessoa.getText());

					// Efetuar cadastro
					PessoaController pc = new PessoaController();
					pc.cadastrar(pb);

					// Adicionar o nome no ArrayList
					nomes.add(nomePessoa + " " + sobrenomePessoa);

					// Limpar campos
					txtNomePessoa.setText("");
					txtSobrenomePessoa.setText("");
				}

			}
		});
		btnCadastrarPessoa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel1.add(btnCadastrarPessoa);

		JLabel lblCadastrarPessoa = new JLabel("Cadastrar Pessoa");
		lblCadastrarPessoa.setForeground(Color.BLACK);
		lblCadastrarPessoa.setBounds(340, 73, 365, 48);
		lblCadastrarPessoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarPessoa.setFont(new Font("Arial Black", Font.PLAIN, 24));
		panel1.add(lblCadastrarPessoa);

		JLabel Logofundo = new JLabel("");
		Logofundo.setIcon(new ImageIcon(
				"C:\\Users\\Marcos David\\Documents\\NetBeansProjects\\MdInfo\\src\\imagens\\text (2).png"));
		Logofundo.setBounds(-29, -49, 469, 204);
		panel1.add(Logofundo);

		JLabel fundoLogo3 = new JLabel("");
		fundoLogo3.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundo2.png")));
		fundoLogo3.setBounds(-280, 52, 1014, 303);
		panel1.add(fundoLogo3);

		JLabel fundoLogo2 = new JLabel("");
		fundoLogo2.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundoCadastro (1).jpg")));
		fundoLogo2.setBounds(0, 0, 859, 392);
		panel1.add(fundoLogo2);

		JPanel panel3 = new JPanel();
		tabbedPane.addTab("Cadastrar espa\u00E7o de caf\u00E9", null, panel3, null);
		panel3.setLayout(null);

		JLabel lblCadastrarEspacoCafe = new JLabel("Cadastrar Espa\u00E7o do Caf\u00E9");
		lblCadastrarEspacoCafe.setForeground(Color.BLACK);
		lblCadastrarEspacoCafe.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarEspacoCafe.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblCadastrarEspacoCafe.setBounds(413, 70, 365, 48);
		panel3.add(lblCadastrarEspacoCafe);

		JLabel fundo3logo3 = new JLabel("New label");
		fundo3logo3.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/text (2).png")));
		fundo3logo3.setBounds(10, 43, 471, 75);
		panel3.add(fundo3logo3);

		JLabel fundo3logo2 = new JLabel("");
		fundo3logo2.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundo2.png")));
		fundo3logo2.setBounds(76, 28, 700, 323);
		panel3.add(fundo3logo2);

		JLabel lblNomeCafe = new JLabel("Nome:");
		lblNomeCafe.setForeground(Color.BLACK);
		lblNomeCafe.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNomeCafe.setBounds(135, 134, 73, 14);
		panel3.add(lblNomeCafe);

		txtNomeEspacoCafe = new JTextField();
		txtNomeEspacoCafe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeEspacoCafe.setColumns(10);
		txtNomeEspacoCafe.setBounds(218, 128, 539, 28);
		panel3.add(txtNomeEspacoCafe);

		JButton btnCadastrarPessoa_1_1 = new JButton("Cadastrar");
		btnCadastrarPessoa_1_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarPessoa_1_1.setBounds(377, 177, 121, 28);
		panel3.add(btnCadastrarPessoa_1_1);

		JLabel fundo2logo1 = new JLabel("New label");
		fundo2logo1.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundoCadastro (1).jpg")));
		fundo2logo1.setBounds(0, -18, 859, 420);
		panel3.add(fundo2logo1);
		btnCadastrarPessoa_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validar se há dois espaços para café
				EspacoCafeDao ed = new EspacoCafeDao();
				if (ed.qtdEspacoCafe() < 2) {

					// Obter dados
					String nomeEspacoCafe = txtNomeEspacoCafe.getText();
					EspacoCafeBeans eb = new EspacoCafeBeans();
					eb.setNomeEspacoCafe(nomeEspacoCafe);

					// Efetuar cadastro
					EspacoCafeController ec = new EspacoCafeController();
					ec.cadastrar(eb);

					// Limpar campos
					txtNomeEspacoCafe.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "Os dois espaços para cafés foram cadastrados.");
				}

			}
		});

		JPanel panel2 = new JPanel();
		tabbedPane.addTab("Cadastrar evento", null, panel2, null);
		panel2.setLayout(null);

		JButton btnCadastrarPessoa_1 = new JButton("Cadastrar");
		btnCadastrarPessoa_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// Validar se há dois espaços para café
				EspacoCafeDao ed = new EspacoCafeDao();
				EventoDao evd = new EventoDao();
				if (txtNomeEvento.getText().equals("") || txtLotacao.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Cadastre os dois espaços para café obrigatórios");
				} else if (evd.qtdEventos() >= 2) {
					JOptionPane.showMessageDialog(null, "Os dois eventos já foram cadastrados");
				} else {

					// Obter dados
					String nomeEvento = txtNomeEvento.getText();
					int lotacaoEvento = Integer.parseInt(txtLotacao.getText());

					// Criar objeto do tipo Evento
					EventoBeans eb = new EventoBeans();
					eb.setNomeEvento(nomeEvento);
					eb.setLotacaoEvento(lotacaoEvento);

					// Efetuar cadastro
					EventoController ec = new EventoController();
					ec.cadastrar(eb);

					// ComboBox - Consulta
					nomesEventos.add(nomeEvento);
					cbxEvento.removeAllItems();
					cbxEvento.addItem("Selecione o evento");
					for (String nome : nomesEventos) {
						cbxEvento.addItem(nome);
					}

					// Limpar campos
					txtNomeEvento.setText("");
					txtLotacao.setText("");

				}

			}
		});
		btnCadastrarPessoa_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnCadastrarPessoa_1.setBounds(366, 190, 121, 28);
		panel2.add(btnCadastrarPessoa_1);

		JLabel lblCadastrarEvento = new JLabel("Cadastrar Evento");
		lblCadastrarEvento.setHorizontalAlignment(SwingConstants.CENTER);
		lblCadastrarEvento.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblCadastrarEvento.setBounds(397, 54, 365, 48);
		panel2.add(lblCadastrarEvento);

		txtLotacao = new JTextField();
		txtLotacao.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtLotacao.setColumns(10);
		txtLotacao.setBounds(216, 151, 407, 28);
		panel2.add(txtLotacao);

		txtNomeEvento = new JTextField();
		txtNomeEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeEvento.setColumns(10);
		txtNomeEvento.setBounds(216, 112, 407, 28);
		panel2.add(txtNomeEvento);

		JLabel fundoLogo3_2 = new JLabel("");
		fundoLogo3_2.setForeground(Color.BLACK);
		fundoLogo3_2.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundo2.png")));
		fundoLogo3_2.setBounds(-34, 32, 746, 324);
		panel2.add(fundoLogo3_2);

		JLabel fundoLogo3_1 = new JLabel("");
		fundoLogo3_1.setBounds(0, 0, 820, 356);
		panel2.add(fundoLogo3_1);

		JLabel Logo2 = new JLabel("");
		Logo2.setIcon(new ImageIcon(
				"C:\\Users\\Marcos David\\Documents\\NetBeansProjects\\MdInfo\\src\\imagens\\text (2).png"));
		Logo2.setBounds(0, 10, 521, 86);
		panel2.add(Logo2);

		JLabel lblNomeEvento = new JLabel("Nome:");
		lblNomeEvento.setForeground(Color.BLACK);
		lblNomeEvento.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNomeEvento.setBounds(102, 118, 104, 14);
		panel2.add(lblNomeEvento);

		JLabel lblLotao = new JLabel("Lota\u00E7\u00E3o:");
		lblLotao.setForeground(Color.BLACK);
		lblLotao.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblLotao.setBounds(102, 157, 104, 22);
		panel2.add(lblLotao);

		JLabel fundoLogo = new JLabel("");
		fundoLogo.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundoCadastro (1).jpg")));
		fundoLogo.setBounds(0, -14, 859, 406);
		panel2.add(fundoLogo);

		JPanel panel4 = new JPanel();
		tabbedPane.addTab("Consultar pessoa", null, panel4, null);
		panel4.setLayout(null);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					String[] nomeCombo = comboBox.getSelectedItem().toString().split(" ");

					String nome = nomeCombo[0];
					String sobrenome = "";

					for (int i = 1; i < nomeCombo.length; i++) {
						sobrenome += nomeCombo[i];
					}

					EventoDao ed = new EventoDao();
					int[] codigosEvento = ed.codigoEvento(nome, sobrenome);
					String[] nomesEventoCafe = ed.nomesEventoCafe(codigosEvento[0], codigosEvento[1]);

					txtEtapa1.setText(nomesEventoCafe[0]);
					txtEtapa2.setText(nomesEventoCafe[2]);
					txtCafe1.setText(nomesEventoCafe[1]);
					txtCafe2.setText(nomesEventoCafe[3]);
				} catch (Exception erro) {
					// TODO: handle exception
				}
			}
		});

		JLabel fundlogo = new JLabel("");
		fundlogo.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/text (2).png")));
		fundlogo.setBounds(29, 24, 448, 73);
		panel4.add(fundlogo);

		comboBox.addItem("Selecione um nome");

		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox.setBounds(223, 129, 539, 28);
		panel4.add(comboBox);

		JLabel lblConsultarPessoa = new JLabel("Consultar Pessoa");
		lblConsultarPessoa.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultarPessoa.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblConsultarPessoa.setBounds(429, 71, 365, 48);
		panel4.add(lblConsultarPessoa);

		txtCafe2 = new JTextField();
		txtCafe2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCafe2.setEnabled(false);
		txtCafe2.setColumns(10);
		txtCafe2.setBounds(223, 341, 539, 28);
		panel4.add(txtCafe2);

		txtCafe1 = new JTextField();
		txtCafe1.setEnabled(false);
		txtCafe1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCafe1.setColumns(10);
		txtCafe1.setBounds(223, 298, 539, 28);
		panel4.add(txtCafe1);

		txtEtapa1 = new JTextField();
		txtEtapa1.setEnabled(false);
		txtEtapa1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEtapa1.setBounds(223, 216, 539, 28);
		panel4.add(txtEtapa1);
		txtEtapa1.setColumns(10);

		txtEtapa2 = new JTextField();
		txtEtapa2.setEnabled(false);
		txtEtapa2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtEtapa2.setColumns(10);
		txtEtapa2.setBounds(223, 254, 539, 28);
		panel4.add(txtEtapa2);

		JLabel fundo4logo2 = new JLabel("New label");
		fundo4logo2.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundo2.png")));
		fundo4logo2.setBounds(85, 39, 720, 353);
		panel4.add(fundo4logo2);

		JLabel lblNomePessoa_1 = new JLabel("Nome:");
		lblNomePessoa_1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblNomePessoa_1.setBounds(73, 135, 153, 14);
		panel4.add(lblNomePessoa_1);

		JLabel lblEtapa1 = new JLabel("Etapa 1:");
		lblEtapa1.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEtapa1.setBounds(73, 217, 153, 14);
		panel4.add(lblEtapa1);

		JLabel lblEtapa2 = new JLabel("Etapa 2:");
		lblEtapa2.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEtapa2.setBounds(73, 260, 153, 14);
		panel4.add(lblEtapa2);

		JLabel lblEspacoCafe = new JLabel("Sala do caf\u00E9 1:");
		lblEspacoCafe.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEspacoCafe.setBounds(73, 304, 153, 14);
		panel4.add(lblEspacoCafe);

		JLabel lblSalaDoCaf = new JLabel("Sala do caf\u00E9 2:");
		lblSalaDoCaf.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblSalaDoCaf.setBounds(73, 347, 153, 14);
		panel4.add(lblSalaDoCaf);

		JLabel fundo4logo1 = new JLabel("New label");
		fundo4logo1.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundoCadastro (1).jpg")));
		fundo4logo1.setBounds(0, 0, 859, 392);
		panel4.add(fundo4logo1);

		JPanel panel5 = new JPanel();
		tabbedPane.addTab("Consultar eventos e espa\u00E7os de caf\u00E9", null, panel5, null);
		panel5.setLayout(null);
		cbxEvento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbxEvento.getSelectedIndex() != 0) {
					System.out.println(cbxEvento.getSelectedIndex());

					// Título
					String texto = "Evento: " + cbxEvento.getSelectedItem();

					// Texto
					texto += "\n1ª Etapa";

					// Obter todos os alunos da primeira turma
					ArrayList<String> nomes1 = ed.primeiraEtapaEvento1(cbxEvento.getSelectedIndex());

					for (String n : nomes1) {
						texto += "\n" + n;
					}

					// Texto
					texto += "\n\n2ª Etapa";

					// Obter todos os alunos da segunda turma
					ArrayList<String> nomes2 = ed.segundaEtapaEvento1(cbxEvento.getSelectedIndex());

					for (String n : nomes2) {
						texto += "\n" + n;
					}

					JOptionPane.showMessageDialog(null, texto);

				}
			}
		});
		cbxEspacoCafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (cbxEspacoCafe.getSelectedIndex() != 0) {
					System.out.println(cbxEspacoCafe.getSelectedIndex());

					// Título
					String texto = "Espaço: " + cbxEspacoCafe.getSelectedItem();

					// Texto
					texto += "\n1ª Intervalo";

					// Obter todos os alunos do primeiro espaço
					ArrayList<String> nomes1 = ecd.primeiraEtapaEspaco1(cbxEspacoCafe.getSelectedIndex());

					for (String n : nomes1) {
						texto += "\n" + n;
					}

					// Texto
					texto += "\n\n2ª Intervalo";

					// Obter todos os alunos do segundo espaço
					ArrayList<String> nomes2 = ecd.primeiraEtapaEspaco2(cbxEspacoCafe.getSelectedIndex());

					for (String n : nomes2) {
						texto += "\n" + n;
					}

					JOptionPane.showMessageDialog(null, texto);

				}

			}
		});

		JLabel fundo5logo = new JLabel("");
		fundo5logo.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/text (2).png")));
		fundo5logo.setBounds(-14, 10, 448, 73);
		panel5.add(fundo5logo);

		cbxEspacoCafe.addItem("Selecione o Espaço");

		cbxEspacoCafe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxEspacoCafe.setBounds(210, 181, 539, 28);
		panel5.add(cbxEspacoCafe);

		cbxEvento.addItem("Selecione o evento");

		cbxEvento.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cbxEvento.setBounds(210, 138, 539, 28);
		panel5.add(cbxEvento);

		JLabel lblConsultaEventosCafe = new JLabel("Consultar Eventos e Espa\u00E7os");
		lblConsultaEventosCafe.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaEventosCafe.setFont(new Font("Arial Black", Font.PLAIN, 24));
		lblConsultaEventosCafe.setBounds(327, 80, 461, 48);
		panel5.add(lblConsultaEventosCafe);

		JLabel fundo5logo2 = new JLabel("");
		fundo5logo2.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundo2.png")));
		fundo5logo2.setBounds(40, 34, 730, 348);
		panel5.add(fundo5logo2);

		JLabel lblEvento = new JLabel("Evento:");
		lblEvento.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblEvento.setBounds(56, 144, 144, 14);
		panel5.add(lblEvento);

		JLabel lblConsultaEspacoCafe = new JLabel("Espa\u00E7o caf\u00E9:");
		lblConsultaEspacoCafe.setFont(new Font("Arial Black", Font.PLAIN, 16));
		lblConsultaEspacoCafe.setBounds(56, 187, 144, 22);
		panel5.add(lblConsultaEspacoCafe);

		JLabel fundo5logo1 = new JLabel("");
		fundo5logo1.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundoCadastro (1).jpg")));
		fundo5logo1.setHorizontalAlignment(SwingConstants.TRAILING);
		fundo5logo1.setBounds(0, 0, 859, 392);
		panel5.add(fundo5logo1);

		JPanel panel6 = new JPanel();
		panel6.setLayout(null);
		panel6.setBackground(Color.LIGHT_GRAY);
		tabbedPane.addTab("Listas ", null, panel6, null);

		tableDados = new JTable();
		tableDados.setModel(
				new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "idPessoa", "nomePessoa" }) {
					boolean[] columnEditables = new boolean[] { false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
		tableDados.getColumnModel().getColumn(0).setResizable(false);
		tableDados.getColumnModel().getColumn(0).setPreferredWidth(52);
		tableDados.getColumnModel().getColumn(1).setResizable(false);
		tableDados.getColumnModel().getColumn(1).setPreferredWidth(196);
		tableDados.setBounds(35, 46, 237, 336);
		panel6.add(tableDados);

		JButton DadosNomes = new JButton("Atualiar Nomes");
		DadosNomes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Conexao conn = new Conexao();
				Principal p = new Principal();

				try {
					Connection con = conn.realizarConexao();

					String sql = "SELECT * FROM projeto.pessoas;";
					java.sql.PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();

					DefaultTableModel model = (DefaultTableModel) tableDados.getModel();
					model.setNumRows(0);

					while (rs.next()) {
						model.addRow(new Object[] { rs.getString("idPessoa"), rs.getString("nomePessoa") });

					}
					rs.close();
					conn.finalizarConexao();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		DadosNomes.setBounds(35, 10, 122, 21);
		panel6.add(DadosNomes);

		JButton deletar = new JButton("Excluir");
		deletar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDados.getSelectedRow() != -1) {

					PessoaBeans pb = new PessoaBeans();

					// Conexão com banco de dados
					Principal p = new Principal();
					Conexao con = new Conexao();
					con.realizarConexao();
					PreparedStatement stmt;

					int confirma = JOptionPane.showInternalConfirmDialog(null,
							"Tem certeza que deseja" + " excluir esta pessoa?", " Atenção ", JOptionPane.YES_NO_OPTION);
					if (confirma == JOptionPane.YES_OPTION) {

					}
					// Tentativa

					try {
						stmt = (PreparedStatement) con.conexao
								.prepareStatement("DELETE FROM pessoas WHERE idPessoa = ?");

						stmt.setInt(1, pb.getIdPessoa() + 1);

						stmt.execute();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao Excluir " + ex.getMessage());
					} finally {
						con.finalizarConexao();
					}

				}
			}
		});
		deletar.setBounds(187, 10, 85, 21);
		panel6.add(deletar);

		tableEvento = new JTable();
		tableEvento.setModel(
				new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "idEvento", "nomeEvento" }));
		tableEvento.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableEvento.getColumnModel().getColumn(1).setPreferredWidth(147);
		tableEvento.setBounds(308, 46, 237, 336);
		panel6.add(tableEvento);

		JButton btnNewButton = new JButton("Atualizar Eventos");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Conexao conn = new Conexao();
				Principal p = new Principal();

				try {
					Connection con = conn.realizarConexao();

					String sql = "SELECT * FROM projeto.eventos;";
					java.sql.PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();

					DefaultTableModel modelEv = (DefaultTableModel) tableEvento.getModel();
					modelEv.setNumRows(0);

					while (rs.next()) {
						modelEv.addRow(new Object[] { rs.getString("idEvento"), rs.getString("nomeEvento") });

					}
					rs.close();
					conn.finalizarConexao();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(307, 10, 143, 21);
		panel6.add(btnNewButton);

		JButton brExcluir = new JButton("Excluir");
		brExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tableDados.getSelectedRow() != -1) {

					// Conexão com banco de dados
					EventoBeans eb = new EventoBeans();
					Principal p = new Principal();
					Conexao con = new Conexao();
					con.realizarConexao();
					PreparedStatement stmt;

					int confirma = JOptionPane.showInternalConfirmDialog(null,
							"Tem certeza que deseja" + " excluir este Evento ?", " Atenção ",
							JOptionPane.YES_NO_OPTION);
					if (confirma == JOptionPane.YES_OPTION) {

					}
					// Tentativa

					try {
						stmt = (PreparedStatement) con.conexao
								.prepareStatement("DELETE FROM pessoas WHERE idEvento = ?");

						stmt.setInt(1, eb.getIdEvento() + 1);

						stmt.execute();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao Excluir " + ex.getMessage());
					} finally {
						con.finalizarConexao();
					}

				}
			}
		});
		brExcluir.setBounds(460, 10, 85, 21);
		panel6.add(brExcluir);

		tableCafe = new JTable();
		tableCafe.setModel(new DefaultTableModel(new Object[][] { { null, null }, },
				new String[] { "idEspacoCafe", "nomeEspacoCafe" }));
		tableCafe.getColumnModel().getColumn(1).setPreferredWidth(172);
		tableCafe.setBounds(581, 46, 237, 336);
		panel6.add(tableCafe);

		JButton btCafe = new JButton("Atualizar Caf\u00E9");
		btCafe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Conexao conn = new Conexao();
				Principal p = new Principal();

				try {
					Connection con = conn.realizarConexao();

					String sql = "SELECT * FROM projeto.espacocafe;";
					java.sql.PreparedStatement stmt = con.prepareStatement(sql);
					ResultSet rs = stmt.executeQuery();

					DefaultTableModel modelCafe = (DefaultTableModel) tableCafe.getModel();
					modelCafe.setNumRows(0);

					while (rs.next()) {
						modelCafe.addRow(new Object[] { rs.getString("idEspacoCafe"), rs.getString("nomeEspacoCafe") });

					}
					rs.close();
					conn.finalizarConexao();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		});
		btCafe.setBounds(574, 10, 129, 21);
		panel6.add(btCafe);

		JButton brExcluir2 = new JButton("Excluir");
		brExcluir2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tableDados.getSelectedRow() != -1) {

					// Conexão com banco de dados
					EspacoCafeBeans ecb = new EspacoCafeBeans();
					Principal p = new Principal();
					Conexao con = new Conexao();
					con.realizarConexao();
					PreparedStatement stmt;

					int confirma = JOptionPane.showInternalConfirmDialog(null,
							"Tem certeza que deseja" + " excluir este espaço de Café ?", " Atenção ",
							JOptionPane.YES_NO_OPTION);
					if (confirma == JOptionPane.YES_OPTION) {

					}
					// Tentativa

					try {
						stmt = (PreparedStatement) con.conexao
								.prepareStatement("DELETE FROM pessoas WHERE idEspacoCafe= ?");

						stmt.setInt(1, ecb.getIdEspacoCafe() + 1);

						stmt.execute();

					} catch (Exception ex) {
						JOptionPane.showMessageDialog(null, "Erro ao Excluir " + ex.getMessage());
					} finally {
						con.finalizarConexao();
					}

				}
			}
		});
		brExcluir2.setBounds(733, 10, 85, 21);
		panel6.add(brExcluir2);

		JLabel fundo = new JLabel("");
		fundo.setIcon(new ImageIcon(Principal.class.getResource("/Imagens/fundoCadastro (1).jpg")));
		fundo.setBounds(0, 0, 859, 392);
		panel6.add(fundo);

		JButton brAjuda = new JButton("Ajuda");
		brAjuda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Tutorial t = new Tutorial();
				t.setVisible(true);

			}
		});
		brAjuda.setBackground(Color.WHITE);
		brAjuda.setForeground(Color.RED);
		brAjuda.setBounds(798, 430, 75, 11);
		contentPane.add(brAjuda);
	}
}
