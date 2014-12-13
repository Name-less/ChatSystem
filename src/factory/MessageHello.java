package factory;

import java.net.InetAddress;

public class MessageHello extends MessageGlobal{

	String nickName;
	
	/**
	 * 
	 * Message hello, with nothing special, juste our nick name if we send the message or the distant nick name if we receive it
	 * 
	 * 
	 * @see MessageGlobal
	 */
	
	public MessageHello(String type,InetAddress ip,String nickName) {
		super(type,ip);
		this.nickName = nickName;
		// TODO Auto-generated constructor stub
	}
	
	public String getNickName(){
		return this.nickName;
	}

}
