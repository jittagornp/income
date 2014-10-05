/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.repository;

import com.pamarin.income.model.Statistic;
import com.pamarin.income.model.User;
import com.pamarin.income.model.rowmapper.StatisticRowMapper;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author anonymous
 */
@Repository
public class NativeIncomeItemRepo {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Statistic findGroupItemByOwner(User user, String direction) {
        return jdbcTemplate.queryForObject(
                "SELECT income_name, income_value "
                + "FROM ( "
                + "	SELECT income_name, SUM(income_value) income_value "
                + "	FROM income_item "
                + "	WHERE (user_id = ?) "
                + "	GROUP BY income_name "
                + ") "
                + "ORDER BY income_value " + direction + " "
                + "LIMIT 1 OFFSET 0",
                new Object[]{ user.getId() },
                new StatisticRowMapper());
    }

    public Statistic findGroupItemByOwner(User user, String direction, Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return findGroupItemByOwner(user, direction);
        }

        Format format = new SimpleDateFormat("yyyy-MM-dd");
        return jdbcTemplate.queryForObject(
                "SELECT income_name, income_value "
                + "FROM ( "
                + "	SELECT income_name, SUM(income_value) income_value "
                + "	FROM income_item "
                + "	WHERE (user_id = ?) AND (income_date BETWEEN ? AND ?)"
                + "	GROUP BY income_name "
                + ") "
                + "ORDER BY income_value " + direction + " "
                + "LIMIT 1 OFFSET 0",
                new Object[]{
                    user.getId(),
                    format.format(startDate),
                    format.format(endDate)
                },
                new StatisticRowMapper());
    }
}
