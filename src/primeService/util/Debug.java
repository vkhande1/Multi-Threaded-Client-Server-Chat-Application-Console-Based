package primeService.util;

	public class Debug {
		private int debugLevel;

		public void setDebug(int level){
			debugLevel = level;
		}


		public int getDebug(){
			return debugLevel;
		}

		private volatile static Debug uniqueInstance;// = new Debug();

		private Debug(){
		}

		public static Debug getInstance(){
			if (uniqueInstance == null){
				synchronized (Debug.class) {
					if(uniqueInstance == null){
						uniqueInstance = new Debug();
					}
				}
			}
			return uniqueInstance;
		}

		public void printToStdout(int level,String debugMessage)
		{
			if(level == 0){

			}
			else if(debugLevel == level){
				System.out.println(debugMessage);
			}
			else {

			}
		}
	}
