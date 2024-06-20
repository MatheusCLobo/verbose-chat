import java.io.*;
import java.net.*;

public class VerboseChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
             OutputStream output = socket.getOutputStream();
             PrintWriter writer = new PrintWriter(output, true);
             InputStream input = socket.getInputStream();
             BufferedReader serverReader = new BufferedReader(new InputStreamReader(input))) {

            String text;
            do {
                System.out.print("Digite a mensagem: ");
                text = reader.readLine();
                writer.println(text);

                String response = serverReader.readLine();
                System.out.println("Resposta do servidor: " + response);
            } while (!text.equalsIgnoreCase("bye"));

        } catch (UnknownHostException ex) {
            System.out.println("Servidor não encontrado: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erro de I/O: " + ex.getMessage());
        }
    }
}
