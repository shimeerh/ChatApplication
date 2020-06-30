//client program to send values to server


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class cli extends JFrame{
	private String msg;
	private JButton SEND;
	private JTextArea sendMessage;
	private static String port;
	private static String ip;
	private static String name;
	private JLabel recivedMessage;
	private JTextField recMessage;
	
	private Border blackline=BorderFactory.createLineBorder(Color.BLACK);
	
	Socket s=new Socket(ip,Integer.parseInt(port));	//local host and port number
	PrintStream p = new PrintStream(s.getOutputStream()); 		//pass the num to server through PrintStream
	Scanner sc = new Scanner(s.getInputStream());		//accepts data from client

	public cli() throws UnknownHostException, IOException {
		super("client side application");
		setLayout(null);
		
		Font f=new Font("Serif",Font.BOLD,14);

		
		recivedMessage = new JLabel("message recieved is :");
		recivedMessage.setFont(f);
		recivedMessage.setBounds(5, 75,150,40);
		recivedMessage.setVisible(true);
		recivedMessage.setBackground(Color.WHITE);
		add(recivedMessage);
		
		recMessage=new JTextField(20);
		recMessage.setEditable(false);
		recMessage.setFont(f);
		recMessage.setBounds(5,110,300,40);
		add(recMessage);
		
		sendMessage = new JTextArea("Enter your message here");
		sendMessage.setEditable(true);
		sendMessage.setBounds(5,200,300,80);
		sendMessage.setBorder(blackline);
		add(sendMessage);		
		
		SEND=new JButton("SEND");
		SEND.setBounds(320,200,80,50);
		add(SEND);
		
		HandlerClass handler = new HandlerClass();
		SEND.addActionListener(handler);
		
		HandlerClass1 handler1 = new HandlerClass1(); 
		sendMessage.addMouseListener(handler1);
		
	}
	
	private class HandlerClass1 implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			sendMessage.setText("");
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}
	
	private class HandlerClass implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(sendMessage.getText().length() > 0 )
				p.println(" "+name+": "+sendMessage.getText());
			sendMessage.setText("");
			
		}
		
	}
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		name=JOptionPane.showInputDialog("enter you name");
		ip=JOptionPane.showInputDialog("Enter IP address");
		port = JOptionPane.showInputDialog("Enter port number");
		cli obj=new cli();
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setSize(450,350);
		obj.setLocation(100, 50);
		obj.setVisible(true);
		while(true) {
			try {
					obj.msg=obj.sc.nextLine();
					obj.recMessage.setText(obj.msg);
					obj.recMessage.setBackground(Color.CYAN);
					TimeUnit.SECONDS.sleep(2);
			}catch(Exception e) {
				obj.recMessage.setText("Server Disconnected");
				obj.recMessage.setBackground(Color.CYAN);
				obj.SEND.setEnabled(false);

			}
		}
	}
}