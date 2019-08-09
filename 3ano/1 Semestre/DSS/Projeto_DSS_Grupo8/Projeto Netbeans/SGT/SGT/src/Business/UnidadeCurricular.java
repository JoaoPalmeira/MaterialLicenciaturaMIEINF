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
@Table(name = "unidade_curricular", catalog = "sgturnos", schema = "")
@NamedQueries({
    @NamedQuery(name = "UnidadeCurricular.findAll", query = "SELECT u FROM UnidadeCurricular u"),
    @NamedQuery(name = "UnidadeCurricular.findByIdUnidadeCurricular", query = "SELECT u FROM UnidadeCurricular u WHERE u.idUnidadeCurricular = :idUnidadeCurricular"),
    @NamedQuery(name = "UnidadeCurricular.findByAno", query = "SELECT u FROM UnidadeCurricular u WHERE u.ano = :ano"),
    @NamedQuery(name = "UnidadeCurricular.findBySemestre", query = "SELECT u FROM UnidadeCurricular u WHERE u.semestre = :semestre")})
public class UnidadeCurricular implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idUnidade_Curricular")
    private String idUnidadeCurricular;
    @Basic(optional = false)
    @Column(name = "Ano")
    private int ano;
    @Basic(optional = false)
    @Column(name = "Semestre")
    private int semestre;

    public UnidadeCurricular() {
    }

    public UnidadeCurricular(String idUnidadeCurricular) {
        this.idUnidadeCurricular = idUnidadeCurricular;
    }

    public UnidadeCurricular(String idUnidadeCurricular, int ano, int semestre) {
        this.idUnidadeCurricular = idUnidadeCurricular;
        this.ano = ano;
        this.semestre = semestre;
    }

    public String getIdUnidadeCurricular() {
        return idUnidadeCurricular;
    }

    public void setIdUnidadeCurricular(String idUnidadeCurricular) {
        String oldIdUnidadeCurricular = this.idUnidadeCurricular;
        this.idUnidadeCurricular = idUnidadeCurricular;
        changeSupport.firePropertyChange("idUnidadeCurricular", oldIdUnidadeCurricular, idUnidadeCurricular);
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        int oldAno = this.ano;
        this.ano = ano;
        changeSupport.firePropertyChange("ano", oldAno, ano);
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        int oldSemestre = this.semestre;
        this.semestre = semestre;
        changeSupport.firePropertyChange("semestre", oldSemestre, semestre);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUnidadeCurricular != null ? idUnidadeCurricular.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UnidadeCurricular)) {
            return false;
        }
        UnidadeCurricular other = (UnidadeCurricular) object;
        if ((this.idUnidadeCurricular == null && other.idUnidadeCurricular != null) || (this.idUnidadeCurricular != null && !this.idUnidadeCurricular.equals(other.idUnidadeCurricular))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Business.UnidadeCurricular[ idUnidadeCurricular=" + idUnidadeCurricular + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
