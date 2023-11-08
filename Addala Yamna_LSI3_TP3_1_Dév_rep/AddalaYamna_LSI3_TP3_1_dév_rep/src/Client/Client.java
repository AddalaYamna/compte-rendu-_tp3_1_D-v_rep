package Client;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class Client implements Runnable {

 private Socket s; // Socket pour la communication avec le serveur
 private int clientnumber; // Numéro du client
public Client(Socket s, int nbC) {
     this.s = s;
     this.clientnumber = nbC;
 }

 // Méthode exécutée lorsqu'un thread client est démarré
 @Override
 public void run() {
     try {
         SocketAddress clientAddress = s.getRemoteSocketAddress(); // Récupère l'adresse IP du client
         System.out.println("IP du Client " + clientnumber + ": " + clientAddress);

         OutputStream out = s.getOutputStream(); // Récupère le flux de sortie pour envoyer des données au serveur
         PrintWriter pw = new PrintWriter(out); // Crée un PrintWriter pour écrire des données dans le flux de sortie
         pw.println("Vous êtes le Client " + clientnumber); // Écrit un message dans le flux
         pw.flush(); // Vide le tampon pour s'assurer que les données sont envoyées immédiatement
         s.close(); // Ferme la connexion avec le serveur
     } catch (IOException e) {
         e.printStackTrace(); // Gère les exceptions liées à l'entrée/sortie
     }
 }
public static void main(String[] args) {
    try {
        Socket socket = new Socket("localhost", 1254); // Connectez-vous au serveur sur le port 1234
        int clientNumber = 1; // Choisissez un numéro de client
        Thread clientThread = new Thread(new Client(socket, clientNumber)); // Créez un thread client
        clientThread.start(); // Démarrez le thread client
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}