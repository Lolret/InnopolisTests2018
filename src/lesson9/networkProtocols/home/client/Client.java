package lesson9.networkProtocols.home.client;

import lesson9.networkProtocols.home.server.Server;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", Server.SERVER_PORT);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            System.out.println("Please, write your Nickname!:");

            Thread msgSender = new Thread(new SendMsg(writer, socket));
            msgSender.start();

            String message;
            while ((message = reader.readLine()) != null && msgSender.isAlive()) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}