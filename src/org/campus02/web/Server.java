package org.campus02.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(5678)) {
            PageCache pc = new PageCache();
            pc.warmUp("C:\\Users\\nikik\\Desktop\\demo_urls.txt");
            WebProxy proxy = new WebProxy(pc);
            while (true) {
                Socket s = ss.accept();
                ClientHandler ch = new ClientHandler(s, proxy);
                ch.run();
            }

        } catch (IOException | UrlLoaderException e) {
            e.printStackTrace();
        }
    }
}
