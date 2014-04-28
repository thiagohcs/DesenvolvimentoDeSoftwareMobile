package br.com.aeso.apresentasao;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

public class Cliente extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	Socket socket;
	PrintWriter escritor;
	JTextPane textoEnviar;
	JTextPane textoVisto;
	String nome;

	// Método que pega o socket e enviar mensagem de texto por ele através da
	// variavel "escrito"
	private void configurarRede() {
		try {
			socket = new Socket("192.168.0.1", 5000);
			escritor = new PrintWriter(socket.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cliente frame = new Cliente("Thiago");
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
	public Cliente(String nome) {
		super();
		this.nome = nome;
		setTitle("ZickaMessenger"+nome);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 400);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Arquivo");
		menuBar.add(mnNewMenu);

		JMenu mnNewMenu_1 = new JMenu("Contato");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("Sair");
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 503, 242);
		contentPane.add(scrollPane);

		textoVisto = new JTextPane();
		textoVisto.setEditable(false);
		scrollPane.setViewportView(textoVisto);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 264, 407, 69);
		contentPane.add(scrollPane_1);

		textoEnviar = new JTextPane();
		scrollPane_1.setViewportView(textoEnviar);

		JButton btnNewButton = new JButton("Enviar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//enviar o texto
				escritor.println(" :"+textoEnviar.getText());
				//valida se a msg foi enviada
				escritor.flush();
				//limpa o ca
				textoEnviar.setText("");
				textoEnviar.requestFocus();
			}
		});
		btnNewButton.setBounds(424, 264, 89, 69);
		contentPane.add(btnNewButton);
		configurarRede();
	}

}
