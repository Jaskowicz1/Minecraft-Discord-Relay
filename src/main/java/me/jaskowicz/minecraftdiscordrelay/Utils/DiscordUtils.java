package me.jaskowicz.minecraftdiscordrelay.Utils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class DiscordUtils {

    public static void sendMessage(String webhookurl, String message) {
        try {
            String USER_AGENT = "Mozilla/5.0";

            URL url = new URL(webhookurl);
            URLConnection con = url.openConnection();
            HttpsURLConnection https = (HttpsURLConnection) con;

            https.setRequestMethod("POST");
            https.setRequestProperty("User-Agent", USER_AGENT);
            con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
            https.setDoOutput(true);

            String out2 = "{\"content\":\"" + message + "\"}";
            byte[] out = out2.getBytes(StandardCharsets.UTF_8);

            int length = out.length;

            https.setFixedLengthStreamingMode(length);
            https.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            https.connect();

            try (DataOutputStream os = new DataOutputStream(con.getOutputStream())) {
                os.write(out);
                os.flush();
                os.close();

            } catch (Exception ignored) {
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
        } catch (Exception e) {
            System.out.println("An error occured when trying to send a message via Discord Webhooks.");
        }
    }

}
