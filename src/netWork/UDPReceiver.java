package netWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPReceiver implements Runnable{

	DatagramSocket socket;
	DatagramPacket remotePacket;
	
	private boolean running;
	private NetWorkInterface interFace;
	
	public void disconnect(){
		this.running = false;
	}
	
	public UDPReceiver(NetWorkInterface context){
		this.interFace = context;
		running = true;
		byte[] buf = new byte[1024];
		remotePacket = new DatagramPacket(buf,buf.length);
		try {
			socket = new DatagramSocket(NetWorkInterface.portReceiver);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running){
			try {
				System.out.println("Avant la reception");
				socket.receive(remotePacket);//packet re�u, on le met dans le remote packet
				System.out.println("Juste aprés la reception");
				DatagramPacket clonePacket = new DatagramPacket(remotePacket.getData(), remotePacket.getData().length, remotePacket.getAddress(), remotePacket.getPort());;
				this.interFace.addPacketRecu(clonePacket);//on l'ajoute � la liste des packets recu dans le networkinterface
				System.out.println("Aprés le traitement");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
