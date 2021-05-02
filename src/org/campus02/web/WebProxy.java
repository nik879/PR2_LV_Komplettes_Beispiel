package org.campus02.web;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WebProxy {
    private PageCache cache;
    private int numCacheHits;
    private int numCacheMisses;

    public WebProxy() {
        PageCache newCache = null;
        this.cache = newCache;
    }

    public WebProxy(PageCache cache) {
        this.cache = cache;
    }

    public WebPage fetch(String url) throws UrlLoaderException {
        try {
            WebPage wp = cache.readFromCache(url);
            numCacheHits++;
            return wp;

        } catch (CacheMissException e) {
            UrlLoader ul = new UrlLoader();
            WebPage wp =
                    ul.loadWebPage(url);
            cache.writeToCache(wp);
            numCacheMisses++;
            return wp;
        }
    }

    public String statsHits() {
        return "stats hits: "+String.valueOf(numCacheHits);
    }public String statsMisses() {
        return "stats misses: "+String.valueOf(numCacheMisses);
    }

    public boolean writePageCacheToFile(String pathTofile) {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathTofile))) {
            PageCache pc = new PageCache();
            HashMap<String, WebPage> filledCache = new HashMap<>();
            filledCache = pc.getCache();
            for (Map.Entry<String, WebPage> stringWebPageEntry : filledCache.entrySet()) {
                bw.write(stringWebPageEntry.getKey() + ";" + stringWebPageEntry.getValue().getContent());
                bw.newLine();
            }
            bw.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
