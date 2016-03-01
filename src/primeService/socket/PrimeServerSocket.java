package primeService.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import primeService.server.AllPrimeQueries;

public class PrimeServerSocket implements Runnable{
	protected int serverport;
	//protected Socket socket;
	protected ServerSocket serverSocket = null;
	protected boolean isStopped = false;
	protected Thread runningThread = null;
	AllPrimeQueries store;
	
	protected String Client;
	

	public PrimeServerSocket(int server_port, AllPrimeQueries s) {
		this.serverport = server_port;
		this.store = s;
		//System.out.println(" PSS constructor");
	}

	public void run() {
		synchronized (this) {
			this.runningThread = Thread.currentThread();
		}
		openServerSocket();
		
		while (!isStopped()) {
			Socket clientsocket = null;
			try {
				clientsocket = serverSocket.accept();
				//System.out.println("waiting...");
				new Thread(new PrimeServerWorker(clientsocket,store)).start();
			} catch (IOException e) {
				if (isStopped()) {
					System.out.println("Server Stopped.");
					return;
				}
				System.err.println("Error accepting client connection" + " - "
						+ e.getMessage());
			}
			
			
		}

	}

	private synchronized boolean isStopped() {
		return this.isStopped;
	}

	public synchronized void stop() {
		this.isStopped = true;
		try {
			this.serverSocket.close();
		} catch (IOException e) {
			System.err.println("Error while closing server" + "  "
					+ e.getMessage());
		}
	}

	private void openServerSocket() {
		try {
			this.serverSocket = new ServerSocket(this.serverport);
			//System.out.println("Server Started.");
		} catch (IOException e) {
			System.err.println("Unable to open port - " + this.serverport
					+ "  " + e.getMessage());
			System.exit(1);
		}
	}

}
