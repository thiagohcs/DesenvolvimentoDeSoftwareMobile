package br.com.aeso.service;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import br.com.aeso.bean.ChatMessage;
import br.com.aeso.bean.ChatMessage.Action;

public class ServidorService {

    private ServerSocket serverSocket;
    private Socket socket;
    private Map<String, ObjectOutputStream> mapOnlines = new HashMap<String, ObjectOutputStream>();

    public ServidorService() {
        try {
        	//uma instancia do ServerSocket, que gera um socket com a porta 5555 de forma passiva, e aguarda uma pedido de conexão do cliente 
            serverSocket = new ServerSocket(5555);
            System.out.println("Servidor ON!");
            while (true) {
            	//Socket para o cliente se comunicar com servidor, ou seja, solicitação de conexão
                socket = serverSocket.accept();
                // cada socket gerado, é também gerado um thread para que o chat possa ter varios clientes conectado ao servidor
                new Thread(new ListenerSocket(socket)).start();
            }
        } catch (IOException ex) {
        	System.out.println(ex);
        	ex.getStackTrace();
        }
    }

    //Ouvinte do servidor (Classe da Thread)
    public class ListenerSocket implements Runnable {

        private ObjectOutputStream output;
        private ObjectInput input;

        public ListenerSocket(Socket socket) {
            try {
            	// em cada socket instancia-se um ObjectOut/ObjectInput para que possa enviar e receber menssagens
                this.output = new ObjectOutputStream(socket.getOutputStream());
                this.input = new ObjectInputStream(socket.getInputStream());
            } catch (IOException ex) {
            	System.out.println(ex);
            	ex.getStackTrace();
            }
        }

        @Override
        public void run() {
            ChatMessage message = null;
            try {
            	// enquando o ObjectInput não for nulo, o servidor avalia qual é a ação do cliente
                while ((message = (ChatMessage) input.readObject()) != null) {
                    Action action = message.getAction();

                    if (action.equals(Action.CONNECT)) {
                        boolean isConnect = connect(message, output);
                        if (isConnect) {
                            mapOnlines.put(message.getNome(), output);
                            sendOnlines();
                        }
                    } else if (action.equals(Action.DISCONNECT)) {
                        disconnect(message, output);
                        sendOnlines();
                        return;
                    } else if (action.equals(Action.SEND_ONE)) {
                        sendOne(message);
                    } else if (action.equals(Action.SEND_ALL)) {
                        sendAll(message);
                    }
                }
            } catch (Exception ex) {
                disconnect(message, output);
                sendOnlines();
                System.out.println(ex);
            	ex.getStackTrace();
            }
        }
    }

    private boolean connect(ChatMessage message, ObjectOutputStream output) {
        if (mapOnlines.size() == 0) {
            message.setTexto("YES");
            send(message, output);
            return true;
        }

        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            if (kv.getKey().equals(message.getNome())) {
                message.setTexto("NO");
                send(message, output);
                return false;
            } else {
                message.setTexto("YES");
                send(message, output);
                return true;
            }
        }
        return false;
    }

    //Quando o cliente se desconectar o cliente que é descartado o socket do mesmo
    private void disconnect(ChatMessage message, ObjectOutputStream output) {
    	message.setTexto("está offline");
        mapOnlines.remove(message.getNome());
        message.setAction(Action.SEND_ALL);
        sendAll(message);
        try {
            socket.close();
            System.out.println("Usuário " + message.getNome() + " saiu do chat!");
        } catch (IOException ex) {
        	System.out.println(ex);
        	ex.getStackTrace();
        }
        //System.out.println("Usuário " + message.getNome() + " saiu do chat!");
    }

    private void send(ChatMessage message, ObjectOutputStream output) {
        try {
            output.writeObject(message);
        } catch (IOException ex) {
        	System.out.println(ex);
        	ex.getStackTrace();
        }
    }
    // método para enviar messagens para todas pessoas que estiverem onlines
    private void sendOne(ChatMessage message) {
    	//percorre a lista de pessoas online
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            //verificar se objeto achado é igual ao clicado na lista de pessoas online
        	if (kv.getKey().equals(message.getNomeReservado())) {
                message.setAction(Action.SEND_ONE);
                try {
                    kv.getValue().writeObject(message);
                } catch (IOException ex) {
                	System.out.println(ex);
                	ex.getStackTrace();
                }
            }
        }
    }
    
    //método para enviar mensagem para todo mundo
    private void sendAll(ChatMessage message) {
    	//percorre a lista de pessoas online
    	for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            if (!kv.getKey().equals(message.getNome())) {
                message.setAction(Action.SEND_ONE);
                try {
                    kv.getValue().writeObject(message);
                } catch (IOException ex) {
                	System.out.println(ex);
                	ex.getStackTrace();
                }
            }
        }
    }
    //método que envia mesagens para todoas as pessoas que estão no chat, porém ele só avisar qual pessoa entrou no chat
    private void sendOnlines() {
        Set<String> setNames = new HashSet<String>();
        //mesma coisa dos métodos acima, percorre as lista de pessoas onlines
        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            setNames.add(kv.getKey());
        }
        ChatMessage message = new ChatMessage();
        message.setAction(Action.USERS_ONLINE);
        message.setSetOnlines(setNames);

        for (Map.Entry<String, ObjectOutputStream> kv : mapOnlines.entrySet()) {
            message.setNome(kv.getKey());
            try {
                System.out.println("name -> " + kv.getKey());
                kv.getValue().writeObject(message);
            } catch (IOException ex) {
            	System.out.println(ex);
            	ex.getStackTrace();
            }
        }
    }
}
