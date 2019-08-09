/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Business.Turno;
import Business.UC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class UCDAO  implements Map<String, UC> {
    
    private static final String UNIDADE_CURRICULAR_TABLE = "unidade_curricular";
    private static final String IDUNIDADE_CURRICULAR = "idUnidade_Curricular";
    private static final String ANO = "ano";
    private static final String SEMESTRE = "semestre";
  
    private static final String ALUNO_UC_TABLE = "aluno_uc";
    private static final String IDALUNO = "idAluno";
    
    private static final String DOCENTE_UC_TABLE = "docente_uc";
    private static final String IDDOCENTE = "idDocente";
    
    
    private Connection connection;
    
    
    @Override
    public UC get(Object key) {
        UC u = null;
        
        try {
            connection = Connect.connect();

            PreparedStatement stm = 
                    connection.prepareStatement("SELECT * FROM " + UNIDADE_CURRICULAR_TABLE +
                            "\nWHERE " + IDUNIDADE_CURRICULAR + " = ?");

            stm.setString(1, (String)key);
            
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                u = new UC(
                        rs.getString(IDUNIDADE_CURRICULAR),
                        rs.getInt(ANO),
                        rs.getInt(SEMESTRE)
                );
                
                PreparedStatement aStm = connection.prepareStatement(
                        "SELECT " + IDALUNO + " FROM " + ALUNO_UC_TABLE +
                                "\nWHERE " + IDUNIDADE_CURRICULAR + " = ?"
                );
                
                aStm.setString(1, (String)key);
                
                ResultSet aRs = aStm.executeQuery();
                
                while (aRs.next()) {
                    u.addAluno(aRs.getString(IDALUNO));
                }
                
                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
        
        return u;
    }
    
    
    @Override
    public UC put(String key, UC value) {
        UC uc = null;
        
        try {
            connection = Connect.connect();
            PreparedStatement stm = 
                    connection.prepareStatement("INSERT INTO " + UNIDADE_CURRICULAR_TABLE +
                            " VALUES (?, ?, ?) " +
                            "ON DUPLICATE KEY UPDATE " + 
                            ANO + " = ?, " + SEMESTRE + " = ?");
            
            stm.setString(1, key);
            stm.setInt(2, value.getAno());
            stm.setInt(3, value.getSemestre());
            stm.setInt(4, value.getAno());
            stm.setInt(5, value.getSemestre());
            stm.executeUpdate();
            
            TurnoDAO tDAO = new TurnoDAO();
            tDAO.add(key, (Turno) value.getTurnos());
            
            List<String> list = value.getAlunos();
            PreparedStatement aStm =
                    connection.prepareStatement("INSERT INTO " + 
                            ALUNO_UC_TABLE + "\nVALUES (?, ?)\n" +
                            "ON DUPLICATE KEY UPDATE " + IDALUNO + 
                            " = ?");
            
            for (String a : list) {
                aStm.setString(1, a);
                aStm.setString(2, key);
                aStm.setString(3, a);
                aStm.addBatch();
            }
            
            aStm.executeBatch();
            
            uc = value;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        } 
        
        return uc;
    }
    
    
    @Override
    public void putAll(Map<? extends String, ? extends UC> map) {
        map.values().forEach((u) -> {
            this.put(u.getNome(), u);
        });
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
    public boolean containsKey(Object key) {
        boolean r = false;
        
        try {
            connection = Connect.connect();
            PreparedStatement stm = 
                    connection.prepareStatement("SELECT " + IDUNIDADE_CURRICULAR +
                            " FROM " + UNIDADE_CURRICULAR_TABLE +
                            "\nWHERE " + IDUNIDADE_CURRICULAR + " = ?");
            
            stm.setString(1, (String)key);
            
            ResultSet rs = stm.executeQuery();
            
           r = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UCDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
        
        return r;
    }
    
    
    @Override 
    public boolean containsValue(Object value) {
        UC u = (UC)value;
        return this.containsKey(u.getNome());
    }
    
        
    @Override
    public Set<Map.Entry<String, UC>> entrySet() {
        throw new NullPointerException();
    }
    
    
    
    
    
    @Override
    public Set<String> keySet() {
        throw new NullPointerException();
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
    public boolean isEmpty() {
        return this.size() == 0;
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

    @Override
    public int size() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<UC> values() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UC remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
