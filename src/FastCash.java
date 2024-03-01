
	import java.awt.*;
	import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

import com.mysql.cj.protocol.Resultset;

	public class FastCash {
		JFrame frame=new JFrame();
		JLabel lab=new JLabel();
		JLabel label=new JLabel("SELECT WITHDRAWL AMOUNT");
	    JButton[] bt=new JButton[7];
	    JTextField textbox=new JTextField();
	    Statement stmt;
	    PreparedStatement dep;
		public FastCash()
		{
			frame.setSize(1550,1080);
			frame.setLocationRelativeTo(null);
			frame.setResizable(false);
			frame.setLayout(null);
			addComponenet();
			stmt=DbConnection.cardno;
			dep=DbConnection.deposit;
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
			
			label.setBounds(420,160,400,35);
			label.setFont(new Font("ariyal",Font.BOLD,20));
			label.setForeground(Color.white);
			lab.add(label);
			
			String[] str= {"100","500","1000","2000","5000","10000","BACK"}; 
			for(int i=0;i<7;i++)
			{
				bt[i]=new JButton(str[i]);
				bt[i].setForeground(Color.white);
				bt[i].setBackground(new Color(65,125,128));
			    lab.add(bt[i]);
				bt[i].addActionListener(new  Cash());
				
			}
			
			bt[0].setBounds(370,229,150,30);
			bt[1].setBounds(615,229,150,30);
			bt[2].setBounds(370,267,150,30);
			bt[3].setBounds(615,267,150,30);
			bt[4].setBounds(370,305,150,30);
			bt[5].setBounds(615,305,150,30);
			bt[6].setBounds(615,343,150,30);
		}

		class Cash implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent e) 
			{
				JButton bb=(JButton) e.getSource();
				if(bb==bt[6])
				{
					new Atm_Machine();
				}
				else 
				{
					int amount=Integer.parseInt(bb.getText());
					try 
					{	
						ResultSet rst=stmt.executeQuery("select * from bank");
						int bal=0;
						while(rst.next())
						{
							String pinno=rst.getString(1);
							dep.setString(1,pinno);
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
		       	      dep.setDate(2,date);
		        	  String type="withDrawl";
		        	  dep.setString(3,type);
		        	  dep.setInt(4,amount);
		        	  dep.executeUpdate();
		        	  
		        	  JOptionPane.showMessageDialog(frame,"Rs."+" "+amount+" "+"Debited successfully");	
					  textbox.setText("");	
	        	}catch(Exception ex)
	        	{
	        		System.out.println(ex);
	        	}
				   }

				}
				
				
			}
		}
