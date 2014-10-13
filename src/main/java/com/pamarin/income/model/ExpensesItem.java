/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import org.eclipse.persistence.annotations.Index;
import org.eclipse.persistence.annotations.Indexes;

/**
 *
 * @author jittagornp
 */
@Entity
@Table(name = "expenses_item")
@Indexes(value = {
    @Index(name = "expenses_item_xcdate_uid_index", columnNames = {"expenses_date", "user_id"})
})
public class ExpensesItem implements Serializable {

    @Id
    @TableGenerator(
            name = "expenses_item_generator",
            table = "income_sequence",
            pkColumnName = "name",
            valueColumnName = "value",
            pkColumnValue = "expenses_item"
    )
    @GeneratedValue(
            generator = "expenses_item_generator",
            strategy = GenerationType.TABLE
    )
    @Column(name = "item_id")
    private Integer id;
    @Column(name = "expenses_name", nullable = false)
    private String expensesName;
    @Column(name = "expenses_value", nullable = false)
    private Double expensesValue;
    @Column(name = "expenses_date", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date expensesDate;
    @Column(name = "expenses_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date expensesTime;
    //
    @Index
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

    public String getExpensesName() {
        return expensesName;
    }

    public void setExpensesName(String expensesName) {
        this.expensesName = expensesName;
    }

    public Double getExpensesValue() {
        return expensesValue;
    }

    public void setExpensesValue(Double expensesValue) {
        this.expensesValue = expensesValue;
    }

    public Date getExpensesDate() {
        return expensesDate;
    }

    public void setExpensesDate(Date expensesDate) {
        this.expensesDate = expensesDate;
    }

    public Date getExpensesTime() {
        return expensesTime;
    }

    public void setExpensesTime(Date expensesTime) {
        this.expensesTime = expensesTime;
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

        Collections.sort(tags, new Comparator<Tag>() {

            @Override
            public int compare(Tag t1, Tag t2) {
                return t1.getName().compareTo(t2.getName());
            }
        });

        return tags;
    }

    public String getTagsString() {
        if (getTags().isEmpty()) {
            return "ไม่มีป้ายกำกับ";
        }

        String tgs = "";
        String splitor = ", ";
        for (Tag tag : getTags()) {
            tgs = tgs + splitor + tag.getName();
        }

        if (tgs.startsWith(splitor)) {
            return tgs.substring(splitor.length());
        }

        return tgs;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final ExpensesItem other = (ExpensesItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
