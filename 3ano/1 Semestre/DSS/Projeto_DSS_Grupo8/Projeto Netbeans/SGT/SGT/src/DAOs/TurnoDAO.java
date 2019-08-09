/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOs;

import Business.Horario;
import Business.Turno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rafae
 */
public class TurnoDAO  {
    private static final String TROCA_TABLE = "troca";
    private static final String IDUNIDADE_CURRICULAR = "idUnidade_Curricular";
    private static final String IDTROCA = "idTroca";
    private static final String TEORICO = "teorico";
    private static final String CAPACIDADE = "CAPACIDADE";
    
    private static final String ALUNO_TROCA_TABLE = "aluno_troca";
    private static final String IDALUNO = "idAluno";
    
    
    private Connection connection;
    
    
    
    
    
    void add(String uc, Turno t) throws ClassNotFoundException {
        try {
            connection = Connect.connect();
            
            PreparedStatement stm = 
                    connection.prepareStatement("INSERT INTO " + TROCA_TABLE + 
                            "\nVALUES (?, ?, ?, ?)\n" +
                            "ON DUPLICATE KEY UPDATE " +
                            TEORICO + " = ?, " + 
                            CAPACIDADE + " = ?");

            stm.setString(1, uc);
            stm.setString(2, t.getNomeTurno());
            stm.setBoolean(3, t.isTeorico());
            stm.setInt(4, t.getCapacidade());
            stm.setBoolean(5, t.isTeorico());
            stm.setInt(6, t.getCapacidade());
            stm.executeUpdate();
            
            List<String> list = t.getAlunos();
            
            PreparedStatement aStm = connection.prepareStatement(
                    "INSERT INTO " + ALUNO_TROCA_TABLE + 
                            "\nVALUES (?, ?, ?)\n" +
                            "ON DUPLICATE KEY UPDATE " + IDALUNO + 
                            " = ?"
            );
            
            for (String a : list) {
                aStm.setString(1, a);
                aStm.setString(2, uc);
                aStm.setString(3, t.getNomeTurno());
                aStm.setString(4, a);
                aStm.addBatch();
            }
            
            aStm.executeBatch();
            
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.close(connection);
        }
    }
    
    
    public void remove(String uc, String turno) throws ClassNotFoundException {
        try {
            connection = Connect.connect();
            
            PreparedStatement stm = connection.prepareStatement(
                    "DELETE FROM " + TROCA_TABLE + "\nWHERE " + IDUNIDADE_CURRICULAR + 
                            " = ? AND " + IDTROCA + " = ?"
            );
            
            stm.setString(1, uc);
            stm.setString(2, turno);
            stm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.close(connection);
        }
    }
    
    
    public Map<String, Turno> get(String uc) throws ClassNotFoundException {
        Map<String, Turno> map = new HashMap<>();
        
        try {
            connection = Connect.connect();
            
            PreparedStatement stm =
                    connection.prepareStatement("SELECT * FROM " + TROCA_TABLE + 
                            "\nWHERE " + IDUNIDADE_CURRICULAR + " = ?");
            
            stm.setString(1, uc);
            
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Turno t = new Turno(
                        rs.getString(IDTROCA),
                        rs.getBoolean(TEORICO),
                        rs.getInt(CAPACIDADE)
                );
                
                PreparedStatement aStm = connection.prepareStatement(
                        "SELECT " + IDALUNO + " FROM " + 
                                ALUNO_TROCA_TABLE + "\nWHERE " +
                                IDUNIDADE_CURRICULAR + " = ? AND " + IDTROCA + " = ?"
                );
                
                aStm.setString(1, uc);
                aStm.setString(2, t.getNomeTurno());
                
                ResultSet aRs = aStm.executeQuery();
                
                while (aRs.next()) {
                    t.addAluno(aRs.getString(IDALUNO));
                }
                
               
                
                map.put(t.getNomeTurno(), t);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Connect.close(connection);
        }
        
        return map;
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
    
    
    public boolean isEmpty(String table) throws ClassNotFoundException {
        return this.size(table) == 0;
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
}
