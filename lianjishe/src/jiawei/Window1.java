package jiawei;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class Window1 {
	JFrame frame;
	JPanel contentPane;
	JTextPane jtp;
	JButton button;
	JButton button1;
	JButton button2;   //发送
	JLabel lbhost,lbguest,label1;
	Client client;
	JTextField textField,iptext,porttext;
	Window1 windowall;
	JRadioButton Readybutton1,Readybutton2;
	JButton buttonstart;
	Window1()
	{	   
		   frame=new JFrame();
		   frame.setTitle("房间");
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   frame.setBounds(100, 100, 600, 600);
		   contentPane=new JPanel();
		   contentPane.setLayout(null);
		   button = new JButton();
		   button.setBounds(250, 150, 100, 40);
		   button.setText("创建房间");
		   button.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) 
	            {   try {
	            	
	            	hosthouse();
	            	client = new Client("创建");
	            	client.setall(windowall);
					client.start();	
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	            }  
	        });
		   button1= new JButton();
		   button1.setBounds(250, 200, 100, 40);
		   button1.setText("连接房间");
		   button1.addActionListener(new ActionListener() {  
	            public void actionPerformed(ActionEvent e) 
	            {   try {
	            	guesthouse();
	            	client = new Client("连接");
	            	client.setall(windowall);
					client.start();	
				
					
				} catch (Exception e1) {
					e1.printStackTrace();
				}
	            }  
	        });
		   label1=new JLabel();
		   label1.setBounds(55,250,160,20);
		   label1.setText("服务器IP地址以及端口号:");
		   iptext=new JTextField();
		   iptext.setBounds(225, 250, 150, 20);
		   iptext.setText("192.168.10.107");
		   porttext=new JTextField();
		   porttext.setBounds(395, 250, 80, 20);
		   porttext.setText("5455");
		   contentPane.add(button);
		   contentPane.add(button1);
		   contentPane.add(iptext);
		   contentPane.add(porttext);
		   contentPane.add(label1);
		   frame.add(contentPane);
	       frame.setVisible(true);
	       
		}
	public void setwindows(Window1 w1)
	{
		this.windowall=w1;
			
	}
	public void hosthouse()
	{	lbhost = new JLabel();		
	lbhost.setBounds(100, 130, 200, 30);
	lbhost.setOpaque(true);
	lbhost.setBackground(Color.GREEN);
	lbhost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	lbguest = new JLabel();
	lbguest.setText("等待连接中。。。");
	lbguest.setBounds(100, 162, 200, 30);
	lbguest.setOpaque(true);
	lbguest.setBackground(Color.RED);
	lbguest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	contentPane.remove(button);
	contentPane.remove(button1);
	contentPane.remove(label1);
	contentPane.remove(iptext);
	contentPane.remove(porttext);
	contentPane.add(lbhost);
	contentPane.add(lbguest);
	jtp = new JTextPane();
	jtp.setBounds(100,200,400,155);
	contentPane.add(jtp);				
	
	textField = new JTextField();
	textField.setText("\u8BF7\u8F93\u5165\u8981\u8BF4\u7684\u8BDD");
	textField.setBounds(100, 358, 309, 21);
	contentPane.add(textField);
	textField.setColumns(10);
	contentPane.updateUI();
	
	button2 = new JButton("发送");
	button2.setBounds(407, 357, 93, 23);
	contentPane.add(button2);
	
	Readybutton1 = new JRadioButton("\u51C6\u5907");
	Readybutton1.setBounds(317, 137, 60, 23);
	contentPane.add(Readybutton1);
	
	Readybutton2 = new JRadioButton("\u51C6\u5907");
	Readybutton2.setEnabled(false);
	Readybutton2.setHorizontalAlignment(SwingConstants.LEFT);
	Readybutton2.setBounds(317, 166, 60, 23);
	contentPane.add(Readybutton2);
	
	buttonstart = new JButton("\u5F00\u59CB\u6E38\u620F");
	buttonstart.setBounds(407, 130, 93, 23);
	contentPane.add(buttonstart);
	
	
		
	}
	public void guesthouse()
	{	lbhost = new JLabel();		
	lbhost.setBounds(100, 130, 200, 30);
	lbhost.setOpaque(true);
	lbhost.setBackground(Color.GREEN);
	lbhost.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	lbguest = new JLabel();
	lbguest.setText("guests");
	lbguest.setBounds(100, 162, 200, 30);
	lbguest.setOpaque(true);
	lbguest.setBackground(Color.RED);
	lbguest.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
	contentPane.remove(button);
	contentPane.remove(button1);
	contentPane.remove(label1);
	contentPane.remove(iptext);
	contentPane.remove(porttext);
	contentPane.add(lbhost);
	contentPane.add(lbguest);
	jtp = new JTextPane();
	jtp.setBounds(100,200,400,155);
	contentPane.add(jtp);				
	
	textField = new JTextField();
	textField.setText("\u8BF7\u8F93\u5165\u8981\u8BF4\u7684\u8BDD");
	textField.setBounds(100, 358, 309, 21);
	contentPane.add(textField);
	textField.setColumns(10);
	contentPane.updateUI();
	
	button2 = new JButton("发送");
	button2.setBounds(407, 357, 93, 23);
	
	Readybutton1 = new JRadioButton("\u51C6\u5907");
	Readybutton1.setEnabled(false);
	Readybutton1.setBounds(317, 137, 60, 23);
	contentPane.add(Readybutton1);
	
	Readybutton2 = new JRadioButton("\u51C6\u5907");
	Readybutton2.setHorizontalAlignment(SwingConstants.LEFT);
	Readybutton2.setBounds(317, 166, 60, 23);
	contentPane.add(Readybutton2);
	
	contentPane.add(button2);
	
	}
}

