package factory;

import java.awt.Color;
import java.net.InetAddress;

public class Message extends MessageGlobal{

	private String data;
	private int number;
	private Color couleur;

	public Color getColor(){
		return this.couleur;
	}
	
	public Message(String type,String data, int number,InetAddress ip,Color couleur) {
		super(type, ip);
		this.data = data;
		this.number = number;
		this.couleur =couleur;
		// TODO Auto-generated constructor stub
	}
	
	public String getData(){
		return this.data;
	}
	
	public int getNumber(){
		return this.number;
	}
	

	public String toString(){
		return this.data;
	}
	
}
