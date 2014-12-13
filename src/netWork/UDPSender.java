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
	/**
	 * 
	 * Create a new socket in order to send message on UDP
	 * We use our own IP Address and a port not really important
	 * 
	 */
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

	/**
	 * When we have a notification we send the packet
	 * The notification come always when we set the datagram packet of the class
	 * After we wait until the next new packet
	 * 
	 * @see setDatagramPacket
	 */
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
	/**
	 * Set the datagram packet and notify the object in order to send it.
	 * @param p
	 */
	public void setDatagramPacket(DatagramPacket p){
		this.packetToSend = p;
		this.notify();
	}
	
}
