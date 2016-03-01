package primeService.client;

import java.util.Scanner;

import primeService.socket.PrimeClientSocket;
import primeService.socket.PrimeClientWorker;
import primeService.util.Debug;

public class ClientDriver {
	protected int client_port;
	protected String Host;
	Scanner sc;
	String query;
	String response;
	

	public ClientDriver(int port, String host) throws InterruptedException {
		Debug.getInstance().printToStdout(4, "ClientDriver's constructor called..");
		this.client_port = port;
		this.Host = host;
		invoke_threads();
	}
		
	public void invoke_threads() throws InterruptedException{
		try{
			Debug.getInstance().printToStdout(3, "ClientDriver's invode_threads method called..");
			new PrimeClientSocket(client_port ,Host);
			ClientMenu cm = new ClientMenu();
			while (true) {
				cm.printMenu();
				sc = new Scanner(System.in);
				int option = sc.nextInt();
				switch (option) {
				case 1:
					option = 1;
					System.out.println("Set client name: ");
					query = sc.next();
					break;
				case 2:
					option = 2;
					System.out.println("Enter number to query for prime");
					query  = query + " " + sc.next();
					//System.out.println("Reached");
					String a = query;
					PrimeClientWorker.worker(a);
					response = PrimeClientWorker.recive_response();
					break;
				case 3:
					option = 3;
					System.out.println("What is the server response?");
				    System.out.println(response);
					response = null;
					break;
				case 4:
					System.out.println("Quit");
					System.exit(1);
					break;

				}
			}	
					 
		}   catch (Exception e) {
			System.err.print("Exception - " + e.getMessage());
			System.exit(1);
		}
}
	
	
}