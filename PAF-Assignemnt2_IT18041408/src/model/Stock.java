package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Stock {
	
	//A common method to connect to the DB 
private Connection connect() {
	Connection con = null;
	
	try {
		 Class.forName("com.mysql.jdbc.Driver");
		 //Provide the correct details: DBServer/DBName, username, password 
		 con= DriverManager.getConnection("jdbc:mysql://localhost/hcdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

		//For testing          
		 System.out.print("Successfully connected");
		 
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	return con; 
}

public String readStock() {  
	String output = "";  
	
	try {  
		Connection con = connect();  
		if (con == null)  {   
			return "Error while connecting to the database for reading.";  
		} 

		// Prepare the html table to be displayed   
		output = "<table border='1'><tr><th>Medicine Name</th>"
				+ "<th>Description</th><th>Amount</th>"
				+ "<th>Price</th>"	
				+ "<th>Update</th><th>Remove</th></tr>";


		  String query = "select * from stock";   
		  Statement stmt = con.createStatement();   
		  ResultSet rs = stmt.executeQuery(query); 

		  // iterate through the rows in the result set   
		  while (rs.next())   {  

			  	String id = Integer.toString(rs.getInt("id"));
				String mname = rs.getString("mname");
				String description = rs.getString("description");
				String amount = Integer.toString(rs.getInt("amount"));
				String price = Integer.toString(rs.getInt("price"));
			
			  // Add into the html table    

			  output += "<tr><td><input id='hidAppIDUpdate' name='hidAppIDUpdate' type='hidden' value='" + id + "'>" + mname + "</td>"; 

			  output += "<td>" + description + "</td>";
				output += "<td>" + amount + "</td>";
				output += "<td>" + price + "</td>"; 
			  
			// buttons     
			  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
			  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-id='"+ id +"'>"+"</td></tr>";

			} 
		  
		  con.close(); 

		  // Complete the html table   
		  output += "</table>"; 
		}
		catch (Exception e) {  
			output = "Error while reading the Stock.";  
			System.err.println(e.getMessage()); 
		}

		return output;
	}

//Insert Stock
public String insertStock(String mname, String description, String amount, String price) {
	String output = "";

	try {
		Connection con = connect();  

		if (con == null) {
			return "Error while connecting to the database";
		}

		// create a prepared statement   
		String query = " insert into stock (`id`,`mname`,`description`,`amount`,`price`)"+" values (?, ?, ?, ?, ?)";

		PreparedStatement preparedStmt = con.prepareStatement(query);

		// binding values 
		preparedStmt.setInt(1, 0);
		preparedStmt.setString(2, mname);
		preparedStmt.setString(3, description);
		preparedStmt.setString(4, amount);
		preparedStmt.setString(5,price);
		
		
		//execute the statement   
		preparedStmt.execute();   
		con.close(); 

		//Create JSON Object to show successful msg.
		String newStock = readStock();
		output = "{\"status\":\"success\", \"data\": \"" + newStock + "\"}";
	}
	catch (Exception e) {  
		//Create JSON Object to show Error msg.
		output = "{\"status\":\"error\", \"data\": \"Error while Inserting Stock.\"}";   
		System.err.println(e.getMessage());  
	} 

	 return output; 
}

//Update Stock
public String updateStock(String id, String mname, String description, String amount, String price)  {   
	String output = ""; 
 
  try   {   
	  Connection con = connect();
 
	  if (con == null)    {
		  return "Error while connecting to the database for updating."; 
	  } 
 
   // create a prepared statement    
	   String query = "UPDATE stock SET mname=?,description=?,amount=?,price=? WHERE id=?";
		 
   PreparedStatement preparedStmt = con.prepareStatement(query); 
 
   // binding values    
   preparedStmt.setString(1, mname);
	preparedStmt.setString(2, description);
	preparedStmt.setInt(3,Integer.parseInt (amount));
	preparedStmt.setInt(4,Integer.parseInt(price));
	preparedStmt.setInt(5, Integer.parseInt(id));
   
 
   // execute the statement    
   preparedStmt.execute();    
   con.close(); 
 
   //create JSON object to show successful msg
   String newStock = readStock();
   output = "{\"status\":\"success\", \"data\": \"" + newStock + "\"}";
   }   catch (Exception e)   {    
	   output = "{\"status\":\"error\", \"data\": \"Error while Updating Stock Details.\"}";      
	   System.err.println(e.getMessage());   
   } 
 
  return output;  
  }

public String deleteStock(String id) {  
	String output = ""; 
 
 try  {   
	 Connection con = connect();
 
  if (con == null)   {    
	  return "Error while connecting to the database for deleting.";   
  } 
 
  // create a prepared statement   
  String query = "DELETE FROM stock WHERE id=?"; 
 
  PreparedStatement preparedStmt = con.prepareStatement(query); 
 
  // binding values   
  preparedStmt.setInt(1, Integer.parseInt(id));       
  // execute the statement   
  preparedStmt.execute();   
  con.close(); 
 
  //create JSON Object
  String newStock = readStock();
  output = "{\"status\":\"success\", \"data\": \"" + newStock + "\"}";
  }  catch (Exception e)  {  
	  //Create JSON object 
	  output = "{\"status\":\"error\", \"data\": \"Error while Deleting Stock.\"}";
	  System.err.println(e.getMessage());  
	  
 } 
 
 return output; 
 }
}