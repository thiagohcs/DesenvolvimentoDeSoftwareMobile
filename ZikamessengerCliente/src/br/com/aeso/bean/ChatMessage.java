package br.com.aeso.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class ChatMessage implements Serializable {

	//Atributos do Chat lado cliente
	private static final long serialVersionUID = 1L;
	private String nome;
    private String texto;
    private String nomeReservado;
    private Set<String> setOnlines = new HashSet<String>();
    private Action action;
    //Ações do chat lado cliente
    public enum Action {

        CONNECT, DISCONNECT, SEND_ONE, SEND_ALL, USERS_ONLINE
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getNomeReservado() {
        return nomeReservado;
    }

    public void setNomeReservado(String nomeReservado) {
        this.nomeReservado = nomeReservado;
    }

    public Set<String> getSetOnlines() {
        return setOnlines;
    }

    public void setSetOnlines(Set<String> setOnlines) {
        this.setOnlines = setOnlines;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

}
