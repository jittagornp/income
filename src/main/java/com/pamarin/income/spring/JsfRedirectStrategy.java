/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pamarin.income.spring;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.util.StringUtils;

/**
 * Inspired by <a
 * href="http://stackoverflow.com/questions/10143539/jsf-2-spring-security-3-x-and-richfaces-4-redirect-to-login-page-on-session-tim">StackOverflow.com</a>
 * and by <a
 * href="http://www.icesoft.org/wiki/display/ICE/Spring+Security#SpringSecurity-Step4%3AConfigureYourSpringSecurityredirectStrategy">Spring
 * Security 3 and ICEfaces 3 Tutorial</a>.
 *
 * @author banterCZ
 */
public class JsfRedirectStrategy implements InvalidSessionStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(JsfRedirectStrategy.class);

    private static final String FACES_REQUEST_HEADER = "faces-request";

    private String invalidSessionUrl;

    public JsfRedirectStrategy(String invalidSessionUrl) {
        this.invalidSessionUrl = invalidSessionUrl;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        boolean ajaxRedirect = "partial/ajax".equals(request.getHeader(FACES_REQUEST_HEADER));
        if (ajaxRedirect) {
            String contextPath = request.getContextPath();
            String redirectUrl = contextPath + invalidSessionUrl;
            LOG.debug("Session expired due to ajax request, redirecting to '{}'", redirectUrl);

            String ajaxRedirectXml = createAjaxRedirectXml(redirectUrl);
            LOG.debug("Ajax partial response to redirect: {}", ajaxRedirectXml);

            response.setContentType("text/xml");
            response.getWriter().write(ajaxRedirectXml);
        } else {
            String requestURI = getRequestUrl(request);
            LOG.debug("Session expired due to non-ajax request, starting a new session and redirect to requested url '{}'", requestURI);
            request.getSession(true);
            response.sendRedirect(requestURI);
        }

    }

    private String getRequestUrl(HttpServletRequest request) {
        StringBuffer requestURL = request.getRequestURL();

        String queryString = request.getQueryString();
        if (StringUtils.hasText(queryString)) {
            requestURL.append("?").append(queryString);
        }

        return requestURL.toString();
    }

    private String createAjaxRedirectXml(String redirectUrl) {
        return new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")
                .append("<partial-response><redirect url=\"")
                .append(redirectUrl)
                .append("\"></redirect></partial-response>")
                .toString();
    }

    public void setInvalidSessionUrl(String invalidSessionUrl) {
        this.invalidSessionUrl = invalidSessionUrl;
    }

}
