import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class TCPCliente {

    public TCPCliente(String host, int puerto) {
        try {
            Socket socket = new Socket(host, puerto);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Ingrese comando (ADD <números>, GET o EXIT):");
                String commando = scanner.nextLine();

                out.writeObject(commando);

                if (commando.startsWith("GET")) {
                    List<RecursoCompartido> list = (List<RecursoCompartido>) in.readObject();
                    System.out.println("Lista recibida del servidor: ");
                    for (RecursoCompartido rc : list) {
                        System.out.println(rc);
                    }
                } else {
                    String response = (String) in.readObject();
                    System.out.println("Servidor dice: " + response);
                    if (commando.equals("EXIT")) {
                        break;
                    }
                }
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 2020;
        new TCPCliente(host, puerto);
    }
}
