package com.frederikzwartbol.springboottwitterrebuild.features.authentication;

import org.springframework.stereotype.Service;

@Service
public class IRedirectService implements RedirectService {

    @Override
    public boolean validateRedirect(String redirect_uri) {
        // example for now every uri is a valid redirect
        return true;
    }

}
