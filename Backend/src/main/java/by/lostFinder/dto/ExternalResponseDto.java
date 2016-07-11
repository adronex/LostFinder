package by.lostFinder.dto;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 *         Created on 10.07.2016.
 */
public class ExternalResponseDto {
    private String code;
    private String clientId;
    private String redirectUri;

    public String getCode() {
        return code;
    }

    public String getClientId() {
        return clientId;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
