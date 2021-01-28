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

import crypt.PrivateKey;
import crypt.PublicKey;

public class ServThread implements Runnable{
	
	protected boolean isRunning = true;
	private Thread thread;

	private Socket socket;
	private BufferedReader entree;
	private PrintWriter sortie;

	private PublicKey pb_key;
	private PrivateKey pv_key;
	private PublicKey pb_key_client;
	
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
		System.out.println("My pbkey = " + pb_key.get_e() + " " + pb_key.get_n() + " " + pb_key.get_m());
		try {
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sortie = new PrintWriter(socket.getOutputStream(), true);
			//Envoie de la clé
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ArrayList<BigInteger> elementList = new ArrayList<BigInteger>();
			elementList.add(pb_key.get_e());
			elementList.add(pb_key.get_n());
			elementList.add(pb_key.get_m());
			oos.writeObject(elementList);
			//Réception clé publique client
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			ArrayList<BigInteger> elementListServer = (ArrayList<BigInteger>) ois.readObject();
			boolean isRunning = true;
			pb_key_client = new PublicKey(elementListServer.get(0), elementListServer.get(1), elementListServer.get(2));
			System.out.println("PbKeyClient = " + pb_key_client.get_e() + " " + pb_key_client.get_n() + " " + pb_key_client.get_m());
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (isRunning) {
			
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
