package factory;

import java.net.InetAddress;

public class MessageGlobal {
	
	private String type;
	private InetAddress ip;
	
	/**
	 * 
	 * Parent class of all our messages
	 * 
	 * @param  type  the type of our message
	 * @param ip the addres ip where the message come from
	 * 
	 */
	
	public MessageGlobal(String type,InetAddress ip){
		this.type = type;
		this.ip = ip;
	}
	
	public String getType(){
		return this.type;
	}
	
	public InetAddress getIp(){
		return this.ip;
	}
	
}
