package Diagrama_de_Classe_Métodos;

public class MoradorDAO implements Map<String, Morador> {
    private Connect _unnamed_Connect_;
    public int size() {
    int size = -1;
    Connection con = null;
    try {
        con = Connect.connect();
        PreparedStatement ps = con.prepareStatement("select count(id) from morador");
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
