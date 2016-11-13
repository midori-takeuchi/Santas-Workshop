package Children;

import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Wishlist {

	public static int getWishlistID(int ChildID) throws SQLException{
		int WishlistID = 0;
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement();
		
		try {
			
			ResultSet rs= stmt.executeQuery("SELECT WishlistID FROM wishlist_created WHERE ChildID=" +ChildID);

			while (rs.next()){
				WishlistID= rs.getInt("WishlistID");
			}
			stmt.close();
		}
		catch (SQLException ex)
		{
			System.out.println("Message: "  + ex.getMessage());
			try 
			{
				// undo the insert
				con.rollback();	
			}
			catch (SQLException ex2)
			{
				System.out.println("Message: " + ex2.getMessage());
				System.exit(-1);
			}
		}
		return WishlistID;	
	}
	
	
	
}
