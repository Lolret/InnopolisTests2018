package lesson9.networkProtocols.home.server;

import java.io.*;
import java.net.Socket;

public class ClientController implements Runnable {

    private Socket socket;
    private String clientNickname;
    private BufferedReader serverSocketReader;
    private BufferedWriter serverSocketWriter;

    ClientController(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            serverSocketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            serverSocketWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            greetsToClient();
            readAndResendMsg();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void greetsToClient() throws IOException {
        clientNickname = serverSocketReader.readLine();
        serverSocketWriter.write("Hello " + clientNickname + "!");
        serverSocketWriter.newLine();
        serverSocketWriter.flush();
    }

    private void readAndResendMsg() throws IOException {
        String message;
        while ((message = serverSocketReader.readLine()) != null || !Server.service.isShutdown()) {
            System.out.println(message = (clientNickname + ": " + message));
            sendAll(message);
        }
    }

    private void sendAll(String message) throws IOException {
        for (ClientController client : SocketsManager.getClients()) {
            client.serverSocketWriter.write(message);
            client.serverSocketWriter.newLine();
            client.serverSocketWriter.flush();
        }
    }
}
