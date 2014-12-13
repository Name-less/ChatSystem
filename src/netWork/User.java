package netWork;

import java.net.InetAddress;

public class User {

	private String name;
	private InetAddress ip;
	
	/**
	 * 
	 * A user is caracterized by his nick name and his address IP
	 * 
	 * @param name the nick name of the user
	 * @param ip his IP address
	 */
	public User(String name, InetAddress ip){
		this.name = name;
		this.ip = ip;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}
	
	
	/**
	 * A user is equal to one other if they have the same nick name AND if they have the same IP address
	 * 
	 * @param b the other user to compare this one
	 * @return true if they are equals
	 * @return false else
	 */
	public boolean compareTo(User b){
		if(this.name.equals(b.getName()) && this.ip.toString().equals(b.getIp().toString())){
			return true;
		}else{
			return false;
		}
	}
	
	
}
