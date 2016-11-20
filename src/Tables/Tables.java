package Tables;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tables {

	public static void main(String[] args) throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:ug", "ora_v3w8", "a36577120");
		
		Statement stmt = con.createStatement();
		
		String dropChildren = "DROP TABLE children CASCADE CONSTRAINT ";
		String dropWishlistCreated = "DROP TABLE wishlist_created CASCADE CONSTRAINT ";
		String dropAssigned = "DROP TABLE assigned ";
		String dropToy = "DROP TABLE toy CASCADE CONSTRAINT ";
		String dropMatRequired = "DROP TABLE mat_required ";
		String dropAssociated = "DROP TABLE associated ";
		String dropTasksAssigned = "DROP TABLE tasks_assigned CASCADE CONSTRAINT ";
		String dropMaterials = "DROP TABLE materials CASCADE CONSTRAINT ";
		String dropStockpile = "DROP TABLE stockpile ";
		String dropElves = "DROP TABLE elves CASCADE CONSTRAINT ";
		String dropProficientWith = "DROP TABLE proficient_with ";
		String dropTools = "DROP TABLE tools CASCADE CONSTRAINT ";
		String dropComplex = "DROP TABLE complex ";
		String dropSimple = "DROP TABLE simple ";
		String dropToolRequired = "DROP TABLE tool_required ";
		
		String children = "create table children" +
				"(ChildID int not null, " +
				" name varchar(30), " +
				" address varchar(50), " +
				" naughty int, " +
				" primary key (ChildID))";
		
		String wishlist_created = "create table wishlist_created" +
				"(WishListID int not null, " + 
				" ChildID int, " +
				" primary key (WishListID), " +
				" foreign key (ChildID) references children ON DELETE CASCADE)";
		
		String toy = "create table toy" + 
				"(ToyID int not null, " +
				" NumberBuild int, " +
				" primary key (ToyID))";
		
		String elves = "create table elves" + 
				"(ElfID int not null, " +
				" Department varchar(2), " +
				" Building varchar(2), " +
				"primary key (ElfID))";
		
		String tools = "create table tools" +
				"(ToolID int not null, " +
				" primary key (ToolID))";
		
		String assigned = "create table assigned" +
				"(WishListID int not null, " +
				" ToyID int not null, " +
				" Quantity int, " +
				" primary key(WishListID, ToyID), " +
				" foreign key(WishListID) references wishlist_created ON DELETE CASCADE, " +
				" foreign key(ToyID) references toy ON DELETE CASCADE)";
		
		String materials = "create table materials" +
				"(MaterialID int not null, " +
				" InventoryQuantity int, " +
				" InventoryLimit int, " +
				" primary key (MaterialID))";
		
		String mat_required = "create table mat_required" +
				"(ToyID int not null, " +
				" MaterialID int not null, " +
				" quantity int, " +
				" primary key(ToyID, MaterialID), " +
				" foreign key(ToyID) references toy ON DELETE CASCADE, " +
				" foreign key(MaterialID) references materials ON DELETE CASCADE)";
		
		String tasks_assigned = "create table tasks_assigned" +
				"(TaskID int not null, " +
				" ElfID int, " +
				" StartDate date, " +
				" EndDate date, " +
				" DueDate date, " +
				" DaysWorked int, " + 
				" primary key (TaskID), " +
				" foreign key(ElfID) references elves ON DELETE CASCADE)";
		
		String associated = "create table associated" + 
				"(ToyID int not null, " +
				" TaskID int not null, " +
				" primary key(ToyID, TaskID), " +
				" foreign key(ToyID) references toy ON DELETE CASCADE, " +
				" foreign key(TaskID) references tasks_assigned ON DELETE CASCADE)";
		
		String stockpile = "create table stockpile" +
				"(ElfID int not null, " +
				" MaterialID int not null, " +
				" Quantity int,	" +
				" primary key(ElfID, MaterialID), " +
				" foreign key(ElfID) references elves ON DELETE CASCADE, " +
				" foreign key(MaterialID) references materials ON DELETE CASCADE)";
		
		String proficient_with = "create table proficient_with" + 
				"(ToolID int not null, " +
				" ElfID int not null, " +
				" primary key(ToolID, ElfID), " +
				" foreign key(ToolID) references tools ON DELETE CASCADE, " +
				" foreign key(ElfID) references elves ON DELETE CASCADE)";
		
		String complex = "create table complex" + 
				"(ToolID int not null, " +
				" Department varchar(20), " +
				" primary key (ToolID), " +
				 "foreign key(ToolID) references tools ON DELETE CASCADE)";
		
		String simple = "create table simple" + 
				"(ToolID int not null, " +
				" Owner varchar(30), " +
				" primary key (ToolID), " +
				" foreign key(ToolID) references tools ON DELETE CASCADE)";
		
		String tool_required = "create table tool_required" + 
				"(ToolID int not null, " +
				" ToyID int not null, " +
				" primary key(ToolID, ToyID), " +
				" foreign key(ToolID) references tools ON DELETE CASCADE, " +
				" foreign key(ToyID) references toy ON DELETE CASCADE )";
		
		String insertChildren1 = "insert into children values (17683634, 'Susan Martin', '177 Green Street', 0)";
		String insertChildren2 = "insert into children values (36749821, 'Charles Harris', '422 Marigold Court', 1)";
		String insertChildren3 = "insert into children values (76387561, 'Linda Davis', '8734 Wesbrook Mall', 1)";
		String insertChildren4 = "insert into children values (83975346, 'Jennifer Thomas', '343 Agronomy Road', 0)";
		String insertChildren5 = "insert into children values (53957612, 'David Anderson', '6775 Main Mall', 1)";
		
		String insertWishlist1 = "insert into wishlist_created values (37658724, 17683634)";
		String insertWishlist2 = "insert into wishlist_created values (97482985, 36749821)";
		String insertWishlist3 = "insert into wishlist_created values (14673876, 76387561)";
		String insertWishlist4 = "insert into wishlist_created values (28478356, 83975346)";
		String insertWishlist5 = "insert into wishlist_created values (94736827, 53957612)";
		
		String insertToy1 = "insert into toy values (83927847, 3)";
		String insertToy2 = "insert into toy values (73875937, 6)";
		String insertToy3 = "insert into toy values (37580867, 10)";
		String insertToy4 = "insert into toy values (27223576, 8)";
		String insertToy5 = "insert into toy values (44938597, 5)";
		String insertToy6 = "insert into toy values (84739483, 4)";
		
		String insertElves1 = "insert into elves values (88391084, 'A', 'A')";
		String insertElves2 = "insert into elves values (12204125, 'B', 'B')";
		String insertElves3 = "insert into elves values (20014553, 'C', 'C')";
		String insertElves4 = "insert into elves values (31892187, 'D', 'D')";
		String insertElves5 = "insert into elves values (60215827, 'E', 'E')";
		
		String insertMaterials1 = "insert into materials values (33536974, 1, 10)";
		String insertMaterials2 = "insert into materials values (22142153, 2, 15)";
		String insertMaterials3 = "insert into materials values (43246980, 7, 8)";
		String insertMaterials4 = "insert into materials values (10742159, 15, 16)";
		String insertMaterials5 = "insert into materials values (50012453, 19, 20)";
		
		String insertTools1 = "insert into tools values (80038289)";
		String insertTools2 = "insert into tools values (11284928)";
		String insertTools3 = "insert into tools values (66271894)";
		String insertTools4 = "insert into tools values (33889498)";
		String insertTools5 = "insert into tools values (53289327)";
		String insertTools6 = "insert into tools values (75379754)";
		String insertTools7 = "insert into tools values (57844678)";
		String insertTools8 = "insert into tools values (96875423)";
		String insertTools9 = "insert into tools values (78547653)";
		String insertTools10 = "insert into tools values (15326875)";
		
		String insertTasksAssigned1 = "insert into tasks_assigned values (73926847, 88391084, '29-JUN-16', '29-JUL-16', '30-JUL-16', 7)";
		String insertTasksAssigned2 = "insert into tasks_assigned values (11324124, 12204125, '01-MAY-16', '20-JUL-16', '30-MAY-16', 4)";
		String insertTasksAssigned3 = "insert into tasks_assigned values (31223535, 20014553, '21-MAR-16', '10-APR-16', '12-APR-16', 2)";
		String insertTasksAssigned4 = "insert into tasks_assigned values (88798631, 31892187, '13-AUG-16', '30-AUG-16', '22-AUG-16', 1)";
		String insertTasksAssigned5 = "insert into tasks_assigned values (66261235, NULL, '22-JAN-16', '05-FEB-16', '28-JAN-16', 0)";
		
		String insertAssigned1 = "insert into assigned values (37658724, 83927847, 3)";
		String insertAssigned2 = "insert into assigned values (97482985, 73875937, 1)";
		String insertAssigned3 = "insert into assigned values (14673876, 37580867, 2)";
		String insertAssigned4 = "insert into assigned values (28478356, 27223576, 4)";
		String insertAssigned5 = "insert into assigned values (94736827, 44938597, 1)";
		
		String insertMatRequired1 = "insert into mat_required values (83927847, 33536974, 8)";
		String insertMatRequired2 = "insert into mat_required values (73875937, 22142153, 5)";
		String insertMatRequired3 = "insert into mat_required values (37580867, 43246980, 7)";
		String insertMatRequired4 = "insert into mat_required values (27223576, 10742159, 10)";
		String insertMatRequired5 = "insert into mat_required values (44938597, 50012453, 4)";
		
		String insertAssociated1 = "insert into associated values (83927847, 73926847)";
		String insertAssociated2 = "insert into associated values (73875937, 11324124)";
		String insertAssociated3 = "insert into associated values (37580867, 31223535)";
		String insertAssociated4 = "insert into associated values (27223576, 88798631)";
		String insertAssociated5 = "insert into associated values (44938597, 66261235)";
		
		String insertStockpile1 = "insert into stockpile values (88391084, 33536974, 3)";
		String insertStockpile2 = "insert into stockpile values (12204125, 22142153, 5)";
		String insertStockpile3 = "insert into stockpile values (20014553, 43246980, 7)";
		String insertStockpile4 = "insert into stockpile values (31892187, 10742159, 2)";
		String insertStockpile5 = "insert into stockpile values (60215827, 50012453, 6)";
		
		String insertProficientWith1 = "insert into proficient_with values (80038289, 88391084)";
		String insertProficientWith2 = "insert into proficient_with values (11284928, 12204125)";
		String insertProficientWith3 = "insert into proficient_with values (66271894, 20014553)";
		String insertProficientWith4 = "insert into proficient_with values (33889498, 31892187)";
		String insertProficientWith5 = "insert into proficient_with values (53289327, 60215827)";
		
		String insertComplex1 = "insert into complex values (80038289, 'A')";
		String insertComplex2 = "insert into complex values (11284928, 'B')";
		String insertComplex3 = "insert into complex values (66271894, 'C')";
		String insertComplex4 = "insert into complex values (33889498, 'D')";
		String insertComplex5 = "insert into complex values (53289327, 'E')";
		
		String insertSimple1 = "insert into simple values (75379754, 18924789)";
		String insertSimple2 = "insert into simple values (57844678, 20018491)";
		String insertSimple3 = "insert into simple values (96875423, 30012938)";
		String insertSimple4 = "insert into simple values (78547653, 49050328)";
		String insertSimple5 = "insert into simple values (15326875, 14891892)";
		
		String insertToolRequired1 = "insert into tool_required values (80038289, 83927847)";
		String insertToolRequired2 = "insert into tool_required values (11284928, 73875937)";
		String insertToolRequired3 = "insert into tool_required values (66271894, 37580867)";
		String insertToolRequired4 = "insert into tool_required values (33889498, 27223576)";
		String insertToolRequired5 = "insert into tool_required values (53289327, 44938597)";
		
		//drop tables so no duplication
		stmt.executeUpdate(dropChildren); 
		stmt.executeUpdate(dropWishlistCreated);
		stmt.executeUpdate(dropToy);
		stmt.executeUpdate(dropElves);
		stmt.executeUpdate(dropMaterials);
		stmt.executeUpdate(dropTools);
		stmt.executeUpdate(dropTasksAssigned);
		stmt.executeUpdate(dropAssigned);
		stmt.executeUpdate(dropMatRequired);
		stmt.executeUpdate(dropAssociated);
		stmt.executeUpdate(dropStockpile);
		stmt.executeUpdate(dropProficientWith);
		stmt.executeUpdate(dropComplex);
		stmt.executeUpdate(dropSimple);
		stmt.executeUpdate(dropToolRequired);
		
		//create table
		stmt.executeUpdate(children);
		stmt.executeUpdate(wishlist_created);
		stmt.executeUpdate(toy);
		stmt.executeUpdate(elves);
		stmt.executeUpdate(materials);
		stmt.executeUpdate(tools);
		stmt.executeUpdate(tasks_assigned);	
		stmt.executeUpdate(assigned);
		stmt.executeUpdate(mat_required);
		stmt.executeUpdate(associated);
		stmt.executeUpdate(stockpile);	
		stmt.executeUpdate(proficient_with);
		stmt.executeUpdate(complex);
		stmt.executeUpdate(simple);
		stmt.executeUpdate(tool_required);
		
		//populate tables
		stmt.executeUpdate(insertChildren1);
		stmt.executeUpdate(insertChildren2);
		stmt.executeUpdate(insertChildren3);
		stmt.executeUpdate(insertChildren4);
		stmt.executeUpdate(insertChildren5);
		
		stmt.executeUpdate(insertWishlist1);
		stmt.executeUpdate(insertWishlist2);
		stmt.executeUpdate(insertWishlist3);
		stmt.executeUpdate(insertWishlist4);
		stmt.executeUpdate(insertWishlist5);
		
		stmt.executeUpdate(insertToy1);
		stmt.executeUpdate(insertToy2);
		stmt.executeUpdate(insertToy3);
		stmt.executeUpdate(insertToy4);
		stmt.executeUpdate(insertToy5);
		stmt.executeUpdate(insertToy6);
		
		stmt.executeUpdate(insertElves1);
		stmt.executeUpdate(insertElves2);
		stmt.executeUpdate(insertElves3);
		stmt.executeUpdate(insertElves4);
		stmt.executeUpdate(insertElves5);
		
		stmt.executeUpdate(insertMaterials1);
		stmt.executeUpdate(insertMaterials2);
		stmt.executeUpdate(insertMaterials3);
		stmt.executeUpdate(insertMaterials4);
		stmt.executeUpdate(insertMaterials5);
		
		stmt.executeUpdate(insertTools1);
		stmt.executeUpdate(insertTools2);
		stmt.executeUpdate(insertTools3);
		stmt.executeUpdate(insertTools4);
		stmt.executeUpdate(insertTools5);
		stmt.executeUpdate(insertTools6);
		stmt.executeUpdate(insertTools7);
		stmt.executeUpdate(insertTools8);
		stmt.executeUpdate(insertTools9);
		stmt.executeUpdate(insertTools10);
		
		stmt.executeUpdate(insertTasksAssigned1);
		stmt.executeUpdate(insertTasksAssigned2);
		stmt.executeUpdate(insertTasksAssigned3);
		stmt.executeUpdate(insertTasksAssigned4);
		stmt.executeUpdate(insertTasksAssigned5);
		
		stmt.executeUpdate(insertAssigned1);
		stmt.executeUpdate(insertAssigned2);
		stmt.executeUpdate(insertAssigned3);
		stmt.executeUpdate(insertAssigned4);
		stmt.executeUpdate(insertAssigned5);
		
		stmt.executeUpdate(insertMatRequired1);
		stmt.executeUpdate(insertMatRequired2);
		stmt.executeUpdate(insertMatRequired3);
		stmt.executeUpdate(insertMatRequired4);
		stmt.executeUpdate(insertMatRequired5);
		
		stmt.executeUpdate(insertAssociated1);
		stmt.executeUpdate(insertAssociated2);
		stmt.executeUpdate(insertAssociated3);
		stmt.executeUpdate(insertAssociated4);
		stmt.executeUpdate(insertAssociated5);
		
		stmt.executeUpdate(insertStockpile1);
		stmt.executeUpdate(insertStockpile2);
		stmt.executeUpdate(insertStockpile3);
		stmt.executeUpdate(insertStockpile4);
		stmt.executeUpdate(insertStockpile5);
		
		stmt.executeUpdate(insertProficientWith1);
		stmt.executeUpdate(insertProficientWith2);
		stmt.executeUpdate(insertProficientWith3);
		stmt.executeUpdate(insertProficientWith4);
		stmt.executeUpdate(insertProficientWith5);
		
		stmt.executeUpdate(insertComplex1);
		stmt.executeUpdate(insertComplex2);
		stmt.executeUpdate(insertComplex3);
		stmt.executeUpdate(insertComplex4);
		stmt.executeUpdate(insertComplex5);
		
		stmt.executeUpdate(insertSimple1);
		stmt.executeUpdate(insertSimple2);
		stmt.executeUpdate(insertSimple3);
		stmt.executeUpdate(insertSimple4);
		stmt.executeUpdate(insertSimple5);
		
		stmt.executeUpdate(insertToolRequired1);
		stmt.executeUpdate(insertToolRequired2);
		stmt.executeUpdate(insertToolRequired3);
		stmt.executeUpdate(insertToolRequired4);
		stmt.executeUpdate(insertToolRequired5);
		
		con.close();
	}
	
}
