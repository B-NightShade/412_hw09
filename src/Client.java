import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    static JTextField input;
    static JTextField output;
    public static void main(String[] args) {
        JButton compute = new JButton("compute");
        compute.addActionListener(new computeButtonAl());
        input = new JTextField(20);
        output = new JTextField(20);
        JFrame jFrame = new JFrame();
        jFrame.getContentPane().add(BorderLayout.CENTER, compute);
        jFrame.getContentPane().add(BorderLayout.NORTH, input);
        jFrame.getContentPane().add(BorderLayout.SOUTH, output);
        jFrame.setSize(500, 500);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    static class computeButtonAl implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                Socket socket = new Socket("127.0.0.1", 5000);
                PrintWriter wr = new PrintWriter(socket.getOutputStream());
                wr.println(input.getText());
                wr.flush();
                System.out.println("Client> sent request to server: " + input.getText());
                BufferedReader bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String res = bf.readLine();
                System.out.println("Client> Server response: " + res);
                output.setText(res);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
