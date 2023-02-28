package com.frederikzwartbol.springboottwitterrebuild.features.authentication.filters;

import com.frederikzwartbol.springboottwitterrebuild.features.authentication.RedirectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.frederikzwartbol.springboottwitterrebuild.features.authentication.SecurityConstants.REDIRECT_URI_REQUEST_PARAMETER;

/**
 * This filter sets redirect location response to redirect parameter from request if valid redirect
 */
@Slf4j
public class RedirectFilter extends OncePerRequestFilter {

    @Autowired
    private RedirectService redirectService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        if (isAuthenticated() && "/authenticate".equals(request.getRequestURI())) {
            String redirectUrlParameter = request.getParameter(REDIRECT_URI_REQUEST_PARAMETER);

            String encodedRedirectURL = response.encodeRedirectURL(request.getContextPath() + "/home");

            if (redirectUrlParameter != null && redirectService.validateRedirect(redirectUrlParameter)) {
                encodedRedirectURL = response.encodeRedirectURL(request.getContextPath() + redirectUrlParameter);
            }
            response.setStatus(HttpStatus.TEMPORARY_REDIRECT.value());
            response.setHeader("Location", encodedRedirectURL);
        }

        filterChain.doFilter(request, response);
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}

