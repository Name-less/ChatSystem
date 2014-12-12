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
			udpSender.notify();
		}
	}
	
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
