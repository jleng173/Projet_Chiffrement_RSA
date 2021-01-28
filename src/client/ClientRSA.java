package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import crypt.Cryptage;
import crypt.PrivateKey;
import crypt.PublicKey;

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
		
		PublicKey pb_key = new PublicKey();
		System.out.println("My pbkey = " + pb_key.get_e() + " " + pb_key.get_n() + " " + pb_key.get_m());

		PublicKey pb_key_server;
		PrivateKey pv_key = new PrivateKey(pb_key.get_e(), pb_key.get_m(), pb_key.get_n());
		
		try {
			socket = new Socket(serveur, port);

			sortie = new PrintWriter(socket.getOutputStream(), true);
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			//Envoi clé publique
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ArrayList<BigInteger> elementList = new ArrayList<BigInteger>();
			elementList.add(pb_key.get_e());
			elementList.add(pb_key.get_n());
			elementList.add(pb_key.get_m());
			oos.writeObject(elementList);
			//Réception clé publique serveur
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ArrayList<BigInteger> elementListServer = (ArrayList<BigInteger>) ois.readObject();
			boolean isRunning = true;
			pb_key_server = new PublicKey(elementListServer.get(0), elementListServer.get(1), elementListServer.get(2));
			System.out.println("PBkeyServer =" + pb_key_server.get_e() + " " + pb_key_server.get_n() + " " + pb_key_server.get_m());
			while (isRunning) {

			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
