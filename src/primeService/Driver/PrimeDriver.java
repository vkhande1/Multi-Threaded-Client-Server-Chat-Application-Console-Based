package primeService.Driver;

import primeService.client.ClientDriver;
import primeService.server.ServerDriver;
import primeService.util.Debug;

public class PrimeDriver {
	static ServerDriver sd;
	static ClientDriver PCS;
	protected static String Host;
	protected static int debug_value;
	
	public static void main(String[] args) throws Exception {
		if(!(args[0].equals("client") || args[0].equals("server"))){
			System.err
			.println("Please enter the correct Arguments");
	System.exit(1);
		}
		if (args.length < 2) 
		{
			System.err
					.println("Please enter the correct Arguments to run the server");
			System.exit(1);
		} 
		
		if (args.length < 4 && args[0].equals("client")) 
		{
			System.err
					.println("Please enter the correct Mode - client , Port Number, Host and Debug value.");
			System.exit(1);
		} 
		
					
		if (args.length < 3 && args.equals("server")) 
		{
			System.err
					.println("Please enter the correct Mode - server , Port Number.");
			System.exit(1);
		}
		
		
		
		try {
			Integer.parseUnsignedInt(args[1]);
				
			if(args[0].equals("client")){
				Host = args[2].toString();
				//System.out.println(Host);
				try{
				debug_value = Integer.parseUnsignedInt(args[3]);
				Debug.getInstance().setDebug(debug_value);
				} catch(NumberFormatException e){
					System.err.println("Debug value error - " + e.getMessage());
				}
			//	System.out.println(debug_value);
				PCS = new ClientDriver(Integer.parseUnsignedInt(args[1]),Host);
				
				
			} else 
				if(args[0].equals("server")){
					sd = new ServerDriver(Integer.parseInt(args[1]));
					sd.invoke_threads();
					//System.out.println("server");
				
			}
		} catch (NumberFormatException nfe) {
			System.err
					.println("Please enter the correct port number to your second argument - "
							+ nfe.getMessage());
			System.exit(1);
		} /*catch(UnknownHostException e){
			System.err.println("Unknown Host, Please correct the Host name - Message: " + e.getMessage() );
		}*/
		
		

	}

}
