package primeService.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

import primeService.util.Debug;

public class PrimeClientWorker {
	protected static Socket Client_socket;
	//private DataOutputStream streamOut = null;
	protected static BufferedReader in;
	static Scanner console;
	static String response; // check for response
	String request;


	public PrimeClientWorker(Socket socket) {
		PrimeClientWorker.Client_socket = socket;
		Debug.getInstance().printToStdout(4, "PrimeClientWorker's class constructor");
	}

	
	public static void stop()
	   {  try
	      {  if (console   != null)  console.close();
	         if (Client_socket    != null)  Client_socket.close();
	      }
	      catch(IOException ioe)
	      {  System.out.println("Error closing ...");
	      }
	   }
	public static void worker(String send) {
		
		try {
			Debug.getInstance().printToStdout(3, "PrimeClientWorker's worker Method called..");
			System.out.println(send);
			java.io.OutputStream os = Client_socket.getOutputStream();
			OutputStreamWriter osw = new OutputStreamWriter(os);
			BufferedWriter bw = new BufferedWriter(osw);
			bw.write(send + "\n");
			bw.flush();
			System.out.println("Message sent to the server : " + send);
		}
			// streamOut = new
			// DataOutputStream(Client_socket.getOutputStream());
			// streamOut.writeBytes(send + "\n");
		catch (IOException e) {
			System.err.println("Stream Exception-  " + e.getMessage());
			System.exit(1);
		}
		
		// console.close();
	}

	
	  public static String recive_response(){
		  while(true){
		  try{
			  Debug.getInstance().printToStdout(3, "PrimeClientWorker's recieve_response Method called..");
			  in = new BufferedReader(new InputStreamReader(Client_socket.getInputStream()));
			 response = in.readLine();
			  //System.out.println(response);
			  
			  
			  } catch (IOException e1) {
				  System.err.println("IO Exception- " +e1.getMessage());
			  }
	      	return response;
		  }
	  }
}
