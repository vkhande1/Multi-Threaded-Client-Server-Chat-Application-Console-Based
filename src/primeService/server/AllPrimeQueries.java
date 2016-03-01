package primeService.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import primeService.util.Debug;

public class AllPrimeQueries {

	static ConcurrentMap<String, List<String>> con = new ConcurrentHashMap<String, List<String>>();
	public static void store_inputs(String key, String value){
		Debug.getInstance().printToStdout(3, "AllPrimeQueries's store_inputs method called");
		List<String> list = con.get(key);	
		if(list==null)
		{
			 list = new ArrayList<String>();
			// System.out.println("Reached");
			 list.add(value);
   			 con.put(key, list);
   			Debug.getInstance().printToStdout(2, "Entry added to the data structure");
		} else
		list.add(value);
		Debug.getInstance().printToStdout(2, "Entry added to the data structure");
	}
	
	public static void Display(){
		Debug.getInstance().printToStdout(1, "Printing Results Stored in results class");
		System.out.println(con);
	}
	
	public static void Display_respective_key(String key){
		System.out.println(key + " " + con.get(key));
		
	}
	
}
