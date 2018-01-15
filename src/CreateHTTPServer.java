import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Created by fox on 7/29/16.
 */
public class CreateHTTPServer {

    public static void main(String[] args) throws Exception {
        String[] tmp = "/send?1234&1235&1334124&3".split("\\/send\\?([1-9]([0-9]*)\\&)*[1-9]([0-9]*)");
        for (String s : tmp) {
            System.out.println(s);
        }
        HttpServer server = HttpServer.create(new InetSocketAddress(40000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    private static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "This is the response";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
}
