
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class Cash_WithDrawl {
	JFrame frame=new JFrame();
	JLabel lab=new JLabel();
	JLabel label=new JLabel("Enter Amount You Want to WithDraw");
    JButton bt1=new JButton("CASH WITHDRAWL");
    JButton bt2=new JButton("BACK");
    JTextField textbox=new JTextField();
    Statement wd;
    PreparedStatement up;
	
	public  Cash_WithDrawl()
	{
		frame.setSize(1550,1080);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponenet();
		wd=DbConnection.cardno;
		up=DbConnection.deposit;
		frame.setVisible(true);
	}
	private void addComponenet()
	{
		ImageIcon atm=new ImageIcon(getClass().getResource("ProjectImages/atm2.png"));
		Image atm1=atm.getImage().getScaledInstance(1400,700,Image.SCALE_DEFAULT);
		ImageIcon atm2=new ImageIcon(atm1);
		lab.setBounds(0,0,1400,700);
		lab.setIcon(atm2);
		frame.add(lab);
		
		label.setBounds(390,160,400,35);
		label.setFont(new Font("ariyal",Font.BOLD,20));
		label.setForeground(Color.white);
		lab.add(label);
		textbox.setBounds(390,210,340,35);
		textbox.setForeground(Color.white);
		textbox.setBackground(new Color(65,125,128));
		textbox.setFont(new Font("ariyal",Font.PLAIN,20));
		frame.add(textbox);
		
		bt1.setBounds(615,305,150,30);
		bt1.setForeground(Color.white);
		bt1.setBackground(new Color(65,125,128));
		lab.add(bt1);
		bt1.addActionListener(new WithDraw());
		bt2.setBounds(615,343,150,30);
		bt2.setForeground(Color.white);
		bt2.setBackground(new Color(65,125,128));
		lab.add(bt2);
		bt2.addActionListener(new WithDraw());
	}
	
	class WithDraw implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton bb=(JButton) e.getSource();
			if(bb==bt1)
			{
				   
				if(textbox.getText().isEmpty())
						{
							JOptionPane.showMessageDialog(frame,"please enter amount");
						   
						}
					   else
					   {
						   int amount=Integer.parseInt(textbox.getText());	
						try 
						{	
							ResultSet rst=wd.executeQuery("select * from bank");
							int bal=0;
							while(rst.next())
							{
								String pinno=rst.getString(1);
								up.setString(1,pinno);
								if(rst.getString(3).equals("deposit"))
								{
									
									bal+=(rst.getInt(4));
								}
								else
								{
									bal-=(rst.getInt(4));
									
								}
							} 
							if(bal<amount)
							{
								JOptionPane.showMessageDialog(frame,"insufficient balance");
								textbox.setText("");
								 return;
							}
							  
			        	  java.util.Date currentDate=new java.util.Date();
			       	      java.sql.Date date=new java.sql.Date(currentDate.getTime());
			       	      up.setDate(2,date);
			        	  String type="withDrawl";
			        	  up.setString(3,type);
			        	  up.setInt(4,amount);
			        	  up.executeUpdate();
			        	  
			        	  JOptionPane.showMessageDialog(frame,"Rs."+" "+amount+" "+"Debited successfully");	
						  textbox.setText("");	
		        	}catch(Exception ex)
		        	{
		        		System.out.println(ex);
		        	}
					   }
			}
			if(bb==bt2)
			{
				new Atm_Machine();
			}
		}
	}
}
		

