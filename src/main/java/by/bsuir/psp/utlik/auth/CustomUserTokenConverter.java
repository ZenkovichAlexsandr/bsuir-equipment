package by.bsuir.psp.utlik.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomUserTokenConverter extends DefaultUserAuthenticationConverter {

    private final UserDetailsService userDetailsService;

    /**
     * Create UsernamePasswordAuthenticationToken based on authentication parameters.
     *
     * @param map the auth parameters.
     * @return UsernamePasswordAuthenticationToken or null, if user is not identified.
     */
    @Override
    public Authentication extractAuthentication(final Map<String, ?> map) {
        if (!map.containsKey(USERNAME)) {
            return null;
        }
        String username = (String) map.get(USERNAME);
        if (AuthConstants.ANONYMOUS_USER.equals(username)) {
            return createAnonymousToken();
        }
        return createUsernamePasswordToken(username);
    }

    private Authentication createAnonymousToken() {
        return new AnonymousAuthenticationToken(AuthConstants.ANONYMOUS_KEY, AuthConstants.ANONYMOUS_USER,
                Collections.singletonList(new SimpleGrantedAuthority(AuthConstants.ANONYMOUS_ROLE)));
    }

    private Authentication createUsernamePasswordToken(final String username) {
        User user = (User) userDetailsService.loadUserByUsername(username);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        UserPrincipal principal = new UserPrincipal(user.getId(), user.getUsername());
        return new UsernamePasswordAuthenticationToken(principal, "N/A", authorities);
    }
}
