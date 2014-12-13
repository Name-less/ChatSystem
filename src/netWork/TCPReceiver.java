package netWork;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class TCPReceiver implements Runnable {

	NetWorkInterface ni;
	ServerSocket mySocket;
	boolean keepGoing = true;
	
	/**
	 * Create a socket which listen a specified port in order to
	 * receive a file
	 * 
	 * @param ni link to the netWorkInterface in order to communicate the file receive to him
	 * 
	 * @see NetWorkInterface
	 */
	
	public TCPReceiver(NetWorkInterface ni){
		this.ni = ni;
		try {
			mySocket = new ServerSocket(NetWorkInterface.portReceiver);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * create a new thread every time we receive a file
	 */
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(keepGoing){
			try {
				Thread newThreadSocket = new Thread(new newThreadWaitingForFile(mySocket.accept(),"string"));
				newThreadSocket.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	public void disconnect(){
		this.keepGoing = false;
	}

	/**
	 * Class used to receive the file in an other thread
	 * and to store it on a new file
	 * before closing the connexion and killing the thread
	 * 
	 * @author julescantegril
	 *
	 */
	public class newThreadWaitingForFile implements Runnable{

	    InputStream is = null;
	    FileOutputStream fos = null;
	    BufferedOutputStream bos = null;
	    Socket mySocket;
	    String fileName;
		
	    public newThreadWaitingForFile(Socket mySocket,String fileName){
	    	this.mySocket = mySocket;
	    	this.fileName = fileName;
	    }
	    
		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			/*
			 * Receive our file
			 */
			
			try {
				is = mySocket.getInputStream();
		        fos = new FileOutputStream(fileName);
		        bos = new BufferedOutputStream(fos);
		        byte[] bytes = new byte[mySocket.getReceiveBufferSize()];
		        int count = 0;

		        while ((count = is.read(bytes)) > 0) {
		        	System.out.println("nem file");
		            bos.write(bytes, 0, count);
		        }
		        
		        bos.flush();
		        bos.close();
		        is.close();
		        mySocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			
			
		}
		
	}

	
}
