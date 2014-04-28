package br.com.aeso.teste;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class ChatCliente extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextPane  textToSend;
	PrintWriter pw;
	Socket socket;
	String name;
	JTextArea receivedText;
	Scanner read;
	
	private void configuredNetwork(){
		try {
			socket = new Socket("127.0.0.1", 5000);
			pw = new PrintWriter(socket.getOutputStream());
			read = new Scanner(socket.getInputStream());
			new Thread(new monitorServer()).start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public ChatCliente(String nome){
		super("ZickaMessenger :" + nome);
		this.name = nome;
		
		Font fonte = new Font("Calibri", Font.PLAIN, 26); 
		textToSend = new JTextPane();
		textToSend.setFont(fonte);
		textToSend.setSize(100, 70);
		
		JButton botaoEnviar = new JButton("Enviar");
		botaoEnviar.setFont(fonte);
		botaoEnviar.addActionListener(new SendListener());
		Container envio = new JPanel();
		envio.setLayout(new BorderLayout());
		envio.add(BorderLayout.CENTER, textToSend);
		envio.add(BorderLayout.EAST, botaoEnviar);
		
		receivedText = new JTextArea();
		receivedText.setFont(fonte);
		JScrollPane scroll2 = new JScrollPane(receivedText);
		
		getContentPane().add(BorderLayout.CENTER, scroll2);
		getContentPane().add(BorderLayout.SOUTH, envio);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 503, 242);
		envio.add(scrollPane);
		scrollPane.setViewportView(textToSend);
		
		configuredNetwork();
		 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700,400);
		setVisible(true);
	}
	
	private class SendListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			pw.println(name+" : "+textToSend.getText());
			pw.flush();
			textToSend.setText("");
			textToSend.requestFocus();
		}
		
	}
	
	private class monitorServer implements Runnable{
		String text;
		@Override
		public void run() {
			try{
				while((text = read.nextLine()) != null){
					receivedText.append(text + "\n");
				}
			}catch(Exception e){}
		}
	}
	
	public static void main(String[] args) {
		new ChatCliente("Thiago");
		new ChatCliente("Laine");

	}

}
