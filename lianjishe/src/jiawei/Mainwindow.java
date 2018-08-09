package jiawei;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class Mainwindow {

	JFrame frame;
	JPanel contentPane;
	JTextPane jtp;
	JButton button;
	JButton button1;
	JLabel lbhost,lbguest;
	Client client; 
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainwindow window = new Mainwindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mainwindow() {
		initialize();
		hosthouse();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize(){
		  
			   frame=new JFrame();
			   frame.setTitle("房间");
			   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			   frame.setBounds(100, 100, 600, 600);
			   contentPane=new JPanel();
			   contentPane.setLayout(null);
			   
			   frame.getContentPane().add(contentPane);
		       frame.setVisible(true);
		       
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
			
			JButton btnNewButton = new JButton("New button");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton.setBounds(407, 357, 93, 23);
			contentPane.add(btnNewButton);
			
			JRadioButton Readybutton1 = new JRadioButton("\u51C6\u5907");
			Readybutton1.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
				}
			});
			Readybutton1.addChangeListener(new ChangeListener() {
				public void stateChanged(ChangeEvent e) {
				}
			});
			Readybutton1.setEnabled(false);
			Readybutton1.setBounds(317, 137, 60, 23);
			contentPane.add(Readybutton1);
			
			JRadioButton Readybutton2 = new JRadioButton("\u51C6\u5907");
			Readybutton2.setHorizontalAlignment(SwingConstants.LEFT);
			Readybutton2.setBounds(317, 166, 60, 23);
			contentPane.add(Readybutton2);
			contentPane.updateUI();
			
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
			contentPane.add(lbhost);
			contentPane.add(lbguest);
			jtp = new JTextPane();
			jtp.setBounds(100,200,400,200);
			contentPane.add(jtp);	
			contentPane.updateUI();
		}
	}




