package lesson9.networkProtocols.inClass.socketSimple;

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

        ExecutorService service = Executors.newCachedThreadPool();

        List<Socket> sockets = new ArrayList();

        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            Runnable serverSocketAvaiting = () -> {
                while (true) {
                    try {
                        Socket socket = serverSocket.accept();

                        sockets.add(socket);
                        socket.getOutputStream().write("Connected to server".getBytes());

                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    BufferedReader in =
                                            new BufferedReader(new InputStreamReader(socket.getInputStream()));
                                    BufferedWriter out =
                                            new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                                    String word;

                                    word = in.readLine();
                                    try {
                                        out.write(word + "\n");
                                        out.flush();
                                    } catch (IOException ignored) {
                                    }
                                    try {
                                        while (true) {
                                            word = in.readLine();
                                            System.out.println("Echoing: " + word);
                                            for (Socket socket : sockets) {
                                                out.write(word + "\n");
                                                out.flush();
                                            }
                                        }
                                    } catch (NullPointerException ignored) {
                                    }

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();


                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            };
            service.submit(serverSocketAvaiting);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

