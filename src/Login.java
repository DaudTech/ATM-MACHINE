import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class Login 
{
	JFrame frame =new JFrame();
	JLabel[] label=new JLabel[6];
	JTextField textbox1=new JTextField();
	JTextField textbox2=new JTextField();
	JButton[] bt=new JButton[3];
	PreparedStatement ps4;
	
	public Login()
	{
		frame.setSize(700,500);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setComponent();
		
		ps4=DbConnection.search;
		frame.setVisible(true);
	}
	private void setComponent()
	{
		ClickListener listener=new ClickListener();
		for(int i=0; i<3; i++)
		{
			bt[i]=new JButton();
			frame.add(bt[i]);
			bt[i].addActionListener(listener);
		}
		bt[0].setBounds(240,310,100,30);
		bt[0].setText("SIGN IN");
		bt[0].setForeground(Color.white);
		bt[0].setBackground(Color.black);
		
		bt[1].setBounds(410,310,100,30);
		bt[1].setText("CLEAR");
		bt[1].setForeground(Color.white);
		bt[1].setBackground(Color.black);
		
		bt[2].setBounds(240,360,270,30);
		bt[2].setText("SIGN UP");
		bt[2].setForeground(Color.white);
		bt[2].setBackground(Color.black);
		
		for(int i=0; i<6; i++)
		{
			label[i]=new JLabel();
			frame.add(label[i]);
		}

		label[0].setBounds(200,130,300,30);
		label[0].setText("WELCOME TO ATM");
		label[0].setFont(new Font("ariyal",Font.BOLD,30));
		label[0].setForeground(Color.white);
		
		label[1].setBounds(110,190,300,30);
		label[1].setText("Card No:");
		label[1].setFont(new Font("ariyal",Font.PLAIN,29));
		label[1].setForeground(Color.white);
		
		textbox1.setBounds(270,190,270,30);
		textbox1.setFont(new Font("ariyal",Font.BOLD,18));
		textbox1.setBackground(Color.white);
		frame.add(textbox1);
		
		textbox2.setBounds(270,250,270,30);
		textbox2.setFont(new Font("ariyal",Font.BOLD,18));
		textbox2.setBackground(Color.white);
		frame.add(textbox2);
		
		label[2].setBounds(110,250,300,30);
		label[2].setText("PIN:");
		label[2].setFont(new Font("ariyal",Font.PLAIN,29));
		label[2].setForeground(Color.white);
		
		ImageIcon i1=new ImageIcon(getClass().getResource("ProjectImages/bank.png"));
		Image i2=i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
		ImageIcon i3=new ImageIcon(i2);
		label[3].setBounds(290,10,100,100);
		label[3].setIcon(i3);
		
		ImageIcon card=new ImageIcon(getClass().getResource("ProjectImages/card.png"));
		Image card1=card.getImage().getScaledInstance(80,80,Image.SCALE_DEFAULT);
		ImageIcon card2=new ImageIcon(card1);
		label[4].setBounds(560,320,200,150);
		label[4].setIcon(card2);
		
		ImageIcon bg=new ImageIcon(getClass().getResource("ProjectImages/backbg.png"));
		Image bg1=bg.getImage().getScaledInstance(700,500,Image.SCALE_DEFAULT);
		ImageIcon bg2=new ImageIcon(bg1);
		label[5].setBounds(0,0,700,500);
		label[5].setIcon(bg2);
		
	}
	class ClickListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton bb=(JButton) e.getSource();
			if(bb==bt[0])
			{
				String cardno=textbox1.getText();
				String pin=textbox2.getText();
				try
				{
					ps4.setString(1,cardno);
					ps4.setString(2,pin);
					ResultSet rst=ps4.executeQuery();
					if(rst.next())
					{
							new Atm_Machine();
							return;
					}
					if(textbox1.getText().equals("") || textbox2.getText().equals(""))
					{
						JOptionPane.showMessageDialog(frame,"fill all details");
					}
					
					else 
					{
							JOptionPane.showMessageDialog(frame,"card_no and pin_no is wrong");
					}

				}catch(Exception ex)
				{
					System.out.println(ex);
				}
			}
			else if(bb==bt[1])
			{
				textbox1.setText("");
				textbox2.setText("");
			}
			else if(bb==bt[2])
			{
				 new Page1();
			}
			
		}
		
		
	}

}
