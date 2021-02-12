package com.teimour.wordsapi.security;

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kebritam
 * Project golden-words
 * Created on 04/01/2021
 */

public class PreAuthTokenHeaderFilter extends AbstractPreAuthenticatedProcessingFilter {

    private final String headerName;

    public PreAuthTokenHeaderFilter(String headerName) {
        this.headerName = headerName;
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getHeader(headerName);
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }
}
