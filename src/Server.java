import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while(true) {
                Socket client = serverSocket.accept();

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String msg = bufferedReader.readLine();
                System.out.println("Message recieved: " + msg);
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
