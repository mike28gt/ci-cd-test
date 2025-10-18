/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.example.ci.cd.test;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * App mínima para demostrar CI/CD con DevSecOps.
 * Expone:
 *   - /health -> {"status":"ok"}
 *   - /sum?a=2&b=3 -> {"result":5}
 */
public class App {

    // Lógica de negocio sencilla (probada con JUnit)
    public static int sum(int a, int b) { 
        return a + b; 
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        server.createContext("/health", (HttpExchange exchange) -> {
            byte[] bytes = "{\"status\":\"ok\"}".getBytes();
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        });

        server.createContext("/sum", (HttpExchange exchange) -> {
            var query = exchange.getRequestURI().getQuery(); // e.g., a=2&b=3
            int a = 0, b = 0;
            if (query != null) {
                for (String p : query.split("&")) {
                    String[] kv = p.split("=");
                    if (kv.length == 2) {
                        if ("a".equals(kv[0])) { 
                            a = Integer.parseInt(kv[1]);
                        }
                        
                        if ("b".equals(kv[0])) {
                            b = Integer.parseInt(kv[1]);
                        }
                    }
                }
            }
            int result = sum(a, b);
            byte[] bytes = String.format("{\"result\":%d}", result).getBytes();
            exchange.getResponseHeaders().add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) { os.write(bytes); }
        });

        server.start();
        System.out.println("Server running on http://localhost:" + port);
    }
}
