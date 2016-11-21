package Children;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

import Elves.Elves;
import Santa.Santa;
/* We have extended the Frame class here,
 * thus our class "SimpleExample" would behave
 * like a Frame
 */
public class SimpleExample extends Frame{
	
	static Frame login;
    static Frame santaUI;
    static Frame elfUI;
    static Frame childUI;
    static int childlogin=0;
    static int elflogin=0;
	
    SimpleExample(){   //default constructor
    }
    
    public static Frame makeelfUI(){
 	 Frame eframe = new Frame();
    	 
    	 //text labels
    	 Label status = new Label("                           ");
    	 Label t1 = new Label("Mark task complete for:");
    	 Label m1 = new Label("                     View materials needed for:");
    	 
    	 //method buttons
    	 Button emethod1=new Button("Complete"); 

    	 
    	 //input fields
    	 TextField mark1 = new TextField("Elf ID");
    	 TextField mark2 = new TextField("Task ID");
    	 TextField mat3 = new TextField("Toy ID");

    	 //view buttons
    	 Button eview1=new Button("View Unassigned Tasks"); 
    	 Button eview2=new Button("View Low Inventory Materials"); 
    	 Button eview3=new Button("View Full Inventory Materials"); 
    	 Button eview4=new Button("View Overdue Tasks");
    	 Button eview5=new Button("View");
    	 Button eview6=new Button("View Highest Stock Material");
    	 Button eview7=new Button("View Lowest Stock Material");
    	 
    	 
    	 Button back =new Button("Back to Login"); 
    	 
         TextArea t = new TextArea();
         t.setEditable(false);
         t.setSize(50, 50);
         t.setText("Default Text");
         
         //add textfield
         eframe.add(t);
              
         //adding button into frame 
         eframe.add(t1);
         eframe.add(mark2);
         eframe.add(emethod1); 
         
         eframe.add(m1);
         eframe.add(mat3);
         eframe.add(eview5);

         
         eframe.add(eview1);
         eframe.add(eview2);
         eframe.add(eview3);
         eframe.add(eview4);
         eframe.add(eview6);
         eframe.add(eview7);
         
         eframe.add(status);
         
         eframe.add(back);
         
        
         //add actions to buttons
         emethod1.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
     	    	Elves elves = new Elves();
     	    	String res="";
     	    	
     	    	if (!(isNumeric(mark1.getText()))){
     	    		status.setText("Field must be numeric");
     	    	}
     	    	else{
     	    	int TaskID= Integer.parseInt(mark2.getText());

     	    	try {
     	    		res= elves.taskComplete(elflogin, TaskID);
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
     	    	status.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
     	    	}
     	    }
     	});
         
         eview1.addActionListener(new ActionListener() { 
      	    public void actionPerformed(ActionEvent e) { 
      	    	Elves elves = new Elves();
     	    	String res="";
     	    	
     	    	try {
     	    		res= elves.unassignedTask();
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
     	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
     	    	
     	    }
      	});

         
         eview2.addActionListener(new ActionListener() { 
      	    public void actionPerformed(ActionEvent e) { 
      	    	Elves elves = new Elves();
     	    	String res="";
     	    	
     	    	try {
     	    		res= elves.lowMaterials();
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
     	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
     	    	
     	    }
      	});
         
         eview3.addActionListener(new ActionListener() { 
       	    public void actionPerformed(ActionEvent e) { 
      	    	Elves elves = new Elves();
     	    	String res="";
     	    	
     	    	try {
     	    		res= elves.fullCapacity();
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
     	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
     	    	
     	    }
       	});
         
         eview4.addActionListener(new ActionListener() { 
        	    public void actionPerformed(ActionEvent e) { 
       	    	Elves elves = new Elves();
      	    	String res="";
      	    	
      	    	try {
      	    		res= elves.overdueTasks();
  				} catch (SQLException e1) {
  					// TODO Auto-generated catch block
  					e1.printStackTrace();
  				}
      	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
      	    	
      	    }
        	});
         
         eview5.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
    	    	Elves elves = new Elves();
   	    	String res="";
   	    	int toyID=0;
   	    	
 	    	if (!(isNumeric(mat3.getText()))){
 	    		status.setText("Field must be numeric");
 	    	}
 	    	
 	    	else {
 	    		int ToyID= Integer.parseInt(mat3.getText().trim());
   	    	try {
   	    		res= elves.matForToy(ToyID);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
 	    	}	
   	    }
     	});

         eview6.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
    	    	Elves elves = new Elves();
   	    	String res="";
   	    	
   	    	try {
   	    		res= elves.highestStockMaterial();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
   	    	
   	    }
     	});
         
         eview7.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
    	    	Elves elves = new Elves();
   	    	String res="";
   	    	
   	    	try {
   	    		res= elves.lowestStockMaterial();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
   	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
   	    	
   	    }
     	});
         // return to login button
         back.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
     	    
     	    	 login.setVisible(true);  
      	    	 santaUI.setVisible(false);  
     	    
     	    }

     	});
    
         //Setting Frame width and height
         eframe.setSize(500,500); 
         
         //Setting the title of Frame
         eframe.setTitle("ELF"); 
         
         //Setting the layout for the Frame
         eframe.setLayout(new FlowLayout());
	   	return eframe; 
    }  
    

    
    public static Frame makechildUI(){
    	
    	Frame cframe = new Frame();
    	
    	 //text labels
   	 	Label status = new Label("                           ");
   	 	
   	 	Label w2 = new Label("of Toy");
   	    Label w3 = new Label("to Child");
   	 	
   	    Button back =new Button("Back to Login"); 
        
        TextArea t = new TextArea();
        t.setEditable(false);
        t.setSize(50, 50);
        t.setText("Default");
        
        //add textfield
        cframe.add(t);
        TextField wishlist1 = new TextField("Quantity");   
        TextField wishlist2 = new TextField("ToyID");
      

        
        //method buttons
        Button cmethod1=new Button("Add"); 
   	 	Button cmethod2=new Button("View Wishlist");  
   
        
        //adding button into frame 

        cframe.add(wishlist1);
        cframe.add(w2);
        cframe.add(wishlist2);
        cframe.add(w3);

        cframe.add(cmethod1); 
        cframe.add(cmethod2); 
        cframe.add(status);
        
        cframe.add(back);
        
        cmethod1.addActionListener(new ActionListener() { 
    	    public void actionPerformed(ActionEvent e) { 
    	    	
    	       	Children children = new Children();
      	    	String res="";
      	    	
      	    	if (!(isNumeric(wishlist1.getText()) || isNumeric(wishlist2.getText()))){
     	    		status.setText("Input be numeric");
     	    	}
      	    	else{
      	    	int quantity= Integer.parseInt(wishlist1.getText().trim());
      	    	int ToyID= Integer.parseInt(wishlist2.getText().trim());
      	    
  					try {
						res=children.updateWishlist(childlogin, ToyID, quantity);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  				  System.out.println(childlogin);	
      	    	status.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
      	    	}
      	    }

    	});
    
        cmethod2.addActionListener(new ActionListener() { 
    	    public void actionPerformed(ActionEvent e) { 
    	    	Children c = new Children();
    	    	String res="";
    	    	try {
					res= c.viewWishlist(36749821);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    	
    	    	t.setText(res);
    	    }
    	});
        
        back.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) {  
     	    	 login.setVisible(true);  
      	    	 childUI.setVisible(false);  
     	    }

     	});
     
    
        //Setting Frame width and height
        cframe.setSize(500,300); 
        
        //Setting the title of Frame
        cframe. setTitle("CHILD"); 
        
        //Setting the layout for the Frame
        cframe.setLayout(new FlowLayout());
       
        
        return cframe;
    }  
    
    
    
    public static Frame makesantaUI(){
    	 Frame sframe = new Frame();
    	 
    	 //text labels
    	 Label status = new Label("                           ");
    	 Label t1 = new Label("Transfer Elf");
    	 Label t2 = new Label("to Dept");
    	 Label r1 = new Label("                Reassign Task");
    	 Label r2 = new Label("to Elf");
    	 Label m1 = new Label("        Mark Child");
    	 Label m2 = new Label("as");
       	 Label o1 = new Label("View elves working over");
    	 Label o2 = new Label("hours");
    	 
    	 //method buttons
    	 Button smethod1=new Button("Transfer"); 
    	 Button smethod2=new Button("Reassign Task"); 
    	 Button smethod3=new Button("Mark Child"); 
    	 Button smethod4=new Button("View"); 
    	 
    	 //input fields
    	 TextField transfer1 = new TextField("Elf ID");
    	 TextField transfer2 = new TextField("Dept");
    	 TextField assign1 = new TextField("Elf ID");
    	 TextField assign2 = new TextField("Task ID");
       	 TextField mark1 = new TextField("Child ID");
    	 TextField mark2 = new TextField("Naughty(1)/Nice(0)");
       	 TextField overworked1 = new TextField("Hours");
       	 JCheckBox overworkedbox = new JCheckBox("Get Days");
    	 
    	 //view buttons
    	 Button sview1=new Button("View Elf Departments"); 
    	 Button sview2=new Button("View Elf Tasks"); 
    	 Button sview3=new Button("View Children"); 
    	 
    	 
    	 Button back =new Button("Back to Login"); 
    	 
         TextArea t = new TextArea();
         t.setEditable(false);
         t.setSize(50, 50);
         t.setText("Default Text");
         
         //add textfield
         sframe.add(t);
              
         //adding button into frame 
         sframe.add(t1);
         sframe.add(transfer1);
         sframe.add(t2);
         sframe.add(transfer2);
         sframe.add(smethod1); 
         
         sframe.add(r1);
         sframe.add(assign1);
         sframe.add(r2);
         sframe.add(assign2);
         sframe.add(smethod2); 

         sframe.add(m1);
         sframe.add(mark1);
         sframe.add(m2);
         sframe.add(mark2);
         sframe.add(smethod3);
         
         sframe.add(o1);
         sframe.add(overworked1);
         sframe.add(o2);
         sframe.add(overworkedbox);
         sframe.add(smethod4);
         
         sframe.add(sview1);
         sframe.add(sview2);
         sframe.add(sview3);
         
         sframe.add(status);
         
         sframe.add(back);
         
        
         //add actions to buttons
         smethod1.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
     	    	Santa santa = new Santa();
     	    	String res="";
     	    	
     	    	if (!(isNumeric(transfer1.getText()) || isNumeric(transfer1.getText()))){
     	    		status.setText("Field must be numeric");
     	    	}
     	    	else{
     	    	int id= Integer.parseInt(transfer1.getText());
     	    	int dpt= Integer.parseInt(transfer2.getText());
     	    	try {
     	    		res= santa.transferElf(id, dpt);
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
     	    	status.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
     	    	}
     	    }
     	});
         
         smethod2.addActionListener(new ActionListener() { 
      	    public void actionPerformed(ActionEvent e) { 
      	    	Santa santa = new Santa();
     	    	String res="";
     	    	
     	    	if (!(isNumeric(assign1.getText()) || isNumeric(assign2.getText()))){
     	    		status.setText("Field must be numeric");
     	    	}
     	    	else{
     	    	
     	    	int TaskID= Integer.parseInt(assign1.getText());
     	    	int ElfID= Integer.parseInt(assign2.getText());
     	    	
     	    	try {
     	    		res= santa.reassignTask(ElfID, TaskID);
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
     	    	status.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
     	    	}
     	    }
      	});
         
         smethod3.addActionListener(new ActionListener() { 
      	    public void actionPerformed(ActionEvent e) { 
     	    	Santa santa = new Santa();
      	    	String res="";
      	    	
      	    	if (!(isNumeric(mark1.getText()) || isNumeric(mark2.getText()))){
     	    		status.setText("Field must be numeric");
     	    	}
      	    	
      	    	else {
      	    	int ChildID=Integer.parseInt(mark1.getText());
      	    	int Naughty=Integer.parseInt(mark2.getText());
      	    	
  					try {
						res= santa.markChildren(ChildID, Naughty);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  				  status.setText(res);
      	    	  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
      	    	}
      	    }
      	});
         
         smethod4.addActionListener(new ActionListener() { 
      	    public void actionPerformed(ActionEvent e) { 
      	    	Santa santa = new Santa();
      	    	String res="";
      	    	
      	    	if (!(isNumeric(overworked1.getText()))){
     	    		status.setText("Hours must be numeric");
     	    	}
      	    	else{
      	    	int daysWorked= Integer.parseInt(overworked1.getText());
      	    	String daysorid="ID";
      	
      	    	if (overworkedbox.isSelected()){
      	    		daysorid="daysWorked";
      	    	 }

  					try {
						res= santa.viewOverworkedElves(daysWorked, daysorid);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
  			
      	    	t.setText(res);  // if we change the fullcapacity method to return a string, we can set the result to UI here instead of printing
      	    	}
      	    }
      	});
         
   // ---------------view methods ------------------
         
         sview1.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
     	    	Santa s = new Santa();
     	    	String res="";
     	    	
     	    	try {
 					res = s.viewElves();
 				} catch (SQLException e1) {
 					// TODO Auto-generated catch block
 					e1.printStackTrace();
 				}
     	    	
     	    	t.setText(res);
     	    }
     	});
         
         
         sview2.addActionListener(new ActionListener() { 
      	    public void actionPerformed(ActionEvent e) { 
      	    	Santa s = new Santa();
      	    	String res="";
      	    	
      	    	try {
  					res = s.viewTasksAssigned();
  				} catch (SQLException e1) {
  					// TODO Auto-generated catch block
  					e1.printStackTrace();
  				}
      	    	
      	    	t.setText(res);
      	    }
      	});
         
         sview3.addActionListener(new ActionListener() { 
       	    public void actionPerformed(ActionEvent e) { 
       	    	Santa s = new Santa();
       	    	String res="";
       	    	
       	    	try {
   					res = s.viewChildren();
   				} catch (SQLException e1) {
   					// TODO Auto-generated catch block
   					e1.printStackTrace();
   				}
       	    	
       	    	t.setText(res);
       	    }
       	});
         
         // return to login button
         back.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
     	    
     	    	 login.setVisible(true);  
      	    	 santaUI.setVisible(false);  
     	    
     	    }

     	});
    
         //Setting Frame width and height
         sframe.setSize(500,500); 
         
         //Setting the title of Frame
         sframe.setTitle("SANTA"); 
         
         //Setting the layout for the Frame
         sframe.setLayout(new FlowLayout());
	   	return sframe; 
    	
    }
    
    
    public static void main(String args[]) throws IOException{  
         // Creating the instance of Frame
         elfUI = makeelfUI();
         santaUI = makesantaUI();
         childUI = makechildUI();
         
      
         
         login = new Frame();
         Button loginbutton=new Button("Login"); 
         
         JLabel imgLabel = new JLabel(new ImageIcon("src/test.png"));
         login.add(imgLabel);
         
         TextField user= new TextField("Username");
         TextField pw = new TextField("Password");
         
         Label status = new Label("                                                   ");
         
         
         //add text fields into frame
         login.add(user);
         login.add(pw);
         
         
         //adding button into frame 
         login.add(loginbutton);
         
         //adding status label into farme
         login.add(status);
     
         
         //add login method to login button
         
         loginbutton.addActionListener(new ActionListener() { 
     	    public void actionPerformed(ActionEvent e) { 
     	    	String inputusername = user.getText();
     	    	String inputpw = pw.getText();
     	    	
     	   
     	   	
     	    	if (inputusername.equals("88391084")){
     	    	elflogin=Integer.parseInt(inputusername);
     	        login.setVisible(false);  
     	    	elfUI.setVisible(true);  
     	    	status.setText(""); //reset status text
     	    	}
     	    
     	    	if (inputusername.equals("Santa")){
     	    		if (inputpw.equals("SantaPW")){
     	    			login.setVisible(false);  
     	    			santaUI.setVisible(true);  
     	    			status.setText(""); //reset status text
     	    		}
     	    		else  status.setText("Invalid Password");
     	    	}
     	    
     	    	if (inputusername.equals("36749821")){
     	    		childlogin=Integer.parseInt(inputusername);
     	    		login.setVisible(false);  
     	    		childUI.setVisible(true); 
     	    		status.setText(""); //reset status text
     	    	}
     	    	
     	    }
 
         });
     
         
         //Setting Frame width and height
         login.setSize(550,400); 
         
         //Setting the title of Frame
         login.setTitle("SANTA'S WORKSHOP LOGIN"); 
         
         //Setting the layout for the Frame
          login.setLayout(new FlowLayout());
         
         /* By default frame is not visible so 
          * we are setting the visibility to true 
          * to make it visible.
          */
         login.setVisible(true);  
         
      
         
    }
    
    public static boolean isNumeric(String s) {  
        return s.matches("[-+]?\\d*\\.?\\d+");  
    } 
    
 
}