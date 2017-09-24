package by.bsuir.psp.utlik.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final static String USER_USERNAME = "user";
    private final static String USER_PASSWORD = "user";

    private final static String ADMIN_USERNAME = "admin";
    private final static String ADMIN_PASSWORD = "admin";

    @Override
    public UserDetails loadUserByUsername(final String username) {
        switch (username) {
            case USER_USERNAME:
                return new User("1", USER_USERNAME, USER_PASSWORD, UserAuthority.USER);
            case ADMIN_USERNAME:
                return new User("2", ADMIN_USERNAME, ADMIN_PASSWORD, UserAuthority.ADMIN);
            default:
                throw new UsernameNotFoundException(username);
        }
    }
}
