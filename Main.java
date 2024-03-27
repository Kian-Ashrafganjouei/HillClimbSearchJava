import java.util.Scanner;

 
public class Main {
	
	


	public static void main(String[] args) {
		
	       
		    int n = 0; 
	        try (Scanner s=new Scanner(System.in)) {
	        	while (true){
	        		System.out.println("Enter the number of Queens :");
	        		n = s.nextInt();
	        		if ( n == 2 || n ==3) {
	        			System.out.println("No Solution possible for "+ n +" Queens. Please enter another number");
	        		}
	        		else
	        			break;
	        	}
	        }
	        long timestamp1 = System.currentTimeMillis();
	        
	        System.out.println("Solution to "+ n +" queens using hill climbing search:");
	        
	        // HillClimbingSearch hcs = new HillClimbingSearch(n);
	        
	        // hcs.runSearch();
	        
	        // if (hcs.getFinalSolution() != null)
	        // 	hcs.printState(hcs.getFinalSolution());
	        
			int numThreads = 8; // Number of threads to run
			ThreadGroup threadGroup = new ThreadGroup("HillClimbingThreads");

			for (int i = 0; i < numThreads; i++) {
				HillClimbingSearch runSearch = new HillClimbingSearch(n);
				Thread thread = new Thread(threadGroup, runSearch, "Thread-" + i);
				thread.start();
			}
	
			// Wait for any thread to finish
			while (threadGroup.activeCount() > 0) {
				// Do nothing, just wait
			}
	
			// Interrupt all threads to ensure they stop
			threadGroup.interrupt();

			// Get the final solution from HillClimbingSearch
			NQueen[] solution = HillClimbingSearch.getFinalSolution();
			if (solution != null) {
				System.out.println("Final solution found:");
				new HillClimbingSearch(n).printState(solution);
			} else {
				System.out.println("No solution found.");
			}
	        
	        //Printing the solution
	        long timestamp2 = System.currentTimeMillis();
			
			long timeDiff = timestamp2 - timestamp1;
			System.out.println("Execution Time: "+timeDiff+" ms");
	        
		   
	    }
}