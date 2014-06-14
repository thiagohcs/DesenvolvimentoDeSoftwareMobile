package br.com.aeso.service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

import br.com.aeso.bean.ChatMessage;

public class ClienteService {
    private Socket socket;
    private ObjectOutputStream output;
    
    //construtor que efetua a conexão com o servidor
    public Socket connect(String ip){
        try {
            this.socket = new Socket(ip, 5555);
            this.output =  new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Você não pode se conectar, pois o IP do servidor não foi informado ou estÃ¡ errado!");
        }
        return socket;
    }
    
    public void send(ChatMessage message){
        try {
            output.writeObject(message);
        } catch (IOException ex) {
             System.out.println(ex);
             ex.getStackTrace();
        }
    }
}
