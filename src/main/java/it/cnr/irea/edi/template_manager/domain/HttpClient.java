package it.cnr.irea.edi.template_manager.domain;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public String get(String url, String accepts) throws IOException {
        return get(new URL(url), accepts);
    }
    public String get(URL url, String accepts) throws IOException {
        if (accepts == null) {
            accepts = "*/*";
        }
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("accept", accepts);

        System.out.println("Request URL ... " + url);

        boolean redirect = false;

        // normally, 3xx is redirect
        int status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == HttpURLConnection.HTTP_SEE_OTHER)
                redirect = true;
        }

        System.out.println("Response Code ... " + status);

        if (redirect) {

            // get redirect url from "location" header field
            String newUrl = connection.getHeaderField("Location");

            // get the cookie if need, for login
            String cookies = connection.getHeaderField("Set-Cookie");

            // open the new connnection again
            connection = (HttpURLConnection) new URL(newUrl).openConnection();
            connection.setRequestProperty("accept", accepts);
/*
            connection.setRequestProperty("Cookie", cookies);
            connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            connection.addRequestProperty("User-Agent", "Mozilla");
            connection.addRequestProperty("Referer", "google.com");
*/

            System.out.println("Redirect to URL : " + newUrl);

        }

        InputStream responseStream = connection.getInputStream();
        StringWriter response = new StringWriter();
        IOUtils.copy(responseStream, response, "UTF8");
        return response.toString();
    }
}
