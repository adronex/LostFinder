package by.lostFinder.security;

import by.lostFinder.entities.Account;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;


public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        Account account = (Account) authentication.getPrincipal();
        final Map<String, Object> additionalInfo = new HashMap<>();

    //    additionalInfo.put("authorities", account.getPermissions().stream().map(Permission::getName).collect(Collectors.toList()));

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        return accessToken;
    }
}
