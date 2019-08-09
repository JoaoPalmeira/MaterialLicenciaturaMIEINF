package Diagrama_de_Classe_Métodos;


public class SenhorioDAO implements Map<String, Senhorio> {
    private Connect _unnamed_Connect_;
    public int size() {
    int size = -1;
    Connection con = null;
    try {
        con = Connect.connect();
        PreparedStatement ps = con.prepareStatement("select count(id) from senhorio");
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            size = rs.getInt(1);
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    }
    return size;
}