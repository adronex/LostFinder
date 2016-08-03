package by.lostFinder.security.external;

import by.lostFinder.dto.ExternalOAuthDto;
import by.lostFinder.entities.Account;
import by.lostFinder.entities.AccountDetail;
import by.lostFinder.repositories.account.AccountRepository;
import by.lostFinder.utils.HttpUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 *         Created on 10.07.2016.
 */
@Service
public class CustomExternalOAuthService {

    @Autowired
    private AccountRepository accountRepository;

    public ExternalOAuthDto facebookProcess(String code, String redirectUri, String clientId) {
        OAuthUserInfo info = new FacebookOAuthHolder(code, redirectUri, clientId).getInfo();
        Account account = accountRepository.findByEmailIgnoreCase(info.getEmail());

        AccountDetail details = new AccountDetail(info.getLastName(), info.getFirstName());
        if (account == null) {
            account = new Account(info.getEmail(), details, info.getId());
        }
        account.setPassword(info.getToken());
        accountRepository.save(account);

        String serverUrl = HttpUtils.getServerUrl() + "/api/login";
        JSONObject nativeResponse = new JSONObject
                (HttpUtils.sendPostRequest(serverUrl, getFacebookOAuthRequestBody(account.getUsername(),
                        info.getToken()), true));
        String nativeOauthToken = nativeResponse.getString("access_token");
        return new ExternalOAuthDto(nativeOauthToken);
    }

    public ExternalOAuthDto googleProcess(String code, String redirectUri, String clientId) {
        OAuthUserInfo info = new GoogleOAuthHolder(code, redirectUri, clientId).getInfo();
        Account account = accountRepository.findByEmailIgnoreCase(info.getEmail());
        AccountDetail details = new AccountDetail(info.getLastName(), info.getFirstName());
        if (account == null) {
            account = new Account(info.getEmail(), details, info.getId());
        }
        account.setPassword(info.getToken());
        accountRepository.save(account);

        String serverUrl = HttpUtils.getServerUrl() + "/api/login";
        JSONObject nativeResponse = new JSONObject
                (HttpUtils.sendPostRequest(serverUrl, getGoogleOAuthRequestBody(account.getUsername(),
                        info.getToken()), true));
        String nativeOauthToken = nativeResponse.getString("access_token");
        return new ExternalOAuthDto(nativeOauthToken);
    }

    private String getFacebookOAuthRequestBody(String login, String password) {
        return "grant_type=password&username=" + login + "&password=" + password;
    }

    private String getGoogleOAuthRequestBody(String login, String password) {
        return "grant_type=password&username=" + login + "&password=" + password;
    }
}
