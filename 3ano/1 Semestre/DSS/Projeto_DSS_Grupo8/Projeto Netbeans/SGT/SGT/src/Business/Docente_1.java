/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Asus
 */
@Entity
@Table(name = "docente", catalog = "sgturnos", schema = "")
@NamedQueries({
    @NamedQuery(name = "Docente_1.findAll", query = "SELECT d FROM Docente_1 d"),
    @NamedQuery(name = "Docente_1.findByIdDocente", query = "SELECT d FROM Docente_1 d WHERE d.idDocente = :idDocente"),
    @NamedQuery(name = "Docente_1.findByNome", query = "SELECT d FROM Docente_1 d WHERE d.nome = :nome"),
    @NamedQuery(name = "Docente_1.findByPass", query = "SELECT d FROM Docente_1 d WHERE d.pass = :pass"),
    @NamedQuery(name = "Docente_1.findByDocRes", query = "SELECT d FROM Docente_1 d WHERE d.docRes = :docRes")})
public class Docente_1 implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDocente")
    private Integer idDocente;
    @Column(name = "Nome")
    private String nome;
    @Basic(optional = false)
    @Column(name = "Pass")
    private String pass;
    @Column(name = "DocRes")
    private Boolean docRes;

    public Docente_1() {
    }

    public Docente_1(Integer idDocente) {
        this.idDocente = idDocente;
    }

    public Docente_1(Integer idDocente, String pass) {
        this.idDocente = idDocente;
        this.pass = pass;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        Integer oldIdDocente = this.idDocente;
        this.idDocente = idDocente;
        changeSupport.firePropertyChange("idDocente", oldIdDocente, idDocente);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        String oldPass = this.pass;
        this.pass = pass;
        changeSupport.firePropertyChange("pass", oldPass, pass);
    }

    public Boolean getDocRes() {
        return docRes;
    }

    public void setDocRes(Boolean docRes) {
        Boolean oldDocRes = this.docRes;
        this.docRes = docRes;
        changeSupport.firePropertyChange("docRes", oldDocRes, docRes);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDocente != null ? idDocente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Docente_1)) {
            return false;
        }
        Docente_1 other = (Docente_1) object;
        if ((this.idDocente == null && other.idDocente != null) || (this.idDocente != null && !this.idDocente.equals(other.idDocente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Business.Docente_1[ idDocente=" + idDocente + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
