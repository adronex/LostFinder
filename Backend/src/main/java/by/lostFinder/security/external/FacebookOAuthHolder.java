package by.lostFinder.security.external;

import by.lostFinder.utils.HttpUtils;
import org.json.JSONObject;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 *         Created on 30.07.2016.
 */
public class FacebookOAuthHolder {

    private String accessToken;
    private final String TOKEN_URL = "https://graph.facebook.com/v2.7/oauth/access_token";
    private final String FACEBOOK_SECRET = "96589a7d9b14042e01affbe00a96ed3c";

    public FacebookOAuthHolder(String code, String redirectUri, String clientId) {
        String facebookTokenResponse = HttpUtils.sendGetRequest(getFacebookTokenUrl(code, redirectUri, clientId));
        accessToken = new JSONObject(facebookTokenResponse).getString("access_token");
    }

    public OAuthUserInfo getInfo(){
        String facebookInfo = HttpUtils.sendGetRequest(getFacebookInfoUrl(accessToken));
        String id = new JSONObject(facebookInfo).getString("id");
        String email = new JSONObject(facebookInfo).getString("email");
        String lastName = new JSONObject(facebookInfo).getString("last_name");
        String firstName = new JSONObject(facebookInfo).getString("first_name");
        String gender = new JSONObject(facebookInfo).getString("gender");
        String pictureUrl = new JSONObject(facebookInfo)
                .getJSONObject("picture")
                .getJSONObject("data")
                .getString("url");
        return new OAuthUserInfo(accessToken, id, email, lastName, firstName, gender, pictureUrl);
    }

    private String getFacebookTokenUrl(String code, String redirectUri, String clientId) {
        return TOKEN_URL + "?" + "client_id=" + clientId + "&redirect_uri=" + redirectUri +
                "&client_secret=" + FACEBOOK_SECRET + "&code=" + code;
    }

    private String getFacebookInfoUrl(String accessToken) {
        return "https://graph.facebook.com/me?fields=id,email,first_name,last_name,age_range,gender,picture&access_token=" +
                accessToken;
    }
}
