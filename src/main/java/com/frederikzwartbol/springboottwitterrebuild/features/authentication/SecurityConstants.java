package com.frederikzwartbol.springboottwitterrebuild.features.authentication;

public class SecurityConstants {
    public static final String USERNAME_REQUEST_PARAMETER = "username";
    public static final String PASSWORD_REQUEST_PARAMETER = "password";
    public static final String REFRESH_TOKEN_REQUEST_PARAMETER = "refresh_token";
    public static final String ANONYMOUS_AUTH_USER = "anonymousUser";
    public static final Long ACCESS_TOKEN_EXPIRY_TIME_MIN = 900L;
    public static final Long REFRESH_TOKEN_EXPIRY_TIME_MIN = 1800L;
    public static final String TOKEN_TYPE = "Bearer";

}
