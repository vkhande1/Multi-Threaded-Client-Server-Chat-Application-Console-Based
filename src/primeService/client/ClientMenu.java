package primeService.client;

import primeService.util.Debug;


public class ClientMenu {

	public void printMenu() {
		Debug.getInstance().printToStdout(3, "ClientMenu's printMenu method called..");
		System.out.println("Please pick any of the below mentioned options- ");
		System.out.println("Choose " + 1 + " For Set client name");
		System.out.println("Choose " + 2
				+ " For Enter number to query for prime");
		System.out.println("Choose " + 3 + " For What is the server response?");
		System.out.println("Choose " + 4 + " To Quit");

	}
}
