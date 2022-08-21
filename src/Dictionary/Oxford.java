package Dictionary;

import org.json.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

public class Oxford {
    /**
     * Lấy ví dụ từ từ điển Oxford.
     * @param target từ cần lấy ví dụ
     * @return string
     */
    public static String Example(String target) {
        String result = "";
        final String word_id = target.toLowerCase();
        final String restUrl = "https://od-api.oxforddictionaries.com:443/api/v2/entries/en-gb/" + word_id + "?"
                + "fields=examples&strictMatch=false";

        final String appID = "36905672";
        final String appKey = "f6eff763764926b7c30112ba157dec1d";

        try {
            URL url = new URL(restUrl);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("app_id", appID);
            urlConnection.setRequestProperty("app_key", appKey);

            // read the output from the server
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
            JSONObject json = new JSONObject(stringBuilder.toString());
            JSONArray array = json.getJSONArray("results");
            JSONObject json1 = array.getJSONObject(0);
            JSONArray array2 = json1.getJSONArray("lexicalEntries");
            JSONObject json2 = array2.getJSONObject(0);
            JSONArray array3 = json2.getJSONArray("entries");
            JSONObject json3 = array3.getJSONObject(0);
            JSONArray array4 = json3.getJSONArray("senses");
            JSONObject json5 = array4.getJSONObject(0);

            JSONArray array5 = json5.getJSONArray("examples");
            int length5 = array5.length();
            for (int i = 0; i < length5; i++) {
                result += "Eg: " + ((JSONObject) array5.get(i)).get("text").toString() + ".\n";
            }
            return result.trim();
        } catch (Exception e) {
            return "";
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String targetWord = sc.next();
        System.out.println(Example(targetWord));
        sc.close();
    }
}