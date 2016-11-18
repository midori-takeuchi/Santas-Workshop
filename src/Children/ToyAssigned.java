package Children;

import java.sql.*;

public class ToyAssigned {
	
	public static void main(String[] args) throws SQLException {
		ToyAssigned.insertToy(94893847, 84787489, 2);
	}
	
	public static void insertToy(int WishlistID, int ToyID, int quantity) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement();
		
		stmt.executeQuery("INSERT INTO assigned VALUES (" + WishlistID + "," + ToyID + "," + quantity + ")");

//		try {
//			ps = con.prepareStatement("INSERT INTO assigned VALUES (?,?,?)");
//			ps.setInt(1, WishlistID);
//			ps.setInt(2, ToyID);
//			ps.setInt(3, quantity);		
//			ps.executeUpdate();
//			con.commit();
//			ps.close();
//		}
//		catch (SQLException ex)
//		{
//			System.out.println("Message: "  + ex.getMessage());
//			try 
//			{
//				// undo the insert
//				con.rollback();	
//			}
//			catch (SQLException ex2)
//			{
//				System.out.println("Message: " + ex2.getMessage());
//				System.exit(-1);
//			}
//		}	
	}
	

}
