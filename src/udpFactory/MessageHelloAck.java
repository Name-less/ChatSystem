package udpFactory;

import java.net.InetAddress;

public class MessageHelloAck extends MessageGlobal{

	private String nickName;
	
	public MessageHelloAck(String type,InetAddress ip,String nickName) {
		super(type, ip);
		// TODO Auto-generated constructor stub
		this.nickName = nickName;
	}
	
	public String getNickName(){
		return this.nickName;
	}

}
