/*
 * AlunoDAO.java
 *
 * Created on 23 de Maio de 2005, 18:23
 */

package main.data;

/**
 Tabela a criar em MYSQL:
 * 
 CREATE TABLE `aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

* 
 * @author jfc
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import main.business.Aluno;

public class AlunoDAO implements Map<String,Aluno> {
    
    private Connection conn;
    
    @Override
    public void clear () {
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            stm.executeUpdate("DELETE FROM aluno");
        } catch (Exception e) {
            //runtime exeption!
            throw new NullPointerException(e.getMessage()); 
        } finally {
            Connect.close(conn);
        }
    }
    
    @Override
    public boolean containsKey(Object key) throws NullPointerException {
        boolean r = false;
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            String sql = "SELECT nome FROM aluno WHERE numero='"+(String)key+"'";
            ResultSet rs = stm.executeQuery(sql);
            r = rs.next();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }
        return r;
    }
    
    @Override
    public boolean containsValue(Object value) {
        Aluno a = (Aluno) value;
        return containsKey(a.getNumero());
    }
    
    @Override
    public Set<Map.Entry<String,Aluno>> entrySet() {
        throw new NullPointerException("public Set<Map.Entry<String,Aluno>> entrySet() not implemented!");
    }
    
    @Override
    public boolean equals(Object o) {
        throw new NullPointerException("public boolean equals(Object o) not implemented!");
    }
    
    @Override
    public Aluno get(Object key) {
        Aluno al = null;
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("SELECT * FROM aluno WHERE id=?");
            stm.setInt(1, (Integer)key);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                al = new Aluno(rs.getString("nome"),rs.getInt("id"),rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return al;
    }
    
    @Override
    public int hashCode() {
        return this.conn.hashCode();
    }
    
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }
    
    @Override
    public Set<String> keySet() {
        throw new NullPointerException("Not implemented!");
    }
    
    @Override
    public Aluno put(String key, Aluno value) {
        Aluno al = null;
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("INSERT INTO aluno\n" +
                "VALUES (?, ?, ?)\n" +
                "ON DUPLICATE KEY UPDATE nome=VALUES(nome),  email=VALUES(email)", Statement.RETURN_GENERATED_KEYS);
            stm.setInt(1, value.getNumero());
            stm.setString(2, value.getNome());
            stm.setString(3, value.getEmail());
            stm.executeUpdate();
            
            ResultSet rs = stm.getGeneratedKeys();
            if(rs.next()) {
                int newId = rs.getInt(1);
                value.setNumero(newId);
            }
            
            al = value;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Connect.close(conn);
        }
        return al;
    }

    @Override
    public void putAll(Map<? extends String,? extends Aluno> t) {
        for(Aluno a : t.values()) {
            put(a.getId(), a);
        }
    }
    
    @Override
    public Aluno remove(Object key) {
        Aluno al = this.get(key);
        try {
            conn = Connect.connect();
            PreparedStatement stm = conn.prepareStatement("delete from aluno where id = ?");
            stm.setInt(1, (Integer)key);
            stm.executeUpdate();
        } catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        } finally {
            Connect.close(conn);
        }
        return al;
    }
    
    @Override
    public int size() {
        int i = 0;
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT count(*) FROM aluno");
            if(rs.next()) {
                i = rs.getInt(1);
            }
        }
        catch (Exception e) {throw new NullPointerException(e.getMessage());}
        finally {
            Connect.close(conn);
        }
        return i;
    }
    
    @Override
    public Collection<Aluno> values() {
        Collection<Aluno> col = new HashSet<Aluno>();
        try {
            conn = Connect.connect();
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM aluno");
            while (rs.next()) {
                col.add(new Aluno(rs.getString("nome"),rs.getInt("id"),rs.getString("email")));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            Connect.close(conn);
        }
        return col;
    }
    
}

