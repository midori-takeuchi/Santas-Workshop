package Elves;
import java.sql.*;

import java.util.Date;

public class Elves {

	
	
	//OUTPUT: List of TaskID that have not been assigned or "No unassigned tasks found"
	//BASIC CASE: Print the set of tasks which is not associated with any elf 
	//Print tasks from TasksAssignedTable where ElfId == NULL
	public static void unassignedTask() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
        Statement stmt = con.createStatement();
        
//        String unAssigned = "SELECT TaskID FROM tasks_assigned WHERE ElfID IS NULL";
//        ResultSet rs = stmt.executeQuery(unAssigned);
        
          String all = "SELECT * FROM tasks_assigned";
          ResultSet rs = stmt.executeQuery(all);
        
        while (rs.next()) {
        	System.out.println(rs.getInt(1));
        }
        
		//TasksAssignedTable tasks = new TasksAssignedTable();
		//tasks.unassignedTasks();
        
        con.close();
		
	}
	
	//OUTPUT: "Low stock materials" or "Sufficient stock"
	//BASIC CASE: The system checks for materials that have less than 20% of InventoryLimit in inventory and returns a list of them
	public static void lowMaterials() throws SQLException {
		//MaterialsTable materials = new MaterialsTable();
		//materials.lowMaterials();
	}
	
	//OUTPUT: List of MaterialID that have over 90% of InventoryLimit in inventory or "Not near full capacity"
	//BASIC CASE: System checks for materials that have over 90% of InventoryLimit in inventory
	public String[] fullCapacity() {
		return new String[] {};
	}
	
	//OUTPUT: List of TaskID that is due soon or overdue/"No tasks due soon or overdue"
	//BASIC CASE: System checks for tasks that have EndDate within 7 days of current date or is overdue
	public String[] overdueTasks() {
		return new String[] {};
	}
	
	//OUTPUT: "You cannot complete this task", "Task completed on time", "Task not completed on time"
	//BASIC CASE: Check that the elf has been assigned the task in question. 
	//Check that the elf has sufficient reserved materials to complete the task. 
	//Check that the elf has training in the required tools. 
	//Debit the elf’s stock of reserved materials by the appropriate amount, increase the number of the toy the task is associated with by the appropriate amount, and add another task completed on time to the elf’s record. 
	public String taskComplete(int elfId, int taskId, Date completionDate) {
		return "";
	}
	
	public static void main(String[] args) throws SQLException{
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
        Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM tool_required");
        
        while (rs.next()) {
        	System.out.println(rs.getString("ToolID"));
        }

		//Elves.unassignedTask();
	}
}
