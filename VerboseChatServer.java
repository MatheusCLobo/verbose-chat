import java.io.*;
import java.net.*;

public class VerboseChatServer {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor está ouvindo na porta " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Novo cliente conectado");

                new ServerThread(socket).start();
            }
        } catch (IOException ex) {
            System.out.println("Exceção do servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private Socket socket;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (InputStream input = socket.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(input));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true)) {

            String text;
            while ((text = reader.readLine()) != null) {
                System.out.println("Recebido: " + text);
                String verboseResponse = "Estou tocando minha eternidade. (mensagem original foi \"" + text + "\")";
                writer.println(verboseResponse);
            }
        } catch (IOException ex) {
            System.out.println("Exceção do servidor: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
