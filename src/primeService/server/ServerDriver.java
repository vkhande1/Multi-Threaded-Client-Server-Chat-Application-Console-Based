package primeService.server;

import java.util.Scanner;

import primeService.socket.PrimeServerSocket;


public class ServerDriver{
	protected Thread T1;
	String query;
	Scanner sc;
	String response;
	
	protected int port;
	
	public ServerDriver(int server_port) {
		this.port = server_port;
		//System.out.println("SD constructor");
	}

	public void invoke_threads() throws InterruptedException{
		try{
		 AllPrimeQueries store = new AllPrimeQueries();
		 T1 = new Thread(new PrimeServerSocket(port, store));
		 T1.start();
		 //System.out.println("T1");
		 ServerMenu sm = new ServerMenu();
		 
		 while(true)
		 {
			 sm.printMenu();
			 
			 sc = new Scanner(System.in);
			 int option = sc.nextInt();
			 
			 switch (option) {
				case 1:
					option = 1;
					System.out.println("Set client name: ");
					query = sc.next();
				    AllPrimeQueries.Display_respective_key(query);
					break;
				case 2:
					option = 2;
					System.out.println("All Client Queries");
					AllPrimeQueries.Display();
					//System.out.println("Reached");
					break;
				case 3:
					option = 3;
					System.out.println("Quit");
					System.exit(1);
					break;
		 }
	}
		 
		 //System.out.println("T2");
		 
		 
		 
		 
		} catch (Exception e) {
			System.err.print("Exception - " + e.getMessage());
			System.exit(1);
		}
	}

	
	

}
