/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.util;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

/**
 *
 * @author jittagornp
 */
public class UrlUtils {

    private static final String HTTP_PORT = "80";
    private static final String HTTPS_PORT = "443";
    private static final String ENCODE = "utf-8";

    private static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequest();
    }

    /**
     * @return host url and include query string
     */
    public static String getRequestUri() {
        return getRequest()
                .getRequestURL()
                .toString()
                + generateQuerystring();
    }

    private static String generateQuerystring() {
        HttpServletRequest request = getRequest();
        String querystring = request.getQueryString();
        if (querystring == null) {
            querystring = "";
        } else {
            querystring = "?" + querystring;
        }

        return querystring;
    }

    public static String getRequestUriEncode() {
        return encode(getRequestUri());
    }

    /**
     * @return host url and include query string encode utf-8
     */
    public static String getRequestUriQuerystringEncode() {
        return getRequest()
                .getRequestURL()
                .toString()
                + encode(generateQuerystring());
    }

    public static String decodeQuerystringRequestUri(String requestUri) {
        if (!StringUtils.hasText(requestUri)) {
            return "";
        }

        int indexOf = requestUri.indexOf(encode("?"));
        if (indexOf == -1) {
            return requestUri;
        }

        String querystring = decode(requestUri.substring(indexOf));
        return requestUri.substring(0, indexOf) + querystring;
    }

    /**
     * @return query string
     */
    public static String getQuerystring() {
        return getRequest().getQueryString();
    }

    /**
     * @return query string encode utf-8
     */
    public static String getQuerystringEncode() {
        return encode(getQuerystring());
    }

    /**
     * example<br/>
     * input --> http%3A%2F%2Fpamarin.com%2F%40jittagornp<br/>
     * output --> http://pamarin.com/@jittagornp
     *
     * @param str
     * @return
     */
    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, ENCODE);
        } catch (Exception ex) {
            return str;
        }
    }

    /**
     * example<br/>
     * input --> http://pamarin.com/@jittagornp<br/>
     * output --> http%3A%2F%2Fpamarin.com%2F%40jittagornp
     *
     * @param str
     * @return
     */
    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, ENCODE);
        } catch (Exception ex) {
            return str;
        }
    }

    private static boolean isReservePort(String port) {
        return HTTP_PORT.equals(port) || HTTPS_PORT.equals(port);
    }

    /**
     * use http servlet request by thread local<br/>
     * build pattern
     * <ul>
     * <li>[http|s]://[domainName]:[port]/[contextRoot]</li>
     * <li>http://pamarin.com/</li>
     * <li>http://localhost:8080/</li>
     * <li>http://localhost/pamarin</li>
     * </ul>
     * ignore port 80 and 443
     *
     * @return
     */
    public static String buildHostUrl() {
        return buildHostUrl(getRequest());
    }

    /**
     * build pattern
     * <ul>
     * <li>[http|s]://[domainName]:[port]/[contextRoot]</li>
     * <li>http://pamarin.com/</li>
     * <li>http://localhost:8080/</li>
     * <li>http://localhost/pamarin</li>
     * </ul>
     * ignore port 80 and 443
     *
     * @param request
     * @return
     */
    public static String buildHostUrl(HttpServletRequest request) {
        String contextPath = request.getContextPath();
        String protocol = request.getScheme();
        String domain = request.getServerName();
        String port = request.getServerPort() + "";
        port = isReservePort(port) ? "" : (":" + port);

        return protocol + "://" + domain + port + contextPath;
    }

    public static void redirectPath2(String path) throws IOException {
        FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .redirect(buildHostUrl() + path);
    }
}
