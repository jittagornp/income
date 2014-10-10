/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model;

import com.pamarin.income.model.pk.SummaryBeforehandPK;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author anonymous
 */
@Entity
@Table(name = "summary_beforehand")
public class SummaryBeforehand implements Serializable {

    @EmbeddedId
    private SummaryBeforehandPK id;

    @Column(name = "pure_total_income")
    private Double pureTotalIncome;
    @Column(name = "total_item")
    private Long totalItem;
    @Column(name = "start_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    //
    @Column(name = "max_item_name")
    private String maxItemName;
    @Column(name = "max_item_value")
    private Double maxItemValue;
    @Column(name = "min_item_name")
    private String minItemName;
    @Column(name = "min_item_value")
    private Double minItemValue;

    @Column(name = "max_itemgroup_name")
    private String maxItemGroupName;
    @Column(name = "max_itemgroup_value")
    private Double maxItemGroupValue;
    @Column(name = "min_itemgroup_name")
    private String minItemGroupName;
    @Column(name = "min_itemgroup_value")
    private Double minItemGroupValue;

    @Column(name = "max_itemtag_name")
    private String maxItemTagName;
    @Column(name = "max_itemtag_value")
    private Double maxItemTagValue;
    @Column(name = "min_itemtag_name")
    private String minItemTagName;
    @Column(name = "min_itemtag_value")
    private Double minItemTagValue;

    @JoinColumn(
            name = "user_id",
            referencedColumnName = "user_id",
            insertable = false,
            updatable = false,
            nullable = false
    )
    @ManyToOne
    private User owner;

    public SummaryBeforehand() {
    }

    public SummaryBeforehand(User owner, String type) {
        this.id = new SummaryBeforehandPK(type, owner.getId());
        this.owner = owner;
    }

    public Double getPureTotalIncome() {
        return pureTotalIncome;
    }

    public void setPureTotalIncome(Double pureTotalIncome) {
        this.pureTotalIncome = pureTotalIncome;
    }

    public Long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Long totalItem) {
        this.totalItem = totalItem;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getMaxItemName() {
        return maxItemName;
    }

    public void setMaxItemName(String maxItemName) {
        this.maxItemName = maxItemName;
    }

    public Double getMaxItemValue() {
        return maxItemValue;
    }

    public void setMaxItemValue(Double maxItemValue) {
        this.maxItemValue = maxItemValue;
    }

    public String getMinItemName() {
        return minItemName;
    }

    public void setMinItemName(String minItemName) {
        this.minItemName = minItemName;
    }

    public Double getMinItemValue() {
        return minItemValue;
    }

    public void setMinItemValue(Double minItemValue) {
        this.minItemValue = minItemValue;
    }

    public String getMaxItemGroupName() {
        return maxItemGroupName;
    }

    public void setMaxItemGroupName(String maxItemGroupName) {
        this.maxItemGroupName = maxItemGroupName;
    }

    public Double getMaxItemGroupValue() {
        return maxItemGroupValue;
    }

    public void setMaxItemGroupValue(Double maxItemGroupValue) {
        this.maxItemGroupValue = maxItemGroupValue;
    }

    public String getMinItemGroupName() {
        return minItemGroupName;
    }

    public void setMinItemGroupName(String minItemGroupName) {
        this.minItemGroupName = minItemGroupName;
    }

    public Double getMinItemGroupValue() {
        return minItemGroupValue;
    }

    public void setMinItemGroupValue(Double minItemGroupValue) {
        this.minItemGroupValue = minItemGroupValue;
    }

    public String getMaxItemTagName() {
        return maxItemTagName;
    }

    public void setMaxItemTagName(String maxItemTagName) {
        this.maxItemTagName = maxItemTagName;
    }

    public Double getMaxItemTagValue() {
        return maxItemTagValue;
    }

    public void setMaxItemTagValue(Double maxItemTagValue) {
        this.maxItemTagValue = maxItemTagValue;
    }

    public String getMinItemTagName() {
        return minItemTagName;
    }

    public void setMinItemTagName(String minItemTagName) {
        this.minItemTagName = minItemTagName;
    }

    public Double getMinItemTagValue() {
        return minItemTagValue;
    }

    public void setMinItemTagValue(Double minItemTagValue) {
        this.minItemTagValue = minItemTagValue;
    }

    public SummaryBeforehandPK getId() {
        return id;
    }

    public void setId(SummaryBeforehandPK id) {
        this.id = id;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
        if (owner != null) {
            getId().setUserId(owner.getId());
        }
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final SummaryBeforehand other = (SummaryBeforehand) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
