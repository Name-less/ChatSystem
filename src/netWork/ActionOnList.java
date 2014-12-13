package netWork;

import java.net.InetAddress;
import java.util.ArrayList;

import controller.Controller;

public class ActionOnList {
	
	private ArrayList<User> userList;
	private ArrayList<User> userGroupToSend;
	private Controller controller;

	public ActionOnList(Controller c){
		this.userList = new ArrayList<User>();
		this.userGroupToSend = new ArrayList<User>();
		this.controller  = c;
	}
	
	/**
	 * 
	 * Use full to add a user to a list, only if he is not actually in this list
	 * Update also the list on the controller in order to display the new list on the screen
	 * 
	 * @param name the nick name of the user to add
	 * @param ip his address IP
	 * 
	 * @see User
	 */
	
	public void addUserToList(String name, InetAddress ip){
		User userToAdd = new User(name,ip);
		if(!this.exist(userToAdd)){
			this.userList.add(userToAdd);//peut �tre v�rifier qu'il n'existe pas d�ja car si il existe deja ca ne vas pas etre possible, � ca non non non
			controller.majUserList(this.userList);
		}
	}
	
	
	/**
	 * 
	 * Use full to remose a user to a list
	 * Update also the list on the controller in order to display the new list on the screen
	 * 
	 * @param name the nick name to delete
	 */
	
	public void removeUserToList(String name){
		if(this.userList.remove(name)){
			controller.majUserList(this.userList);
		}
	}
	
	public void addUserToListGroup(int whichUser){
		User userToAdd = userList.get(whichUser);
		this.userGroupToSend.add(userToAdd);//peut �tre v�rifier qu'il n'existe pas d�ja car si il existe deja ca ne vas pas etre possible, � ca non non non
	}
	
	public void removeUserToListGroup(int whichUser){
		this.userGroupToSend.remove(whichUser);		
	}

	public ArrayList<User> getUserGroup(){
		return this.userGroupToSend;
	}
	
	public boolean exist(User b){
		for(int i = 0;i<this.userList.size();i++){
			if(userList.get(i).compareTo(b)){
				return true;
			}
		}
		return false;
	}
	
	public void reInitList(){
		this.userList = new ArrayList<User>();
		this.userGroupToSend = new ArrayList<User>();
	}
	
/**
 * 
 * Return the nick name from the IP address passed
 * 
 * @param address the IP address to use in order to find the nick name
 * @return the nick name of the user who has this IP address. 
 * @return null if the address IP is not in the list
 */
	
	public String getNameFromIp(InetAddress address){
		for(int i = 0;i<this.userList.size();i++){
			if(userList.get(i).getIp().equals(address)){
				return userList.get(i).getName();
			}
		}
		return null;
	}
	
	/**
	 * 
	 * Return the nick name from the IP address passed
	 * 
	 * @param name the nick name to use in order to find the IP address
	 * @return the IP address of the user who has this nick name. 
	 * @return null if the nick name is not in the list
	 */

	public InetAddress getIpFromName(String name){
		for(int i = 0;i<this.userList.size();i++){
			if(userList.get(i).getName().equals(name)){
				return userList.get(i).getIp();
			}
		}
		return null;
	}
	
	

}
