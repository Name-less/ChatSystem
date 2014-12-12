package netWork;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
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
		}

		
	}

}
