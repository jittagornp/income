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

    public void createChart(Date startDate, Date endDate) {
        User user = SecurityUtils.getUser();
        lineChartModel = new LineChartModel();
        LineChartSeries series = new LineChartSeries();
        series.setLabel("รายรับ");
        List<IncomeItem> items = service.findByOwnerAndBetweenIncomeDate(user, startDate, endDate);
        if (items != null) {
            for (IncomeItem item : items) {
                series.set(new SimpleDateFormat("yyyy-MM-dd").format(item.getIncomeDate()), item.getIncomeValue());
            }

            LOG.debug("chart size --> {}", items.size());
        }

        lineChartModel.addSeries(series);
        lineChartModel.setTitle("ทิศทางรายรับ");
        lineChartModel.setAnimate(true);
        lineChartModel.setZoom(true);
        lineChartModel.setLegendPosition("ne");
        Axis yAxis = lineChartModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        yAxis.setLabel("จำนวนเงิน (" + Currency.getInstance(user.getSettings().getCurrencyCode()).getSymbol()+ ")");
        DateAxis axis = new DateAxis("วัน/เวลา");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
         
        lineChartModel.getAxes().put(AxisType.X, axis);
    }

    public LineChartModel getLineChartModel() {
        return lineChartModel;
    }

}
