package graphicalUserInterface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

import netWork.NetWorkInterface;

import org.json.JSONException;

import controller.Controller;

public class GUI_Not_Connected {
	private static JTextField list;
	private static JTextField textField;

   public static void main(String[] args) {

	   JCheckBox[] checkBoxArray = new JCheckBox[10];
		JFrame fConnected = new JFrame("A JFrame");
		fConnected.setSize(725, 734);
		fConnected.setLocation(300,200);
		fConnected.getContentPane().setLayout(null);
     
		
		DefaultListModel    messageHistorique = new  DefaultListModel();
		JList listHistorique = new JList(messageHistorique);
        listHistorique.setLayoutOrientation(JList.VERTICAL);
        listHistorique.setVisibleRowCount(15);

       /* JScrollPane  historique = new JScrollPane();
	     historique.setFocusable(false);
	     historique.setBounds(506, 5, 213, 260);
        historique.setColumnHeaderView(listHistorique);

        messageHistorique.add(0, "Historique des messages recues");
        
	     fConnected.getContentPane().add(historique);*/
	     		     
	     JButton btnSend = new JButton("Send");
	     btnSend.setBounds(10, 277, 181, 29);
	     fConnected.getContentPane().add(btnSend);
     
	    JTextField  messageToSend = new JTextField();
      //  messageToSend.setForeground(couleur);

        messageToSend.setBounds(203, 276, 291, 28);
        fConnected.getContentPane().add(messageToSend);
        messageToSend.setColumns(10);

     
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(203, 17, 291, 247);
     
        DefaultListModel message = new  DefaultListModel();
        JList listMessage = new JList(message);
        listMessage.setLayoutOrientation(JList.VERTICAL);
        listMessage.setVisibleRowCount(15);

     	scrollPane.setColumnHeaderView(listMessage);
     	fConnected.getContentPane().add(scrollPane);

     	JButton btnDeconnect = new JButton("Deconnect");
       btnDeconnect.setBounds(10, 248, 181, 29);
       fConnected.getContentPane().add(btnDeconnect);
       fConnected.setVisible(true);
     
       JCheckBox  checkBox = new JCheckBox("New check box");
     	checkBox.setBounds(10, 1, 181, 23);
     	fConnected.getContentPane().add(checkBox);
     	
     	JCheckBox 	checkBox_1 = new JCheckBox("New check box");
     	checkBox_1.setBounds(10, 30, 181, 23);
     	fConnected.getContentPane().add(checkBox_1);
     
     	JCheckBox 	checkBox_2 = new JCheckBox("New check box");
     	checkBox_2.setBounds(10, 57, 181, 23);
     	fConnected.getContentPane().add(checkBox_2);
     
     	JCheckBox 	checkBox_3 = new JCheckBox("New check box");
     	checkBox_3.setBounds(10, 82, 181, 23);
     	fConnected.getContentPane().add(checkBox_3);
     
     	JCheckBox	checkBox_4 = new JCheckBox("New check box");
     	checkBox_4.setBounds(10, 106, 181, 23);
     	fConnected.getContentPane().add(checkBox_4);
     
     	JCheckBox	checkBox_5 = new JCheckBox("New check box");
     	checkBox_5.setBounds(10, 128, 181, 23);
     	fConnected.getContentPane().add(checkBox_5);
     
     	JCheckBox	checkBox_6 = new JCheckBox("New check box");
     	checkBox_6.setBounds(10, 152, 181, 23);
     	fConnected.getContentPane().add(checkBox_6);
     
     	JCheckBox	checkBox_7 = new JCheckBox("New check box");
     	checkBox_7.setBounds(10, 177, 181, 23);
     	fConnected.getContentPane().add(checkBox_7);
     
     	JCheckBox	checkBox_8 = new JCheckBox("New check box");
     	checkBox_8.setBounds(10, 202, 181, 23);
     	fConnected.getContentPane().add(checkBox_8);
     
     	JCheckBox	checkBox_9 = new JCheckBox("New check box");
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
     
     JButton btnNewButton = new JButton("Send file");
     btnNewButton.addActionListener(new ActionListener() {
     	public void actionPerformed(ActionEvent arg0) {
     	}
     });
     btnNewButton.setBounds(510, 358, 146, 21);
     fConnected.getContentPane().add(btnNewButton);
     
     textField = new JTextField();
     textField.setBounds(449, 391, 251, 50);
     fConnected.getContentPane().add(textField);
     textField.setColumns(10);
     
     fConnected.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   
   	}
}