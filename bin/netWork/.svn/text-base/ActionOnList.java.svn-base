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
	
	
	public void addUserToList(String name, InetAddress ip){
		User userToAdd = new User(name,ip);
		if(!this.exist(userToAdd)){
			this.userList.add(userToAdd);//peut être vérifier qu'il n'existe pas déja car si il existe deja ca ne vas pas etre possible, à ca non non non
			controller.majUserList(this.userList);
		}
	}
	
	public void removeUserToList(String name){
		if(this.userList.remove(name)){
			controller.majUserList(this.userList);
		}
	}
	
	public void addUserToListGroup(int whichUser){
		User userToAdd = userList.get(whichUser);
		this.userGroupToSend.add(userToAdd);//peut être vérifier qu'il n'existe pas déja car si il existe deja ca ne vas pas etre possible, à ca non non non
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
	
	public String getNameFromIp(InetAddress address){
		for(int i = 0;i<this.userList.size();i++){
			if(userList.get(i).getIp().equals(address)){
				return userList.get(i).getName();
			}
		}
		return null;
	}

	public InetAddress getIpFromName(String name){
		for(int i = 0;i<this.userList.size();i++){
			if(userList.get(i).getName().equals(name)){
				return userList.get(i).getIp();
			}
		}
		return null;
	}
	
	

}
