package Santa;

import java.sql.*;

public class Santa {
	
	public static void main(String[] args) throws SQLException {

		 //Santa.transferElf(88391085, 'E');
		 //Santa.reassignTask(12204125, 11324124);
		 //Santa.markChildren(83975346, '0');
		//Santa.viewOverworkedElves(2, "daysWorked");
		System.out.println("Message");
		//Santa.printNaughtyNice(0);
		Santa.division();
		
	}

	// INPUT: An ElfID (int) and a Building (string)
	// OUTPUT: "Elf not transferred" or "Elf transferred successfully"
	// BASIC CASE: Change the department of the specified elf from their current
	// building to
	// the specified building
	public static String transferElf(int ElfID, int Department) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String ElfCheck = "SELECT ElfID FROM elves WHERE ElfID = " + ElfID;
		String transfer = "UPDATE elves SET Department = " + Department + " WHERE ElfID = " + ElfID;
		
		String res="";
		ResultSet rs = stmt.executeQuery(ElfCheck);

		if (rs.next()) {
			rs.beforeFirst();
			stmt.executeQuery(transfer);
			res+="Elf transferred successfully!";
		} else {
			res+="Elf not transferred";
		}
		con.close();
		return res;
	}

	// INPUT: An ElfID (int) and a taskID (int)
	// OUTPUT: "Invalid task", "Invalid elf", "Elf cannot complete task", "Task
	// successfully reassigned"
	// BASIC CASE: Assigns a task to a new elf
	public static String reassignTask(int ElfID, int TaskID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String TaskCheck = "SELECT TaskID FROM tasks_assigned WHERE TaskID = " + TaskID;
		String ElfCheck = "SELECT ElfID FROM elves where ElfID = " + ElfID;
		
		String res="";

		try{
		ResultSet rs1 = stmt.executeQuery(TaskCheck);
		ResultSet rs2 = stmt.executeQuery(ElfCheck);
	
		
		if (rs1 != null) {
			if (rs2 != null) {
				
				stmt.executeQuery("UPDATE tasks_assigned SET ElfID =" + ElfID + "WHERE TaskID = " + TaskID);	
				res+="Task reassigned!";
				
			} else {
				res+="Invalid elf";
			}
		} else {
			res+="Invalid task";
		}
		con.close();
		
		}
		catch(SQLException ex){
			//System.out.println("SQL exception caught on update error");
			  res+="Invalid elf or task";
		}
		return res;
	}

	// INPUT: A ChildID (int) and a NaughtyNice (char)
	// OUTPUT: "Child is now naughty", "Child is now nice", "Invalid child", "Invalid Naughty/Nice state. Valid states are T and F."
	// BASIC CASE: Updates whether a child is naughty or nice
	public static String markChildren(int ChildID, int Naughty) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String ChildCheck = "SELECT ChildID FROM children WHERE ChildID = " + ChildID;
		String behaviour = "UPDATE children SET naughty = " + Naughty + " WHERE ChildID = " + ChildID;

		ResultSet rs = stmt.executeQuery(ChildCheck);
		String res="";
		
		if (Naughty == 1 || Naughty == 0)
			if (rs.next()) {
				rs.beforeFirst();
				stmt.executeQuery(behaviour);
				if (Naughty == 1) {
					res+= "Child is now naughty";
				} else {
					res+= "Child is now nice";
				}
			} else {
				res+="Invalid child";
			}
		else {
			res+="Invalid Naughty/Nice state. Valid states are 0 (nice) and 1 (naughty).";
		}
		con.close();
		return res;
	}

	// INPUT: numTasks, a number of tasks (int)
	// OUTPUT: A list of overworked elves, or "No overworked elves"
	// BASIC CASE: The system prints the ElfIDs of any elves who have worked >= daysWorked

	public static String viewOverworkedElves(int daysWorked, String daysOrID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String overworked = "SELECT * FROM tasks_assigned WHERE DaysWorked >= " +  daysWorked;
		String res="";
		ResultSet rs = stmt.executeQuery(overworked);

		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				if (daysOrID == "ID") {
				res+="ID:  " + rs.getString("ElfID") + "\n";
				} else if (daysOrID == "daysWorked") {
					res+=("ID:  " + rs.getString("ElfID"))+ "       ";
					res+= ("Days:  " + rs.getString("DaysWorked") + "\n");
				}
			}
		} else {
			res+=("No overworked elves");
		}
		con.close();
		return res;
	}
	
	// INPUT: Boolean value for Naughty
	// OUTPUT: A list of children who are naughty or nice, depending on user input
	// BASIC CASE: the system print the ChildIDs of children who are naughty or nice
	public static void printNaughtyNice(int Naughty) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String naughtyNice = "SELECT ChildID FROM children WHERE naughty = " + Naughty;
		ResultSet rs = stmt.executeQuery(naughtyNice);
		
		if (Naughty == 1 || Naughty == 0) {
			if (rs.next()) {
				rs.beforeFirst();
				while (rs.next()) {
					System.out.println(rs.getString("ChildID"));
				}
			} else {
				System.out.println("No children with this status.");
			}
		} else {
			System.out.println("Invalid Naughty/Nice state. Valid states are 0 (nice) and 1 (naughty).");
		}
		con.close();
	}
	
	
	//display all the rows of the elves/dept table
	public static String viewElves() throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection con = DriverManager.getConnection(
	    		"jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
	    String res= "";
	    String columns="";
	    int ElfID=0;
		String Department="";


		try{
		  Statement stmt = con.createStatement();

		  ResultSet rs = stmt.executeQuery("SELECT * FROM elves");
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();

		  // get number of columns
		  int numCols = rsmd.getColumnCount();

		 // System.out.println(" ");
		  
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it

		    //  System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		      columns+= rsmd.getColumnName(i+1)+ "     ";
		  }

		  System.out.println(" ");
		  	
		 
		  while(rs.next())
		  {
		      // for display purposes get everything from Oracle 
		      // as a string

		      // simplified output formatting; truncation may occur
			  
			

		      ElfID = rs.getInt("ElfID");
		     // System.out.printf("%-10.10s", ElfID);

		      Department = rs.getString("Department");
		     // System.out.printf("%-20.20s", Department);


		      res += (""+ ElfID+ "        ") +  (Department + "                       ")  + "\n";
		  }
		  
		  
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		}
		return columns+ "\n" + res;
		
	}
	
	
	
	//display all the rows of the child's Wishlist table
	public static String viewTasksAssigned() throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection con = DriverManager.getConnection(
	    		"jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
	    String res= "";
	    String columns="";
	    int TaskID=0;
		int ElfID=0;
		int DaysWorked=0;
		String StartDate="";
		String EndDate="";
		String DueDate="";
		try{
		  Statement stmt = con.createStatement();

		  ResultSet rs = stmt.executeQuery("SELECT * FROM tasks_assigned");
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();

		  // get number of columns
		  int numCols = rsmd.getColumnCount();

		 // System.out.println(" ");
		  
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it

		    //  System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		      columns+= rsmd.getColumnName(i+1)+ "                     ";
		  }

		 
		  while(rs.next())
		  {
		      // for display purposes get everything from Oracle 
		      // as a string

		      // simplified output formatting; truncation may occur

		      TaskID = rs.getInt("TaskID");
		      ElfID = rs.getInt("ElfID");
		      DaysWorked = rs.getInt("DaysWorked");
		      StartDate = rs.getString("StartDate");
		      DueDate = rs.getString("DueDate");
		      EndDate=rs.getString("EndDate");
		      
		      
		     // System.out.printf("%-15.15s", Building);

		      res += (""+ TaskID+ "        ") +  (ElfID + "           ") + (""+ StartDate+ "        ") + (""+ EndDate+ "        ")  +  (""+ DueDate+ "        ")+ (""+ DaysWorked+ "        ")+ "\n";
		  }
		  
		  
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		}
		return columns+ "\n" + res;
		
	}
	
	//display all the rows of the elves/dept table
	public static String viewChildren() throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection con = DriverManager.getConnection(
	    		"jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
	    String res= "";
	    String columns="";
	    int ChildID=0;
		String address="";
		int naughty=0;
		String name="";

		try{
		  Statement stmt = con.createStatement();

		  ResultSet rs = stmt.executeQuery("SELECT * FROM children");
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();

		  // get number of columns
		  int numCols = rsmd.getColumnCount();

		 // System.out.println(" ");
		  
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it

		    //  System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		      columns+= rsmd.getColumnName(i+1)+ "                  ";
		  }

		  System.out.println(" ");
		  	
		 
		  while(rs.next())
		  {
		      // for display purposes get everything from Oracle 
		      // as a string

		      // simplified output formatting; truncation may occur
			  
			

		      ChildID = rs.getInt("ChildID");
		     // System.out.printf("%-10.10s", ElfID);

		      address = rs.getString("address");
		     // System.out.printf("%-20.20s", Department);

		      naughty = rs.getInt("naughty");
		     // System.out.printf("%-15.15s", Building);
		      name = rs.getString("name");

		      res += (""+ ChildID+ "        ") + (""+ name+"        ")+  (address + "                       ") + (""+ naughty+ "        ") + "\n";
		  }
		  
		  
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		}
		return columns+ "\n" + res;
		
	}
	
	public static String division() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		String res="";
		String division = "Select DISTINCT W.WishListID "
				+ "FROM wishlist_created W "
				+ "WHERE NOT EXISTS ( SELECT T.ToyID "
				+ "FROM toy T "
				+ "WHERE NOT EXISTS ( SELECT A.ToyID "
				+ "FROM assigned A "
				+ "WHERE A.ToyID = T.ToyID "
				+ "AND A.WishListID = W.WishListID))";
		
		ResultSet rs = stmt.executeQuery(division);
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+="Wishlist ID: " + (rs.getString("WishListID"));
			}
		} else {
			res+=("No wishlist asks for all toys");
		}
		con.close();
		return res;
		
	}
	
	public static String deleteChild(int ChildID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String childCheck = "SELECT * FROM children WHERE ChildID = " + ChildID;
		ResultSet rs = stmt.executeQuery(childCheck);
		String res="";
		String delChild = "DELETE FROM children WHERE ChildID = " + ChildID;
		
		if (rs.next()) {
			stmt.execute(delChild);
			res+=("Child deleted");
		} else {
			res+=("Child does not exist!");
		}
		
		con.close();
		return res;
	}
	
	public static String deleteWishlist(int WishlistID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String wishlistCheck = "SELECT * FROM wishlist_created WHERE WishlistID = " + WishlistID;
		ResultSet rs = stmt.executeQuery(wishlistCheck);
		String res="";
		String delWishlist = "DELETE FROM wishlist_created WHERE WishlistID = " + WishlistID;
		
		if (rs.next()) {
			stmt.executeQuery(delWishlist);
			res+=("Wishlist deleted.");
		} else {
			res+=("Wishlist does not exist!");
		}
		return res;
	}
	
	public static String viewwishlistcreated() throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection con = DriverManager.getConnection(
	    		"jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
	    String res= "";
	    String columns="";
	    int WishListID=0;
		int ChildID=0;
		String name="";

		try{
		  Statement stmt = con.createStatement();

		  ResultSet rs = stmt.executeQuery("SELECT * FROM wishlist_created");
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();

		  // get number of columns
		  int numCols = rsmd.getColumnCount();

		 // System.out.println(" ");
		  
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it

		    //  System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		      columns+= rsmd.getColumnName(i+1)+ "         ";
		  }

		  System.out.println(" ");
		  	
		 
		  while(rs.next())
		  {
		      // for display purposes get everything from Oracle 
		      // as a string

		      // simplified output formatting; truncation may occur
			  
			

		      WishListID = rs.getInt("WishListID");
		     // System.out.printf("%-10.10s", ElfID);

		      ChildID = rs.getInt("ChildID");
		     // System.out.printf("%-15.15s", Building);


		      res += (""+ WishListID+ "        ") + (""+ ChildID+"        ")+  "\n";
		  }
		  
		  
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		  
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		}
		return columns+ "\n" + res;
		
	}
	
}

