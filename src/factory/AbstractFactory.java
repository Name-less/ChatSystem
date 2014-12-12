package factory;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.JSONException;

public abstract class AbstractFactory {
	
	public abstract DatagramPacket createMessage(String type,String value,InetAddress host,int messageNumber,int port)  throws UnknownHostException, JSONException, UnsupportedEncodingException;
	
	public abstract MessageGlobal traiterPacketRecu(DatagramPacket packetToTreat)throws UnsupportedEncodingException, JSONException, UnknownHostException;

}
