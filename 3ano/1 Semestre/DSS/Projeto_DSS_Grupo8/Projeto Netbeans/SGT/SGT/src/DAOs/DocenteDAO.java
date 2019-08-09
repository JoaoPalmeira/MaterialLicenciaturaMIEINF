/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Business.Docente;
import Business.Utilizador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class DocenteDAO extends UtilizadorDAO 
        implements Map<String, Utilizador>{
    
    private static final String DOCENTE_TABLE = "docente";
    private static final String DOCENTE_UC_TABLE = "docente_cu";
    private static final String IDDOCENTE = "idDocente";
    private static final String PASS = "pass";
    private static final String IDUNIDADE_CURRICULAR = "idUnidade_Curricular";

    
    private Connection connection;
    
    
    @Override 
    public Collection<Utilizador> values() {
        Collection<Utilizador> col = new HashSet<>();
        
        try {
            connection = Connect.connect();
            
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM " + DOCENTE_TABLE
            );
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Docente p = new Docente(
                        rs.getString(IDDOCENTE),
                        rs.getString(PASS)
                        
                );
                
                PreparedStatement uStm = connection.prepareStatement(
                        "SELECT " + IDUNIDADE_CURRICULAR + " FROM " + DOCENTE_UC_TABLE +
                                "\nWHERE " + IDDOCENTE + " = ?"
                );
                uStm.setString(1, p.getNomeUtilizador());
                ResultSet uRs = uStm.executeQuery();
                
                while (uRs.next()) {
                    p.addUC(uRs.getString(IDUNIDADE_CURRICULAR));
                }
                
                col.add(p);
            } 
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
        
        return col;
    }
    
    
   
    public void remove(String table, String column, String key) {
        try {
            connection = Connect.connect();
            PreparedStatement stm =
                    connection.prepareStatement("DELETE FROM " + table +
                            "\nWHERE " + column + " = " + key);
            
            stm.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.close(connection);
        }
    }
    
    
    @Override
    public Utilizador put(String key, Utilizador value) {
        Utilizador p = null;
        
        try {
            connection = Connect.connect();
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO " + DOCENTE_TABLE + 
                            "\nVALUES (?, ?)\n" +
                            "ON DUPLICATE KEY UPDATE " +
                            PASS + " = ?"
            );

            stm.setString(1, value.getNomeUtilizador());
            stm.setString(2, value.getPassword());
            stm.setString(3, value.getPassword());
            stm.executeUpdate();
            
            List<String> list = ((Docente)value).getUCs();
            
            PreparedStatement uStm = connection.prepareStatement(
                    "INSERT INTO " + DOCENTE_UC_TABLE + 
                            "\nVALUES (?, ?)\n" +
                            "ON DUPLICATE KEY UPDATE " +
                            IDDOCENTE + " = ?"
            );
            
            for (String u : list) {
                uStm.setString(1, key);
                uStm.setString(2, u);
                uStm.setString(3, key);
                uStm.addBatch();
            }
            
            uStm.executeBatch();
            
            p = value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
        
        return p;
    }


    @Override
    public Utilizador get(Object key) {
        Utilizador u = null;
        
        try {
            connection = Connect.connect();
            
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM " + DOCENTE_TABLE + 
                            "\nWHERE " + IDDOCENTE + " = ?"
            );
            
            stm.setString(1, (String)key);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                Docente p = new Docente(
                        rs.getString(IDDOCENTE),
                        rs.getString(PASS)
                        
                );
                
                PreparedStatement uStm = connection.prepareStatement(
                        "SELECT " + IDUNIDADE_CURRICULAR + " FROM " + DOCENTE_UC_TABLE +
                                "\nWHERE " + IDDOCENTE + " = ?"
                );
                
                uStm.setString(1, (String)key);
                ResultSet uRs = uStm.executeQuery();
                
                while (uRs.next()) {
                    p.addUC(uRs.getString(IDUNIDADE_CURRICULAR));
                }
                
                u = p;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
            
        return u;
    }
    
    
    @Override
    public boolean containsKey(Object key) {
        boolean r = false;
        
        try {
            connection = Connect.connect();
            PreparedStatement stm = 
                    connection.prepareStatement("SELECT " + IDDOCENTE + 
                            " FROM " + DOCENTE_TABLE + "\nWHERE " +
                            IDDOCENTE + " = ?");
            
            stm.setString(4, (String)key);
            
            ResultSet rs = stm.executeQuery();
            
           r = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DocenteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
        
        return r;
    }
    
    
    
    public void clear(String table) throws ClassNotFoundException {
       try {
            connection = Connect.connect();
            PreparedStatement stm = 
                    connection.prepareStatement("DELETE FROM " + table);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.close(connection);
        }
    }
 
    
    
    public int size(String table) throws ClassNotFoundException {
        int i = 0;
        
        try {
            connection = Connect.connect();
            PreparedStatement stm = 
                    connection.prepareStatement("SELECT count(*) FROM " + 
                            table);
            
            ResultSet rs = stm.executeQuery();
            
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            Connect.close(connection);
        }
        
        return i;
    }

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilizador remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
