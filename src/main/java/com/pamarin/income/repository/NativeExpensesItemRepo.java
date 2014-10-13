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
 * @author jittagornp
 */
@Repository
public class NativeExpensesItemRepo {

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
                "SELECT expenses_name, expenses_value "
                + "FROM ( "
                + "     select tg.tag_name expenses_name, sum(item.expenses_value) expenses_value "
                + "     from EXPENSES_ITEM item  "
                + "         left join ITEM_TAG it "
                + "         on (item.item_id = it.item_id) "
                + "         left join TAG tg "
                + "         on (it.tag_id = tg.tag_id) "
                + "     where item.user_id = ? "
                + "     group by tg.tag_name "
                + ") "
                + "ORDER BY expenses_value " + direction + " "
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
                    "SELECT expenses_name, expenses_value "
                    + "FROM ( "
                    + "     select tg.tag_name expenses_name, sum(item.expenses_value) expenses_value "
                    + "     from EXPENSES_ITEM item  "
                    + "         left join ITEM_TAG it "
                    + "         on (item.item_id = it.item_id) "
                    + "         left join TAG tg "
                    + "         on (it.tag_id = tg.tag_id) "
                    + "     where item.user_id = ? AND (expenses_date BETWEEN ? AND ?) "
                    + "     group by tg.tag_name "
                    + ") "
                    + "ORDER BY expenses_value " + direction + " "
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
                "SELECT expenses_name, expenses_value "
                + "FROM ( "
                + "	SELECT expenses_name, SUM(expenses_value) expenses_value "
                + "	FROM expenses_item "
                + "	WHERE (user_id = ?) "
                + "	GROUP BY expenses_name "
                + ") "
                + "ORDER BY expenses_value " + direction + " "
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
                    "SELECT expenses_name, expenses_value "
                    + "FROM ( "
                    + "	SELECT expenses_name, SUM(expenses_value) expenses_value "
                    + "	FROM expenses_item "
                    + "	WHERE (user_id = ?) AND (expenses_date BETWEEN ? AND ?)"
                    + "	GROUP BY expenses_name "
                    + ") "
                    + "ORDER BY expenses_value " + direction + " "
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
