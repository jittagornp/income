/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model.pk;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author anonymous
 */
@Embeddable
public class IncomeTagPK implements Serializable {

    @Column(name = "item_id")
    private Integer itemId;
    @Column(name = "tag_id")
    private Integer tagId;

    public IncomeTagPK() {
    }

    public IncomeTagPK(Integer itemId, Integer tagId) {
        this.itemId = itemId;
        this.tagId = tagId;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getTagId() {
        return tagId;
    }

    public void setTagId(Integer tagId) {
        this.tagId = tagId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.itemId);
        hash = 59 * hash + Objects.hashCode(this.tagId);
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
        final IncomeTagPK other = (IncomeTagPK) obj;
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        if (!Objects.equals(this.tagId, other.tagId)) {
            return false;
        }
        return true;
    }

}
