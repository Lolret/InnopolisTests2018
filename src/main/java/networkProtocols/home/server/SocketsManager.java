package networkProtocols.home.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

class SocketsManager implements Runnable {

    private static Set<ClientController> clients = new HashSet<>();

    @Override
    public void run() {
        try (ServerSocket serverSocket = new ServerSocket(Server.SERVER_PORT)) {
            while (!(Server.service.isTerminated() || Server.service.isShutdown())) {
                System.out.println("waiting connection on port" + serverSocket.toString());
                Socket socket = serverSocket.accept();
                System.out.println(socket + " connected");

                ClientController client = new ClientController(socket);
                clients.add(client);
                Server.service.submit(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected static Set<ClientController> getClients() {
        return clients;
    }
}