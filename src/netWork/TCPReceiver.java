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
