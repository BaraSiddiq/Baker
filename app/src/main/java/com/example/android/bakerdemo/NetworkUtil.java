package com.example.android.bakerdemo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtil {


    public static String httpRequest() throws IOException {
        String urlString = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";
        URL url = new URL(urlString);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        InputStream is = httpURLConnection.getInputStream();
        Scanner sc = new Scanner(is);
        sc.useDelimiter("\\A");
        if(sc.hasNext()){
            String s = sc.next();
            return s;
        }
        return "";
    }
}
