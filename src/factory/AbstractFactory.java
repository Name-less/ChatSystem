package factory;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.json.JSONException;

public abstract class AbstractFactory {
	
	
	/**
	 * Abstrac method use in order to fill a datagramPacket with a specified technology
	 * 
	 * @param  type  kind of message we want to send
	 * @param value the message to send
	 * @param host the distant host to send the packet
	 * @param messageNumber message number in order to receive a good ack
	 * @param port the receiver port which is listening for our packet
	 * @param couleur color of our message
	 * @return      the DatagramPacket filled correcty
	 * @see DatagramPacket
	 * 
	 * 
	 */
	
	public abstract DatagramPacket createMessage(String type,String value,InetAddress host,int messageNumber,int port,Color couleur)  throws UnknownHostException, JSONException, UnsupportedEncodingException;
	
	/**
	 * Abstrac method use in order to treate a datagramPacket received and to create a message with
	 * all the data received
	 * 
	 * @param  packetToTreat  the packet we want to tread
	 * @return     the message fill with all the data of the packet to treat
	 * @see MessageGlobal
	 * 
	 */
	
	public abstract MessageGlobal traiterPacketRecu(DatagramPacket packetToTreat)throws UnsupportedEncodingException, JSONException, UnknownHostException;

}
