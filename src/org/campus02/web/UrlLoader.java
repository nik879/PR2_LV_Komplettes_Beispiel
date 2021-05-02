package org.campus02.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlLoader {
    public  WebPage loadWebPage(String url) throws UrlLoaderException {
        try {
            URL myUrl = new URL(url);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(myUrl.openStream()))) {
                String line;
                String content="";
                while ((line = br.readLine()) != null) {
                    content += line;
                }
                WebPage wp = new WebPage(url, content);
                return wp;
            } catch (IOException e) {
                throw new UrlLoaderException(e);
            }
        } catch (MalformedURLException e) {
            throw new UrlLoaderException(e);
        }

    }
}
