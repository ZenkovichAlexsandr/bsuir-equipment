package by.bsuir.psp.utlik.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@RequiredArgsConstructor
@EnableAuthorizationServer
@EnableConfigurationProperties
public class OAuth2AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManagerBean;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtAccessTokenConverter accessTokenConverter;
    private final ClientDetailsService clientDetailsService;
    private final ObjectMapper objectMapper;

    /**
     * Default oauth2 request factory as a bean.
     *
     * @return default request factory
     */
    @Bean
    public OAuth2RequestFactory requestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService);
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient("browser")
                .scopes(AuthConstants.UI_SCOPE)
                .authorizedGrantTypes(AuthConstants.REFRESH_TOKEN_GRANT_TYPE, AuthConstants.PASSWORD_GRANT_TYPE);
        // @formatter:on
    }

    @Override
    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.accessTokenConverter(accessTokenConverter)
                .authenticationManager(authenticationManagerBean)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .allowFormAuthenticationForClients()
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");

        oauthServer.addTokenEndpointAuthenticationFilter(new JsonToUrlAuthenticationFilter(objectMapper));
    }
}
