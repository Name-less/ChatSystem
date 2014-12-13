package factory;

import java.net.InetAddress;

public class MessageHelloAck extends MessageGlobal{

	private String nickName;
	
	/**
	 * 
	 * Acknolegment of a hello, with nothing special, just our nick name if we send the ack message or the distant nick name if we receive it
	 * 
	 * 
	 * @see MessageGlobal
	 */
	
	public MessageHelloAck(String type,InetAddress ip,String nickName) {
		super(type, ip);
		// TODO Auto-generated constructor stub
		this.nickName = nickName;
	}
	
	public String getNickName(){
		return this.nickName;
	}

}
