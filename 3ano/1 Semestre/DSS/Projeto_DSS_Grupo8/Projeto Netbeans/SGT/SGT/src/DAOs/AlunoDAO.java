/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Business.Aluno;
import Business.Utilizador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafae
 */
public class AlunoDAO extends UtilizadorDAO implements Map<String, Utilizador> {
    
    
    private static final String IDUNIDADE_CURRICULAR = "idUnidade_Curricular";
    private static final String ALUNO_TABLE = "aluno";
    private static final String ALUNO_TROCA_TABLE = "aluno_troca";
    private static final String ALUNO_UC_TABLE = "aluno_uc";
    private static final String ID_ALUNO = "id_Aluno";
    private static final String PASS = "pass";
    private static final String TESTUDANTE = "tEstudante";
    private static final String ID_TROCA = "id_troca";        
    
    private Connection connection;
    
    
    @Override 
    public Collection<Utilizador> values() {
        Collection<Utilizador> col = new HashSet<>();
        
        try {
            connection = Connect.connect();
            
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM " + ALUNO_TABLE
            );
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Aluno a = new Aluno(
                        rs.getString(ID_ALUNO),
                        rs.getString(PASS),
                        rs.getBoolean(TESTUDANTE)
                );
                
                PreparedStatement sStm = connection.prepareStatement(
                            "SELECT " + IDUNIDADE_CURRICULAR + ", " + ID_TROCA + 
                                    " FROM " + ALUNO_TROCA_TABLE + 
                                    "\nWHERE " + ID_ALUNO + " = ?"
                );
                
                sStm.setString(1, a.getNomeUtilizador());
                ResultSet sRs = sStm.executeQuery();
                
                while (sRs.next()) {
                    a.adicionaTurno(sRs.getString(IDUNIDADE_CURRICULAR), sRs.getString(ID_TROCA));
                }
                
                PreparedStatement pStm = connection.prepareStatement(
                            "SELECT " + IDUNIDADE_CURRICULAR  + " FROM " + 
                                    ALUNO_UC_TABLE + "\nWHERE " + ID_ALUNO +
                                    " = ?"
                );
                
                pStm.setString(1, a.getNomeUtilizador());
                ResultSet pRs = sStm.executeQuery();
                
                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
        
        return col;
    }
    
    public void remove(String table, String column, String key) throws ClassNotFoundException {
        try {
            connection = Connect.connect();
            PreparedStatement stm =
                    connection.prepareStatement("DELETE FROM " + table +
                            "\nWHERE " + column + " = " + key);
            
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.close(connection);
        }
    }
    
    @Override
    public Utilizador put(String key, Utilizador value) {
        Utilizador a = null;
        
        try {
            connection = Connect.connect();
            PreparedStatement stm = connection.prepareStatement(
                    "INSERT INTO " + ALUNO_TABLE + 
                            "\nVALUES (?, ?, ?)\n" +
                            "ON DUPLICATE KEY UPDATE " + PASS + 
                            " = ?," + TESTUDANTE +
                            " = ?"
            );
            
            stm.setString(1, key);
            stm.setString(2, value.getPassword());
            stm.setBoolean(3, ((Aluno)value).isTeorico());
            stm.setString(4, value.getPassword());
            stm.setBoolean(5, ((Aluno)value).isTeorico());
            stm.executeUpdate();
            
            a = value;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            Connect.close(connection);
        }
        
        return a;
    }
        
    
    @Override
    public Utilizador get(Object key) {
        Utilizador u = null;
        
        try {
            connection = Connect.connect();
            
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT * FROM " + ALUNO_TABLE + 
                            "\nWHERE " + ID_ALUNO + " = ?"
            );
            
            stm.setString(1, (String)key);
            ResultSet rs = stm.executeQuery();
            
            if (rs.next()) {
                Aluno a = new Aluno(
                        rs.getString(ID_ALUNO),
                        rs.getString(PASS),
                        rs.getBoolean(TESTUDANTE)
                );
                
                PreparedStatement sStm = connection.prepareStatement(
                            "SELECT " + IDUNIDADE_CURRICULAR + ", " + ID_TROCA + " FROM " + 
                                    ALUNO_TROCA_TABLE + 
                                    "\nWHERE " + ID_ALUNO + " = ?"
                );
                
                sStm.setString(1, (String)key);
                ResultSet sRs = sStm.executeQuery();
                
                while (sRs.next()) {
                    a.adicionaTurno(sRs.getString(IDUNIDADE_CURRICULAR), sRs.getString(ID_TROCA));
                }
                
                PreparedStatement pStm = connection.prepareStatement(
                            "SELECT " + IDUNIDADE_CURRICULAR + " FROM " + 
                                    ALUNO_UC_TABLE + "\nWHERE " +
                                    ID_ALUNO + " = ?"
                );
                
                pStm.setString(1, (String)key);
                ResultSet pRs = sStm.executeQuery();
                
                
            }
        } catch (SQLException e) {
            throw new RuntimeException(e); 
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
                    connection.prepareStatement("SELECT " + ID_ALUNO + 
                            " FROM " + ALUNO_TABLE + "\nWHERE " +
                            ID_ALUNO + " = ?");
            
            stm.setString(1, (String)key);
            
            ResultSet rs = stm.executeQuery();
            
           r = rs.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
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
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public Utilizador remove(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
