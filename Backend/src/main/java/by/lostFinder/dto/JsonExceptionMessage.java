package by.lostFinder.dto;

/**
 * Transforms default exception messages into JSON for simple processing
 * on client-side.
 *
 * Created on 08.06.2016;
 *
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
public class JsonExceptionMessage {

    private String error;
    private String message;

    public JsonExceptionMessage(String error, String message) {
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
