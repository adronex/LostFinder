package by.lostFinder.oauth.external;

/**
 * Default class description.
 *
 * @author P.Sinitsky.
 *         Created on 30.07.2016.
 */
public class OAuthUserInfo {
    private String token;
    private String id;
    private String email;
    private String lastName;
    private String firstName;
    private String gender;
    private String avatarUrl;

    public OAuthUserInfo(String token, String id, String email, String lastName, String firstName, String gender, String avatarUrl) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.gender = gender;
        this.avatarUrl = avatarUrl;
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getGender() {
        return gender;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }
}
