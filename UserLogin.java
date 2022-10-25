import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UserLogin extends JFrame implements ActionListener
{
	JLabel title, userLabel, passLabel ,registerLabel;
	JTextField userTF;
	JPasswordField passPF;
	JButton loginBtn, exitBtn, regBtn,adminBtn;
	
	JPanel panel;
	
	public UserLogin()
	{
		super("B23");
		
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		Font titleFont = new Font("Cambria", Font.ITALIC + Font.BOLD, 35);
		
		title = new JLabel("MOVIE THEATER MANAGEMENGT SYSTEM");
		title.setBounds(300,150,1000,60);
		title.setFont(titleFont);
		panel.add(title);
		
		Font labelFont=new Font("Arial",  Font.ITALIC+Font.BOLD, 25);
		Font btnFont  =new Font("Arial",  Font.ITALIC, 25);
		
		Font tfFont=new Font("Arial",  Font.ITALIC, 20);
		
		userLabel = new JLabel("User ID      : ");
		userLabel.setBounds(350, 250, 150, 40);
		userLabel.setFont(labelFont);
		panel.add(userLabel);
		
		userTF = new JTextField();
		userTF.setBounds(550, 250, 250, 40);
		userTF.setFont(tfFont);
		panel.add(userTF);
		
		passLabel = new JLabel("Password  : ");
		passLabel.setBounds(350, 300, 150, 40);
		passLabel.setFont(labelFont);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(550, 300, 250, 40);
		passPF.setFont(tfFont);
		panel.add(passPF);
		
		loginBtn = new JButton("Login");
		loginBtn.setBounds(580, 380, 150, 40);
		loginBtn.setFont(btnFont);
		loginBtn.addActionListener(this);
		panel.add(loginBtn);
		
		
		exitBtn = new JButton("Exit");
		exitBtn.setBounds(400, 380, 150, 40);
		exitBtn.setFont(btnFont);
		exitBtn.addActionListener(this);
		panel.add(exitBtn);
		
		registerLabel=new JLabel("New Member ?");
		registerLabel.setBounds(400,480,200,40);
		registerLabel.setFont(labelFont);
		panel.add(registerLabel);
		
		regBtn = new JButton("Sign Up");
		regBtn.setBounds(630, 480, 150, 40);
		regBtn.setFont(btnFont);
		regBtn.addActionListener(this);
		panel.add(regBtn);
		
		adminBtn = new JButton("Admin ");
	    adminBtn.setBounds(1100, 25, 150, 40);
		adminBtn.setFont(btnFont);
		adminBtn.addActionListener(this);
		panel.add(adminBtn);
		
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(loginBtn.getText()))
		{
			checkLogin();
		}
		else if(text.equals(exitBtn.getText()))
		{
			System.exit(0);
		}
		else if(text.equals(regBtn.getText()))
		{
			Signup su= new Signup();
			su.setVisible(true);
			this.setVisible(false);
		}
		else if (text.equals(adminBtn.getText()))
		{
			AdminLogin al= new AdminLogin();
			al.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public void checkLogin()
	{
		String query = "SELECT `userid`, `password` FROM `userlogin`;";     
        Connection con=null;//for connection
        Statement st = null;//for query execution
		ResultSet rs = null;//to get row by row result from DB
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/b23","root","");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			
			boolean flag = false;			
			while(rs.next())
			{
                String userId = rs.getString("userid");
				String password = rs.getString("password");
				
				if(userId.equals(userTF.getText()) && password.equals(passPF.getText()))
				{
					flag=true;
					Customer c=new Customer(userId);
					c.setVisible(true);
					this.setVisible(false);
				}
			}
			
			if(!flag)
			{
				JOptionPane.showMessageDialog(this,"Invalid ID or Password");
				userTF.setText("");
				passPF.setText("");
			}
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
	}
}