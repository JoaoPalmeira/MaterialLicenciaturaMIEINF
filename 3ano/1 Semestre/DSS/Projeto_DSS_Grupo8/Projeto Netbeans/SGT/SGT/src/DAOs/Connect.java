
package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Connect {   
    
    private static final String URL = "localhost";
    private static final String DB = "turma";
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = ""; 
    
    
    public static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
      
        return DriverManager.getConnection("jdbc:mysql://"+URL+"/"+DB+"?user="+USERNAME+"&password="+PASSWORD);    
    }
    
    /**
     * 
     * @param c 
     */
    public static void close(Connection c) {
        try {
            if(c!=null && !c.isClosed()) {
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
