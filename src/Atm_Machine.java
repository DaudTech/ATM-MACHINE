import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Atm_Machine {
	JFrame frame=new JFrame();
	JLabel lab=new JLabel();
	JLabel label=new JLabel("Please Select Your Transasction");
    JButton[] bt=new JButton[7];
    JTextField textbox=new JTextField();
	
	public Atm_Machine()
	{
		frame.setSize(1550,1080);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponenet();
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
		
		String[] str= {"DEPOSIT","CASH WITHDRAWL","FAST CASH","MINI STATEMENT","PIN CHANGE","BALANCE ENQUIRY","EXIT"}; 
		for(int i=0;i<7;i++)
		{
			bt[i]=new JButton(str[i]);
			bt[i].setForeground(Color.white);
			bt[i].setBackground(new Color(65,125,128));
		    lab.add(bt[i]);
			bt[i].addActionListener(new  AtmListener());
			
		}
		
		bt[0].setBounds(370,229,150,30);
		bt[1].setBounds(615,229,150,30);
		bt[2].setBounds(370,267,150,30);
		bt[3].setBounds(615,267,150,30);
		bt[4].setBounds(370,305,150,30);
		bt[5].setBounds(615,305,150,30);
		bt[6].setBounds(615,343,150,30);
	}

	class AtmListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			JButton bb=(JButton) e.getSource();
			if(bb==bt[0])
			{
				new Deposit();
				
			}
			else if(bb==bt[1])
			{
				new Cash_WithDrawl();
				
			}
			else if(bb==bt[2])
			{
				new FastCash();
				
			}
			else if(bb==bt[3])
			{
				new MiniStaement();
				
			}
			else if(bb==bt[4])
			{
				new Pin_Change();
				
			}
			else if(bb==bt[5])
			{
				new Balance_Enqiery();
				
			}
			else if(bb==bt[6])
			{
				System.exit(0);
				
			}
			
		}
		
	}

}
