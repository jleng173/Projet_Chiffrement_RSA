package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientRSA {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket socket;
		BufferedReader entree;
		PrintWriter sortie;
		String serveur = "localhost";
		int port = 36000;

		System.out.println("CLIENT");
		System.out.println("Port " + port + " adresse " + serveur);
		
		try {
			socket = new Socket(serveur, port);

			sortie = new PrintWriter(socket.getOutputStream(), true);
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String chaine;
		
			boolean isRunning = true;
			
			while (isRunning) {
				
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
