package factory;

import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

import netWork.NetWorkInterface;

import org.json.JSONException;
import org.json.JSONObject;

import controller.Controller;

public class Factory extends AbstractFactory {

	private NetWorkInterface ni;
	
	public Factory(NetWorkInterface ni){
		this.ni = ni;
	}
	
	public DatagramPacket createMessage(String type,String value,InetAddress host,int messageNumber,int port) throws UnknownHostException, JSONException, UnsupportedEncodingException {
		byte [] lol = new byte[1024];
		DatagramPacket message = new DatagramPacket(lol,0,host,port);
		
		JSONObject data = new JSONObject();
			if(type.equals(Controller.message)){
				
				data.put(Controller.type, Controller.message);
				data.put(Controller.messageData,value);
				data.put(Controller.messageNumber,messageNumber);
				message.setPort(port);
				message.setAddress(host);
				NetWorkInterface.numeroMessageSend++;
				
			}else if(type.equals(Controller.messageAck)){
				
				data.put(Controller.type, Controller.messageAck);
				data.put(Controller.messageNumber,messageNumber);
				message.setAddress(host);
				message.setPort(port);

			}else if(type.equals(Controller.connect)){
				
					data.put(Controller.type, Controller.connect);
					data.put(Controller.userName,value);
					message.setAddress(host);
					message.setPort(port);

			}else if(type.equals(Controller.disconnect)){
				
					data.put(Controller.type, Controller.disconnect);
					message.setAddress(InetAddress.getByName("255.255.255.255"));
					message.setPort(port);
					
			}else if(type.equals(Controller.connectAck)){
				
				data.put(Controller.type, Controller.connectAck);
				data.put(Controller.userName, NetWorkInterface.myName);
				message.setAddress(host);
				message.setPort(port);

			}	
		
			System.out.println("type envoyé "+type +" data "+data.toString());
			message.setData(data.toString().getBytes("UTF-8"));
			
		return message;
	}
	
	public MessageGlobal traiterPacketRecu(DatagramPacket packetToTreat)throws UnsupportedEncodingException, JSONException, UnknownHostException{
		//on traite et on envoit au controller ce qu'il faut modifier
		MessageGlobal messageGlobal = null;
		
			JSONObject data;
			String test = new String(packetToTreat.getData(),"UTF-8");
			data = new JSONObject(test);
			if(data.getString(Controller.type).equals(Controller.message)){
				
				messageGlobal = new Message(data.getString(Controller.type),data.getString(Controller.messageData),Integer.parseInt(data.getString(Controller.messageNumber)),packetToTreat.getAddress());
								
			}else if(data.getString(Controller.type).equals(Controller.messageAck)){
				
				messageGlobal = new MessageAck(data.getString(Controller.type),packetToTreat.getAddress(),Integer.parseInt(data.getString(Controller.messageNumber)));
								
			}else if(data.getString(Controller.type).equals(Controller.connect)){
				
				messageGlobal = new MessageHello(data.getString(Controller.type),packetToTreat.getAddress(),data.getString(Controller.userName));

			}else if(data.getString(Controller.type).equals(Controller.disconnect)){
				
				messageGlobal = new MessageDisconnect(data.getString(Controller.type),packetToTreat.getAddress());
				
			}else if(data.getString(Controller.type).equals(Controller.connectAck)){
				
				messageGlobal = new MessageHelloAck(data.getString(Controller.type),packetToTreat.getAddress(),data.getString(Controller.userName));
				
			}		
			
			return messageGlobal;
			
	}
	
	
	
}
