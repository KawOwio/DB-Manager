package dbmanager;

public class TimeTracking {

	int start;
	/*
	 * 
	 *   object of TimeTracking class should be created when file is opened
	 *   and elapsedTime() method should be called, when file is being closed
	 *   
	 */

	public TimeTracking() {
		start = (int) System.currentTimeMillis(); 					// captures current time in milliseconds
	}

	public String elapsedTime() {									// should be called when file is closed
		int now = (int) System.currentTimeMillis(); 				// captures current time in milliseconds
		String elapsedTime = "Time spent on editing file: ";
		int seconds = (now - start) / 1000;							// milliseconds are negotiated, just seconds
		int minutes = 0;
		int hours = 0;

		if (seconds >= 3600) {										// checks for hours
			hours = (seconds / 3600);
			elapsedTime += hours + " hour(s), ";
			seconds = seconds % 3600;
		}
		if (seconds >= 60) {										// checks for minutes
			minutes = seconds / 60;
			elapsedTime += minutes + " minute(s), ";
			seconds = seconds % 60;
		}
		elapsedTime += seconds + " seconds(s).";					// add seconds that are left
		return elapsedTime;
	}
	
//	public static void main(String[] args) {
	// start
//		TimeTracking test = new TimeTracking();
	// end
//		System.out.println(test.elapsedTime());
//		
//	}
	
}
