package serveur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServThread implements Runnable{
	
	protected boolean isRunning = true;
	private Thread thread;

	private Socket socket;
	private BufferedReader entree;
	private PrintWriter sortie;

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
		try {
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			sortie = new PrintWriter(socket.getOutputStream(), true);
		} catch (IOException e) {
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
