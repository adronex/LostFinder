package by.lostFinder.security;

import by.lostFinder.dto.ExternalOAuthDto;
import by.lostFinder.entities.Account;
import by.lostFinder.entities.AccountDetail;
import by.lostFinder.entities.OAuthType;
import by.lostFinder.repositories.AccountRepository;
import by.lostFinder.utils.HttpUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 * Created on 10.07.2016.
 */
@Service
public class CustomExternalOAuthService {

    private final String FACEBOOK_SECRET = "96589a7d9b14042e01affbe00a96ed3c";
    private final String GOOGLE_SECRET = "7YOQc6kNnj4FLrbXQVJRL47-";

    @Autowired
    private AccountRepository accountRepository;

    public ExternalOAuthDto facebookProcess(String code, String redirectUri, String clientId) {
        String facebookTokenResponse = HttpUtils.sendGetRequest(getFacebookTokenUrl(code, redirectUri, clientId));
        String facebookAccessToken = getJsonValue(facebookTokenResponse, "access_token");
        String facebookInfo = HttpUtils.sendGetRequest(getFacebookInfoUrl(facebookAccessToken));
        Account account = accountRepository.findByOauthIdAndOauthType(getJsonValue(facebookInfo, "id"), OAuthType.FACEBOOK);

        String lastName = getJsonValue(facebookInfo, "last_name");
        String firstName = getJsonValue(facebookInfo, "first_name");
        String email = getJsonValue(facebookInfo, "email");
        String id = getJsonValue(facebookInfo, "id");
        AccountDetail details = new AccountDetail(lastName, firstName);
        if (account == null) {
            account = new Account(email, details, id, OAuthType.FACEBOOK);
        } else {
            account.getAccountDetail().setName(lastName + " " + firstName);
            account.setEmail(email);
        }
        account.setPassword(facebookAccessToken);
        accountRepository.save(account);

        String serverUrl = HttpUtils.getServerUrl() + "/api/login";
        String nativeOauthToken = getJsonValue(HttpUtils.sendPostRequest(serverUrl, getFacebookOAuthRequestBody(account.getUsername(),
                facebookAccessToken), true), "access_token");
        return new ExternalOAuthDto(nativeOauthToken);
    }

    public ExternalOAuthDto googleProcess(String code, String redirectUri, String clientId) {
        String googleTokenResponse = HttpUtils.sendPostRequest(getGoogleTokenUrl(), getGoogleTokenBody(code, clientId, redirectUri), false);
        String googleAccessToken = getJsonValue(googleTokenResponse, "access_token");
        String googleInfo = HttpUtils.sendGetRequest(getGoogleInfoUrl(googleAccessToken));
        Account account = accountRepository.findByOauthIdAndOauthType(getJsonValue(googleInfo, "id"), OAuthType.GOOGLE);

        String id = getJsonValue(googleInfo, "id");
        String name = getJsonValue(googleInfo, "displayName");
        String email = getGoogleEmail(googleInfo);
        AccountDetail details = new AccountDetail(name);
        if (account == null) {
            account = new Account(email, details, id, OAuthType.GOOGLE);
        } else {
            account.getAccountDetail().setName(name);
            account.setEmail(email);
        }
        account.setPassword(googleAccessToken);
        accountRepository.save(account);

        String serverUrl = HttpUtils.getServerUrl() + "/api/login";
        String nativeOauthToken = getJsonValue(HttpUtils.sendPostRequest(serverUrl, getGoogleOAuthRequestBody(account.getUsername(),
                googleAccessToken), true), "access_token");
        return new ExternalOAuthDto(nativeOauthToken);
    }

    private String getJsonValue(String jsonString, String key) {
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getString(key);
    }

    private String getGoogleEmail(String googleInfo) {
        JSONObject jsonObject = new JSONObject(googleInfo);
        JSONObject email = jsonObject.getJSONArray("emails").getJSONObject(0);
        return email.getString("value");
    }

    private String getFacebookTokenUrl(String code, String redirectUri, String clientId) {
        return "https://graph.facebook.com/v2.6/oauth/access_token?" +
                "client_id=" + clientId + "&redirect_uri=" + redirectUri +
                "&client_secret=" + FACEBOOK_SECRET + "&code=" + code;
    }

    private String getFacebookInfoUrl(String accessToken) {
        return "https://graph.facebook.com/me?fields=id,email,first_name,last_name,age_range,picture&access_token=" +
                accessToken;
    }

    private String getGoogleTokenUrl() {
        return "https://accounts.google.com/o/oauth2/token";
    }

    private String getGoogleTokenBody(String code, String clientId, String redirectUrl) {
        return "code=" + code
                + "&client_id=" + clientId + "&client_secret=" + GOOGLE_SECRET
                + "&redirect_uri=" + redirectUrl
                + "&grant_type=authorization_code";
    }

    private String getGoogleInfoUrl(String accessToken) {
        return "https://www.googleapis.com/plus/v1/people/me?access_token=" +
                accessToken;
    }

    private String getFacebookOAuthRequestBody(String login, String password) {
        return "grant_type=password&username=" + login + "###FACEBOOK&password=" + password;
    }

    private String getGoogleOAuthRequestBody(String login, String password) {
        return "grant_type=password&username=" + login + "###GOOGLE&password=" + password;
    }
}
