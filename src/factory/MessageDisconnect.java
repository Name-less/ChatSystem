package factory;

import java.net.InetAddress;

public class MessageDisconnect extends MessageGlobal{

	/**
	 * 
	 * Deconnexion, with nothing special, just his type.
	 * 
	 * 
	 * @see MessageGlobal
	 */
	
	public MessageDisconnect(String type,InetAddress ip) {
		super(type,ip);
		// TODO Auto-generated constructor stub
	}

}
