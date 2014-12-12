package netWork;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPSender implements Runnable {

	private DatagramSocket socket;
	private DatagramPacket packetToSend;
	
	private boolean running;
	
	public UDPSender(){
		running = true;
		try {
			socket = new DatagramSocket(NetWorkInterface.portSender,InetAddress.getLocalHost());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(running){
			try {
				synchronized(this){
					wait();
					socket.send(packetToSend);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void disconnect(){
		this.running = false;
	}
	
	public void setDatagramPacket(DatagramPacket p){
		this.packetToSend = p;
	}
	
}
