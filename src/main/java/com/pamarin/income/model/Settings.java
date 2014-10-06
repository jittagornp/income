/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author jittagornp
 */
@Entity
@Table(name = "settings")
public class Settings implements Serializable {
    
    @Id
    @Column(name = "user_id")
    private Integer id;
    @OneToOne
    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            insertable = false,
            updatable = false
    )
    private User owner;
    @Column(name = "currency_code", nullable = false)
    private String currencyCode;
    @Column(name = "floating_point", nullable = false)
    private Integer floatingPoint;

    public Settings() {
    }

    public Settings(User owner) {
        this.owner = owner;
        if (owner != null) {
            this.id = owner.getId();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
        if (owner != null) {
            setId(owner.getId());
        }
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getFloatingPoint() {
        if(floatingPoint == null){
            floatingPoint = 0;
        }
        
        return floatingPoint;
    }

    public void setFloatingPoint(Integer floatingPoint) {
        this.floatingPoint = floatingPoint;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Settings other = (Settings) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
