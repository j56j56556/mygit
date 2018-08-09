package jiawei;
import java.net.*;
import java.awt.Color;
import java.awt.Label;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

public class TestMultiServer {
	public static void main(String[] args) throws IOException {
		Clientdata clientdata=new Clientdata();
		ServerSocket server = new ServerSocket(5455);
		ExecutorService exec = Executors.newCachedThreadPool();  

		try {
			System.out.println("服务器程序启动，开始监听客户的请求。。。");
			while(true) {
				Socket socket = server.accept();
				exec.execute(new SingleServer1(clientdata,socket)) ;
				clientdata.l2.add(socket);
				clientdata.jtp.setText("");
				for(Socket soc1:clientdata.l2)
				{ 
					System.out.println(soc1.getInetAddress());	
					clientdata.jtp.setText(clientdata.jtp.getText()+"客户端IP为：" +soc1.getInetAddress() +"加入\n");		
				}
			}
		} finally {
			server.close();
		}
	}
}

class SingleServer1 implements Runnable {
	private Socket socket;
	private Clientdata clientdata;
	
	public SingleServer1(Clientdata c1,Socket s1) {
		this.socket = s1;
		clientdata=c1;
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		try {
			
			
			DataInputStream in = new DataInputStream(
					new BufferedInputStream(socket.getInputStream()));
			DataOutputStream out = new DataOutputStream(
					new BufferedOutputStream(socket.getOutputStream()));
			do {
				String testClient = in.readUTF();
				clientdata.jtp.setText(clientdata.jtp.getText()+"客户端：" + testClient.toString()+"\n");		
			} while(in.readInt() != 0);
		} catch(IOException e) {
			System.err.println(e);
		} finally {
			System.out.println("与客户" + clientdata.clientNo + "通信结束！");
			
			try {
				socket.close();
			} catch(IOException e) {
				System.out.println("与客户" + clientdata.clientNo + "通信的socket未能正确关闭！");
			}
		}
	}
}
class Clientdata
{
int clientNo = 1;
List<String> l1 = new ArrayList<String>();
List<Socket> l2 = new ArrayList<Socket>();
JFrame frame;
JPanel contentPane;
JTextPane jtp;
	Clientdata()
		{
		   frame=new JFrame();
		   frame.setTitle("房间");
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   frame.setBounds(100, 100, 600, 600);
		   contentPane=new JPanel();
		   contentPane.setLayout(null);                  
		   jtp=new JTextPane();	
		   jtp.setBounds(70,70,400,400);
		   contentPane.add(jtp);
		   frame.add(contentPane);
	       frame.setVisible(true); 
		}
}
