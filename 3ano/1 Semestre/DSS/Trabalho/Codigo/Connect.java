package Diagrama_de_Classe_M�todos;

public class Connect {

    public static Connection connect() throws SQLException, ClassNotFoundException {
        throw new UnsupportedOperationException() {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connect = DriverManager.getConnection("jdbc:mysql://host/sgd?user=username&password=password");
        return connect;
        }
    }
}