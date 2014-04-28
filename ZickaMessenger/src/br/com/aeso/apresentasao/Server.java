package br.com.aeso.apresentasao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	ServerSocket server;

	public Server() {
		try {
			server = new ServerSocket(9000);
			while (true) {
				// método que aguarda requisição de clientes a este servidor e
				// retorna u socket para se comunicar com o cliente o requisitou
				Socket socket = server.accept();
				new Thread(new monitoraCliente(socket)).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class monitoraCliente implements Runnable {

		Scanner in;

		public monitoraCliente(Socket socket) {
			try {
				in = new Scanner(socket.getInputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			String txt;
			try {
				while ((txt = in.nextLine()) != null) {
					System.out.println("Recebeu: " + txt);
				}
			} catch (Exception e) {}
		}

	}

	public static void main(String[] args) {
		new Server();
	}

}
