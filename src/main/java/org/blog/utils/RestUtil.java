package org.blog.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestUtil {

    public static void restUtil(String strURL) {
        StringBuffer buffer = new StringBuffer();
        HttpURLConnection httpConn = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(strURL);
            httpConn = (HttpURLConnection) url.openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            reader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (httpConn != null) {
                httpConn.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("接口返回值："+buffer.toString());
    }

}
