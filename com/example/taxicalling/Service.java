package com.example.taxicalling;

import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy.Builder;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

public class Service {
    public String get(String url) {
        String result = "";
        StrictMode.setThreadPolicy(new Builder().permitAll().build());
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new DefaultHttpClient().execute(new HttpPost("http://192.168.110.1/TaxiCalling1/" + url)).getEntity().getContent(), "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    return sb.toString();
                }
                sb.append(new StringBuilder(String.valueOf(line)).append("\n").toString());
            }
        } catch (Exception e) {
            return result;
        }
    }
}
