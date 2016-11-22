package Elves;

import java.sql.*;

import java.util.Date;

public class Elves {

	public static void main(String[] args) throws SQLException {

		 //Elves.unassignedTask();
		 //Elves.lowMaterials();
		//Elves.fullCapacity();
		// Elves.overdueTasks();
		//Elves.taskComplete(88391084, 73926847);
		//Elves.matForToy(83927847);
		Elves.highestStockMaterial();
		//Elves.lowestStockMaterial();
		Elves.checkmaterialgreater("InventoryQuantity", 15, "InventoryQuantity");
	}

	// OUTPUT: List of TaskID that have not been assigned or "No unassigned
	// tasks found"
	// BASIC CASE: Print the set of tasks which is not associated with any elf
	// Print tasks from TasksAssignedTable where ElfId == NULL
	public static String unassignedTask() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String res="";
		String unAssigned = "SELECT TaskID FROM tasks_assigned WHERE ElfID IS NULL";

		ResultSet rs = stmt.executeQuery(unAssigned);

		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+=rs.getString("TaskID") + "\n";
			}
		} else {
			res+="No unassigned tasks found";
		}

		con.close();
		String column="Task ID \n";
		return  column +res;

	}

	// OUTPUT: "Low stock materials" or "Sufficient stock"
	// BASIC CASE: The system checks for materials that have less than 20% of
	// InventoryLimit in inventory and returns a list of them
	// Compare value (InventoryLimit * 0.2) to ____? -> quantity in stockpile?
	public static String lowMaterials() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String lowInventory = "SELECT MaterialID FROM materials WHERE InventoryQuantity < (InventoryLimit * 0.2)";

		ResultSet rs = stmt.executeQuery(lowInventory);
		String res="";
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+=rs.getString("MaterialID")+"\n";
			}
		} else {
			res+="Sufficient stock";
		}

		con.close();
		String column="Material ID \n";
		return  column +res;

	}

	// OUTPUT: List of MaterialID that have over 90% of InventoryLimit in
	// inventory or "Not near full capacity"
	// BASIC CASE: System checks for materials that have over 90% of
	// InventoryLimit in inventory
	public static String fullCapacity() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement();

		String fullCapacity = "SELECT MaterialID FROM materials WHERE InventoryQuantity > (InventoryLimit * 0.9)";
		String res="";
		ResultSet rs = stmt.executeQuery(fullCapacity);

		if (rs != null) {
			while (rs.next()) {
				res+=rs.getString("MaterialID") +"\n";
			}
		} else {
			res+="Not near full capacity";
		}

		con.close();
		String column="Material ID \n";
		return  column +res;
	}

	// OUTPUT: List of TaskID that is due soon or overdue/"No tasks due soon or
	// overdue"
	// BASIC CASE: System checks if EndDate is later than DueDate
	public static String overdueTasks() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String overdue = "SELECT TaskID FROM tasks_assigned WHERE EndDate > DueDate";

		ResultSet rs = stmt.executeQuery(overdue);
		String res="";
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+=rs.getString("TaskID") +"\n";
			}
		} else {
			res+="No tasks due soon or overdue";
		}
		con.close();
		String column="Task ID \n";
		return  column +res;
	}

	// OUTPUT: "You cannot complete this task", "Task completed"
	// BASIC CASE: Check that the elf has been assigned the task in question.

	public static String taskComplete(int elfId, int taskId) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String task = "SELECT * FROM tasks_assigned WHERE ElfID = " + elfId + "AND TaskID = " + taskId;
		ResultSet rs = stmt.executeQuery(task);
		String res="";
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+="Task completed";
			}
		} else {
			res+="You cannot complete this task.";
		}
		con.close();
		return res;
	}
	
	public static String matForToy(int toyID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String materials = "SELECT MaterialID, quantity FROM mat_required "
				+ "INNER JOIN associated ON mat_required.ToyID = associated.ToyID WHERE mat_required.ToyID = " + toyID;
		
		ResultSet rs = stmt.executeQuery(materials);
		String res="";
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+=(rs.getString("MaterialID"))+"         ";
				res+=(rs.getString("quantity"));
			}
		} else {
			res+=("Invalid ToyID");
		}
		String column="Material ID      Quantity \n";
		return  column +res;
	}
	
	public static String highestStockMaterial() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String highestStock = "SELECT * FROM (SELECT MaterialID, MAX(InventoryQuantity) AS HighestInventory from materials "
				+ "GROUP BY MaterialID ORDER BY MIN(InventoryQuantity) desc) WHERE rownum = 1";
		
		ResultSet rs = stmt.executeQuery(highestStock);
		String res="";
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+=rs.getString("MaterialID") + "           ";
				res+=rs.getString("HighestInventory");
			}
		} else {
			res+="No stock.";
		}
		con.close();
		String column="Material ID      Inventory \n";
		return  column +res;
	}
	
	public static String lowestStockMaterial() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String lowestStock = "SELECT * FROM (SELECT MaterialID, MIN(InventoryQuantity) AS LowestInventory from materials "
				+ "GROUP BY MaterialID ORDER BY MIN(InventoryQuantity) asc) WHERE rownum = 1";
		
		ResultSet rs = stmt.executeQuery(lowestStock);
		String res="";
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+=rs.getString("MaterialID") + "           ";
				res+=rs.getString("LowestInventory");
			}
		} else {
			res+=("No stock.");
		}
		con.close();
		String column="Material ID        Inventory \n";
		return  column +res;
	}
	
	
	public static String checkmaterialgreater(String constant, int number, String attribute) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String matgreater = "SELECT " + attribute + " FROM materials"
				+ " WHERE " + constant +  " > " + number;
		
		
		ResultSet rs = stmt.executeQuery(matgreater);
		String res="";
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				res+=rs.getString(attribute) + "\n";
			}
		} else {
			res+=("No stock.");
		}
		con.close();
		String column=attribute+ " \n";
		System.out.println(column+res);
		return  column +res;

	}
}
