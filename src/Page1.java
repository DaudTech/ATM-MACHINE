import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

import javax.swing.*;

import com.toedter.calendar.JDateChooser;

public class Page1 
{
	JFrame frame =new JFrame();
	JLabel[] label1=new JLabel[4];
	JLabel[] label=new JLabel[10];
	JTextField[] textbox=new JTextField[10];
	JButton bt=new JButton("Next");
	JDateChooser datechooser=new JDateChooser();
	JRadioButton r1=new JRadioButton("Male");
	JRadioButton r2=new JRadioButton("Female");
	JRadioButton r3=new JRadioButton("Married");
	JRadioButton r4=new JRadioButton("Unmarried");
	JRadioButton r5=new JRadioButton("Other");
	static Random ran=new Random();
	static int n=ran.nextInt(1000);
	PreparedStatement ps1;
	public Page1()
	{
		frame.setSize(700,900);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addComponent();
		ps1=DbConnection.page1;
		frame.setVisible(true);
	}
	private void addComponent()
	{
		frame.getContentPane().setBackground(new Color(222,255,228));
		for(int i=0; i<4; i++)
		{
			label1[i]=new JLabel();
			frame.add(label1[i]);
		}
		ImageIcon sign=new ImageIcon(getClass().getResource("ProjectImages/bank.png"));
		Image sign1=sign.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
		ImageIcon sign2=new ImageIcon(sign1);
		label1[0].setBounds(10,5,100,100);
		label1[0].setIcon(sign2);
		
		label1[1].setBounds(130,25,500,30);
		label1[1].setFont(new Font("ariyal",Font.BOLD,30));
		label1[1].setText("APPLICATION FORM NO."+" "+n);
		
		label1[2].setBounds(300,60,100,33);
		label1[2].setText("Page 1");
		label1[2].setFont(new Font("ariyal",Font.PLAIN,27));
		label1[2].setForeground(Color.black);

		label1[3].setBounds(250,85,200,33);
		label1[3].setText("Personal Detail");
		label1[3].setFont(new Font("ariyal",Font.PLAIN,27));
		label1[3].setForeground(Color.black);
		
		int y=150;
		Font f=new Font("ariyal",Font.BOLD,15);
		Font f1=new Font("ariyal",Font.PLAIN,17);
		String[] str= {"Name:","Father's Name:","Gender:","Date of Birth:","Email Address:","Marital Status:","Address:","City:","State:","Pin Code:"};
		for(int i=0; i<10; i++)
		{
			label[i]=new JLabel(str[i]);
			label[i].setBounds(90,y,150,25);
			label[i].setForeground(Color.black);
			label[i].setFont(f);
			frame.add(label[i]);
			textbox[i]=new JTextField();
			textbox[i].setBounds(300,y,300,25);
			textbox[i].setFont(f1);
			frame.add(textbox[i]);
			y+=45;
		}
		textbox[2].setVisible(false);
		textbox[3].setVisible(false);
		textbox[5].setVisible(false);
		bt.setBounds(490,620,100,30);
		bt.setBackground(Color.black);
		bt.setForeground(Color.white);
		bt.setFont(f1);
		frame.add(bt);
		bt.addActionListener(new NextListener());
		
		datechooser.setForeground(Color.blue);
		datechooser.setBounds(300,285,300,25);
		frame.add(datechooser);
		
		r1.setFont(new Font("ariyal",Font.BOLD,12));
		r1.setBounds(300,240,70,15);
		frame.add(r1);

		r2.setFont(new Font("ariyal",Font.BOLD,12));
		r2.setBounds(400,240,70,15);
		frame.add(r2);
		
		r3.setFont(new Font("ariyal",Font.BOLD,12));
		r3.setBounds(300,380,70,15);
		frame.add(r3);

		r4.setFont(new Font("ariyal",Font.BOLD,12));
		r4.setBounds(400,380,90,15);
		frame.add(r4);
		r5.setFont(new Font("ariyal",Font.BOLD,12));
		r5.setBounds(520,380,60,15);
		frame.add(r5);
	}
		
		class NextListener implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				try
				{
				    String formno=(label1[1].getText());
					ps1.setString(1,formno);
					String name=textbox[0].getText();
					ps1.setString(2,name);
					String fname=textbox[1].getText();
					ps1.setString(3,fname);
					String gender=null;
					if(r1.isSelected())
					{
						gender="Male";
					}
					else if(r2.isSelected())
					{
						gender="Female";
					}
					ps1.setString(4,gender);
					String DOB=((JTextField) datechooser.getDateEditor().getUiComponent()).getText();
					ps1.setString(5,DOB);
					String email=textbox[4].getText();
					ps1.setString(6,email);
					String marital=null;
					if(r3.isSelected())
					{
						marital="Married";
					}
					else if(r4.isSelected())
					{
						marital="Unmarried";
					}
					else if(r5.isSelected())
					{
						marital="Other";
					}
					    ps1.setString(7,marital);
						String add=textbox[6].getText();
						ps1.setString(8,add);
						String city=textbox[7].getText();
						ps1.setString(9,city);
						String state=textbox[8].getText();
						ps1.setString(10,state);
						int pin=Integer.parseInt(textbox[9].getText());
						ps1.setInt(11,pin);
	                if(name.equals(""))
					{
						JOptionPane.showMessageDialog(frame,"fill all fields");
					}
	                else if(!email.contains("@gmail.com"))
					{
	                	JOptionPane.showMessageDialog(frame,"incorrect email");
					}
					else
					{
						ps1.executeUpdate();
						new Page2();
					}
					} catch(Exception ex)
					{
						 System.out.println(ex);
					}
			}
		}
	}
