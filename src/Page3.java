import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

import javax.swing.*;

public class Page3 
{
	JFrame frame=new JFrame();
	JLabel[] label=new JLabel[4];
	JRadioButton r1=new JRadioButton("Saving Account");
	JRadioButton r2=new JRadioButton("Fixed Deposit Account");
	JRadioButton r3=new JRadioButton("Current Account");
	JRadioButton r4=new JRadioButton("Recurring Deposit Account");
	JLabel[] label1=new JLabel[9];
	JCheckBox[] box=new JCheckBox[6];
	JButton bt1=new JButton("Submit");
	JButton bt2=new JButton("Cancel");
	PreparedStatement ps3;
	static String cardno;
	static String pin;
	
	JCheckBox box1=new JCheckBox("I hereby declares that the above entered details correct to the best of my knowledge");
	public Page3()
	{

		frame.setSize(700,900);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponenet();
		ps3=DbConnection.page3;
		frame.setVisible(true);
	}
	private void addComponenet()
	{
		frame.getContentPane().setBackground(new Color(222,255,228));
		for(int i=0;i<4;i++)
		{
			label[i]=new JLabel();
			label[i].setForeground(Color.black);
			frame.add(label[i]);
		}
		ImageIcon next=new ImageIcon(getClass().getResource("ProjectImages/bank.png"));
		Image next1=next.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
		ImageIcon next2=new ImageIcon(next1);
		label[0].setBounds(100,3,100,100);
		label[0].setIcon(next2);
		
		label[1].setBounds(260,35,200,28);
		label[1].setText("Page 3:-");
		label[1].setFont(new Font("ariyal",Font.BOLD,18));
		

		label[2].setBounds(540,15,300,25);
		label[2].setText("Form No:"+Page1.n);
		label[2].setFont(new Font("ariyal",Font.BOLD,15));
		
		
		label[3].setBounds(260,65,300,25);
		label[3].setText("Account Details");
		label[3].setFont(new Font("ariyal",Font.BOLD,18));
		Font f1=new Font("ariyal",Font.BOLD,15);
		r1.setBounds(90,170,150,20);
		r1.setBackground(new Color(222,255,228));
		r1.setFont(f1);
		frame.add(r1);
		r2.setBounds(350,170,250,20);
		r2.setBackground(new Color(222,255,228));
		r2.setFont(f1);
		frame.add(r2);
		r3.setBounds(90,210,150,20);
		r3.setBackground(new Color(222,255,228));
		r3.setFont(f1);
		frame.add(r3);
		r4.setBounds(350,210,250,20);
		r4.setBackground(new Color(222,255,228));
		r4.setFont(f1);
		frame.add(r4);
		String[] str= {"Account Type:","Card Number:","(Your 16-digit Card Number)","XXXX-XXXX-XXXX-5531","it would appear ATM Card/Cheque Book and Statements","PIN:","(4-digit password)","XXXX","Sevices Required:"};
	    Font f=new Font("ariyal",Font.BOLD,18);
		for(int i=0;i<9;i++)
	    {
		label1[i]=new JLabel(str[i]);
		frame.add(label1[i]);
	    }
	    label1[0].setBounds(90,120,330,30);
	    label1[0].setFont(f);
	    label1[1].setBounds(90,250,330,30);
	    label1[1].setFont(f);
	    
	    label1[2].setBounds(90,270,330,30);
	    label1[2].setFont(new Font("ariyal",Font.PLAIN,12));
	    
	    label1[3].setBounds(320,250,330,30);
	    label1[3].setFont(f);
	    
	    label1[4].setBounds(320,270,330,30);
	    label1[4].setFont(new Font("ariyal",Font.PLAIN,12));
	    
	    label1[5].setBounds(90,310,100,30);
	    label1[5].setFont(f);
	    
	    label1[6].setBounds(90,330,250,30);
	    label1[6].setFont(new Font("ariyal",Font.PLAIN,12));
	    
	    label1[7].setBounds(320,310,200,30);
	    label1[7].setFont(f);
	    
	    label1[8].setBounds(90,370,200,30);
	    label1[8].setFont(f);
	    String[] check= {"ATM CARD","Internet Banking","Mobile Banking","EMAIL Alerts","Cheque Book","E-Statement"};
	    for(int i=0;i<6;i++)
	    {
	    	box[i]=new JCheckBox(check[i]);
	    	box[i].setFont(f);
	    	box[i].setBackground(new Color(222,255,228));
	    	frame.add(box[i]);
	    }
	    box[0].setBounds(90,410,150,30);
	    box[1].setBounds(350,410,250,30);
	    box[2].setBounds(90,460,250,30);
	    box[3].setBounds(350,460,250,30);
	    box[4].setBounds(90,510,250,30);
	    box[5].setBounds(350,510,250,30);
	    box1.setBounds(90,580,550,30);
	    box1.setBackground(new Color(222,255,228));
	    frame.add(box1);
	    
	    bt1.setBounds(200,630,100,30);
	    bt1.setForeground(Color.white);
		bt1.setBackground(Color.black);
		bt1.setFont(new Font("ariyal",Font.PLAIN,17));
		frame.add(bt1);
		bt1.addActionListener(new SubmitListener());
		
		bt2.setBounds(400,630,100,30);
		bt2.setForeground(Color.white);
	    bt2.setBackground(Color.black);
	    bt2.setFont(new Font("ariyal",Font.PLAIN,17));
	    bt2.addActionListener(new SubmitListener());
		frame.add(bt2);
	}
	class SubmitListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			long n1=(Page1.ran.nextLong() % 9000000000L)+1423600000000000L;
			cardno=""+Math.abs(n1);
			long n2=(Page1.ran.nextLong() % 900L)+1000L;
			String pin=""+Math.abs(n2);
			JButton bb=(JButton) e.getSource();
			if(bb==bt1)
			{
				try
				{
					String formno=label[2].getText();
					ps3.setString(1,formno);
					String acc_type=null;
					if(r1.isSelected())
					{
						acc_type="Saving Account";
					}
					else if(r2.isSelected())
					{
						acc_type="Fixed Deposit Account";
					}
					else if(r3.isSelected())
					{
						acc_type="Current Account";
						
					}
					else if(r1.isSelected())
					{
						acc_type="Recurring Deposit Account";	
					}
					ps3.setString(2,acc_type);
					ps3.setString(3,cardno);
					ps3.setString(4,pin);
					
					String service=null;
					if(box[0].isSelected())
					{
						service="ATM CARD";
					}
					else if(box[1].isSelected())
					{
						service="Internet Banking";
					}
					else if(box[2].isSelected())
					{
						service="Mobile Banking";
						
					}
					else if(box[3].isSelected())
					{
						service="Email Alerts";	
					}
					else if(box[4].isSelected())
					{
						service="Cheque Book";
					}
					else if(box[5].isSelected())
					{
						service="E-Statement";
						
					}
					ps3.setString(5,service);
					if(acc_type.equals(""))
					{
						JOptionPane.showMessageDialog(null,"fill all details");
					}
					else
					{
						ps3.executeUpdate();
						JOptionPane.showMessageDialog(null,"Card_no:"+" "+n1+"\n"+"PIN_no:"+" "+n2);
						new Atm_Machine();
					}
				
				}catch(Exception ex)
				{
					System.out.println(ex);
				}
				
				
				
			}
			else if(bb==bt2)
			{
				new Page2();
				
			}
			
			
		}
		

}
	
}