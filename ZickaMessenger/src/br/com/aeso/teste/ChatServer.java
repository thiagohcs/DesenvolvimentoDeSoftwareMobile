package br.com.aeso.teste;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChatServer {

	List<PrintWriter> writers = new ArrayList<>();
	
	public ChatServer(){
		ServerSocket server;
		try {
			server = new ServerSocket(5000);
			while(true){
				Socket socket = server.accept();
				new Thread(new monitorClient(socket)).start();
				PrintWriter p = new PrintWriter(socket.getOutputStream());
				writers.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private class monitorClient implements Runnable{
		Scanner in;
		String text;
		public monitorClient(Socket socket){
			try{
				in = new Scanner(socket.getInputStream());
			}catch(Exception e ){}
		}
		
		@Override
		public void run() {
			try{
				while((text = in.nextLine()) != null){
					System.out.println("Recebeu: "+text);
					forwardForAll(text);
				}
			}catch(Exception e){}
		}
	}
	
	private void forwardForAll(String text){
		for (PrintWriter w : writers) {
			w.println(text);
			w.flush();
		}
	}
	
	public static void main(String[] args) {
		new ChatServer();

	}

}
