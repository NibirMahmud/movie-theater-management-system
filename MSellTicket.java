import java.lang.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class MSellTicket extends JFrame implements ActionListener
{
	JLabel seatLabel,sellLabel,nameLabel,dateLabel,timeLabel,asLabel,sLabel,customerLabel;
	JButton addEmployeeBtn, viewEmployeeBtn, logoutBtn,sdBtn,timeBtn,ssBtn,refreshBtn,backBtn,sellBtn;
	JPanel panel;
	JTextField seatTF;
	JComboBox movieCombo,dateCombo,timeCombo,customerCombo;
	String userId;
	String movId[]=new String[10];
	String dates[]=new String[5];
	String times[]=new String[5];
	int totalMovie=0,id=0,totalDate=0,totalTime=0,seats,totalCustomer=0,as;
	String movieName[]= new String[10];
	String customerId[]= new String[10];
	public MSellTicket(String userId)
	{
		super("");
		
		this.userId = userId;
		this.setSize(1300,700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font labelFont=new Font("Arial",  Font.ITALIC+Font.BOLD, 25);
		Font slabelFont=new Font("Arial",  Font.ITALIC, 15);
		Font btnFont  =new Font("Arial",  Font.ITALIC, 25);
		Font comboFont=new Font("Arial",  Font.ITALIC, 20);
		panel = new JPanel();
		panel.setLayout(null);
		
		//label 
		
		Font sellFont=new Font("Cambria",  Font.ITALIC+Font.BOLD, 40);
		
		sellLabel=new JLabel("Sell Ticket");
		sellLabel.setBounds(550,50,300,60);
		sellLabel.setFont(sellFont);
		panel.add(sellLabel);
		
		customerLabel=new JLabel("Customer ID");
		customerLabel.setBounds(300,150,150,40);
		customerLabel.setFont(labelFont);
		panel.add(customerLabel);
		
		nameLabel=new JLabel("Movies");
		nameLabel.setBounds(300,220,150,40);
		nameLabel.setFont(labelFont);
		panel.add(nameLabel);
		
		dateLabel=new JLabel("Date");
		dateLabel.setBounds(300,290,150,40);
		dateLabel.setFont(labelFont);
		panel.add(dateLabel);
		dateLabel.setVisible(false);
		
		timeLabel=new JLabel("Times ");
		timeLabel.setBounds(300,360,150,40);
		timeLabel.setFont(labelFont);
		panel.add(timeLabel);
		timeLabel.setVisible(false);
		
		seatLabel=new JLabel("Seats  ");
		seatLabel.setBounds(300,430,200,40);
		seatLabel.setFont(labelFont);
		panel.add(seatLabel);
		seatLabel.setVisible(false);
		
		seatTF=new JTextField("");
		seatTF.setBounds(500,430,300,40);
		seatTF.setFont(comboFont);
		panel.add(seatTF);
		seatTF.setVisible(false);
		
		asLabel=new JLabel("Available seats  ");
		asLabel.setBounds(820,430,120,40);
		asLabel.setFont(slabelFont);
		asLabel.setForeground(Color.RED);
		panel.add(asLabel);
		asLabel.setVisible(false);
		
		sLabel=new JLabel(" "+seats);
		sLabel.setBounds(950,430,50,40);
		sLabel.setFont(slabelFont);
		sLabel.setForeground(Color.RED);
		panel.add(sLabel);
		sLabel.setVisible(false);
		
		//getcoustomer id
		
		String itemc[] = new String[10];
		
		
		
		String query = "SELECT `userid` FROM `userinfo` ;";     
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
			
			int j=0;
			while(rs.next())
			{
               itemc[j]=rs.getString("userid");
			   System.out.println(itemc[j]);
			   totalCustomer++;
			   j++;
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
		
		
		
		for(int j=0;j<totalCustomer;j++)
		{
			customerId[j]=itemc[j];
		}
		
		customerCombo= new JComboBox(customerId);
		customerCombo.setBounds(500,150,300,40);
		customerCombo.setFont(comboFont);
		customerCombo.addActionListener(this);
		panel.add(customerCombo);
		
		//get movie name 
		
		String item[] = new String[10];

		String querym = "SELECT `movieName`,`movieId` FROM `movieinfo` ;";     
        Connection conm=null;//for connection
        Statement stm = null;//for query execution
		ResultSet rsm = null;//to get row by row result from DB
		System.out.println(querym);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");//load driver
			System.out.println("driver loaded");
			conm = DriverManager.getConnection("jdbc:mysql://localhost:3306/b23","root","");
			System.out.println("connection done");//connection with database established
			stm = conm.createStatement();//create statement
			System.out.println("statement created");
			rsm = stm.executeQuery(querym);//getting result
			System.out.println("results received");
			
			int i=0;
			while(rsm.next())
			{
               item[i]=rsm.getString("movieName");
			   movId[i]=rsm.getString("movieId");
			   System.out.println(movId[i]);
			   totalMovie++;
			   i++;
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
                if(rsm!=null)
					rs.close();

                if(stm!=null)
					st.close();

                if(conm!=null)
					con.close();
            }
            catch(Exception ex){}
        }
		
		
		
		for(int i=0;i<totalMovie;i++)
		{
			movieName[i]=item[i];
		}
		
		movieCombo= new JComboBox(movieName);
		movieCombo.setBounds(500,220,300,40);
		movieCombo.setFont(comboFont);
		movieCombo.addActionListener(this);
		panel.add(movieCombo);
		
		//movie name got;
		
		//date
		
		dateCombo= new JComboBox(dates);
		dateCombo.setBounds(500,290,300,40);
		dateCombo.setFont(comboFont);
		dateCombo.setEditable(true);
		dateCombo.addActionListener(this);
		panel.add(dateCombo);
		dateCombo.setVisible(false);
		
		//time 
		
		timeCombo= new JComboBox(times);
		timeCombo.setBounds(500,360,300,40);
		timeCombo.setFont(comboFont);
		timeCombo.setEditable(true);
		timeCombo.addActionListener(this);
		panel.add(timeCombo);
		timeCombo.setVisible(false);
		
		//button
		
		logoutBtn = new JButton("Logout");
		logoutBtn.setBounds(1130, 30, 120, 40);
		logoutBtn.setFont(btnFont);
		logoutBtn.addActionListener(this);
		panel.add(logoutBtn);
		
		
		sdBtn=new JButton("Show Date");
		sdBtn.setBounds(850,220,180,40);
		sdBtn.setFont(btnFont);
		sdBtn.addActionListener(this);
		panel.add(sdBtn);
		
		timeBtn=new JButton("Show Time");
		timeBtn.setBounds(850,290,180,40);
		timeBtn.setFont(btnFont);
		timeBtn.addActionListener(this);
		panel.add(timeBtn);
		timeBtn.setVisible(false);
		
		ssBtn=new JButton("Show Seat");
		ssBtn.setBounds(850,360,180,40);
		ssBtn.setFont(btnFont);
		ssBtn.addActionListener(this);
		panel.add(ssBtn);
		ssBtn.setVisible(false);
		
		refreshBtn=new JButton("Refresh");
		refreshBtn.setBounds(300,500,150,40);
		refreshBtn.setFont(btnFont);
		refreshBtn.addActionListener(this);
		panel.add(refreshBtn);
		refreshBtn.setVisible(false);
		
		backBtn=new JButton("Back");
		backBtn.setBounds(500,500,150,40);
		backBtn.setFont(btnFont);
		backBtn.addActionListener(this);
		panel.add(backBtn);
		
		sellBtn=new JButton("Sell");
		sellBtn.setBounds(700,500,150,40);
		sellBtn.setFont(btnFont);
		sellBtn.addActionListener(this);
		panel.add(sellBtn);
		sellBtn.setVisible(false);
		
			
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String text = ae.getActionCommand();
		
		if(text.equals(logoutBtn.getText()))
		{
			UserLogin ul=new UserLogin();
			ul.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(sdBtn.getText()))
		{
			//get movie id
			
			
			String mName=movieCombo.getSelectedItem().toString();
			
			for(int i=0;i<totalMovie;i++)
			{
				if(mName.equals(movieName[i]))
				{
					id=i;
					break;
				}
			}
			String itemd[]=new String[5];
			String query =  "SELECT `date` FROM `movie_timeinfo` WHERE `movieId`='"+movId[id]+"';";      
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
			
				int i=0;
				while(rs.next())
				{
					itemd[i]=rs.getString("date");
					System.out.println(itemd[i]);
					totalDate++;
					i++;
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
			
			dateCombo.removeAllItems();
		
			for(int i=0;i<5;i++)
			{
				dates[i]=itemd[i];
				System.out.println(dates[i]);
				dateCombo.insertItemAt(dates[i], i);
			}
			dateCombo.setSelectedIndex(0);
			
			dateLabel.setVisible(true);
			dateCombo.setVisible(true);
			timeBtn.setVisible(true);
			timeLabel.setVisible(false);
			timeCombo.setVisible(false);
			refreshBtn.setVisible(true);
			ssBtn.setVisible(false);
			asLabel.setVisible(false);
			sLabel.setVisible(false);
			seatLabel.setVisible(false);
			seatTF.setVisible(false);
			sellBtn.setVisible(false);
			
		}
		else if(text.equals(timeBtn.getText()))
		{
			//get movie id
			
			
			String mName=movieCombo.getSelectedItem().toString();
			String mDate=dateCombo.getSelectedItem().toString();
			
			for(int i=0;i<totalMovie;i++)
			{
				if(mName.equals(movieName[i]))
				{
					id=i;
					break;
				}
			}
			String itemt[]=new String[5];
			String query =  "SELECT `time` FROM `movie_timeinfo` WHERE `movieId`='"+movId[id]+"' and `date`='"+mDate+"';";      
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
			
				int i=0;
				while(rs.next())
				{
					itemt[i]=rs.getString("time");
					System.out.println(itemt[i]);
					totalTime++;
					i++;
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
			
			timeCombo.removeAllItems();
		
			for(int i=0;i<3;i++)
			{
				times[i]=itemt[i];
				System.out.println(times[i]);
				timeCombo.insertItemAt(times[i], i);
			}
			timeCombo.setSelectedIndex(0);
			
			timeLabel.setVisible(true);
			timeCombo.setVisible(true);
			ssBtn.setVisible(true);
			asLabel.setVisible(false);
			sLabel.setVisible(false);
			seatLabel.setVisible(false);
			seatTF.setVisible(false);
			sellBtn.setVisible(false);
		}
		else if(text.equals(ssBtn.getText()))
		{
			//get available seat
			
			
			String mName=movieCombo.getSelectedItem().toString();
			String mDate=dateCombo.getSelectedItem().toString();
			String mTime=timeCombo.getSelectedItem().toString();
			
			for(int i=0;i<totalMovie;i++)
			{
				if(mName.equals(movieName[i]))
				{
					id=i;
					break;
				}
			}
			String query =  "SELECT availableseat FROM `movie_timeinfo` WHERE `movieId`='"+movId[id]+"' and `date`='"+mDate+"' and `time`='"+mTime+"';";      
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
			
				int i=0;
				while(rs.next())
				{
					seats=rs.getInt("availableseat");
					System.out.println(seats);
					
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
			
			
			sLabel.setText(""+seats);
			asLabel.setVisible(true);
			sLabel.setVisible(true);
			seatLabel.setVisible(true);
			seatTF.setVisible(true);
			sellBtn.setVisible(true);

			
		}
		else if(text.equals(refreshBtn.getText()))
		{
			dateLabel.setVisible(false);
			dateCombo.setVisible(false);
			timeBtn.setVisible(false);
			timeLabel.setVisible(false);
			timeCombo.setVisible(false);
			ssBtn.setVisible(false);
			asLabel.setVisible(false);
			sLabel.setVisible(false);
			seatLabel.setVisible(false);
			seatTF.setVisible(false);
			sellBtn.setVisible(false);
			refreshBtn.setVisible(false);
			
		}
		else if(text.equals(backBtn.getText()))
		{
			ManagerHome mh= new ManagerHome(userId);
			mh.setVisible(true);
			this.setVisible(false);
		}
		else if(text.equals(sellBtn.getText()))
		{
			String cId=customerCombo.getSelectedItem().toString();
			String mName=movieCombo.getSelectedItem().toString();
			String mDate=dateCombo.getSelectedItem().toString();
			String mTime=timeCombo.getSelectedItem().toString();
			String p=seatTF.getText();
			try{
			int bs=Integer.parseInt(p);
			
			for(int i=0;i<totalMovie;i++)
			{
				if(mName.equals(movieName[i]))
				{
					id=i;
					break;
				}
			}
			String query =  "SELECT availableseat FROM `movie_timeinfo` WHERE `movieId`='"+movId[id]+"' and `date`='"+mDate+"' and `time`='"+mTime+"';";      
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
			
				int i=0;
				try
				{
					
					while(rs.next())
					{
						as=rs.getInt("availableseat");
						System.out.println(as);
					
					}
					if(bs<as)
					{
						if(bs>0)
						{
					
							String query3="UPDATE  `movie_timeinfo` SET `availableSeat`="+(as-bs)+" WHERE `movieId`='"+movId[id]+"' and `date`='"+mDate+"' and `time`='"+mTime+"';";    
							String query1 = "INSERT INTO soldticket VALUES ('"+cId+"','"+mName+"','"+ mDate+"','"+mTime+"',"+bs+");";
							try
							{
								st.execute(query1);
								System.out.println(query1);
								st.execute(query3);
								System.out.println(query3);
								System.out.println("Ticket Sold");
						
								JOptionPane.showMessageDialog(this,"Ticket Sold");
						
								dateLabel.setVisible(false);
								dateCombo.setVisible(false);
								timeBtn.setVisible(false);
								timeLabel.setVisible(false);
								timeCombo.setVisible(false);
								ssBtn.setVisible(false);
								asLabel.setVisible(false);
								sLabel.setVisible(false);
								seatLabel.setVisible(false);
								seatTF.setVisible(false);
								sellBtn.setVisible(false);
								refreshBtn.setVisible(false);
							}
							catch(Exception ex)
							{}
						}
						else
						{
							JOptionPane.showMessageDialog(this,"Invalid  Ticket Input");
							seatTF.setText("");
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Not Available seat");
						seatTF.setText("");
					}
				}
				catch(Exception ex)
				{
					JOptionPane.showMessageDialog(this,"Wrong Input");
					seatTF.setText("");
				}
				
			}
			catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
				JOptionPane.showMessageDialog(this,"Invalid Input");
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
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(this,"Wrong Input");
				seatTF.setText("");
			}
			
		}
		
	}
	
	
}