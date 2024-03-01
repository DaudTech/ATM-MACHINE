import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Balance_Enqiery {
	JFrame frame=new JFrame();
	JLabel lab=new JLabel();
    JButton bt2=new JButton("BACK");
    JLabel plab=new JLabel();
    JLabel label1 = new JLabel("Your Current Balance is Rs.");
    Statement ps;

	public  Balance_Enqiery() 
	{
		frame.setSize(1550,1080);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		ps=DbConnection.cardno;
		addComponenet();
		showBalance();
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
		
		bt2.setBounds(615,343,150,30);
		bt2.setForeground(Color.white);
		bt2.setBackground(new Color(65,125,128));
		lab.add(bt2);
		bt2.addActionListener(new Enquiery());

		 
	        label1.setForeground(Color.white);
	        label1.setFont(new Font("System", Font.BOLD, 16));
	        label1.setBounds(430,180,700,35);
	        
		plab.setFont(new Font("ariyal",Font.BOLD,20));
		plab.setForeground(Color.white);
		plab.setBounds(430,220,400,35);
		lab.add(label1);
		lab.add(plab);
		
	}
	private void showBalance()
	{
		int bal=0;
		try
		{
			ResultSet rst=ps.executeQuery("select * from bank");
			while(rst.next())
			{
				if(rst.getString(3).equals("deposit"))
				{
					bal+=rst.getInt(4);
				}
				else
				{
					bal-=rst.getInt(4);
				}
				
			}
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
		plab.setText(""+bal);
		
	}
	}
	class Enquiery implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
				new Atm_Machine();
			
			
		}
		
	
}