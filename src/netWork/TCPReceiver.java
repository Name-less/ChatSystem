package netWork;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
				Thread newThreadSocket = new Thread(new newThreadWaitingForFile(mySocket.accept()));
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
		
	    public newThreadWaitingForFile(Socket mySocket){
	    	this.mySocket = mySocket;
	    }
	    
		@Override
		public void run() {
			// TODO Auto-generated method stub
			/*
			 * Receive our file
			 */
			try{
			InputStream in = mySocket.getInputStream();
            DataInputStream clientData = new DataInputStream(in);

            String nameFile = clientData.readUTF();
            long size = clientData.readLong();
             
            OutputStream output = new FileOutputStream(nameFile);
            
            int bytesRead;
            byte[] buffer = new byte[1024];
            while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                output.write(buffer, 0, bytesRead);
                size -= bytesRead;
            }	
			}catch(IOException e){
				
			}					
		}
		
	}

	
}
