package me.zp4rker.discord.microbot.util;

import me.zp4rker.discord.core.exception.ExceptionHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author ZP4RKER
 */
public class JSONUtil {

    public static JSONObject readFile(File file) {
        String data = "";
        try {
            FileReader rd = new FileReader(file);
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = rd.read()) != -1) {
                sb.append((char) c);
            }
            data = sb.toString();
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return data.isEmpty() ? new JSONObject() : new JSONObject(data);
    }

    public static JSONObject readUrl(String url) {
        String data = "";
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
                        sb.append(line).append("\n");
                    }
                    br.close();
                    data = sb.toString().replaceAll("\\\\([\"/])", "$1");
            }
        } catch (Exception e) {
            ExceptionHandler.handleException(e);
        }
        return data.isEmpty() ? new JSONObject() : new JSONObject(data);
    }

}
