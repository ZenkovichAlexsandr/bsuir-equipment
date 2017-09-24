package by.bsuir.psp.utlik.auth.serialize;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = OAuth2AccessTokenSerializer.class)
public interface OAuth2AccessTokenMixIn {
}
