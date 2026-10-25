package clientPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Client2_1 {
    public static void main(String[] args)  throws IOException {
        Socket socket = new Socket("localhost", 1234);
        System.out.println("Je suis connecté");
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        Scanner sc = new Scanner(System.in);
        while (true) {
            String op = sc.nextLine();
            out.write(op);
            String[] op_test = op.split(" ");
            System.out.println(Arrays.toString(op_test));
        }
    }
}


