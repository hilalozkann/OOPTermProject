package application;
import java.sql.Connection;
import java.sql.DriverManager;

public class MySqlConnectionSingleton {
	
		private static Connection conn;
		private MySqlConnectionSingleton() { }
		static Connection getConnection() {
          Connection conn = null;
		try {
			
			
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection("jdbc:mysql://localhost/oop","root","1998");
				
			}
			catch(Exception ex) {
				System.out.println(ex.getMessage());
			}
		return conn;
		}

		public static Connection getDBConnection() {
			return((conn!=null)?conn:getConnection());
		}

}