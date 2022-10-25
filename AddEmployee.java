import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class AddEmployee extends JFrame implements ActionListener
{
	JLabel userLabel, passLabel, eNameLabel, phoneLabel, roleLabel, salaryLabel;
	JTextField userTF, passTF, phoneTF1, phoneTF2, eNameTF, salaryTF;
	JComboBox roleCombo;
	JButton autoPassBtn, addBtn, backBtn, logoutBtn;
	JPanel panel;
	
	String userId;
	
	public AddEmployee(String userId)
	{
		super("");
		
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.userId = userId;
		
		panel = new JPanel();
		panel.setLayout(null);
		
		Font titleFont = new Font("Cambria",Font.ITALIC+Font.BOLD, 40);
		Font labelFont=new Font("Arial",  Font.ITALIC, 25);
		Font btnFont  =new Font("Arial",  Font.ITALIC, 25);
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(1130, 30, 120, 40);
		logoutBtn.setFont(btnFont);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		//LABEL
		
		userLabel = new JLabel("User ID : ");
		userLabel.setBounds(300, 100, 150, 40);
		userLabel.setFont(labelFont);
		panel.add(userLabel);
		
		eNameLabel = new JLabel("Emp.Name : ");
		eNameLabel.setBounds(300, 170, 150, 40);
		eNameLabel.setFont(labelFont);
		panel.add(eNameLabel);
		
		passLabel = new JLabel("Password : ");
		passLabel.setBounds(300, 230, 150, 40);
		passLabel.setFont(labelFont);
		panel.add(passLabel);
		
		phoneLabel = new JLabel("Phone No : ");
		phoneLabel.setBounds(300,290,150,40);
		phoneLabel.setFont(labelFont);
		panel.add(phoneLabel);
		
		roleLabel = new JLabel("Role : ");
		roleLabel.setBounds(300,350,150,40);
		roleLabel.setFont(labelFont);
		panel.add(roleLabel);
		
		salaryLabel = new JLabel("Salary : ");
		salaryLabel.setBounds(300,410,150,40);
		salaryLabel.setFont(labelFont);
		panel.add(salaryLabel);
		
		//TEXTFIELD
		
		Font tfFont=new Font("Arial",  Font.ITALIC, 20);
		
		userTF = new JTextField();
		userTF.setBounds(550, 100, 250, 40);
		userTF.setFont(tfFont);
		panel.add(userTF);
		
		eNameTF = new JTextField();
		eNameTF.setBounds(550, 170, 250, 40);
		eNameTF.setFont(tfFont);
		panel.add(eNameTF);
		
		passTF = new JTextField();
		passTF.setBounds(550, 230, 250, 40);
		passTF.setFont(tfFont);
		passTF.setEnabled(false);
		panel.add(passTF);
		
		phoneTF1 = new JTextField("+880");
		phoneTF1.setBounds(550, 290, 50, 40);
		phoneTF1.setFont(tfFont);
		phoneTF1.setEnabled(false);
		phoneTF1.setForeground(Color.BLACK);
		panel.add(phoneTF1);
		
		phoneTF2 = new JTextField();
		phoneTF2.setBounds(600, 290, 200, 40);
		phoneTF2.setFont(tfFont);
		panel.add(phoneTF2);
		
		salaryTF = new JTextField();
		salaryTF.setBounds(550, 410, 250, 40);
		salaryTF.setFont(tfFont);
		panel.add(salaryTF);
		
		//COMBO BOX
		
		String []items = {"Manager", "General"};
		roleCombo = new JComboBox(items);
		roleCombo.setFont(tfFont);
		roleCombo.setBounds(550,350,250,40);
		panel.add(roleCombo);
		
		//BUTTON
		
		autoPassBtn = new JButton("Generate");
		autoPassBtn.setBounds(850, 230, 150, 40);
		autoPassBtn.setFont(btnFont);		
		autoPassBtn.addActionListener(this);
		panel.add(autoPassBtn);
		
		
		
		addBtn = new JButton("ADD");
		addBtn.setBounds(600, 500, 150,40);
		addBtn.setFont(btnFont);
		addBtn.addActionListener(this);
		panel.add(addBtn);
		
		backBtn = new JButton("Back");
		backBtn.setBounds(400, 500, 150,40);
		backBtn.setFont(btnFont);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
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
			UserLogin lg = new UserLogin();
			lg.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(autoPassBtn.getText()))
		{
			Random r = new Random();
			passTF.setText(r.nextInt(89999999)+10000000+"");
			autoPassBtn.setEnabled(false);
		}
		else if(text.equals(addBtn.getText()))
		{
			insertIntoDB();
		}
		else{}
	}
	public void insertIntoDB()
	{
		String newId = userTF.getText();
		String newPass = passTF.getText();
		String eName = eNameTF.getText();
		String phnNo = phoneTF1.getText()+phoneTF2.getText();
		String role = roleCombo.getSelectedItem().toString();
		String salary = salaryTF.getText();
		int status;
		if(roleCombo.getSelectedItem().toString().equals("Manager"))
		{
			status=0;
			
		}
		else
		{
			status=1;
			
		}
		
		
		String query1 = "INSERT INTO Employeeinfo VALUES ('"+newId+"','"+eName+"','"+ phnNo+"','"+role+"',"+salary+");";
		String query2 = "INSERT INTO adminLogin VALUES ('"+newId+"','"+newPass+"',"+status+");";
		System.out.println(query1);
		System.out.println(query2);
        
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b23", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query1);
			stm.execute(query2);
			stm.close();
			con.close();
			JOptionPane.showMessageDialog(this, "Success !!!");
			ManagerHome mh=new ManagerHome(userId);
			mh.setVisible(true);
			this.setVisible(false);
		}
        catch(Exception ex)
		{
			JOptionPane.showMessageDialog(this, "Oops !!!");
        }
    }	
}