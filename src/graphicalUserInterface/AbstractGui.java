package graphicalUserInterface;

import java.awt.Color;
import java.util.ArrayList;

import controller.Controller;
import netWork.User;

/**
 * 
 * @author julescantegril
 *
 * Abstract class to implement with the specific technology used in order to be able to display message on the screen,
 * update connected list or send message
 *
 */

public interface AbstractGui {

	
	public abstract void majUserList(ArrayList<User> userList);
	
	public abstract void afficheMessage(String from,String message, int numero);
	
	public abstract void afficheMessageAck(String from,int numero);
	
	public abstract void accuseMessage(int numeroMessage);

	public void setController(Controller controller);
	
	public abstract void initComponentNotConnected();
	
	public abstract void initComponentConnected(String pseudo);
	
	public abstract Color getColor();
}
