package by.lostFinder.dto;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 * Created on 10.07.2016.
 */
public class ExternalOAuthDto {

    private String access_token;
    private String token_type;

    public ExternalOAuthDto(String access_token) {
        this.access_token = access_token;
        token_type = "Bearer";
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }
}
