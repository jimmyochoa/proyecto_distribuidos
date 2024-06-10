import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    private static final int PORT = 2020;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Servidor esperando conexiones...");
            while (true) {
                Socket socket = server.accept();
                new Thread(new Tarea(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
