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
 * @author jittagornp
 */
@Embeddable
public class IncomeItemPK implements Serializable{

    @Column(name = "topic_id")
    private Integer topicId;
    @Column(name = "user_id")
    private Integer userId;

    public IncomeItemPK() {
    }

    public IncomeItemPK(Integer topicId, Integer userId) {
        this.topicId = topicId;
        this.userId = userId;
    }

    public Integer getTopicId() {
        return topicId;
    }

    public void setTopicId(Integer topicId) {
        this.topicId = topicId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.topicId);
        hash = 23 * hash + Objects.hashCode(this.userId);
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
        final IncomeItemPK other = (IncomeItemPK) obj;
        if (!Objects.equals(this.topicId, other.topicId)) {
            return false;
        }
        if (!Objects.equals(this.userId, other.userId)) {
            return false;
        }
        return true;
    }

}
