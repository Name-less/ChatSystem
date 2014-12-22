package netWork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPSender implements Runnable  {

	Socket mySocket;
	String path;
	
	FileInputStream fis = null;
    BufferedInputStream bis;
    BufferedOutputStream out;
    /**
     * This constructor create a new socket toward the distant host in order to send him the file
     * 
     * @param filePath the path to the file to send
     * @param toSend the remote host to send the file
     * 
     * @see Socket
     * 
     */
	public TCPSender(String filePath,InetAddress toSend){
		this.path = filePath;
		try {
			this.mySocket = new Socket(toSend,NetWorkInterface.portSender);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * Send the file to send and destroye the socket and the thread after that
	 * 
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		   FileInputStream fileInputStream = null;

	        try {
	            File fileToSend = new File(path); // String filePath 
	            byte[] fileBytes = new byte[(int) fileToSend.length()];
	            fileInputStream = new FileInputStream(fileToSend);
	            // getting informations about the file to send 
	            BufferedInputStream bis = new BufferedInputStream(fileInputStream);
	            DataInputStream dis = new DataInputStream(bis);
	            dis.readFully(fileBytes, 0, fileBytes.length);

	            // ready to send through the socket
	            OutputStream socketOutput = mySocket.getOutputStream();

	            // sending file name and file size 
	            DataOutputStream dos = new DataOutputStream(socketOutput);
	            dos.writeUTF(fileToSend.getName());
	            dos.writeLong(fileBytes.length);
	            dos.flush();

	            // sending file data 
	            socketOutput.write(fileBytes, 0, fileBytes.length);
	            socketOutput.flush();
	        }catch(IOException e){
	        	
	        }
		
		
		
		/*try {
			 File file = new File(path);
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			out = new BufferedOutputStream(mySocket.getOutputStream());
			
		    int count;
		    byte[] bytes = new byte[(int) file.length()];
		    while ((count = bis.read(bytes)) > 0) {//on rempli bytes avec notre fichier
		        out.write(bytes, 0, count);//et on l'envoit dans notre socket
		    }
		    
		    out.flush();
		    out.close();
		    fis.close();
		    bis.close();
		    mySocket.close();
		    
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/

		
	}

}
