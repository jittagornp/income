/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;

/**
 *
 * @author jittagornp
 */
@Entity
@Table(name = "income_item")
public class IncomeItem implements Serializable {

    @Id
    @TableGenerator(
            name = "income_item_generator",
            table = "income_sequence",
            pkColumnName = "name",
            valueColumnName = "value",
            pkColumnValue = "income_item"
    )
    @GeneratedValue(
            generator = "income_item_generator",
            strategy = GenerationType.TABLE
    )
    @Column(name = "item_id")
    private Integer id;
    @Column(name = "income_name", nullable = false)
    private String incomeName;
    @Column(name = "income_value", nullable = false)
    private Double incomeValue;
    @Column(name = "income_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date incomeDate;
    //
    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false
    )
    private User owner;
    @ManyToMany
    @JoinTable(
            name = "item_tag",
            joinColumns = {
                @JoinColumn(
                        name = "item_id",
                        referencedColumnName = "item_id"
                )
            },
            inverseJoinColumns = {
                @JoinColumn(
                        name = "tag_id",
                        referencedColumnName = "tag_id"
                )
            }
    )
    private List<Tag> tags;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIncomeName() {
        return incomeName;
    }

    public void setIncomeName(String incomeName) {
        this.incomeName = incomeName;
    }

    public Double getIncomeValue() {
        return incomeValue;
    }

    public void setIncomeValue(Double incomeValue) {
        this.incomeValue = incomeValue;
    }

    public Date getIncomeDate() {
        return incomeDate;
    }

    public void setIncomeDate(Date incomeDate) {
        this.incomeDate = incomeDate;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Tag> getTags() {
        if (tags == null) {
            tags = new ArrayList<>();
        }

        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

//    public List<IncomeTag> getTags() {
//        if (tags == null) {
//            tags = new ArrayList<>();
//        }
//
//        return tags;
//    }
//
//    public void setTags(List<IncomeTag> tags) {
//        this.tags = tags;
//    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
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
        final IncomeItem other = (IncomeItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
