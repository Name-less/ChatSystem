package udpFactory;

import java.net.InetAddress;

public class MessageAck extends MessageGlobal{

	int messageNumber;
	
	public MessageAck(String type, InetAddress ip,int messageNumber) {
		super(type, ip);
		this.messageNumber = messageNumber;
		// TODO Auto-generated constructor stub
	}
	
	public int getMessageNumber(){
		return this.messageNumber;
	}

}
