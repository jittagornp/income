/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model;

import com.pamarin.income.model.pk.IncomeTagPK;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "income_tag")
public class IncomeTag implements Serializable {

    @EmbeddedId
    private IncomeTagPK id;
    @JoinColumn(
            name = "item_id",
            referencedColumnName = "item_id",
            insertable = false,
            updatable = false
    )
    @ManyToOne
    private IncomeItem item;
    @JoinColumn(
            name = "tag_id",
            referencedColumnName = "tag_id",
            insertable = false,
            updatable = false
    )
    @ManyToOne
    private Tag tag;

    public IncomeTag() {
    }

    public IncomeTag(IncomeTagPK id) {
        this.id = id;
    }

    public IncomeTagPK getId() {
        return id;
    }

    public void setId(IncomeTagPK id) {
        this.id = id;
    }

    public IncomeItem getItem() {
        return item;
    }

    public void setItem(IncomeItem item) {
        this.item = item;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final IncomeTag other = (IncomeTag) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
