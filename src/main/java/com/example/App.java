package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import javax.swing.TransferHandler;

import com.example.atmservice.JobQueue;
import com.example.transactions.TransactionHandler;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.example.atmservice.*;
import com.example.onlinegame.*;
import com.example.transactions.*;

public class App {

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/atms/calculateOrder", new DataHandlerAtms());
        server.createContext("/transactions/report", new DataHandlerTransactions());
        server.createContext("/onlinegame/calculate", new DataHandlerOnlineGameCalculate());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
        System.out.println("Server started on port 8080");
    }

    static class DataHandlerAtms implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String method = t.getRequestMethod();
            if (method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                isr.close();

                try {
                    JSONParser parser = new JSONParser();
                    String jsonString = sb.toString();
                    JSONArray json = (JSONArray) parser.parse(jsonString);
                    JobQueue jobQueue = new JobQueue();
                    jobQueue.loadJobs(json);
                    jobQueue.sortJobs();
                    String response = jobQueue.toJSONObject().toJSONString();
                    t.getResponseHeaders().set("Content-Type", "text/plain");
                    t.sendResponseHeaders(200, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (ParseException e) {
                    e.printStackTrace();
                    String response = "Invalid JSON";
                    t.getResponseHeaders().set("Content-Type", "text/plain");
                    t.sendResponseHeaders(400, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                String response = "Unsupported method";
                t.getResponseHeaders().set("Content-Type", "text/plain");
                t.sendResponseHeaders(405, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class DataHandlerTransactions implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String method = t.getRequestMethod();
            if (method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                isr.close();

                try {
                    String jsonString = sb.toString();
                    TransactionHandler transactionHandler = new TransactionHandler();
                    transactionHandler.processTransactions(jsonString);
                    JSONArray accountsReport = transactionHandler.getSortedAccountsInJSONArray();
                    String response = accountsReport.toJSONString();
                    t.getResponseHeaders().set("Content-Type", "text/plain");
                    t.sendResponseHeaders(200, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } catch (ParseException e) {
                    e.printStackTrace();
                    String response = "Invalid JSON";
                    t.getResponseHeaders().set("Content-Type", "text/plain");
                    t.sendResponseHeaders(400, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
            } else {
                String response = "Unsupported method";
                t.getResponseHeaders().set("Content-Type", "text/plain");
                t.sendResponseHeaders(405, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class DataHandlerOnlineGameCalculate implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String method = t.getRequestMethod();
            if (method.equals("POST")) {
                InputStreamReader isr = new InputStreamReader(t.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                isr.close();
                JSONParser parser = new JSONParser();
                JSONObject jsonObject = null;
                try {
                    String jsonString = sb.toString();
                    jsonObject = (JSONObject) parser.parse(jsonString);
                } catch (ParseException e) {
                    String response = "Invalid JSON";
                    t.getResponseHeaders().set("Content-Type", "text/plain");
                    t.sendResponseHeaders(400, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                }
                EventPlanner eventPlanner = new EventPlanner(jsonObject);
                eventPlanner.createGroups();
                String response = eventPlanner.getGroupsInJSONArray().toJSONString();
                t.getResponseHeaders().set("Content-Type", "text/plain");
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                String response = "Unsupported method";
                t.getResponseHeaders().set("Content-Type", "text/plain");
                t.sendResponseHeaders(405, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
