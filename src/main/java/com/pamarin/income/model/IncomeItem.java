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
@Table(name = "income_item")
@Indexes(value = {
    @Index(name = "income_item_icdate_uid_index", columnNames = {"income_date", "user_id"})
})
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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date incomeDate;
    @Column(name = "income_time")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date incomeTime;
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
            name = "ic_item_tag",
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

    public Date getIncomeTime() {
        return incomeTime;
    }

    public void setIncomeTime(Date incomeTime) {
        this.incomeTime = incomeTime;
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
