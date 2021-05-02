package org.campus02.web;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private Socket client;
    private WebProxy proxy;

    public ClientHandler(Socket client, WebProxy proxy) {
        this.client = client;
        this.proxy = proxy;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            String incommingMessage;
            while ((incommingMessage = br.readLine()) != null) {
                if (incommingMessage.equals("bye")) {
                    break;
                }
                if (incommingMessage.length() <= 3 && !incommingMessage.contains(" ")) {
                    bw.write("error:command invalid");
                    bw.newLine();

                }
                String[] parts = incommingMessage.split(" ");
                if (parts[0].equals("fetch")) {

                    try {
                        WebPage wp = proxy.fetch(parts[1]);
                        bw.write("Here is the content of the webpage:");
                        bw.write(wp.getContent());
                    } catch (UrlLoaderException e) {
                        bw.write("error: loading the url failed");
                        bw.newLine();

                    }
                }
                if (parts[0].equals("stats")) {


                    if (parts[1].equals("hits")) {
                        bw.write(proxy.statsHits());
                        bw.newLine();

                    }
                    if (parts[1].equals("misses")) {
                        bw.write(proxy.statsMisses());
                        bw.newLine();

                    } else {
                        bw.write("error:command invalid");
                        bw.newLine();

                    }
                }
                bw.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
