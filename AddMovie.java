import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class AddMovie extends JFrame implements ActionListener
{
	String userId;
	JLabel textLabel,idLabel,nameLabel,date1Label,time1Label,date2Label,time2Label;
	JButton addBtn,add1Btn,add2Btn,backBtn,logoutBtn;
	JPanel panel;
	JTextField idText,nameTF,date1TF,time1TF,date2TF,time2TF;
	JComboBox combo,movieCombo,dateCombo,timeCombo;
	String movieName,movieId,date1,date2,time1,time2;
	
	public AddMovie(String userId)
	{
		super("");
		
		this.userId = userId;
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		Font tLFont=new Font("Cambria",  Font.ITALIC+Font.BOLD, 40);
		Font labelFont=new Font("Arial",  Font.ITALIC+Font.BOLD, 25);
		Font btnFont  =new Font("Arial",  Font.ITALIC, 25);
		Font tfFont=new Font("Arial",  Font.ITALIC, 20);
		Font comboFont=new Font("Arial",  Font.ITALIC, 20);
		panel = new JPanel();
		panel.setLayout(null);
		
		
		
		textLabel=new JLabel("Add Movie");
		textLabel.setBounds(500,50,400,40);
		textLabel.setFont(tLFont);
		panel.add(textLabel);
		
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(1130, 30, 120, 40);
		logoutBtn.setFont(btnFont);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		
		idLabel=new JLabel("Movie ID");
		idLabel.setBounds(300,150,150,40);
		idLabel.setFont(labelFont);
		panel.add(idLabel);
		
		idText=new JTextField();
		idText.setBounds(500, 150, 250, 40);
		idText.setFont(tfFont);
		panel.add(idText);
		
		
		nameLabel=new JLabel("Movie Name");
		nameLabel.setBounds(300,220,150,40);
		nameLabel.setFont(labelFont);
		panel.add(nameLabel);
		
		nameTF=new JTextField();
		nameTF.setBounds(500, 220, 250, 40);
		nameTF.setFont(tfFont);
		panel.add(nameTF);
		nameTF.setVisible(true);
		
		addBtn=new JButton("Create");
		addBtn.setBounds(800, 220, 150,40);
		addBtn.setFont(btnFont);
		addBtn.addActionListener(this);
		panel.add(addBtn);
		
		date1Label=new JLabel("Date");
		date1Label.setBounds(300,290,150,40);
		date1Label.setFont(labelFont);
		panel.add(date1Label);
		date1Label.setVisible(false);
		
		date1TF=new JTextField();
		date1TF.setBounds(500, 290, 250, 40);
		date1TF.setFont(tfFont);
		panel.add(date1TF);
		date1TF.setVisible(false);
		
		time1Label=new JLabel("Time");
		time1Label.setBounds(300,360,150,40);
		time1Label.setFont(labelFont);
		panel.add(time1Label);
		time1Label.setVisible(false);
		
		time1TF=new JTextField();
		time1TF.setBounds(500, 360, 250, 40);
		time1TF.setFont(tfFont);
		panel.add(time1TF);
		time1TF.setVisible(false);
		
		add1Btn=new JButton("ADD");
		add1Btn.setBounds(600, 500, 150,40);
		add1Btn.setFont(btnFont);
		add1Btn.addActionListener(this);
		panel.add(add1Btn);
		add1Btn.setVisible(false);
		
		
		
		
		backBtn=new JButton("Back");
		backBtn.setBounds(400, 500, 150,40);
		backBtn.setFont(btnFont);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		backBtn.setVisible(true);
		
		this.add(panel);
	}
	
	
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(backBtn.getText()))
		{
			ManagerHome me = new ManagerHome(userId);
			me.setVisible(true);
			this.setVisible(false);
		}
		
		else if(text.equals(logoutBtn.getText()))
		{
			AdminLogin al = new AdminLogin();
			al.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(addBtn.getText()))
		{
			movieId=idText.getText();
			movieName=nameTF.getText();
			String query1 = "INSERT INTO movieinfo VALUES ('"+movieId+"','"+movieName+"',"+ 100+","+100+");";			
			System.out.println(query1);
			
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b23", "root", "");
				Statement stm = con.createStatement();
				stm.execute(query1);
				stm.close();
				con.close();
				date1Label.setVisible(true);
				date1TF.setVisible(true);
				time1Label.setVisible(true);
				time1TF.setVisible(true);
				add1Btn.setVisible(true);
				idText.setEnabled(false);
				nameTF.setEnabled(false);
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, "Invalid Input !!!");
			}
		}
		else if(text.equals(add1Btn.getText()))
		{
			
			date1=date1TF.getText();
			time1=time1TF.getText();
			String query1 = "INSERT INTO movie_timeinfo VALUES ('"+movieId+"','"+date1+"','"+time1+"',"+ 100+","+100+");";			
			System.out.println(query1);
			
			
			try
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b23", "root", "");
				Statement stm = con.createStatement();
				stm.execute(query1);
				stm.close();
				con.close();
				
				JOptionPane.showMessageDialog(this, "Success !!!");
				date1TF.setText("");
				time1TF.setText("");
				
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this, "Invalid Input !!!");
			}
		}
	}
	
}