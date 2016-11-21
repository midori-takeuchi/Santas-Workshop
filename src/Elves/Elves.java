package Elves;

import java.sql.*;

import java.util.Date;

public class Elves {

	public static void main(String[] args) throws SQLException {

		Elves.unassignedTask();
		Elves.lowMaterials();
		Elves.fullCapacity();
		Elves.overdueTasks();
		Elves.taskComplete(88391084, 73926847);
		Elves.matForToy(83927847);
		Elves.highestStockMaterial();
		Elves.lowestStockMaterial();
	}

	// OUTPUT: List of TaskID that have not been assigned or "No unassigned
	// tasks found"
	// BASIC CASE: Print the set of tasks which is not associated with any elf
	// Print tasks from TasksAssignedTable where ElfId == NULL
	public static void unassignedTask() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String unAssigned = "SELECT TaskID FROM tasks_assigned WHERE ElfID IS NULL";

		ResultSet rs = stmt.executeQuery(unAssigned);

		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getString("TaskID"));
			}
		} else {
			System.out.println("No unassigned tasks found");
		}

		con.close();

	}

	// OUTPUT: "Low stock materials" or "Sufficient stock"
	// BASIC CASE: The system checks for materials that have less than 20% of
	// InventoryLimit in inventory and returns a list of them
	// Compare value (InventoryLimit * 0.2) to ____? -> quantity in stockpile?
	public static void lowMaterials() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String lowInventory = "SELECT MaterialID FROM materials WHERE InventoryQuantity < (InventoryLimit * 0.2)";

		ResultSet rs = stmt.executeQuery(lowInventory);

		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getString("MaterialID"));
			}
		} else {
			System.out.println("Sufficient stock");
		}

		con.close();

	}

	// OUTPUT: List of MaterialID that have over 90% of InventoryLimit in
	// inventory or "Not near full capacity"
	// BASIC CASE: System checks for materials that have over 90% of
	// InventoryLimit in inventory
	public static void fullCapacity() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement();

		String fullCapacity = "SELECT MaterialID FROM materials WHERE InventoryQuantity > (InventoryLimit * 0.9)";

		ResultSet rs = stmt.executeQuery(fullCapacity);

		if (rs != null) {
			while (rs.next()) {
				System.out.println(rs.getString("MaterialID"));
			}
		} else {
			System.out.println("Not near full capacity");
		}

		con.close();
	}

	// OUTPUT: List of TaskID that is due soon or overdue/"No tasks due soon or
	// overdue"
	// BASIC CASE: System checks if EndDate is later than DueDate
	public static void overdueTasks() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String overdue = "SELECT TaskID FROM tasks_assigned WHERE EndDate > DueDate";

		ResultSet rs = stmt.executeQuery(overdue);

		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getString("TaskID"));
			}
		} else {
			System.out.println("No tasks due soon or overdue");
		}
		con.close();
	}

	// OUTPUT: "You cannot complete this task", "Task completed"
	// BASIC CASE: Check that the elf has been assigned the task in question.

	public static void taskComplete(int elfId, int taskId) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String task = "SELECT * FROM tasks_assigned WHERE ElfID = " + elfId + "AND TaskID = " + taskId;
		ResultSet rs = stmt.executeQuery(task);
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println("Task completed");
			}
		} else {
			System.out.println("You cannot complete this task.");
		}
		con.close();
	}
	
	public static void matForToy(int toyID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String materials = "SELECT MaterialID, quantity FROM mat_required "
				+ "INNER JOIN associated ON mat_required.ToyID = associated.ToyID WHERE mat_required.ToyID = " + toyID;
		
		ResultSet rs = stmt.executeQuery(materials);
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getString("MaterialID"));
				System.out.println(rs.getString("quantity"));
			}
		} else {
			System.out.println("Invalid ToyID");
		}
		con.close();
	}
	
	public static void highestStockMaterial() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String highestStock = "SELECT * FROM (SELECT MaterialID, MAX(InventoryQuantity) AS HighestInventory from materials "
				+ "GROUP BY MaterialID ORDER BY MIN(InventoryQuantity) desc) WHERE rownum = 1";
		
		ResultSet rs = stmt.executeQuery(highestStock);
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getString("MaterialID"));
				System.out.println(rs.getString("HighestInventory"));
			}
		} else {
			System.out.println("No stock.");
		}
		con.close();
	}
	
	public static void lowestStockMaterial() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		String lowestStock = "SELECT * FROM (SELECT MaterialID, MIN(InventoryQuantity) AS LowestInventory from materials "
				+ "GROUP BY MaterialID ORDER BY MIN(InventoryQuantity) asc) WHERE rownum = 1";
		
		ResultSet rs = stmt.executeQuery(lowestStock);
		
		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				System.out.println(rs.getString("MaterialID"));
				System.out.println(rs.getString("LowestInventory"));
			}
		} else {
			System.out.println("No stock.");
		}
		con.close();
	}
}
