package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import crypt.Cryptage;
import crypt.Key;
import crypt.PrivateKey;
import crypt.PublicKey;

public class ServThread implements Runnable{
	
	protected boolean isRunning = true;
	private Thread thread;

	private Socket socket;
	private PublicKey pb_key;
	private PrivateKey pv_key;
	private Key pb_key_client;
	private Key pv_key_server;
	
	
	ArrayList<BigInteger> message_chiffre;

	public ServThread(ServerSocket ecoute) {
		try {
			socket = ecoute.accept();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void run() {
		pb_key = new PublicKey();
		pv_key = new PrivateKey(pb_key.get_e(), pb_key.get_m(), pb_key.get_n());
		pv_key_server = pv_key.getPKey();
		System.out.println("My pbkey = " + pb_key.get_e() + " " + pb_key.get_n() + " " + pb_key.get_m());
		try {
			new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new PrintWriter(socket.getOutputStream(), true);
			
			//Envoie de la clé
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ArrayList<BigInteger> elementList = new ArrayList<BigInteger>();
			elementList.add(pb_key.get_n());
			elementList.add(pb_key.get_e());
//			elementList.add(pb_key.get_m());
			oos.writeObject(elementList);
			
			//Réception clé publique client
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ArrayList<BigInteger> elementListServer = (ArrayList<BigInteger>) ois.readObject();
			boolean isRunning = true;
			pb_key_client = new Key(elementListServer.get(0), elementListServer.get(1));
			System.out.println("PbKeyClient = n:" + pb_key_client.getN() + " e:" + pb_key_client.getY() + "\n");
			
			//String chaine = entree.readLine();
			//System.out.println(chaine);
			
			String message_claire;
			

			
			while (isRunning) {
				
				//Récupération message crypté et déchiffrement
				ois = new ObjectInputStream(socket.getInputStream());
				ArrayList<BigInteger> message_chiffre = (ArrayList<BigInteger>)ois.readObject();
				ArrayList<BigInteger> message_ascii = crypt.Cryptage.dechiffrement(message_chiffre, pv_key_server);
				message_claire = crypt.Cryptage.ascii_to_string(message_ascii);
				System.out.println(message_claire);
				
				if(message_claire.matches("exit")) {
					isRunning=false;
				}
				else {
					//Envoi message crypté
					oos = new ObjectOutputStream(socket.getOutputStream());
					ArrayList<BigInteger> phrase = Cryptage.convert_ascii(message_claire);
					ArrayList<BigInteger> texte_chiffre = crypt.Cryptage.chiffrement(phrase, pb_key_client);
					oos.writeObject(texte_chiffre);				
				}
			}
			
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
	}
	
	public void stop() {
		isRunning = false;
	}

	public void init() {
		System.out.println("Connexion Client");
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}
	

}
