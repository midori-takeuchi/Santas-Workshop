package Santa;

import java.sql.*;

public class Santa {
	
	public static void main(String[] args) throws SQLException {

		 //Santa.transferElf(88391085, 'E');
		 //Santa.reassignTask(12204125, 11324124);
		 //Santa.markChildren(83975346, '0');
		//Santa.viewOverworkedElves(7, "daysWorked");
		Santa.printNaughtyNice(0);
		
	}

	// INPUT: An ElfID (int) and a Building (string)
	// OUTPUT: "Elf not transferred" or "Elf transferred successfully"
	// BASIC CASE: Change the department of the specified elf from their current
	// building to
	// the specified building
	public static void transferElf(int ElfID, int Department) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String ElfCheck = "SELECT ElfID FROM elves WHERE ElfID = " + ElfID;
		String transfer = "UPDATE elves SET Department = " + Department + " WHERE ElfID = " + ElfID;
		
		ResultSet rs = stmt.executeQuery(ElfCheck);

		if (rs.next()) {
			rs.beforeFirst();
			stmt.executeQuery(transfer);
			System.out.println("Elf transferred successfully!");
		} else {
			System.out.println("Elf not transferred");
		}
		con.close();
	}

	// INPUT: An ElfID (int) and a taskID (int)
	// OUTPUT: "Invalid task", "Invalid elf", "Elf cannot complete task", "Task
	// successfully reassigned"
	// BASIC CASE: Assigns a task to a new elf
	public static void reassignTask(int ElfID, int TaskID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String TaskCheck = "SELECT TaskID FROM tasks_assigned WHERE TaskID = " + TaskID;
		String ElfCheck = "SELECT ElfID FROM elves where ElfID = " + ElfID;
		

		ResultSet rs1 = stmt.executeQuery(TaskCheck);
		ResultSet rs2 = stmt.executeQuery(ElfCheck);

		if (rs1 != null) {
			if (rs2 != null) {
				stmt.executeQuery("UPDATE tasks_assigned SET ElfID =" + ElfID + "WHERE TaskID = " + TaskID);	
				System.out.println("Task reassigned!");
			} else {
				System.out.println("Invalid elf");
			}
		} else {
			System.out.println("Invalid task");
		}
		con.close();
	}

	// INPUT: A ChildID (int) and a NaughtyNice (char)
	// OUTPUT: "Child is now naughty", "Child is now nice", "Invalid child", "Invalid Naughty/Nice state. Valid states are T and F."
	// BASIC CASE: Updates whether a child is naughty or nice
	public static void markChildren(int ChildID, int Naughty) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String ChildCheck = "SELECT ChildID FROM children WHERE ChildID = " + ChildID;
		String behaviour = "UPDATE children SET naughty = " + Naughty + " WHERE ChildID = " + ChildID;

		ResultSet rs = stmt.executeQuery(ChildCheck);

		if (Naughty == 1 || Naughty == 0)
			if (rs.next()) {
				rs.beforeFirst();
				stmt.executeQuery(behaviour);
				if (Naughty == 1) {
					System.out.println("Child is now nice");
				} else {
					System.out.println("Child is now naughty");
				}
			} else {
				System.out.println("Invalid child");
			}
		else {
			System.out.println("Invalid Naughty/Nice state. Valid states are 0 (nice) and 1 (naughty).");
		}
		con.close();
	}

	// INPUT: numTasks, a number of tasks (int)
	// OUTPUT: A list of overworked elves, or "No overworked elves"
	// BASIC CASE: The system prints the ElfIDs of any elves who have worked >= daysWorked

	public static void viewOverworkedElves(int daysWorked, String daysOrID) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

		String overworked = "SELECT * FROM tasks_assigned WHERE DaysWorked >= " +  daysWorked;

		ResultSet rs = stmt.executeQuery(overworked);

		if (rs.next()) {
			rs.beforeFirst();
			while (rs.next()) {
				if (daysOrID == "ID") {
				System.out.println(rs.getString("ElfID"));
				} else if (daysOrID == "daysWorked") {
					System.out.println(rs.getString("DaysWorked"));
				}
			}
		} else {
			System.out.println("No overworked elves");
		}
		con.close();
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
}

