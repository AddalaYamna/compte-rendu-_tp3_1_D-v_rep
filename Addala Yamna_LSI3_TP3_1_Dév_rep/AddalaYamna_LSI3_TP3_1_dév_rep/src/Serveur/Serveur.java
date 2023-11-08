package Serveur;
import java.io.IOException;
	import java.net.ServerSocket;
	import java.net.Socket;

	import Client.Client;

	public class Serveur {

	    public static void main(String[] args) {
	        try {
	           ServerSocket serverSocket = new ServerSocket(1254);
	            System.out.println("Serveur démarré. En attente de connexions...");

	            int clientNumber = 1;

	            while (true) {
	                Socket clientSocket = serverSocket.accept();  // Accepte une connexion du client
	                Thread clientThread = new Thread(new Client(clientSocket, clientNumber));  // Crée un thread client avec le socket et le numéro de client
	                clientThread.start();   // Démarre le thread client
	                clientNumber++;   // Incrémente le numéro du client pour le prochain client
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}


