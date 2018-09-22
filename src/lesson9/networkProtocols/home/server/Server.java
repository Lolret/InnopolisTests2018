package lesson9.networkProtocols.home.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final Integer SERVER_PORT = 4999;
    protected static ExecutorService service;

    public static void main(String[] args) {

        service = Executors.newCachedThreadPool();
        service.submit(new SocketsManager());
    }
}