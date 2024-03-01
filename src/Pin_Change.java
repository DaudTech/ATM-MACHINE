import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class Pin_Change {
	JFrame frame=new JFrame();
	JLabel lab=new JLabel();
    JButton bt2=new JButton("BACK");
    JButton bt1=new JButton("CHANGE");
    JLabel label2=new JLabel("Change your Pin");
    JLabel label1=new JLabel("Old PIN:");
    JLabel label=new JLabel("New PIN:");
    JPasswordField p1=new JPasswordField();
    JPasswordField p2=new JPasswordField();
    JTextField textbox1=new JTextField();
    PreparedStatement updateb,searchb,updateP,searchP;

	public  Pin_Change() 
	{
		frame.setSize(1550,1080);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponenet();
		
		searchP=DbConnection.searchPage3;
		updateP=DbConnection.updatePage3;
		searchb=DbConnection.searchbank;
		updateb=DbConnection.updateBank;
		
		
		
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
		
		label2.setBounds(390,140,400,35);
		label2.setFont(new Font("ariyal",Font.BOLD,20));
		label2.setForeground(Color.white);
		lab.add(label2);
		
		bt2.setBounds(615,343,150,30);
		bt2.setForeground(Color.white);
		bt2.setBackground(new Color(65,125,128));
		bt1.setBounds(615,305,150,30);
		bt1.setBackground(new Color(65,125,128));
		bt1.setForeground(Color.white);
		lab.add(bt2);
		lab.add(bt1);
		bt1.addActionListener(new ChangeListener());
		bt2.addActionListener(new ChangeListener());
		
		label1.setForeground(Color.WHITE);
	    label1.setFont(new Font("System", Font.BOLD, 16));
        label1.setBounds(380,190,150,35);       
		lab.add(label1);
		
        label.setForeground(Color.WHITE);
        label.setFont(new Font("System", Font.BOLD, 16));
        label.setBounds(380,220,400,35);
        lab.add(label);
		
        p1 = new JPasswordField();
        p1.setBackground(new Color(65,125,128));
        p1.setForeground(Color.WHITE);
        p1.setBounds(560,190,180,25);
        p1.setFont(new Font("Raleway", Font.BOLD,22));
        lab.add(p1);
		
        p2.setBackground(new Color(65,125,128));
        p2.setForeground(Color.WHITE);
        p2.setBounds(560,220,180,25);
        p2.setFont(new Font("Raleway", Font.BOLD,22));
        lab.add(p2);
	}
	class ChangeListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton bb=(JButton)e.getSource();
			if(bb==bt1)
			{

				String oldpin=p1.getText();
				String newpin=p2.getText();
				
				 if(p1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"Please Enter Old Pin");
					return;
				}
				else if(p2.getText().equals(""))
				{
					JOptionPane.showMessageDialog(frame,"Please Enter New Pin");
					return;
				}
				 if(p1.getText().equals(p2.getText()))
					{
						JOptionPane.showMessageDialog(frame,"old PIN does not match");
						return;
					}
				
				else 
				{
					try
					{
					    searchP.setString(1,oldpin);
						ResultSet rst=searchP.executeQuery();
						if(rst.next())
						{
							   
							    updateP.setString(1,rst.getString(2));
							    updateP.setString(2,rst.getString(3));
							    updateP.setString(3,rst.getString(4));
							    updateP.setString(4,rst.getString(5));
							    updateP.setString(5,newpin);
							    updateP.executeUpdate();
		                        JOptionPane.showMessageDialog(frame, "Password changed successfully in page3");
						}
						searchb.setString(1,oldpin);
						ResultSet rst1=searchb.executeQuery();
						if(rst1.next())
						{
							updateb.setString(1,rst1.getString(2));
							updateb.setInt(2,rst1.getInt(3));
							updateb.setString(3,newpin);
							updateb.executeUpdate();
							JOptionPane.showMessageDialog(frame,"password change successfully");
						}
						
					}catch(Exception ex)
					{
						System.out.println(ex);
					}
				}
			}
			
			else if(bb==bt2)
			{
				new Atm_Machine();
			}
			
		}
		
	}
	
		
	}