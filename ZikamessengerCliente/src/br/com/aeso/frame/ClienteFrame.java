package br.com.aeso.frame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Set;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import br.com.aeso.bean.ChatMessage;
import br.com.aeso.bean.ChatMessage.Action;
import br.com.aeso.service.ClienteService;
import java.awt.Toolkit;

public class ClienteFrame extends javax.swing.JFrame {


	private static final long serialVersionUID = 1L;
	private Socket socket;
	private ChatMessage message;
	private ClienteService service;

	public ClienteFrame() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\Users\\Thiago Henrique\\Imagens\\coroa.png"));
		initComponents();
	}

	private class ListenerSocket implements Runnable {

		private ObjectInputStream input;
		// thread para cada ObjectInput
		public ListenerSocket(Socket socket) {
			try {
				this.input = new ObjectInputStream(socket.getInputStream());
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		//inicialização da thread
		@Override
		public void run() {
			ChatMessage message = null;
			try {
				while ((message = (ChatMessage) input.readObject()) != null) {
					Action action = message.getAction();
					if (action.equals(Action.CONNECT)) {
						connected(message);
					} else if (action.equals(Action.DISCONNECT)) {
						disconnected();
						socket.close();
						// return;
					} else if (action.equals(Action.SEND_ONE)) {
						receive(message);
					} else if (action.equals(Action.USERS_ONLINE)) {
						refreshOnlines(message);
					}
				}
			} catch (IOException | ClassNotFoundException ex) {
				System.out.println(message.getNome()+" saiu do chat!");
				//ex.printStackTrace();
			}
		}
	}
	// valida se o cliente realmente está conectado
	private void connected(ChatMessage message) {
		if (message.getTexto().equals("NO")) {
			this.txtName.setText("");
			JOptionPane.showMessageDialog(this,"Conexão não realizada!\nTente novamente com um novo nome.");
			return;
		}
		this.message = message;
		this.btnConectar.setEnabled(false);
		this.txtName.setEnabled(false);
		// this.listOnlines.setEnabled(true);
		habilitaCampos();
		JOptionPane.showMessageDialog(this, "Você está conectado no chat!");
	}
	//desconecta o cliente do do chat
	private void disconnected() {
		this.btnConectar.setEnabled(true);
		this.txtName.setEnabled(true);
		JOptionPane.showMessageDialog(this, "Você saiu do chat!");
		desabilitaCampos();
		limpraCampos();
		// this.listOnlines.setEnabled(false);
	}
	// método que recebe mensagem
	private void receive(ChatMessage message) {
		this.txtAreaReceive.append(message.getNome() + " - diz: "+ message.getTexto() + "\n");
	}
	
	//atualiza lista de pessoas online
	private void refreshOnlines(ChatMessage message) {
		Set<String> names = message.getSetOnlines();

		names.remove((String) message.getNome());
		String[] array = names.toArray(new String[names.size()]);
		this.listOnlines.setListData(array);
		this.listOnlines.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.listOnlines.setLayoutOrientation(JList.VERTICAL);

	}
	

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jLabel1 = new javax.swing.JLabel();
		txtName = new javax.swing.JTextField();
		btnConectar = new javax.swing.JButton();
		btnDesconectar = new javax.swing.JButton();
		jPanel2 = new javax.swing.JPanel();
		jScrollPane3 = new javax.swing.JScrollPane();
		listOnlines = new javax.swing.JList();
		jPanel3 = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtAreaReceive = new javax.swing.JTextArea();
		jScrollPane2 = new javax.swing.JScrollPane();
		txtAreaSend = new javax.swing.JTextArea();
		btnClear = new javax.swing.JButton();
		btnSend = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		setTitle("Zickamessenger");
		setResizable(false);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Conectar",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Calibri", 1, 14)));

		jLabel1.setFont(new java.awt.Font("Calibri", 0, 12));
		jLabel1.setText("Nome: ");

		txtName.setFont(new java.awt.Font("Calibri", 0, 12));

		btnConectar.setFont(new java.awt.Font("Calibri", 1, 12));
		btnConectar.setText("conectar");
		btnConectar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnConectarActionPerformed(evt);
			}
		});

		btnDesconectar.setFont(new java.awt.Font("Calibri", 1, 12));
		btnDesconectar.setText("sair");
		btnDesconectar.setCursor(new java.awt.Cursor(
				java.awt.Cursor.DEFAULT_CURSOR));
		btnDesconectar.setEnabled(false);
		btnDesconectar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnDesconectarActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(jLabel1)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(
												txtName,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												138,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnConectar,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnDesconectar,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addGap(4, 4, 4)));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1)
										.addComponent(
												txtName,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(btnConectar)
										.addComponent(btnDesconectar)));

		jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null,
				"Onlines",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Calibri", 1, 14))); 

		jScrollPane3.setViewportView(listOnlines);

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 118,
				Short.MAX_VALUE));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jScrollPane3));

		txtAreaReceive.setEditable(false);
		txtAreaReceive.setColumns(20);
		txtAreaReceive.setFont(new java.awt.Font("Calibri", 0, 13)); 
		txtAreaReceive.setLineWrap(true);
		txtAreaReceive.setRows(5);
		txtAreaReceive.setEnabled(false);
		jScrollPane1.setViewportView(txtAreaReceive);

		txtAreaSend.setColumns(20);
		txtAreaSend.setFont(new java.awt.Font("Calibri", 0, 13)); 
		txtAreaSend.setRows(5);
		txtAreaSend.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1,
				1, 1, new java.awt.Color(153, 204, 255)));
		txtAreaSend.setEnabled(false);
		txtAreaSend.setName(""); 
		txtAreaSend.setOpaque(false);
		jScrollPane2.setViewportView(txtAreaSend);
		txtAreaSend.getAccessibleContext().setAccessibleName("");
		txtAreaSend.getAccessibleContext().setAccessibleDescription("");

		btnClear.setFont(new java.awt.Font("Calibri", 1, 12));
		btnClear.setText("Limpar");
		btnClear.setEnabled(false);
		btnClear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnClearActionPerformed(evt);
			}
		});

		btnSend.setFont(new java.awt.Font("Calibri", 1, 12)); 
		btnSend.setText("Enviar");
		btnSend.setEnabled(false);
		btnSend.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnSendActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(
				jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout
				.setHorizontalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																javax.swing.GroupLayout.Alignment.TRAILING,
																jPanel3Layout
																		.createSequentialGroup()
																		.addGap(203,
																				203,
																				203)
																		.addComponent(
																				btnClear,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnSend,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE))
														.addComponent(
																jScrollPane2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																393,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																393,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		jPanel3Layout
				.setVerticalGroup(jPanel3Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel3Layout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jScrollPane1,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												149, Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jScrollPane2,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												88,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel3Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnClear)
														.addComponent(btnSend))
										.addContainerGap()));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jPanel1,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														jPanel3,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jPanel2,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING,
												false)
												.addComponent(
														jPanel2,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jPanel1,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jPanel3,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(19, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void btnDesconectarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDesconectarActionPerformed
		this.message.setAction(Action.DISCONNECT);
		this.service.send(this.message);
		disconnected();
	}// GEN-LAST:event_btnDesconectarActionPerformed

	private void btnConectarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnConectarActionPerformed
		String name = txtName.getText();
		if (!name.isEmpty()) {
			this.message = new ChatMessage();
			this.message.setAction(Action.CONNECT);
			this.message.setNome(name);
			this.service = new ClienteService();
			String ip = JOptionPane.showInputDialog(this,
					"Informo o IP do servicor:");
			if (!ip.trim().equals("")) {
				this.socket = this.service.connect(ip);
				new Thread(new ListenerSocket(socket)).start();
				this.service.send(message);
				setTitle("Zickamessenger - " + this.message.getNome());
			}
		}
	}// GEN-LAST:event_btnConectarActionPerformed

	private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSendActionPerformed
		String text = this.txtAreaSend.getText();
		String name = this.txtName.getText();
		this.message = new ChatMessage();

		if (this.listOnlines.getSelectedIndex() > -1) {
			this.message.setNomeReservado((String) this.listOnlines.getSelectedValue());
			this.message.setAction(Action.SEND_ONE);
			this.listOnlines.clearSelection();
		} else {
			this.message.setAction(Action.SEND_ALL);
		}
		if (!text.isEmpty()) {
			this.message.setNome(name);
			this.message.setTexto(text);
			this.txtAreaReceive.append("Você disse: " + text + "\n");
			this.service.send(this.message);
		}
		this.txtAreaSend.setText("");
	}// GEN-LAST:event_btnSendActionPerformed

	private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnClearActionPerformed
		txtAreaSend.setText("");
	}// GEN-LAST:event_btnClearActionPerformed

	private void limpraCampos() {
		this.txtName.setText("");
		this.txtAreaReceive.setText("");
		this.txtAreaSend.setText("");
	}

	private void desabilitaCampos() {
		this.btnDesconectar.setEnabled(false);
		this.txtAreaReceive.setEnabled(false);
		this.txtAreaSend.setEnabled(false);
		this.btnSend.setEnabled(false);
		this.btnClear.setEnabled(false);
	}

	private void habilitaCampos() {
		this.btnDesconectar.setEnabled(true);
		this.txtAreaSend.setEnabled(true);
		this.txtAreaReceive.setEnabled(true);
		this.btnSend.setEnabled(true);
		this.btnClear.setEnabled(true);
	}

	private javax.swing.JButton btnClear;
	private javax.swing.JButton btnConectar;
	private javax.swing.JButton btnDesconectar;
	private javax.swing.JButton btnSend;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JScrollPane jScrollPane3;
	private javax.swing.JList listOnlines;
	private javax.swing.JTextArea txtAreaReceive;
	private javax.swing.JTextArea txtAreaSend;
	private javax.swing.JTextField txtName;


}
