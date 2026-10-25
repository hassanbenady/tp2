package serverPackage;

import java.io.*;
import java.net.*;

public class Serveur {
    public static void main(String[] args) {
        int port = 1234;
        try (ServerSocket serveur = new ServerSocket(port)) {
            System.out.println("Serveur en attente sur le port " + port + "...");
            while (true) {
                try (Socket socket = serveur.accept()) {
                    System.out.println("Client connecte : " + socket.getInetAddress());

                    // Cote serveur : creer le flux de sortie en premier et le vider
                    // pour eviter un blocage (interblocage des en-tetes de flux).
                    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                    out.flush();
                    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                    // c) + d) Reception de l'objet et recuperation des donnees
                    Operation op = (Operation) in.readObject();
                    System.out.println("Recu : " + op);

                    // e) Calcul
                    Object reponse;
                    switch (op.getOperateur()) {
                        case '+': reponse = op.getOperande1() + op.getOperande2(); break;
                        case '-': reponse = op.getOperande1() - op.getOperande2(); break;
                        case '*': reponse = op.getOperande1() * op.getOperande2(); break;
                        case '/':
                            if (op.getOperande2() == 0) reponse = "Erreur : division par zero";
                            else reponse = op.getOperande1() / op.getOperande2();
                            break;
                        default: reponse = "Erreur : operateur inconnu";
                    }

                    // f) Envoi du resultat
                    out.writeObject(reponse);
                    out.flush();
                } catch (ClassNotFoundException e) {
                    System.err.println("Classe inconnue : " + e.getMessage());
                } catch (IOException e) {
                    System.err.println("Erreur client : " + e.getMessage());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
