package lesson9.networkProtocols.home;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        try (Socket socket = new Socket("127.0.0.1", 4999);
             BufferedWriter writer = new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {
            Thread msgSender = new Thread(new SendMsg(writer));
            msgSender.start();
            String message;
            while ((message = reader.readLine()) != null && msgSender.isAlive()) {
                System.out.println(message);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SendMsg implements Runnable {

        BufferedWriter writer;

        public SendMsg(BufferedWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            {
                Scanner scanner = new Scanner(System.in);
                String message;
                while (!(message = scanner.nextLine()).equals("")) {
                    if (message.equals("stop")) break;
                    try {
                        writer.write(message);
                        writer.newLine();
                        writer.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
