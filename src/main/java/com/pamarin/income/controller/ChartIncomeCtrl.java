/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.controller;

import com.pamarin.income.model.IncomeItem;
import com.pamarin.income.model.User;
import com.pamarin.income.security.SecurityUtils;
import com.pamarin.income.service.IncomeItemService;
import static com.pamarin.income.util.CollectionUtils.isEmpty;
import com.pamarin.income.util.DateUtils;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author anonymous
 */
@Component
@Scope("view")
public class ChartIncomeCtrl {

    private static final Logger LOG = LoggerFactory.getLogger(ChartIncomeCtrl.class);

    private LineChartModel lineChartModel;
    @Autowired
    private IncomeItemService service;

    private String getXLabel() {
        return "วัน/เวลา";
    }

    private String getYLabel() {
        return "จำนวนเงิน (" + getCurrencySymbol() + ")";
    }

    private String getGraphTitle() {
        return "ภาพรวมรายรับ";
    }

    private String getLineTitle() {
        return "รายรับ";
    }

    private void addData2Series(LineChartSeries series, List<IncomeItem> items) {
        Format format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (isEmpty(items)) {
            series.set(format.format(new Date()), 0);
        } else {
            for (IncomeItem item : items) {
                Date key = DateUtils.mergeDateTime(
                        item.getIncomeDate(),
                        item.getIncomeTime()
                );

                series.set(format.format(key), item.getIncomeValue());
            }
        }
    }

    private String getCurrencySymbol() {
        return Currency.getInstance(
                SecurityUtils
                .getUser()
                .getSettings()
                .getCurrencyCode()
        ).getSymbol();
    }

    private DateAxis getAxis() {
        Axis yAxis = lineChartModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel(getYLabel());
        DateAxis axis = new DateAxis(getXLabel());
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");

        return axis;
    }

    public void createChart(Date startDate, Date endDate) {
        lineChartModel = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        series.setLabel(getLineTitle());
        addData2Series(series, service.findByOwnerAndBetweenIncomeDate(
                SecurityUtils.getUser(),
                startDate,
                endDate
        ));

        lineChartModel.addSeries(series);
        lineChartModel.setTitle(getGraphTitle());
        lineChartModel.setLegendPosition("ne");
        lineChartModel.getAxes().put(AxisType.X, getAxis());
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

}
