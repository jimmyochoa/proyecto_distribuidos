import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class TCPCliente {

    public TCPCliente(String host, int puerto) {
        try {
            Socket socket = new Socket(host, puerto);

            // ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            // ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            // String data = "Hola desde el cliente 1";
            // out.writeObject(data);
            
            socket.close();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 2020;
        TCPCliente cliente = new TCPCliente(host, puerto);
    }
}
