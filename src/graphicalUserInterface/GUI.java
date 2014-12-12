package graphicalUserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.json.JSONException;

import netWork.NetWorkInterface;
import netWork.User;
import controller.Controller;


public class GUI implements AbstractGui {
	protected static JTextField messageToSend;
	protected static DefaultListModel message;
	protected static DefaultListModel messageHistorique;
	protected static JList listHistorique;
	protected static JList listMessage;
	protected static JScrollPane historique;
	protected static JTextArea textNotCo;
	protected static JButton btnSend;
	protected static JButton btnDeconnect;
	protected static JButton connect;
	protected JFrame fConnected;
	private static boolean connectedOnce = false;
	private Controller controller;
		
	private Color couleur;
	
	JCheckBox checkBox,checkBox_1,checkBox_2,checkBox_3,checkBox_4,checkBox_5,checkBox_6,checkBox_7,checkBox_8,checkBox_9;
	JCheckBox[] checkBoxArray;
	public void initComponentConnected(String pseudo){
			checkBoxArray = new JCheckBox[10];
			fConnected = new JFrame("A JFrame");
			fConnected.setSize(725, 650);
			fConnected.setLocation(300,200);
			fConnected.getContentPane().setLayout(null);
	      
			
	         messageHistorique = new  DefaultListModel();
	         listHistorique = new JList(messageHistorique);
	         listHistorique.setLayoutOrientation(JList.VERTICAL);
	         listHistorique.setVisibleRowCount(15);
	
		     historique = new JScrollPane();
		     historique.setFocusable(false);
		     historique.setBounds(506, 5, 213, 260);
	         historique.setColumnHeaderView(listHistorique);

	         messageHistorique.add(0, "Historique des messages recues");
	         
		     fConnected.getContentPane().add(historique);
		     		     
		     btnSend = new JButton("Send");
		     btnSend.setBounds(10, 277, 181, 29);
		     fConnected.getContentPane().add(btnSend);
	      
	         messageToSend = new JTextField();
	         messageToSend.setForeground(couleur);

	         messageToSend.setBounds(203, 276, 291, 28);
	         fConnected.getContentPane().add(messageToSend);
	         messageToSend.setColumns(10);

	      
	         JScrollPane scrollPane = new JScrollPane();
	         scrollPane.setBounds(203, 17, 291, 247);
	      
	         message = new  DefaultListModel();
	         listMessage = new JList(message);
	         listMessage.setLayoutOrientation(JList.VERTICAL);
	         listMessage.setVisibleRowCount(15);

	      	scrollPane.setColumnHeaderView(listMessage);
	      	fConnected.getContentPane().add(scrollPane);
   
	        btnDeconnect = new JButton("Deconnect");
	        btnDeconnect.setBounds(10, 248, 181, 29);
	        fConnected.getContentPane().add(btnDeconnect);
	        fConnected.setVisible(true);
	      
	        checkBox = new JCheckBox("New check box");
	      	checkBox.setBounds(10, 1, 181, 23);
	      	fConnected.getContentPane().add(checkBox);
	      	
	      	checkBox_1 = new JCheckBox("New check box");
	      	checkBox_1.setBounds(10, 30, 181, 23);
	      	fConnected.getContentPane().add(checkBox_1);
	      
	      	checkBox_2 = new JCheckBox("New check box");
	      	checkBox_2.setBounds(10, 57, 181, 23);
	      	fConnected.getContentPane().add(checkBox_2);
	      
	      	checkBox_3 = new JCheckBox("New check box");
	      	checkBox_3.setBounds(10, 82, 181, 23);
	      	fConnected.getContentPane().add(checkBox_3);
	      
	      	checkBox_4 = new JCheckBox("New check box");
	      	checkBox_4.setBounds(10, 106, 181, 23);
	      	fConnected.getContentPane().add(checkBox_4);
	      
	      	checkBox_5 = new JCheckBox("New check box");
	      	checkBox_5.setBounds(10, 128, 181, 23);
	      	fConnected.getContentPane().add(checkBox_5);
	      
	      	checkBox_6 = new JCheckBox("New check box");
	      	checkBox_6.setBounds(10, 152, 181, 23);
	      	fConnected.getContentPane().add(checkBox_6);
	      
	      	checkBox_7 = new JCheckBox("New check box");
	      	checkBox_7.setBounds(10, 177, 181, 23);
	      	fConnected.getContentPane().add(checkBox_7);
	      
	      	checkBox_8 = new JCheckBox("New check box");
	      	checkBox_8.setBounds(10, 202, 181, 23);
	      	fConnected.getContentPane().add(checkBox_8);
	      
	      	checkBox_9 = new JCheckBox("New check box");
	      	checkBox_9.setBounds(10, 225, 181, 23);
	      	fConnected.getContentPane().add(checkBox_9);
	      
	      checkBoxArray[0] = checkBox;
	      checkBoxArray[1] = checkBox_1;
	      checkBoxArray[2] = checkBox_2;
	      checkBoxArray[3] = checkBox_3;
	      checkBoxArray[4] = checkBox_4;
	      checkBoxArray[5] = checkBox_5;
	      checkBoxArray[6] = checkBox_6;
	      checkBoxArray[7] = checkBox_7;
	      checkBoxArray[8] = checkBox_8;
	      checkBoxArray[9] = checkBox_9;

	      for(int i = 0;i<checkBoxArray.length;i++){
	    	  final int y = i;
	    	  checkBoxArray[y].addItemListener(new ItemListener(){

				@Override
				public void itemStateChanged(ItemEvent arg0) {
					// TODO Auto-generated method stub
		            if (arg0.getStateChange() == ItemEvent.DESELECTED){
		            	checkBoxArray[y].setSelected(false);
		            	controller.getNI().getActionOnList().removeUserToListGroup(y);
		            }else{
		            	checkBoxArray[y].setSelected(true);
		            	controller.getNI().getActionOnList().addUserToListGroup(y);
		            }
				}
	    		  
	    	  });
     
	      }
	      
	      btnSend.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					for(int i = 0;i<controller.getNI().getActionOnList().getUserGroup().size();i++){
						controller.getNI().sendMessage(controller.getNI().getFactory().createMessage(Controller.message, messageToSend.getText(), controller.getNI().getActionOnList().getUserGroup().get(i).getIp(), NetWorkInterface.numeroMessageSend, NetWorkInterface.portReceiver));
					}
					messageToSend.setText("");
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	  
	      });
		
	      btnDeconnect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					fConnected.setVisible(false);
					initComponentNotConnected();
					controller.getNI().getActionOnList().reInitList();
					controller.getNI().sendMessage(controller.getNI().getFactory().createMessage(Controller.disconnect, "", null, 0, NetWorkInterface.portReceiver));
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    	  
	      });
	     
	      messageToSend.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(arg0.getKeyChar() == 10){
					try {
						for(int i = 0;i<controller.getNI().getActionOnList().getUserGroup().size();i++){
							controller.getNI().sendMessage(controller.getNI().getFactory().createMessage(Controller.message, messageToSend.getText(), controller.getNI().getActionOnList().getUserGroup().get(i).getIp(), NetWorkInterface.numeroMessageSend, NetWorkInterface.portReceiver));
						}
						messageToSend.setText("");
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
	    	  
	      });
	      
	      fConnected.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      
	      final JTextField fileToSend = new JTextField();
	      fileToSend.setBounds(449, 391, 251, 50);
	      fConnected.getContentPane().add(fileToSend);
	      fileToSend.setColumns(10);
	      fileToSend.setFocusable(false);
	      
	      final JFileChooser fileChooser = new JFileChooser();
	      fileChooser.setBounds(0, 340, 400, 260);
	      fConnected.getContentPane().add(fileChooser);
	      
	      JButton btnNewButton = new JButton("Send file to every user selected");
	      btnNewButton.addActionListener(new ActionListener() {
	      	public void actionPerformed(ActionEvent arg0) {
	      		if(!fileToSend.getText().toString().equals("")){
					for(int i = 0;i<controller.getNI().getActionOnList().getUserGroup().size();i++){
						controller.getNI().sendFile(fileToSend.getText().toString(), controller.getNI().getActionOnList().getUserGroup().get(i).getIp());
					}
		      		fileToSend.setText("");
	      		}
	      	}
	      });
	      btnNewButton.setBounds(510, 358, 146, 21);
	      fConnected.getContentPane().add(btnNewButton);
	      

	      	      
	      fileChooser.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {

	        	  if(e.getID() == 1001){
	        		  fileToSend.setText(fileChooser.getSelectedFile().getAbsolutePath()); 
	        	  }

	            }
	          });
	      

	}
	
	public void initComponentNotConnected(){
	      final JFrame f = new JFrame("A JFrame");
	      f.setSize(269, 150);
	      f.setLocation(300,200);
	      f.getContentPane().setLayout(null);
	      
	      connect = new JButton("Connect");
	      connect.setBounds(53, 39, 117, 29);
	      
	      f.getContentPane().add(connect);
	      
	      textNotCo = new JTextArea();
	      textNotCo.setBounds(6, 6, 214, 21);
	      f.getContentPane().add(textNotCo);
	      textNotCo.setColumns(10);
	      
	      JPanel panel = new JPanel();
	      panel.setBounds(16, 68, 10, 10);
	      panel.setBackground(Color.blue);
	      f.getContentPane().add(panel);
	      
	      JPanel panel_1 = new JPanel();
	      panel_1.setBounds(38, 68, 10, 10);
	      f.getContentPane().add(panel_1);
	      panel_1.setBackground(Color.red);

	      JPanel panel_2 = new JPanel();
	      panel_2.setBounds(63, 68, 10, 10);
	      f.getContentPane().add(panel_2);
	      panel_2.setBackground(Color.green);

	      JPanel panel_3 = new JPanel();
	      panel_3.setBounds(84, 68, 10, 10);
	      f.getContentPane().add(panel_3);
	      panel_3.setBackground(Color.yellow);

	      JPanel panel_4 = new JPanel();
	      panel_4.setBounds(107, 68, 10, 10);
	      f.getContentPane().add(panel_4);
	      panel_4.setBackground(Color.BLACK);

	      JPanel panel_5 = new JPanel();
	      panel_5.setBounds(129, 68, 10, 10);
	      f.getContentPane().add(panel_5);
	      panel_5.setBackground(Color.CYAN);

	      JPanel panel_6 = new JPanel();
	      panel_6.setBounds(151, 68, 10, 10);
	      f.getContentPane().add(panel_6);
	      panel_6.setBackground(Color.orange);

	      JPanel panel_7 = new JPanel();
	      panel_7.setBounds(173, 68, 10, 10);
	      f.getContentPane().add(panel_7);
	      panel_7.setBackground(Color.PINK);

	      JPanel panel_8 = new JPanel();
	      panel_8.setBounds(194, 68, 10, 10);
	      f.getContentPane().add(panel_8);
	      f.setVisible(true);
	      panel_8.setBackground(Color.GRAY);

	      JPanel[] panelArray = new JPanel[9];
	      panelArray[0] = panel;
	      panelArray[1] = panel_1;
	      panelArray[2] = panel_2;
	      panelArray[3] = panel_3;
	      panelArray[4] = panel_4;
	      panelArray[5] = panel_5;
	      panelArray[6] = panel_6;
	      panelArray[7] = panel_7;
	      panelArray[8] = panel_8;

	      final Color [] arrayCouleur = new Color[9];
	      
	      arrayCouleur[0] = Color.blue;
	      arrayCouleur[1] = Color.red;
	      arrayCouleur[2] = Color.green;
	      arrayCouleur[3] = Color.yellow;
	      arrayCouleur[4] = Color.black;
	      arrayCouleur[5] = Color.cyan;
	      arrayCouleur[6] = Color.orange;
	      arrayCouleur[7] = Color.pink;
	      arrayCouleur[8] = Color.gray;

	      textNotCo.addKeyListener(new KeyListener(){

				@Override
				public void keyPressed(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void keyReleased(KeyEvent arg0) {
					// TODO Auto-generated method stub
					if(arg0.getKeyChar() == 10){
						try {
							try {
								f.setVisible(false);
								if(!connectedOnce){
								initComponentConnected(textNotCo.getText());
								}else{
									fConnected.setVisible(true);
								}
								NetWorkInterface.myName = textNotCo.getText();
								controller.getNI().sendMessage(controller.getNI().getFactory().createMessage(Controller.connect, textNotCo.getText(), InetAddress.getLocalHost(), 0, NetWorkInterface.portReceiver));
								controller.getNI().sendMessage(controller.getNI().getFactory().createMessage(Controller.connect, textNotCo.getText(), InetAddress.getByName("255.255.255.255"), 0, NetWorkInterface.portReceiver));
							} catch (UnsupportedEncodingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (UnknownHostException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}finally{

						}
					}
				}

				@Override
				public void keyTyped(KeyEvent arg0) {
					// TODO Auto-generated method stub
					
				}
		    	  
		      });
	      
	      for(int i = 0;i<panelArray.length;i++){
	    	  final int y = i;
	    	  panelArray[y].addMouseListener(new MouseListener(){

				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					couleur = arrayCouleur[y];
					textNotCo.setForeground(couleur);
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}
	    		  
	    	  });
	    	  
	      }
	      
	      connect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				try {
					try {
						f.setVisible(false);
						if(!connectedOnce){
						initComponentConnected(textNotCo.getText());
						}else{
							fConnected.setVisible(true);
						}
						NetWorkInterface.myName = textNotCo.getText();
						controller.getNI().sendMessage(controller.getNI().getFactory().createMessage(Controller.connect, textNotCo.getText(), InetAddress.getLocalHost(), 0, NetWorkInterface.portReceiver));
						controller.getNI().sendMessage(controller.getNI().getFactory().createMessage(Controller.connect, textNotCo.getText(), InetAddress.getByName("255.255.255.255"), 0, NetWorkInterface.portReceiver));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{

				}
			}
	    	  
	      });

	      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
   
	public void majUserList(ArrayList<User> userList){
		//this.setUserList(userList);EN GROS
		for(int i = 0;i< userList.size();i++){
			checkBoxArray[i].setText(userList.get(i).getName()+" "+userList.get(i).getIp().toString());
			checkBoxArray[i].setVisible(true);
		}
		for(int i = userList.size();i<checkBoxArray.length;i++){
			checkBoxArray[i].setVisible(false);
		}
	}
	
	public void afficheMessage(String from,String message, int numero){
		this.message.add(0,numero +" - "+from+" : "+message);//setText(this.messages.getText()+"\n"+numero +" - "+from+" : "+message);
	}
	
	public void afficheMessageAck(String from,int numero){
		this.messageHistorique.add(1,"Message n� "+numero +" re�u par "+from);
	}
	
	public void accuseMessage(int numeroMessage){
		
	}
	
	public void setController(Controller c){
		this.controller = c;
	}
	
	
}