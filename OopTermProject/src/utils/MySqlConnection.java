package utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class MySqlConnection {
Connection conn=null;
public static Connection conDB()
{
	try {
		
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?autoReconnect=true&useSSL=false","root","1998");
	return con;
} catch(ClassNotFoundException | SQLException ex) {
	System.err.println("MySqlConnection : "+ex.getMessage());
	return null;
}
}
}
