import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MiniStaement {
	JFrame frame=new JFrame();
	JLabel lab=new JLabel();
    JButton bt2=new JButton("BACK");
    JTable table=new JTable();
    DefaultTableModel model=new DefaultTableModel();
    JScrollPane pane=new JScrollPane();
    Statement show;

	public  MiniStaement() 
	{
		frame.setSize(1550,1080);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setLayout(null);
		addComponenet();
		show=DbConnection.cardno;
		showRecord();
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
		bt2.addActionListener(new MiniListener());
		
		
	
	}
	private void showRecord()
	{
		table=new JTable(model);
		pane=new JScrollPane(table);
		pane.setBounds(370,150,395,170);
		pane.setForeground(Color.white);
		lab.add(pane);
		model.addColumn("Pin_no");
		model.addColumn("Date");
		model.addColumn("Type");
		model.addColumn("Amount");
		try
		{
			ResultSet rst=show.executeQuery("select * from bank");
			while(rst.next())
			{
				Object[] ob= {rst.getInt(1),rst.getString(2),rst.getString(3),rst.getInt(4)};
				model.addRow(ob);
			}
			
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
	class MiniListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e) 
		{
			new Atm_Machine();
		}
		
	}
}