package udpFactory;

import java.net.InetAddress;

public class Message extends MessageGlobal{

	private String data;
	private int number;
	
	public Message(String type,String data, int number,InetAddress ip) {
		super(type, ip);
		this.data = data;
		this.number = number;
		// TODO Auto-generated constructor stub
	}
	
	public String getData(){
		return this.data;
	}
	
	public int getNumber(){
		return this.number;
	}
	

}
