import java.sql.*;
import java.util.*;

public class my_crud {

	public static void main(String[] args) {
		String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:4306/products";//Products our database name
        String USER = "root"; //root --> default username of xampp
        String PASS = "";//blank --> default password of xampp
        
        try
        {
        	Class.forName(JDBC_DRIVER);
        	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        	Statement stmt = conn.createStatement();
        	
        	
        	String query = "CREATE TABLE IF NOT EXISTS  users (id INT NOT NULL AUTO_INCREMENT,name VARCHAR(255),"
        			+ "email VARCHAR(255),PRIMARY KEY (id))";//users is the name of our table with column of id, name and email,id is ur primary key
        	
        	stmt.executeUpdate(query);
        	
        	Scanner scan = new Scanner(System.in);
        	
        	System.out.println("1. Add User");
        	System.out.println("2. Read User");
        	System.out.println("3. Update User");
        	System.out.println("4. Delete User");
        	
        	System.out.println("Enter Choice: ");
        	String choice = scan.nextLine();
        	
        	switch(choice)
        	{
			case "1":
				//add user
	        	System.out.println("Enter user name: ");
	        	String name = scan.nextLine();
	        	
	        	System.out.print("Enter user email: ");
	        	String email = scan.nextLine();
	        	
	        	query = " INSERT INTO users (name, email)  VALUES('"+name+" ' , '"+email+" ' )";
	        	stmt.executeUpdate(query);
				break;
			case "2":
				//Read user
				System.out.println("Enter user id: ");
				int id = scan.nextInt();
				
				query = "SELECT * FROM users WHERE  id = "+ id;
				
				ResultSet  rs = stmt.executeQuery(query);
				if(rs.next())
				{
					System.out.println("ID: "+rs.getInt("id"));
					System.out.println("Name: "+rs.getString("name"));
					System.out.println("Email: "+rs.getString("email"));
				}
				else
				{
					System.out.println("User not found");
				}
				break;
			case "3":
				
				System.out.println("Enter user id: ");
				id = scan.nextInt();
				
				scan.nextLine(); //skip a line
				
				System.out.println("Enter user name: ");
	        	name = scan.nextLine();
	        	
	        	System.out.print("Enter user email: ");
	        	email = scan.nextLine();
	        	
	        	query = "UPDATE users SET name = ' "+name+"' ,email = ' "+email+"' WHERE id = " + id;
	        	int result = stmt.executeUpdate(query);
	        	
	        	if(result > 0)
	        	{
	        		System.out.println("User updated Successfully!");
	        	}
	        	else
	        	{
	        		System.out.println("User not found");
	        	}
				break;
				
			case"4":
				System.out.println("Enter user id: ");
				id = scan.nextInt();
				
				query = "DELETE FROM users WHERE id = " + id;
	        	result = stmt.executeUpdate(query);
	        	
	        	if(result > 0)
	        	{
	        		System.out.println("User deleted Successfully!");
	        	}
	        	else
	        	{
	        		System.out.println("User not found");
	        	}
				break;
        	}
        	
        	stmt.close();
        	conn.close();
        }catch(Exception e)
        {
        	System.out.println("Error: "+e.getMessage());
        }
        
	}

}
