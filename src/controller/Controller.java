package controller;

import java.util.ArrayList;
import java.util.HashMap;

import netWork.NetWorkInterface;
import netWork.User;

import graphicalUserInterface.AbstractGui;
import graphicalUserInterface.GUI;

public class Controller{
	
	private NetWorkInterface ni;
	
	/*
	 * 
	 * DECLARATIONS DE TOUS MES TYPES DE MESSAGES
	 * 
	 */
	public static String message = "message";
	public static String messageAck = "messageAck";
	public static String connect = "hello";
	public static String connectAck = "helloAck";
	public static String disconnect = "goodBye";
	
	/*
	 * 
	 * EN TETE DE MES JSON ARRAY
	 * 
	 */
	
	public static String type = "type";
	public static String userName = "userName";
	public static String messageData = "messageData";
	public static String messageNumber = "messageNumber";

	private AbstractGui gui;
	
	
	public Controller(){
		ni = new NetWorkInterface(this);
	}
	
	public void setGui(AbstractGui gui){
		this.gui = gui;
	}
	
	public void majUserList(ArrayList<User> userList){
		gui.majUserList(userList);
	}
	
	public void afficheMessage(String from,String message, int numero){
		this.gui.afficheMessage(from, message, numero);
	}
	
	public void addMessageAck(String from, int numero){
		this.gui.afficheMessageAck(from, numero);
	}
	
	public void accuseMessage(int numeroMessage){
		
	}

	public NetWorkInterface getNI(){
		return this.ni;
	}
	
}
