package primeService.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import primeService.util.Debug;

public class PrimeClientSocket {
	private Socket socket = null;
	String query;
	

	public PrimeClientSocket(int client_port, String host) {
		//System.out.println("Connecting...");
		try {
			//System.out.println(host);
			socket = new Socket(host, client_port);
			Debug.getInstance().printToStdout(4, "PrimeClientSocket's constructor called");
			System.out.println("Connected: " + socket);
			new PrimeClientWorker(socket); 
			
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
			System.exit(1);
		} catch (IOException ioe) {
			System.out.println("Please start the server: " + ioe.getMessage());
			System.exit(1);
		}
	}

	
	


}
