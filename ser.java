//Receiving value from cli


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
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

public class ser extends JFrame {
	
	private String msg;
	private JLabel receivedMessage;
	private JTextField message;
	private static String port;
	private static String name;
	private JTextArea sendMessage;
	private JButton SEND;
	
	private Border blackline=BorderFactory.createLineBorder(Color.BLACK);
	
	ServerSocket s1= new ServerSocket(Integer.parseInt(port));
	Socket ss = s1.accept();			//accept the incoming request from client
	Scanner sc = new Scanner(ss.getInputStream());		//accepts data from client
	PrintStream p = new PrintStream(ss.getOutputStream()); 		//pass the num to server through PrintStream

	public ser() throws IOException {
		super("Server side application");
		setLayout(null);	
		setResizable(false);
		
		Font f=new Font("Serif",Font.BOLD,14);
		
		
		receivedMessage = new JLabel("message recieved is :");
		receivedMessage.setFont(f);
		receivedMessage.setBounds(5, 75,150,40);
		receivedMessage.setVisible(true);
		receivedMessage.setBackground(Color.WHITE);
		add(receivedMessage);
		receivedMessage.revalidate();
		
		
		message=new JTextField(20);
		message.setEditable(false);
		message.setFont(f);
		message.setBounds(5,110,300,40);
		add(message);
		
		
		sendMessage = new JTextArea("type here");
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
	private class HandlerClass implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(sendMessage.getText().length() > 0)
				p.println(" "+name+": "+sendMessage.getText());
			sendMessage.setText("");
		}
			
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

	
	public static void main(String[] args) throws Exception {
		name=JOptionPane.showInputDialog("enter you name");
		port=JOptionPane.showInputDialog("Enter Listening port number");
		JOptionPane.showMessageDialog(null, "waiting for client connection" );
		ser obj = new ser();
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		obj.setSize(450,350);
		obj.setLocation(1000, 50);

		obj.setVisible(true);
		
		while(true) {
			try {
				obj.msg=obj.sc.nextLine();
				obj.message.setText(obj.msg);
				obj.message.setBackground(Color.CYAN);
				TimeUnit.SECONDS.sleep(2);				
			}catch(Exception e) {
				obj.message.setBackground(Color.CYAN);
				obj.message.setText("Client Disconnected");
				obj.SEND.setEnabled(false);
			}
		}
	
	}
}
