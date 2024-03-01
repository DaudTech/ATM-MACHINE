import java.sql.*;

public class DbConnection {
	
	public static PreparedStatement page1,page2,page3,search,deposit,searchbank,updateBank,searchPage3,updatePage3;
    public static Statement cardno;
	
	
	public static void createConnection()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection cn=DriverManager.getConnection("jdbc:mysql://localhost:3306/BankSystem?createDatabaseIfNotExist=true","root","mysql");
			Statement st=cn.createStatement();
			st.execute("create table if not exists page1(formno varchar(60),name varchar(30),fname varchar(30),gender varchar(20),DOB varchar(30),Email varchar(60) primary key,marital varchar(20),Address varchar(60),city varchar(20),state varchar(60),pin int)");
			page1=cn.prepareStatement("insert into page1 values(?,?,?,?,?,?,?,?,?,?,?)");
			st.execute("create table if not exists page2(formNo varchar(60),relegion varchar(30),Category varchar(30),Income varchar(30),Education varchar(70),Occupation varchar(60),PANno int,AdhaarNo int primary key,Citizen varchar(20),Account varchar(20))");
			page2=cn.prepareStatement("insert into page2 values(?,?,?,?,?,?,?,?,?,?)");
			st.execute("create table if not exists page3(formno varchar(60),Account_Type varchar(30),Card_No varchar(30),PIN varchar(20),Service varchar(200))");
			page3=cn.prepareStatement("insert into page3 values(?,?,?,?,?)");
			search=cn.prepareStatement("select * from page3 where Card_No=? and PIN=?");
			cardno=cn.createStatement();
			st.execute("create table if not exists bank(Pin_no varchar(30),date Date,type varchar(50),Amount int)");
			deposit=cn.prepareStatement("insert into bank values(?,?,?,?)");
			searchbank=cn.prepareStatement("select * from bank where Pin_no=?");
			updateBank=cn.prepareStatement("update bank set Pin_no=? where Pin_no=?");
			searchPage3=cn.prepareStatement("select * from page3 where PIN=?");
			updatePage3=cn.prepareStatement("update page3 set PIN=? where PIN=?");
			
			
		}catch(Exception ex)
		{
			System.out.println(ex);
		}
	}

}
