/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.model.rowmapper;

import com.pamarin.income.model.Statistic;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author anonymous
 */
public class StatisticRowMapper implements RowMapper<Statistic> {

    @Override
    public Statistic mapRow(ResultSet rs, int i) throws SQLException {
//        if (i == 0) {
//            return null;
//        }

        return new Statistic(
                rs.getString("income_name"),
                rs.getDouble("income_value")
        );
    }

}
