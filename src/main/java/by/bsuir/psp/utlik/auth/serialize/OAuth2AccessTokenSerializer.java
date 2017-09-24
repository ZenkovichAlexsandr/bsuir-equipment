package by.bsuir.psp.utlik.auth.serialize;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.io.IOException;
import java.util.Objects;

public class OAuth2AccessTokenSerializer extends JsonSerializer<OAuth2AccessToken> {
    @Override
    public void serialize(final OAuth2AccessToken token, final JsonGenerator jgen, final SerializerProvider serializers)
            throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField(OAuth2AccessToken.ACCESS_TOKEN, token.getValue());
        OAuth2RefreshToken refreshToken = token.getRefreshToken();
        if (Objects.nonNull(refreshToken)) {
            jgen.writeStringField(OAuth2AccessToken.REFRESH_TOKEN, refreshToken.getValue());
        }
        jgen.writeEndObject();
    }
}
