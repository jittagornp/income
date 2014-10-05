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
import org.springframework.dao.EmptyResultDataAccessException;
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

    private Format createFormat() {
        return new SimpleDateFormat("yyyy-MM-dd");
    }

    private Statistic findItemTagByOwner(User user, String direction) {
        return jdbcTemplate.queryForObject(
                "SELECT income_name, income_value "
                + "FROM ( "
                + "     select tg.tag_name income_name, sum(item.income_value) income_value "
                + "     from INCOME_ITEM item  "
                + "         inner join ITEM_TAG it "
                + "         on (item.item_id = it.item_id) "
                + "         inner join TAG tg "
                + "         on (it.tag_id = tg.tag_id) "
                + "     where item.user_id = ? "
                + "     group by tg.tag_name "
                + ") "
                + "ORDER BY income_value " + direction + " "
                + "LIMIT 1 OFFSET 0",
                new Object[]{user.getId()},
                new StatisticRowMapper());
    }

    public Statistic findItemTagByOwner(User user, String direction, Date startDate, Date endDate) {
        try {
            if (startDate == null || endDate == null) {
                return findItemTagByOwner(user, direction);
            }

            Format format = createFormat();
            return jdbcTemplate.queryForObject(
                    "SELECT income_name, income_value "
                    + "FROM ( "
                    + "     select tg.tag_name income_name, sum(item.income_value) income_value "
                    + "     from INCOME_ITEM item  "
                    + "         inner join ITEM_TAG it "
                    + "         on (item.item_id = it.item_id) "
                    + "         inner join TAG tg "
                    + "         on (it.tag_id = tg.tag_id) "
                    + "     where item.user_id = ? AND (income_date BETWEEN ? AND ?) "
                    + "     group by tg.tag_name "
                    + ") "
                    + "ORDER BY income_value " + direction + " "
                    + "LIMIT 1 OFFSET 0",
                    new Object[]{
                        user.getId(),
                        format.format(startDate),
                        format.format(endDate)
                    },
                    new StatisticRowMapper());
        } catch (EmptyResultDataAccessException ex) {
            return Statistic.EMPTY;
        }
    }

    private Statistic findItemGroupByOwner(User user, String direction) {
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
                new Object[]{user.getId()},
                new StatisticRowMapper());
    }

    public Statistic findItemGroupByOwner(User user, String direction, Date startDate, Date endDate) {
        try {
            if (startDate == null || endDate == null) {
                return findItemGroupByOwner(user, direction);
            }

            Format format = createFormat();
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
        } catch (EmptyResultDataAccessException ex) {
            return Statistic.EMPTY;
        }
    }
}
