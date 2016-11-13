package Santa;

import java.sql.*;

public class Santa {
	
	public static void main(String[] args) throws SQLException {

		// Santa.transferElf(88391084, "Engineering");
		// Santa.assignTask();
		// Santa.markChildren(17683634, 'F');
		Santa.viewOverworkedElves(1);
		
	}

	// INPUT: An ElfID (int) and a Building (string)
	// OUTPUT: "Elf not transferred" or "Elf transferred successfully"
	// BASIC CASE: Change the department of the specified elf from their current
	// building to
	// the specified building
	public static void transferElf(int ElfID, String Department) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_q3l8", "a29240116");
		Statement stmt = con.createStatement();

		String ElfCheck = "SELECT ElfID FROM elves WHERE ElfID = " + ElfID;
		String transfer = "UPDATE elves SET Department = " + Department + " WHERE ElfID = " + ElfID;
		
		ResultSet rs = stmt.executeQuery(ElfCheck);

		if (rs != null) {
			stmt.executeQuery(transfer);
			System.out.println("Elf transfereed successfully");
		} else {
			System.out.println("Elf not transferred");
		}
		con.close();
	}

	// INPUT: An ElfID (int) and a taskID (int)
	// OUTPUT: "Invalid task", "Invalid elf", "Elf cannot complete task", "Task
	// successfully assigned"
	// BASIC CASE: Assigns a task to an elf
	public static void assignTask(int ElfID, int TaskID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_q3l8", "a29240116");
		Statement stmt = con.createStatement();

		String ElfCheck = "SELECT TOP 1 Elves.ElfID FROM Elves WHERE Elves.ElfID = " + ElfID;
		String TaskCheck = "SELECT TOP 1 Tasks.TaskID FROM Tasks WHERE Tasks.TaskID = " + TaskID;

		ResultSet rs1 = stmt.executeQuery(ElfCheck);
		ResultSet rs2 = stmt.executeQuery(TaskCheck);

		if (rs1 != null) {
			if (rs2 != null) {
				ResultSet rs = stmt.executeQuery(
						"SELECT StartDate FROM tasks_assigned WHERE tasks_assigned.taskID = " + TaskID );
				String StartDate = rs.getString("StartDate");
				rs = stmt.executeQuery(
						"SELECT EndDate FROM tasks_assigned WHERE tasks_assigned.taskID = " + TaskID );
				String EndDate = rs.getString("EndDate");
				rs = stmt.executeQuery(
						"SELECT DueDate FROM tasks_assigned WHERE tasks_assigned.taskID = " + TaskID);
				String DueDate = rs.getString("DueDate");

				String assignTask = "INSERT INTO tasks_assigned VALUES (" + ElfID + ", " + TaskID + ", " + ", "
						+ StartDate + ", " + EndDate + ", " + DueDate;
				stmt.executeQuery(assignTask);
			} else {
				System.out.println("Invalid task");
			}
		} else {
			System.out.println("Invalid elf");
		}
		con.close();
	}

	// INPUT: A ChildID (int) and a NaughtyNice (char)
	// OUTPUT: "Child is now naughty", "Child is now nice", "Invalid child", "Invalid Naughty/Nice state. Valid states are T and F."
	// BASIC CASE: Updates whether a child is naughty or nice
	public static void markChildren(int ChildID, char Naughty) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_q3l8", "a29240116");
		Statement stmt = con.createStatement();

		String ChildCheck = "SELECT ChildID FROM children WHERE ChildID = " + ChildID;
		String behaviour = "UPDATE children SET naughty = " + Naughty + " WHERE ChildID = " + ChildID;

		ResultSet rs = stmt.executeQuery(ChildCheck);

		if (Naughty == 'T' || Naughty == 'F')
			if (rs != null) {
				stmt.executeQuery(behaviour);
				if (Naughty == 'T') {
					System.out.println("Child is now naughty");
				} else {
					System.out.println("Child is now nice");
				}
			} else {
				System.out.println("Invalid child");
			}
		else {
			System.out.println("Invalid Naughty/Nice state. Valid states are T and F.");
		}
		con.close();
	}

	// INPUT: numTasks, a number of tasks (int)
	// OUTPUT: A list of overworked elves, or "No overworked elves"
	// BASIC CASE: The system checks that the input is >= 0, and then prints the ElfIDs of any elves who have >= TaskNum tasks

	public static void viewOverworkedElves(int numTasks) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_q3l8", "a29240116");
		Statement stmt = con.createStatement();

		String overworked = "SELECT ElfID FROM Elves WHERE Elves.NumTasks >= " +  numTasks;

		ResultSet rs = stmt.executeQuery(overworked);

		if (rs != null) {
			while (rs.next()) {
				System.out.println(rs.getString("ElfID"));
			}
		} else {
			System.out.println("No overworked elves");
		}
		con.close();
	}
}
