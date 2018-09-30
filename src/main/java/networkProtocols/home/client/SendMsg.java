package networkProtocols.home.client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class SendMsg implements Runnable {

    private BufferedWriter writer;
    private Socket socket;

    SendMsg(BufferedWriter writer, Socket socket) {
        this.writer = writer;
        this.socket = socket;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        String message;
        while (!(message = scanner.nextLine()).equals("")) {
            if (message.equals("stop")) {
                stopClient();
                break;
            }
            try {
                writer.write(message);
                writer.newLine();
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void stopClient() {
        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }
}