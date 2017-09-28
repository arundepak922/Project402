package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {


	
	public Connection getConnection(){
		Connection conn=null;
		SQLConnection.registerDriver();
		try{
//				create connection
//			System.out.println("^^^^^^^^^^^getting connection");
			conn = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "rohit", "tihor");
			}catch(Exception ex){
				System.err.print(ex.getMessage());
			}
			
	return conn;
	}
	
	
	public static final void registerDriver(){
//		register driver
			try {
				Class.forName("oracle.jdbc.OracleDriver");
//				System.out.println("^^^^^^^^^^^^^^^^^register driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
