import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.*;

public class Page2 {
	JFrame frame=new JFrame();
	JLabel[] label=new JLabel[4];
	JLabel[] label1=new JLabel[9];
	JComboBox<String>[] box=new JComboBox[5];
	JTextField textbox1=new JTextField();
	JTextField textbox2=new JTextField();
	JRadioButton r1=new JRadioButton("Yes");
	JRadioButton r2=new JRadioButton("No");
	JRadioButton r3=new JRadioButton("Yes");
	JRadioButton r4=new JRadioButton("No");
	JButton bt=new JButton("Next");
	PreparedStatement ps2;
	public Page2()
	{
		frame.setSize(700,900);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponenet();
		ps2=DbConnection.page2;
		frame.setVisible(true);
	}
	private void addComponenet()
	{
		frame.getContentPane().setBackground(Color.yellow);
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
		label[1].setText("Page 2:-");
		label[1].setFont(new Font("ariyal",Font.BOLD,20));
		

		label[2].setBounds(540,15,300,25);
		label[2].setText("Form No:"+Page1.n);
		label[2].setFont(new Font("ariyal",Font.BOLD,15));
		
		
		label[3].setBounds(260,65,300,25);
		label[3].setText("Additional Details");
		label[3].setFont(new Font("ariyal",Font.BOLD,20));
		
		int y=150;
		Font f=new Font("ariyal",Font.BOLD,15);
		Font f1=new Font("ariyal",Font.BOLD,13);
		String[] str= {"Religion:","Category:","Income:","Educational Qualification:","Occupation:","PAN Number:","Aadhar Number:","Senior Citizen:","Existing Account:"};
		
		String[] religion= {"Muslim","Hindu","Sikh","Christian","Other"};
		String[] category= {"General","OBC","SC","ST","Other"};
		String[] income= {"Null","<1,50,000","<2,50,000","5,00,000","Upto 10,00,000"};
		String[] education= {"Non-Graduate","Graduate","Post-Gradua","Doctrate","Others"};
		String[] occupation= {"Salaried","Self-Employed","Business","Student","Retired","Other"};
		String[][] data= {religion,category,income,education,occupation};
		for(int i=0;i<9;i++)
		{
			label1[i]=new JLabel(str[i]);
			label1[i].setBounds(90,y,230,30);
			label1[i].setForeground(Color.black);
			label1[i].setFont(f);
			frame.add(label1[i]);
			y+=55;
		}
		y=150;
		for(int i=0; i<5; i++)
		{
			box[i]=new JComboBox(data[i]);
			box[i].setBounds(320,y,300,25);
			box[i].setFont(f1);
			box[i].setBackground(Color.white);
			frame.add(box[i]);
			y+=55;
		}
		textbox1.setBounds(320,430,300,25);
		textbox1.setFont(new Font("ariyal",Font.PLAIN,17));
		frame.add(textbox1);
		textbox2.setBounds(320,485,300,25);
		textbox2.setFont(new Font("ariyal",Font.PLAIN,17));
		frame.add(textbox2);
		r1.setBounds(320,540,50,20);
		frame.add(r1);
		r2.setBounds(470,540,50,20);
		frame.add(r2);
		r3.setBounds(320,595,50,20);
		frame.add(r3);
		r4.setBounds(470,595,50,20);
		frame.add(r4);
		bt.setBounds(530,640,100,30);
		bt.setForeground(Color.black);
		bt.setBackground(Color.white);
		bt.setFont(new Font("ariyal",Font.PLAIN,17));
		frame.add(bt);
		bt.addActionListener(new NextButtonListener());
	}
	class NextButtonListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				String Formno=label[2].getText();
				ps2.setString(1,Formno);
				String Relegion=(String)box[0].getSelectedItem();
				ps2.setString(2,Relegion);
				String Category=(String)box[1].getSelectedItem();
				ps2.setString(3,Category);
			    String Income=(String)box[2].getSelectedItem();
				ps2.setString(4,Income);
				String Education=(String)box[3].getSelectedItem();
				ps2.setString(5,Education);
				String Occupation=(String)box[4].getSelectedItem();
				ps2.setString(6,Occupation);
				int PAN_no=Integer.parseInt(textbox1.getText());
				ps2.setInt(7,PAN_no);
				int Aadhar_no=Integer.parseInt(textbox2.getText());
				ps2.setInt(8,Aadhar_no);
				
				String citizen=null;
				if(r1.isSelected())
				{
					citizen="Yes";
				}
				else if(r2.isSelected())
				{
					citizen="No";
				}
				ps2.setString(9,citizen);

				String account=null;
				if(r3.isSelected())
				{
					account="Yes";
				}
				else if(r4.isSelected())
				{
					account="No";
				}
				ps2.setString(10,account);
				
				if(String.valueOf(Aadhar_no).equals(""))
				{
					JOptionPane.showMessageDialog(frame,"fill all details");
				}
				else
				{
					ps2.executeUpdate();
					new Page3();
				}
			
				} catch(Exception ex)
				{
					 System.out.println(ex);
				}
		}
		
	}

}
