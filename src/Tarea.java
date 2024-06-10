import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Tarea implements Runnable {

    private Socket socket;
    private static List<RecursoCompartido> listaNumeros = new ArrayList<>();

    public Tarea(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            ObjectInputStream in = new ObjectInputStream(this.socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(this.socket.getOutputStream());

            while (true) {
                String data = (String) in.readObject();
                System.out.println("Cliente dice: " + data);

                if (data.startsWith("ADD")) {
                    String[] parts = data.split(" ");
                    for (int i = 1; i < parts.length; i++) {
                        listaNumeros.add(new RecursoCompartido(Integer.parseInt(parts[i])));
                    }
                    out.writeObject("Números añadidos correctamente.");
                } else if (data.equals("GET")) {
                    out.writeObject(new ArrayList<>(listaNumeros));
                } else if (data.equals("EXIT")) {
                    out.writeObject("Conexión cerrada.");
                    break;
                }
            }

            System.out.println("Lista enviada al cliente: " + listaNumeros);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
