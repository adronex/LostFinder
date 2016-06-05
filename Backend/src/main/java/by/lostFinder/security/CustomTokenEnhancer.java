package by.lostFinder.security;

import by.lostFinder.entities.Account;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created on 18.04.2016;
 * @author operb_000 (operbober@gmail.com);
 * @since 1.0.
 */
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
