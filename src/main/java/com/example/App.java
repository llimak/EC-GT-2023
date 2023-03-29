package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.json.simple.JSONObject;

public class App {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/data", new DataHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server started on port 8000");
    }

    static class DataHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            System.out.println("Handling request");
            JSONObject json = new JSONObject();
            json.put("name", "John");
            json.put("age", 30);
            json.put("city", "New York");

            String response = json.toJSONString();

            t.getResponseHeaders().set("Content-Type", "application/json");
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
