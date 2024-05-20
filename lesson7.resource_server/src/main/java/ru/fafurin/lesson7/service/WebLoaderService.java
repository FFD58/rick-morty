package ru.fafurin.lesson7.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;

public class WebLoaderService {

    public static String downloadWebPage(String url) {
        StringBuilder res = new StringBuilder();
        String line;
        try {
            URLConnection urlConnection = new URI(url).toURL().openConnection();
            InputStream is = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                res.append(line);
            }
        } catch (IOException | URISyntaxException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Record " + res + " successfully downloaded...");
        return res.toString();
    }
}
