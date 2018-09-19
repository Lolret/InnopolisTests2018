package lesson9.networkProtocols.inClass.socketSimple;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    public static Integer CLIENT_PORT = 4998;

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Scanner scanner = new Scanner(System.in);
                        String message;
                        while (!(message = scanner.nextLine()).equals("")) {
                            try {
                                writer.write(message);
                                writer.newLine();
                                writer.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String message = null;
                        try {
                            while ((message = reader.readLine()) != null) {
                                System.out.println(message);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
