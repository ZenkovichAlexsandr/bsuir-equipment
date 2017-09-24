package by.bsuir.psp.utlik;

import by.bsuir.psp.utlik.auth.OAuth2AuthorizationConfig;
import by.bsuir.psp.utlik.auth.OAuth2ResourceServerConfig;
import by.bsuir.psp.utlik.auth.WebSecurityConfig;
import by.bsuir.psp.utlik.auth.serialize.OAuth2AccessTokenMixIn;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;

@EnableOAuth2Client
@SpringBootApplication
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({OAuth2AuthorizationConfig.class,
		OAuth2ResourceServerConfig.class, WebSecurityConfig.class})
public class EquipmentApplication {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(EquipmentApplication.class, args);
	}

	@PostConstruct
	public void init() {
		objectMapper.addMixIn(OAuth2AccessToken.class, OAuth2AccessTokenMixIn.class);
	}

	/**
	 * CORS configuration for api gateway to support cross domain requests from ui application and other clients.
	 *
	 * @return configured cors filter to accept all requests
	 */
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("HEAD");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("DELETE");
		config.addAllowedMethod("PATCH");
		source.registerCorsConfiguration("/**", config);

		return new CorsFilter(source);
	}
}
