/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cinematicserver;

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
 * @author Oz
 */
@Entity
@Table(name = "account", catalog = "cinematicsmsserver", schema = "")
@NamedQueries({@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"), @NamedQuery(name = "Account.findById", query = "SELECT a FROM Account a WHERE a.id = :id"), @NamedQuery(name = "Account.findByCardNumber", query = "SELECT a FROM Account a WHERE a.cardNumber = :cardNumber"), @NamedQuery(name = "Account.findByNomorHp", query = "SELECT a FROM Account a WHERE a.nomorHp = :nomorHp"), @NamedQuery(name = "Account.findBySaldo", query = "SELECT a FROM Account a WHERE a.saldo = :saldo"), @NamedQuery(name = "Account.findByStatus", query = "SELECT a FROM Account a WHERE a.status = :status")})
public class Account implements Serializable {
    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "card_number")
    private String cardNumber;
    @Column(name = "nomor_hp")
    private String nomorHp;
    @Basic(optional = false)
    @Column(name = "saldo")
    private int saldo;
    @Basic(optional = false)
    @Column(name = "status")
    private short status;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(Integer id, String cardNumber, int saldo, short status) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.saldo = saldo;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        String oldCardNumber = this.cardNumber;
        this.cardNumber = cardNumber;
        changeSupport.firePropertyChange("cardNumber", oldCardNumber, cardNumber);
    }

    public String getNomorHp() {
        return nomorHp;
    }

    public void setNomorHp(String nomorHp) {
        String oldNomorHp = this.nomorHp;
        this.nomorHp = nomorHp;
        changeSupport.firePropertyChange("nomorHp", oldNomorHp, nomorHp);
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        int oldSaldo = this.saldo;
        this.saldo = saldo;
        changeSupport.firePropertyChange("saldo", oldSaldo, saldo);
    }

    public short getStatus() {
        return status;
    }

    public void setStatus(short status) {
        short oldStatus = this.status;
        this.status = status;
        changeSupport.firePropertyChange("status", oldStatus, status);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cinematicserver.Account[id=" + id + "]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }

}
