package primeService.socket;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import primeService.server.AllPrimeQueries;
import primeService.util.CheckPrime;

public class PrimeServerWorker implements Runnable {
	protected static Socket socket;
	private static DataInputStream streamIn = null;
	protected String line;
	protected int Threshold = 3;
	AllPrimeQueries store;
	PrintWriter output;

	public PrimeServerWorker(Socket clientsocket, AllPrimeQueries s) {
		PrimeServerWorker.socket = clientsocket;
		this.store = s;

	}

	public static void close() throws IOException {
		if (socket != null)
			socket.close();
		if (streamIn != null)
			streamIn.close();
		
	}

	public void run() {
		try {

			while (true) {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				line = br.readLine();
				// System.out.println("Message received from client is "+line);
				String[] slice = line.split(" ");
				String name = slice[0];
				int value = 0;
				//java.io.OutputStream os = socket.getOutputStream();
				//OutputStreamWriter osw = new OutputStreamWriter(os);
				//BufferedWriter bw = new BufferedWriter(osw);
				PrintWriter pw	= new PrintWriter(new BufferedOutputStream(socket.getOutputStream()), true);
				
				try {
					value = Integer.parseInt(slice[1].toString());
					if (value > Threshold) {
						AllPrimeQueries.store_inputs(name, slice[1]);
						//store.Display();
						CheckPrime cp = new CheckPrime();
						String reply = cp.checkprime(value);
						pw.write(reply + "\n");
						//System.out.println("Message sent to the client is "		+ reply);
						pw.flush();
						
					} else {
						String inform_client = "<primeQueryResponse><intValue>"
								+ value
								+ "</intValue><isPrime>Not Valid</isPrime></primeQueryResponse>";
						pw.write(inform_client + "\n");
						System.out.println("Message sent to the client is "+inform_client);
						pw.flush();
						
					}  
				} catch (NumberFormatException e) {
					System.err
							.println("Please enter the value to your query-  "
									+ e.getMessage());
				}
						//br.close();
			}

		} catch (IOException e) {
			System.err.println("you have stopped the connection - " + e.getMessage());
		}
	}

}
