package Children;

import java.sql.*;
import Children.ToyAssigned;
import Children.Wishlist;

public class Children {
	
	public static void main(String[] args) throws SQLException {
		Children.updateWishlist(36749821, 27223576, 3);
		//Children.viewWishlist(17683634);
	}

	public static String updateWishlist(int ChildID, int ToyID, int quantity) throws SQLException{
		String res="";
		
		try{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		int wishlistID = Wishlist.getWishlistID(ChildID);
		
		String ToyCheck = "SELECT ToyID FROM toy WHERE ToyID = " + ToyID;
		ResultSet toyCheck = stmt.executeQuery(ToyCheck);
		
		String update = "INSERT INTO assigned values (" + wishlistID + "," + ToyID + "," + quantity + ")";
		
			if (toyCheck.next()) {
				toyCheck.beforeFirst();
				stmt.executeQuery(update);
				res+="Toy added to wishlist!";
			} else {
				res+="Toy does not exist.";
			}

		
		con.close();
		} catch (SQLException ex){
			res+="Toy already added";
			System.out.println(ex);
		}
		return res;
	}
	
	//display all the rows of the child's Wishlist table
	public static String viewWishlist(int ChildID) throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	    Connection con = DriverManager.getConnection(
	    		"jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
	    int ToyID;
	    int qty;
	    int WishListID = Wishlist.getWishlistID(ChildID);
	    String res="";
	    String columns="";

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

			  columns+= rsmd.getColumnName(i+1)+ "              ";  
		  }


		  while(rs.next())
		  {
		      // for display purposes get everything from Oracle 
		      // as a string

		      // simplified output formatting; truncation may occur

		      WishListID = rs.getInt("WishListID");
		      ToyID = rs.getInt("ToyID");
		      qty = rs.getInt("Quantity");
		   
		      res += (""+ WishListID+ "               ") + (""+ ToyID+"               ") + (""+ qty+ "               ") + "\n";
 
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
