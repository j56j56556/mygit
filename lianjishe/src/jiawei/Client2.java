package jiawei;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

class Client2 extends Thread
{	String judge;
	JTextPane jtp;
	String hostname="";
	JLabel lbhost,lbguest;
	JPanel contentPane;
	
	Socket socket;
	Socket socket1;
	ServerSocket server ;								//房主主机服务
	ServerSocket server1 ;								//客人主机服务
	
	Client2(String j1)
	{
		judge=j1;
	}
	public void setall(JTextPane jtp,JLabel l1,JLabel l2,JPanel c1)
	{
		this.jtp=jtp;
		this.lbhost=l1;
		this.lbguest=l2;
		this.contentPane=c1;
	}
	public void run()
	{
		try {
			begin();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public void begin()throws IOException 
	{
		socket = new Socket("localhost",8080);      //连上服务器端		
		Scanner sc = new Scanner(System.in);
		String str1;
		try {
			DataInputStream in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			System.out.println("*******开始与服务器聊天*******");
			boolean stop = false;
			while(! stop) {
				if(judge.equals("创建"))
				{lbhost.setText("nick杨");
				 server= new ServerSocket(5454);
				 socket1=server.accept();
				 DataInputStream in1 = new DataInputStream(new BufferedInputStream(socket1.getInputStream()));
				 DataOutputStream out1 = new DataOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				 String testClient = in1.readUTF();    //	读取加入的客人的名字
				 lbguest.setText(testClient);
				 out1.writeUTF("nick杨");
				 out1.flush();
				 out1.writeUTF("加入成功");
				 out1.flush();
				 do{
					  testClient = in1.readUTF();
					jtp.setText(jtp.getText()+"客户端：" + testClient.toString()+"\n");		
					 
				 }
				 while(in1.readInt()!=1);
				 socket1.close();
				}
				else if(judge.equals("连接"))
				{socket1 = new Socket("localhost",5454);
				boolean stop1 = false;
				DataInputStream in1 = new DataInputStream(new BufferedInputStream(socket1.getInputStream()));
				 DataOutputStream out1 = new DataOutputStream(new BufferedOutputStream(socket1.getOutputStream()));
				 out1.writeUTF("guests");           //客人名字传输
				 out1.flush();
				 String testClient = in1.readUTF();  //读取联机机主的名字
				 lbhost.setText(testClient);
				String stringrecieve=in1.readUTF();  //读取“加入成功”
				System.out.println(stringrecieve);
				
					while(stop1==false)
					{String str = sc.next();
					 out1.writeUTF(str);
					 out1.flush();
					 out1.writeInt(0);
					 out1.flush();						
					}
				socket1.close();
				}
				String str = sc.next();
				out.writeUTF(str);
				out.flush();
				out.writeInt(1);
				out.flush();
				judge="0";
				stop=true;
			}
		}finally {
			socket.close();
		}
	}
	
	
}


