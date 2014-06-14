package br.com.aeso.app;

import br.com.aeso.frame.ClienteFrame;

public class Cliente {

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Metal".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				// método para start o cliente
				new ClienteFrame().setVisible(true);
			}
		});
	}
}
