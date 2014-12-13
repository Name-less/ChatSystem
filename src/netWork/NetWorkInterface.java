package netWork;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import factory.Factory;
import factory.Message;
import factory.MessageAck;
import factory.MessageDisconnect;
import factory.MessageGlobal;
import factory.MessageHello;
import factory.MessageHelloAck;

import org.json.JSONException;
import org.json.JSONObject;

import controller.Controller;

public class NetWorkInterface {

	private ActionOnList actionOnList;
	private Factory factory;
	private Controller controller;
	private UDPSender udpSender;
	private UDPReceiver udpReceiver;
	
	private TCPReceiver tcpReceiver;
	
	public static int numeroMessageSend = 0;
	public static int portSender = 2222;
	public static int portReceiver = 1337;
	
	public static String myName = "Xineohp";

/**
 * 
 * This construcor launch 3 thread in order to be able to send message, receive them and also to receive a file.
 * 
 * @param controller the controller used to give every message we receive in order to give them to the 
 * Gui and display something at the screen if necessary
 * 
 * @see Controller
 * @see UDPSender
 * @see UDPReceiver
 * @see TCPReceiver
 */
	public NetWorkInterface(Controller controller){
		actionOnList = new ActionOnList(controller);
		factory = new Factory(this);
		
		this.controller = controller;
		udpReceiver = new UDPReceiver(this);
		udpSender = new UDPSender();
		
		Thread runUDPThreadRec = new Thread(udpReceiver);
		runUDPThreadRec.start();
		
		Thread runUDPThreadSender = new Thread(udpSender);
		runUDPThreadSender.start();
		
		Thread renTCPThreadReceiver = new Thread(tcpReceiver);
		renTCPThreadReceiver.start();
		
	}
	/**
	 * Launch a new thread at each UDP packet received
	 * 
	 * @see TraiterPacketRecu
	 */
	public void addPacketRecu(DatagramPacket packet){		
		Thread threadTraitePacket = new Thread(new TraiterPacketRecu(packet));
		threadTraitePacket.start();
	}
		
	/*
	 * 
	 * ACTION SUR CONTROLLER
	 * 
	 */

	public void addMessage(String from, String message,int numero){
		controller.afficheMessage(from, message, numero);
	}
	
	public void addMessageAck(String from, int numero){
		controller.addMessageAck(from, numero);
	}
	
	/*
	 * 
	 * LES RUNNABLES
	 * 
	 */
	
/**
 * This class is just a Runnable that will be launch in a new thread in order to treat the packet in an
 * other thread than the main one
 * 
 * @author julescantegril
 *
 * @see AbstractFactory
 */
	public class TraiterPacketRecu implements Runnable{

		DatagramPacket toTreat;
		public TraiterPacketRecu(DatagramPacket packet){
			this.toTreat = packet;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				treatMessage(factory.traiterPacketRecu(toTreat));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void killThreads(){
		udpSender.disconnect();
		udpReceiver.disconnect();
	}
	
	/*
	 * 
	 * ACTIONS DECLANCHEE PAR LE CONTROLEUR
	 * 
	 */
	
	public void sendMessage(DatagramPacket packetToSend){
		synchronized(udpSender){
			udpSender.setDatagramPacket(packetToSend);
		}
	}
	
	/**
	 * Create a new thread to send a specific file by a TCP connexion
	 * 
	 * @param filePath the file to the path we want to send
	 * @param destination the IP adress where we want to send it
	 * 
	 */
	public void sendFile(String filePath,InetAddress destination){
		
		Thread launchFileSend = new Thread(new TCPSender(filePath,destination));
		launchFileSend.start();
		
	}
	
	/*
	 * 
	 * AUTRE METHODES 
	 * 
	 */
	
	public ActionOnList getActionOnList(){
		return this.actionOnList;
	}

	public Factory getFactory(){
		return this.factory;
	}
	/**
	 * This method do something with the message we just
	 * receive by the UDP channel.
	 * For sample, the NI use the Controller to tell the Gui to udpate the user list displayed at
	 * the screen if we receive a helloAck message or a hell message.
	 * 
	 * @param toTreat the message we need to tread.
	 * 
	 * @see MessageGlobal
	 * 
	 */
	public void treatMessage(MessageGlobal toTreat){
		if(toTreat.getType().equals(Controller.message)){
			Message toTreatReal = (Message)toTreat;
			
			this.addMessage(this.getActionOnList().getNameFromIp(toTreatReal.getIp()),toTreatReal.getData(),toTreatReal.getNumber());
			try {
				this.sendMessage(this.factory.createMessage(Controller.messageAck,this.myName,toTreatReal.getIp(),toTreatReal.getNumber(),NetWorkInterface.portReceiver,this.controller.getGui().getColor()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else if(toTreat.getType().equals(Controller.messageAck)){
					
			MessageAck toTreatReal = (MessageAck)toTreat;
			this.addMessageAck(this.getActionOnList().getNameFromIp(toTreatReal.getIp()),toTreatReal.getMessageNumber());
			
		}else if(toTreat.getType().equals(Controller.connect)){
			
			MessageHello toTreatReal = (MessageHello)toTreat;
			this.getActionOnList().addUserToList(toTreatReal.getNickName(),toTreatReal.getIp());
			try {
				this.sendMessage(this.factory.createMessage(Controller.connectAck,this.myName,toTreatReal.getIp(),0,NetWorkInterface.portReceiver,this.controller.getGui().getColor()));
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if(toTreat.getType().equals(Controller.disconnect)){
			
			MessageDisconnect toTreatReal = (MessageDisconnect)toTreat;
			this.getActionOnList().removeUserToList(this.actionOnList.getNameFromIp(toTreatReal.getIp()));
			
		}else if(toTreat.getType().equals(Controller.connectAck)){
			
			MessageHelloAck toTreatReal = (MessageHelloAck)toTreat;
			this.getActionOnList().addUserToList(toTreatReal.getNickName(),toTreatReal.getIp());
			
			
		}		
	}
}
