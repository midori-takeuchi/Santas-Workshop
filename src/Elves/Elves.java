package Elves;

import java.sql.*;

import java.util.Date;

public class Elves {

	public static void main(String[] args) throws SQLException {

		// Elves.unassignedTask();
		// Elves.lowMaterials();
		Elves.fullCapacity();
		// Elves.overdueTasks();
	}

	// OUTPUT: List of TaskID that have not been assigned or "No unassigned
	// tasks found"
	// BASIC CASE: Print the set of tasks which is not associated with any elf
	// Print tasks from TasksAssignedTable where ElfId == NULL
	public static void unassignedTask() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement();

		String unAssigned = "SELECT TaskID FROM tasks_assigned WHERE ElfID IS NULL";

		ResultSet rs = stmt.executeQuery(unAssigned);

		if (rs != null) {
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
		Statement stmt = con.createStatement();

		String lowInventory = "SELECT MaterialID FROM materials WHERE InventoryQuantity < (InventoryLimit * 0.2)";

		ResultSet rs = stmt.executeQuery(lowInventory);

		if (rs != null) {
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
		Statement stmt = con.createStatement();

		String overdue = "SELECT TaskID FROM tasks_assigned WHERE EndDate > DueDate";

		ResultSet rs = stmt.executeQuery(overdue);

		if (rs != null) {
			while (rs.next()) {
				System.out.println(rs.getString("TaskID"));
			}
		} else {
			System.out.println("No tasks due soon or overdue");
		}
	}

	// OUTPUT: "You cannot complete this task", "Task completed on time", "Task
	// not completed on time"
	// BASIC CASE: Check that the elf has been assigned the task in question.
	// Check that the elf has sufficient reserved materials to complete the
	// task.
	// Check that the elf has training in the required tools.
	// Debit the elf’s stock of reserved materials by the appropriate amount,
	// increase the number of the toy the task is associated with by the
	// appropriate amount, and add another task completed on time to the elf’s
	// record.
	public String taskComplete(int elfId, int taskId, Date completionDate) {
		return "";
	}
}
