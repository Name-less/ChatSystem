package chatSystem;

import graphicalUserInterface.AbstractGui;
import graphicalUserInterface.GUI;
import controller.Controller;

public class ChatSystem {

	private static Controller c;
	private static AbstractGui g;
	
	public static void main(String [] arg0){
		
		c = new Controller();
		g = new GUI();
		g.setController(c);
		c.setGui(g);
		
		g.initComponentNotConnected();
		
	}
	
}
