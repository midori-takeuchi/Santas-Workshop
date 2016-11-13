package Children;

import java.sql.*;
import Children.ToyAssigned;
import Children.Wishlist;

public class Children {
	
	public static void main(String[] args) throws SQLException {
		//Children.addToWishlist(17683634, 39927447, 3);
		Children.viewWishlist(17683634);
	}

	public static boolean addToWishlist(int ChildID, int ToyID, int quantity) throws SQLException{
		
		int WishlistID = Wishlist.getWishlistID(ChildID);
		
		ToyAssigned.insertToy(WishlistID, ToyID, quantity);
		
		return true;
		
	}
	
	//display all the rows of the child's Wishlist table
	public static void viewWishlist(int ChildID) throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection con = DriverManager.getConnection(
	    		"jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
	    int ToyID;
	    int qty;
	    int WishListID = Wishlist.getWishlistID(ChildID);

		try{
		  Statement stmt = con.createStatement();

		  ResultSet rs = stmt.executeQuery("SELECT * FROM assigned WHERE WishlistID=" + WishListID);
		  // get info on ResultSet
		  ResultSetMetaData rsmd = rs.getMetaData();

		  // get number of columns
		  int numCols = rsmd.getColumnCount();

		  System.out.println(" ");
		  
		  // display column names;
		  for (int i = 0; i < numCols; i++)
		  {
		      // get column name and print it

		      System.out.printf("%-15s", rsmd.getColumnName(i+1));    
		  }

		  System.out.println(" ");

		  while(rs.next())
		  {
		      // for display purposes get everything from Oracle 
		      // as a string

		      // simplified output formatting; truncation may occur

		      WishListID = rs.getInt("WishListID");
		      System.out.printf("%-10.10s", WishListID);

		      ToyID = rs.getInt("ToyID");
		      System.out.printf("%-20.20s", ToyID);

		      qty = rs.getInt("Quantity");
		      System.out.printf("%-15.15s", qty);

		     
		  }
	 
		  // close the statement; 
		  // the ResultSet will also be closed
		  stmt.close();
		}
		catch (SQLException ex)
		{
		    System.out.println("Message: " + ex.getMessage());
		}
	}
	
	
	
}
