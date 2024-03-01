
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Deposit {
	JFrame frame=new JFrame();
	JLabel lab=new JLabel();
	JLabel label=new JLabel("Enter Amount You Want To Deposit");
    JButton bt1=new JButton("DEPOSIT");
    JButton bt2=new JButton("BACK");
    JTextField textbox=new JTextField();
    PreparedStatement dp;
    Statement dp1;
	int amt;
	public  Deposit() 
	{
		frame.setSize(1550,1080);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponenet();
		dp=DbConnection.deposit;
		dp1=DbConnection.cardno;
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
		bt1.addActionListener(new DepositListener());
		bt2.setBounds(615,343,150,30);
		bt2.setForeground(Color.white);
		bt2.setBackground(new Color(65,125,128));
		lab.add(bt2);
		bt2.addActionListener(new DepositListener());
	}
	class DepositListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton bb=(JButton) e.getSource();
	        if(bb==bt1)
	        {
	        	if(textbox.getText().equals(""))
	    		{
	    			JOptionPane.showMessageDialog(frame,"please enter amount");
	    		   
	    		}
	    		else
	    		{
	    			   
	    			    amt=Integer.parseInt(textbox.getText());
	    			    JOptionPane.showMessageDialog(frame,"Rs."+" "+amt+" "+"is successfully deposited");
	    			    textbox.setText("");
	    		}
	        	
	        	try
	        	{
	        		
	        	  ResultSet rst=dp1.executeQuery("select * from page3");
	        	  if(rst.next())
	        	  {
	        		  String pin_no=rst.getString(4);
	        		  dp.setString(1,pin_no);
	        	  }
	        	  java.util.Date currentDate=new java.util.Date();
	       	      java.sql.Date date=new java.sql.Date(currentDate.getTime());
				  
	       	      dp.setDate(2,date);
	        	  String type="deposit";
	        	  dp.setString(3,type);
	        	  dp.setInt(4,amt);
	        	  dp.executeUpdate();
	        	}catch(Exception ex)
	        	{
	        		System.out.println(ex);
	        	}

	        }
			if(bb==bt2)
			{
				new Atm_Machine();
			}
			
			
		}
		
	}
}