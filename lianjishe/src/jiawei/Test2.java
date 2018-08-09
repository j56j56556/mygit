package jiawei;
import java.awt.Color;
import java.awt.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;  
import javax.swing.JFrame;  
import javax.swing.JLabel;
import javax.swing.JPanel;  

  
public class Test2 extends TimerTask implements KeyListener{
   private JFrame frame;
   private JPanel contentPane;
   private JLabel label;
   JLabel[][] labellist= new JLabel[30][30];			//全局
   
   private int headx=7,heady=8,tailx=4,taily=8;  		//p1
   int headdirect=39,taildirectindex=0;
   private int[] tdirectarray=new int[50];
   private int length=4;
   private int directing=39;
   
   private int headx2=7,heady2=10,tailx2=4,taily2=10;   //p2
   int headdirect2=39,taildirectindex2=0;
   private int[] tdirectarray2=new int[50];
   private int length2=4;
   private int directing2=39;
   
   Socket socket2;
   DataOutputStream out1;
   DataInputStream in1;
   String player="p1";
   public Test2(Socket s1,String p)  
  {	   frame=new JFrame();
	   frame.setTitle(p);
	   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setBounds(100, 100, 600, 600);
	  
	   this.player=p;
	   this.socket2=s1;
	   try {out1 = new DataOutputStream(new BufferedOutputStream(socket2.getOutputStream()));} catch (IOException e) {e.printStackTrace();}
	   if(this.player=="p1")
	   controlevent();
	   else 
	   controlevent2();  	  
	   contentPane=new JPanel();
	   contentPane.setLayout(null); 
	   frame.add(contentPane);	   
	   chushihuawall();
	   chushihua();      
       frame.setVisible(true);
   }  
   public void chushihuawall ()
   {	
	   for(int i=0;i<28;i++)
		   {

		   labellist[0][i]=productwall(0,i);
		   labellist[28][i]=productwall(28,i);
		   }
	   for(int i=0;i<28;i++)
	   {
		   labellist[i][0]=productwall(i,0);
		   labellist[i][27]=productwall(i,27);
	   }
   }
   public void chushihua()
   {
	   labellist[4][8]=product(4,8);    //p1初始化
	   tdirectarray[0]=39;
	   labellist[5][8]=product(5,8);
	   tdirectarray[1]=39;
	   labellist[6][8]=product(6,8);
	   tdirectarray[2]=39;
	   labellist[7][8]=product(7,8);
	   
	   labellist[4][10]=product2(4,10);    //p2初始化
	   tdirectarray2[0]=39;
	   labellist[5][10]=product2(5,10);
	   tdirectarray2[1]=39;
	   labellist[6][10]=product2(6,10);
	   tdirectarray2[2]=39;
	   labellist[7][10]=product2(7,10);
	   	    
	   labellist[11][11]=productfood(11,11);
	   
	  
   }
   public JLabel product(int i,int j)
   {	JLabel l;
	   	l=new JLabel();
		l.setBounds(i*20, j*20, 20, 20);
		l.setOpaque(true);
		l.setBackground(Color.yellow);
		l.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		l.setName("body");
		contentPane.add(l);
		contentPane.updateUI();
		return l;
   }
   public JLabel product2(int i,int j)
   {	JLabel l;
	   	l=new JLabel();
		l.setBounds(i*20, j*20, 20, 20);
		l.setOpaque(true);
		l.setBackground(Color.blue);
		l.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		l.setName("body2");
		contentPane.add(l);
		contentPane.updateUI();
		return l;
   }
   public JLabel productfood(int i,int j)
   {	JLabel l;
	   	l=new JLabel();
		l.setBounds(i*20, j*20, 20, 20);
		l.setOpaque(true);
		l.setBackground(Color.GREEN);
		l.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		l.setName("food");
		contentPane.add(l);
		contentPane.updateUI();
		return l;
   }
   public JLabel productwall(int i,int j)
   {	JLabel l;
	   	l=new JLabel();
		l.setBounds(i*20, j*20, 20, 20);
		l.setOpaque(true);
		l.setBackground(Color.PINK);
		l.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
		l.setName("wall");
		contentPane.add(l);
		contentPane.updateUI();
		return l;
   }

   public void delete(int i,int j)
   {	
	   contentPane.remove(labellist[i][j]); 
	   labellist[i][j]=null;
	   
   }
   public void run() {   	  
	   
	   mainmove();	   
	   
	   p2move();
	   
	}  
   public void p2control()throws IOException 
   {
	 
	  out1.writeUTF("playermove");
	  out1.flush();
	  if(player.equals("p1"))
	  {	
		out1.writeInt(headdirect);
		out1.flush();	
		System.out.println("p1发送"+headdirect);
	  }
	  else
	  {
		out1.writeInt(headdirect2);
		out1.flush();
		System.out.println("p2发送"+headdirect);
	  } 
	  out1.writeInt(0);
	  out1.flush();  
   }
   public void Fulshfood()throws IOException 
   {if(player=="p1")
      {	  while(true)
		 {
		     int i = (int) Math.round(Math.random()*26+1);
			 int j = (int) Math.round(Math.random()*26+1);
			 if(labellist[i][j]==null)
			 {	 out1.writeUTF("flushfood");
			  	 out1.flush();
			  	 out1.writeInt(i);
				 out1.flush();
				 out1.writeInt(j);
				 out1.flush();
				 out1.writeInt(0);
				 out1.flush();
				 labellist[i][j]=productfood(i,j);
				 break;
			 }
		 }
      }
   }
   public void Fulshfood2() throws IOException
   {if(player=="p2")
   {  while(true)
	 {
	     int i = (int) Math.round(Math.random()*26+1);
		 int j = (int) Math.round(Math.random()*26+1);
		 if(labellist[i][j]==null)
		 {	 out1.writeUTF("flushfood");
		  	 out1.flush();
		  	 out1.writeInt(i);
			 out1.flush();
			 out1.writeInt(j);
			 out1.flush();
			 out1.writeInt(0);
			 out1.flush();
			 labellist[i][j]=productfood(i,j);
			 break;
		 }
	 }
   }
   }
   public void mainmove()
   {
	   switch(tdirectarray[taildirectindex])
	   {
	   case 37:delete(tailx,taily);tailx--;break;
	   case 38:delete(tailx,taily);taily--;break;
	   case 39:delete(tailx,taily);tailx++;break;
	   case 40:delete(tailx,taily);taily++;break;
	   }
	   
	   switch(headdirect)
	   {
	   case 37:eatfood(headx-1,heady);
	   headx--;labellist[headx][heady]=product(headx,heady);break;
	   case 38:eatfood(headx,heady-1);
	   heady--;labellist[headx][heady]=product(headx,heady);break;
	   case 39:eatfood(headx+1,heady);
	   headx++;labellist[headx][heady]=product(headx,heady);break;
	   case 40:eatfood(headx,heady+1);
	   heady++;labellist[headx][heady]=product(headx,heady);break;
	   default:break;
	   }
	   directing=headdirect;

   		sentaildirectindex();
   		taildirectindex++;
   }
   
   public void p2move()
   {
	   switch(tdirectarray2[taildirectindex2])
	   {
	   case 37:delete(tailx2,taily2);tailx2--;break;
	   case 38:delete(tailx2,taily2);taily2--;break;
	   case 39:delete(tailx2,taily2);tailx2++;break;
	   case 40:delete(tailx2,taily2);taily2++;break;
	   }
	   
	   switch(headdirect2)
	   {
	   case 37:eatfood2(headx2-1,heady2);
	   headx2--;labellist[headx2][heady2]=product2(headx2,heady2);break;
	   case 38:eatfood2(headx2,heady2-1);
	   heady2--;labellist[headx2][heady2]=product2(headx2,heady2);break;
	   case 39:eatfood2(headx2+1,heady2);
	   headx2++;labellist[headx2][heady2]=product2(headx2,heady2);break;
	   case 40:eatfood2(headx2,heady2+1);
	   heady2++;labellist[headx2][heady2]=product2(headx2,heady2);break;
	   default:break;
	   }
	   directing2=headdirect2;

   		sentaildirectindex2();
   		taildirectindex2++;
   }
   public void eatfood(int headx1,int heady1)
   {
	if(labellist[headx1][heady1]!=null)
   { System.out.println(labellist[headx1][heady1]);
	if(labellist[headx1][heady1].getName().equals("food"))
   {delete(headx1,heady1);labellist[headx1][heady1]=product(headx1,heady1);
   headx=headx1;heady=heady1;
   sentaildirectindex();
   System.out.println("eating");
   length++;    
   try {
	Fulshfood();
} catch (IOException e) {
	e.printStackTrace();
}
   }
   }
   }
   
   public void eatfood2(int headx1,int heady1)
   {
	if(labellist[headx1][heady1]!=null)
   { System.out.println(labellist[headx1][heady1]);
	if(labellist[headx1][heady1].getName().equals("food"))
   {delete(headx1,heady1);labellist[headx1][heady1]=product2(headx1,heady1);
   headx2=headx1;heady2=heady1;
   sentaildirectindex2();
   System.out.println("eating2");
   length2++;    
   try {
	Fulshfood2();
} catch (IOException e) {
	// TODO 自动生成的 catch 块
	e.printStackTrace();
}
   }
   }
   }
   
   
   public void sentaildirectindex()
   {  		
  			if(taildirectindex+length-2>=49)
  			{
  				tdirectarray[length+taildirectindex-51]=headdirect;
  				if(taildirectindex==49)
  		  		{System.out.println("置0");
  		  		taildirectindex=-1; 		
  		  		}
  				
  			}
  			else
  			{ 
  				tdirectarray[taildirectindex+(length-1)]=headdirect;
  			}
   }
   
   public void sentaildirectindex2()
   {  		
  			if(taildirectindex2+length2-2>=49)
  			{
  				tdirectarray2[length2+taildirectindex2-51]=headdirect2;
  				if(taildirectindex2==49)
  		  		{System.out.println("置0");
  		  		taildirectindex2=-1; 		
  		  		}
  				
  			}
  			else
  			{ 
  				tdirectarray2[taildirectindex2+(length2-1)]=headdirect2;
  			}
   }
   public void controlevent()
   {
      frame.addKeyListener(new KeyAdapter()
       {
           public void keyPressed(KeyEvent e)
           {if(e.getKeyCode()<=40&&e.getKeyCode()>=37)
	           {if(Math.abs(directing-e.getKeyCode())!=2)
	           	headdirect=e.getKeyCode();
	           try {p2control();} catch (IOException e1) {e1.printStackTrace();}
	           }
           }
           
       });      
   }
   public void controlevent2()
   {
      frame.addKeyListener(new KeyAdapter()
       {
           public void keyPressed(KeyEvent e)
           {if(e.getKeyCode()<=40&&e.getKeyCode()>=37)
	           {if(Math.abs(directing2-e.getKeyCode())!=2)
	           	headdirect2=e.getKeyCode();   
	           try {p2control();} catch (IOException e1) {e1.printStackTrace();}
	           }
           }
           
       });      
   }
public void keyTyped(KeyEvent e) {	
}
public void keyReleased(KeyEvent e) {		
}
public void keyPressed(KeyEvent e) {
}

}  
