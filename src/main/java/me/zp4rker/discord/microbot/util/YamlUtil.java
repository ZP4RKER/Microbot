package me.zp4rker.discord.microbot.util;

import me.zp4rker.discord.core.logger.ZLogger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ZP4RKER
 */
public class YamlUtil {

    public static String fromUrl(String url) {
        HttpURLConnection connection = null;
        try {
            URL u = new URL(url);
            connection = (HttpURLConnection) u.openConnection();
            connection.connect();
            int response = connection.getResponseCode();
            switch (response) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }
        } catch (Exception e) {
            ZLogger.warn("Could not read JSON from: " + url);
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return null;
    }

}
