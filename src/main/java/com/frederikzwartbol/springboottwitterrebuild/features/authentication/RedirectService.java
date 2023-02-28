package com.frederikzwartbol.springboottwitterrebuild.features.authentication;


/**
 * Service for validation of Redirects
 */
public interface RedirectService {
    boolean validateRedirect(String redirect_uri);
}
