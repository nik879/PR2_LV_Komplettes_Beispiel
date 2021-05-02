package org.campus02.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class PageCache {
    private HashMap<String, WebPage> cache = new HashMap<>(); //String=URL

    public HashMap<String, WebPage> getCache() {
        return cache;
    }
    public WebPage readFromCache(String url) throws CacheMissException {
        if (cache.containsKey(url)) {
            return cache.get(url);
        }
        throw new CacheMissException("The requested url " + url + " is not in the cache");
    }

    public void writeToCache(WebPage webPage) {
        cache.put(webPage.getUrl(), webPage);
    }

    public void warmUp(String pathToUrls) throws UrlLoaderException {
        try (BufferedReader br = new BufferedReader(new FileReader(pathToUrls))) {
            String line;
            while ((line = br.readLine()) != null) {
                UrlLoader ul = new UrlLoader();
                ul.loadWebPage(line);
            }
        } catch (FileNotFoundException e) {
            throw new UrlLoaderException("Error while trying to warmUp");
        } catch (IOException e) {
            throw new UrlLoaderException("Error while trying to warmUp");
        }
    }
}
