package serverPackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2_1 {
    public  static  void main(String[] args) throws IOException{
        ServerSocket socketServer=new ServerSocket(1234);
        System.out.println("server waiting for client...");
        Socket socket=socketServer.accept();
        System.out.println("client connected!!!!!!!!!!!");


        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String res= in.readLine();
        System.out.println("test");






    }

    /** Objet echange entre le client et le serveur. */
    public static class Operation implements Serializable {
        private static final long serialVersionUID = 1L;

        private double operande1;
        private char operateur;
        private double operande2;

        public Operation(double operande1, char operateur, double operande2) {
            this.operande1 = operande1;
            this.operateur = operateur;
            this.operande2 = operande2;
        }

        public double getOperande1() { return operande1; }
        public char getOperateur() { return operateur; }
        public double getOperande2() { return operande2; }

        @Override
        public String toString() {
            return operande1 + " " + operateur + " " + operande2;
        }
    }
}
