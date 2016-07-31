package by.lostFinder.security.external;

import by.lostFinder.utils.HttpUtils;
import org.json.JSONObject;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 *         Created on 30.07.2016.
 */
public class GoogleOAuthHolder {

    private String accessToken;
    private final String GOOGLE_SECRET = "7YOQc6kNnj4FLrbXQVJRL47-";

    public GoogleOAuthHolder (String code, String redirectUri, String clientId) {
        String googleTokenResponse = HttpUtils.sendPostRequest(getGoogleTokenUrl(), getGoogleTokenBody(code, clientId, redirectUri), false);
        accessToken = new JSONObject(googleTokenResponse).getString("access_token");
    }

    public OAuthUserInfo getInfo(){
        String googleInfo = HttpUtils.sendGetRequest(getGoogleInfoUrl(accessToken));
        String id = new JSONObject(googleInfo).getString("id");
        String email = new JSONObject(googleInfo).getJSONArray("emails").getJSONObject(0).getString("value");
        JSONObject googleNameObject = new JSONObject(googleInfo).getJSONObject("name");
        String lastName = googleNameObject.optString("familyName");
        String firstName = googleNameObject.optString("givenName");
        String gender = new JSONObject(googleInfo).optString("gender");
        String avatarUrl = new JSONObject(googleInfo).getJSONObject("image").getString("url").split("\\?")[0];
        return new OAuthUserInfo(accessToken, id, email, lastName, firstName, gender, avatarUrl);
    }

    private String getGoogleTokenUrl() {
        return "https://accounts.google.com/o/oauth2/token";
    }

    private String getGoogleInfoUrl(String accessToken) {
        return "https://www.googleapis.com/plus/v1/people/me?access_token=" +
                accessToken;
    }

    private String getGoogleTokenBody(String code, String clientId, String redirectUrl) {
        return "code=" + code
                + "&client_id=" + clientId + "&client_secret=" + GOOGLE_SECRET
                + "&redirect_uri=" + redirectUrl
                + "&grant_type=authorization_code";
    }
}
