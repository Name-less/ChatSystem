package factory;

import java.net.InetAddress;

public class MessageAck extends MessageGlobal{

	int messageNumber;
	/**
	 * 
	 * Message acknoledgement with a specific message number.
	 * 
	 * @param messageNumber the number of the message to send or received
	 * 
	 * @see MessageGlobal
	 */
	public MessageAck(String type, InetAddress ip,int messageNumber) {
		super(type, ip);
		this.messageNumber = messageNumber;
		// TODO Auto-generated constructor stub
	}
	
	public int getMessageNumber(){
		return this.messageNumber;
	}

}
