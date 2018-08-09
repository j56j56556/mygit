package jiawei;

import java.net.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import java.util.Scanner;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TestClient {
	public static void main(String[] args) {          
		 Window1 mainwd=new Window1();
		 mainwd.setwindows(mainwd);
		
	}

}
class Client extends Thread
{	String judge;
	String testClient;
	Test2 she;
	int reciveint,reciveint2;
	String hostname="";
	Window1 windowall;
	Socket socket;
	Socket socket1;
	ServerSocket server ;								//������������
	ServerSocket server1 ;								//������������
	DataInputStream in,in1;
	DataOutputStream out,out1;
	Client(String j1)
	{
		judge=j1;
	}
	public void setall(Window1 w2)
	{
		this.windowall=w2;
	}
	public void run()
	{
		try {
			begin();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}
	}
	public void begin()throws IOException 
	{	
		String ipserver=windowall.iptext.getText();
		String port=windowall.porttext.getText();
		socket = new Socket(ipserver,Integer.parseInt(port));      //���Ϸ�������				
		try {
			in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			out = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			System.out.println("*******��ʼ�����������*******");
				if(judge.equals("����"))
				{windowall.lbhost.setText("nick��");
				 server= new ServerSocket(5454);
				 socket1=server.accept();
				 in1 = new DataInputStream(new BufferedInputStream(socket1.getInputStream()));
				 out1 = new DataOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				 testClient = in1.readUTF();    //	��ȡ����Ŀ��˵�����
				 windowall.lbguest.setText(testClient);
				 out1.writeUTF("nick��");
				 out1.flush();
				 out1.writeUTF("����ɹ�");
				 out1.flush();
				 windowall.button2.addActionListener(new ActionListener() {  
			            public void actionPerformed(ActionEvent e) 
			            {  String str = windowall.textField.getText();
							try {
								out1.writeUTF("chatsignal");
								out1.flush();
								out1.writeUTF(str);
								out1.flush();
								out1.writeInt(0);
								out1.flush();
								windowall.jtp.setText(windowall.jtp.getText()+windowall.lbhost.getText()+"��" +str+"\n");
								windowall.textField.setText("");
							} catch (IOException e1) {
								e1.printStackTrace();
							}											
			            }  
			        });
				 windowall.Readybutton1.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							try {
								out1.writeUTF("readysignal");
								out1.flush();
								if(windowall.Readybutton1.isSelected()==true)
								{
								out1.writeInt(1);
								out1.flush();
								}
								else
								{
								out1.writeInt(0);
								out1.flush();	
								}
								out1.writeInt(0);
								out1.flush();						
							} catch (IOException e1) {
								e1.printStackTrace();
							}	
						}						
					});
				 windowall.buttonstart.addActionListener(new ActionListener() {  
				        public void actionPerformed(ActionEvent e) 
				        { 
				        	if(!(windowall.Readybutton1.isSelected()&&windowall.Readybutton2.isSelected()))
				        	JOptionPane.showMessageDialog(null, "�����δ׼��!", "����", JOptionPane.ERROR_MESSAGE); 
				        	else
				        	{
				        		Timer timer = new Timer(); 
				        		she=new Test2(socket1,"p1");
				        		try {
									out1.writeUTF("startgame");
									out1.flush();
								} catch (IOException e2) {
									e2.printStackTrace();
								}
								
				        		 try {
				        				Thread.sleep(500);
				        			} catch (InterruptedException e1) {
				        				
				        				e1.printStackTrace();
				        			}
				        		 
				        	    timer.schedule(she, 0, 500);  
				        	}
				        	
							try {
								out1.writeInt(0);
								out1.flush();
							} catch (IOException e1) {
								// TODO �Զ����ɵ� catch ��
								e1.printStackTrace();
							}
				        }
				        }
					);		
				 do{
					 testClient = in1.readUTF();
					switch(testClient)
						{
						case "chatsignal":
						testClient = in1.readUTF();
						windowall.jtp.setText(windowall.jtp.getText()+windowall.lbguest.getText()+"��" + testClient.toString()+"\n");							 
						break;
						case "readysignal":						
							reciveint = in1.readInt();	
							if(reciveint==1)
							windowall.Readybutton2.setSelected(true);
							else
							windowall.Readybutton2.setSelected(false);
							break;
							
						case "playermove":	
							reciveint = in1.readInt();	
							she.headdirect2=reciveint;
							System.out.println("�յ�"+reciveint);
							break;
						case "flushfood" :
							reciveint = in1.readInt();
							reciveint2= in1.readInt();
							she.labellist[reciveint][reciveint2]=she.productfood(reciveint, reciveint2);
							break;
							
						}
				 }while(in1.readInt()!=1);
				 socket1.close();
				}
				else if(judge.equals("����"))
				{socket1 = new Socket(ipserver,5454);
				in1 = new DataInputStream(new BufferedInputStream(socket1.getInputStream()));
				 out1 = new DataOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				 out1.writeUTF("guests");           //�������ִ���
				 out1.flush();
				 String testClient = in1.readUTF();  //��ȡ��������������
				 windowall.lbhost.setText(testClient);
				String stringrecieve=in1.readUTF();  //��ȡ������ɹ���
				System.out.println(stringrecieve);				
				windowall.button2.addActionListener(new ActionListener() {  
		            public void actionPerformed(ActionEvent e) 
		            {  String str = windowall.textField.getText();
						try {
							out1.writeUTF("chatsignal");
							out1.flush();
							out1.writeUTF(str);
							out1.flush();
							out1.writeInt(0);
							out1.flush();
							windowall.jtp.setText(windowall.jtp.getText()+windowall.lbguest.getText()+"��" +str+"\n");
							windowall.textField.setText("");
						} catch (IOException e1) {
							e1.printStackTrace();
						}											
		            }  
		        });	
				windowall.Readybutton2.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						try {
							out1.writeUTF("readysignal");
							out1.flush();
							if(windowall.Readybutton2.isSelected()==true)
							{
							out1.writeInt(1);
							out1.flush();
							}
							else
							{
							out1.writeInt(0);
							out1.flush();	
							}
							out1.writeInt(0);
							out1.flush();						
						} catch (IOException e1) {
							e1.printStackTrace();
						}	
					}						
				});
				do{ 
					testClient = in1.readUTF();
					System.out.println("�յ���Ϣ��"+testClient);
					switch(testClient)
					{case "chatsignal":
						testClient = in1.readUTF();
						windowall.jtp.setText(windowall.jtp.getText()+windowall.lbhost.getText()+"��" + testClient.toString()+"\n");							 
						break;
					case "readysignal":
						reciveint = in1.readInt();	
						if(reciveint==1)
						windowall.Readybutton1.setSelected(true);
						else
						windowall.Readybutton1.setSelected(false);
						break;
					case "startgame":
						Timer timer = new Timer(); 
		        		she=new Test2(socket1,"p2");
		        		 try {Thread.sleep(500);
		        			} catch (InterruptedException e1) {		        				
		        				e1.printStackTrace();
		        			}		        		 
		        	    timer.schedule(she, 0, 500);
		        		break;
					case "playermove":	
						reciveint = in1.readInt();	
						she.headdirect=reciveint;						
						break;
					case "flushfood" :
						reciveint = in1.readInt();
						reciveint2= in1.readInt();
						she.labellist[reciveint][reciveint2]=she.productfood(reciveint, reciveint2);
						break;
					}									
				 }while(in1.readInt()!=1);
				System.out.println("���ж���");
				}
				judge="0";
		}finally {
			socket.close();
		}
	}
	
	
}


