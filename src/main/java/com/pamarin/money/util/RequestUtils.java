/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.money.util;

import java.util.Map;
import javax.faces.context.FacesContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author anonymous
 */
public class RequestUtils {

    private static final Logger LOG = LoggerFactory.getLogger(RequestUtils.class);

    public static String requestString(String parameterName) {
        String request = requestMap().get(parameterName);
        if (request == null) {
            throw new NullPointerException("request parameter \"" + parameterName + "\" is null.");
        }

        return request;
    }

    public static Map<String, String> requestMap() {
        return FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();
    }

    public static Integer requestInteger(String parameterName) {
        return Integer.valueOf(requestString(parameterName));
    }

    public static Long requestLong(String parameterName) {
        return Long.valueOf(requestString(parameterName));
    }

    public static Float requestFloat(String parameterName) {
        return Float.valueOf(requestString(parameterName));
    }

    public static Double requestDouble(String parameterName) {
        return Double.valueOf(requestString(parameterName));
    }

    public static Boolean requestBoolean(String parameterName) {
        return Boolean.valueOf(requestString(parameterName));
    }

    public static String getQueryString(RequestFilter filter) {
        String queryString = "";
        Map<String, String> parameterMap = requestMap();

        if (parameterMap != null) {
            StringBuilder builder = new StringBuilder();
            for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                if (filter.filterParam(entry)) {
                    builder.append("&")
                            .append(entry.getKey())
                            .append("=")
                            .append(entry.getValue());
                }
            }

            queryString = builder.toString();
            if (queryString.startsWith("&")) {
                queryString = queryString.substring(1);
            }
        }

        return queryString;
    }

    public static String getQueryString() {
        return getQueryString(new RequestFilter() {

            @Override
            public boolean filterParam(Map.Entry<String, String> entryParam) {
                return true;
            }
        });
    }

    public static String getQueryStringNotFaces() {
        return getQueryString(new RequestFilter() {

            @Override
            public boolean filterParam(Map.Entry<String, String> entryParam) {
                return !entryParam.getKey().contains("javax.faces")
                        && !entryParam.getKey().startsWith("j_id");
            }
        });
    }

    public static abstract class RequestFilter {

        public abstract boolean filterParam(Map.Entry<String, String> entryParam);
    }
}
