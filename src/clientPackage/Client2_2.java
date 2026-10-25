package clientPackage;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in);
             Socket socket = new Socket("localhost", 1234)) {

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            System.out.print("Premier operande : ");
            double a = sc.nextDouble();
            System.out.print("Operateur (+, -, *, /) : ");
            char op = sc.next().charAt(0);
            System.out.print("Deuxieme operande : ");
            double b = sc.nextDouble();

            // b) Envoi de l'objet
            out.writeObject(new Operation(a, op, b));
            out.flush();

            // g) Lecture et affichage du resultat
            Object resultat = in.readObject();
            System.out.println("Resultat : " + resultat);

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erreur : " + e.getMessage());
        }
    }
}
