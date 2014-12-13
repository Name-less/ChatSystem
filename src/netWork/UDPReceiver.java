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
	/**
	 * 
	 * The UDPReceiver need to communicate with the networkInterface in order
	 * to give him every message received
	 * This constructor create the socket on the port receiver common to every use of
	 * the chat to be able to communicate all together.
	 * 
	 * We also create the DatagramPacket used to store the distant one received
	 * 
	 * @param context the networkInterface to notify when we receive something
	 */
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
	/**
	 * 
	 * Give the packet to the NI when we receive it.
	 * And he treat it, then we wait for an other one
	 * 
	 * @see NetWorkInterface
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running){
			try {
				//System.out.println("Avant la reception");
				socket.receive(remotePacket);//packet re�u, on le met dans le remote packet
				//System.out.println("Juste aprés la reception");
				DatagramPacket clonePacket = new DatagramPacket(remotePacket.getData(), remotePacket.getData().length, remotePacket.getAddress(), remotePacket.getPort());;
				this.interFace.addPacketRecu(clonePacket);//on l'ajoute � la liste des packets recu dans le networkinterface
				//System.out.println("Aprés le traitement");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
	}

}
