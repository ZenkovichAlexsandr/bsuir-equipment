package by.bsuir.psp.utlik.auth;

final class AuthConstants {

    static final String JWT_SIGNING_KEY = "W2XaLH6Xq4euBraA";

    static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
    @SuppressWarnings("squid:S2068")
    static final String PASSWORD_GRANT_TYPE = "password";
    static final String UI_SCOPE = "ui";

    static final String ANONYMOUS_USER = "anonymousUser";
    static final String ANONYMOUS_KEY = "anonymous_key";
    static final String ANONYMOUS_ROLE = "ROLE_ANONYMOUS";

    private AuthConstants() {
    }
}
