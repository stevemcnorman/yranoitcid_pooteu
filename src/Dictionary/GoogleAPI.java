package Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GoogleAPI {

    /**
     * Dịch câu/đoạn văn bản.
     * @param text câu cần tra
     * @return String
     */
    public static String translate(String text) {
        try {
            String urlStr = "https://script.google.com/macros/s/AKfycbzzimSXyRhh4zy2rePQ-cTwisM1WdYZRVc1x1UVRj0-NCV2DHkprugTqvoOsh95APWy/exec" +
                    "?q=" + URLEncoder.encode(text, "UTF-8") +
                    "&target=" + "vi" +
                    "&source=" + "en";
            URL url = new URL(urlStr);
            StringBuilder response = new StringBuilder();
            HttpURLConnection httpUrlCon = (HttpURLConnection) url.openConnection();
            httpUrlCon.setRequestProperty("User-Agent", "Chrome/104.0.5112.81");
            BufferedReader in = new BufferedReader(new InputStreamReader(httpUrlCon.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (IOException e) {
            return "";
        }
    }
}