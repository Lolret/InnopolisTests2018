package lesson9.networkProtocols.home;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static final Integer SERVER_PORT = 4999;

    public static void main(String[] args) {

        List<Socket> sockets = new ArrayList();
        ExecutorService service = Executors.newCachedThreadPool();

        service.submit(new ServerSocketAvaiting(sockets, service));
        service.submit(new MsgsSender(sockets, service));

    }


    private static class ServerSocketAvaiting implements Runnable {

        private List<Socket> sockets;
        private ExecutorService service;

        ServerSocketAvaiting(List<Socket> sockets, ExecutorService service) {
            this.sockets = sockets;
            this.service = service;
        }

        @Override
        public void run() {
            try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
                while (true) {
                    System.out.println("waiting connection on port" +serverSocket.toString());
                    Socket socket = serverSocket.accept();

                    sockets.add(socket);
                    System.out.println("connected to server");
                    socket.getOutputStream().write("Connected to server".getBytes());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static class MsgsSender implements Runnable {

        List<Socket> sockets;
        ExecutorService service;

        public MsgsSender(List<Socket> sockets, ExecutorService service) {
            this.sockets = sockets;
            this.service = service;
        }


        @Override
        public void run() {
            while (!(service.isTerminated() || service.isShutdown())) {
                for (Socket socket: sockets) {
                    try(BufferedReader reader = new BufferedReader(
                            new InputStreamReader(socket.getInputStream()))) {
                        String message;
                        if ((message = reader.readLine()) != null && message != "") {
                            for (Socket skt: sockets) {
                                try(BufferedWriter writer = new BufferedWriter(
                                        new OutputStreamWriter(socket.getOutputStream()))) {
                                    writer.write(message);
                                    writer.newLine();
                                    writer.flush();
                                }

                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


            }
        }
    }
}
