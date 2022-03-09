import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000);
            while(true) {
                System.out.println("Server> waiting for client to connect");
                Socket client = serverSocket.accept();
                System.out.println("Server> connected to client socket[addr=/127.0.0.1, localport=5000]");
                System.out.println("Server> waiting for client to send data...");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String msg = bufferedReader.readLine();
                System.out.println("Server> data from client " + msg);
                String msgA[] = msg.trim().split(" ");
                String op = msgA[1];
                double x = 0;
                double y = 0;
                boolean valid = true;
                try {
                    x = Double.parseDouble(msgA[0]);
                    y = Double.parseDouble(msgA[2]);
                }
                catch(NumberFormatException e){
                    valid = false;
                }

                double result = 0;
                if(op.equals("+")){
                    result = x + y;
                }
                else if(op.equals("-")){
                    result = x - y;
                }
                else if(op.equals("*")){
                    result = x * y;
                }
                else if(op.equals("/")){
                    result = x / y;
                }
                else if(op.equals("^")){
                    result = Math.pow(x,y);
                }
                else if(op.equals("%")){
                    result = Math.pow(x,y);
                }
                else{
                    valid = false;
                }
                PrintWriter serWriter = new PrintWriter(client.getOutputStream());
                if (valid) {
                    serWriter.println(result);
                    serWriter.flush();
                }
                else{
                    serWriter.println("ERROR: invalid input");
                    serWriter.flush();
                }
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
