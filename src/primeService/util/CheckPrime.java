package primeService.util;

public class CheckPrime {
	
	
	public String checkprime(int prime){
		String msg;
		if(prime%2==0){
			msg = "<primeQueryResponse><intValue>"+prime+"</intValue><isPrime>No Idea!</isPrime></primeQueryResponse>";
					
		}
		else{
			msg = "<primeQueryResponse><intValue>"+prime+"</intValue><isPrime>Yes</isPrime></primeQueryResponse>";
		}
		return msg;
		
		
	}

}


