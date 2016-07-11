package by.lostFinder.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created on 07.07.2016;
 *
 * @author p.sinitskiy (adronex303@gmail.com);
 * @since 1.0.
 */
public final class HttpUtils {

    private static final String APP_AUTH_HEADER = "Basic bG9zdEZpbmRlcjpQb3NodWtheSptbmUqdHV0KjA1MDY=";
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);

    private HttpUtils() {
    }

    public static String sendGetRequest(String urlAsString) {

        URL url;
        BufferedReader bufferedReader;

        try {
            url = new URL(urlAsString);
            HttpsURLConnection httpURLConnection =
                    (HttpsURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            bufferedReader = new BufferedReader(new
                    InputStreamReader(httpURLConnection.getInputStream()));

            String lineRead;

            StringBuilder response = new StringBuilder();

            while ((lineRead = bufferedReader.readLine()) != null) {
                response.append(lineRead);
            }

            return response.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String sendPostRequest(String urlAsString, String body, Boolean authNeeded) {

        try {
            URL url = new URL(urlAsString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            //add reuqest header
            httpURLConnection.setRequestMethod("POST");
            if (authNeeded) {
                httpURLConnection.setRequestProperty("Authorization", APP_AUTH_HEADER);
            }
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=utf-8");


            // Send post request
            httpURLConnection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(httpURLConnection.getOutputStream());
            wr.writeBytes(body);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //print result
            return response.toString();

        } catch (Exception e) {
            LOGGER.error("POST request failed! " + e.getMessage());
        }

        return null;
    }

    /**
     * @return server host url with container context (if exists)
     */
    public static String getServerUrl() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes != null && requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            return request.getRequestURL().toString().replaceAll(request.getRequestURI(), request.getContextPath());
        }
        LOGGER.warn("Unknown server URL.");
        return null;
    }
}

