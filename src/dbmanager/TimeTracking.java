package dbmanager;

public class TimeTracking {

	int start;
	String time;
	/*
	 * 
	 *   object of TimeTracking class should be created when file is opened
	 *   and elapsedTime() method should be called, when file is being closed
	 *   
	 */

//	public TimeTracking() {
//		start = (int) System.currentTimeMillis(); 					// captures current time in milliseconds
//	}
	
	public void startTracking() {
		start = (int) System.currentTimeMillis(); 					// captures current time in milliseconds
	}

	public void elapsedTime(int startTime) {									// should be called when file is closed
		int now = (int) System.currentTimeMillis(); 				// captures current time in milliseconds
		time = "Time spent: ";
		int seconds = (now - startTime) / 1000;							// milliseconds are negotiated, just seconds
		int minutes = 0;
		int hours = 0;

		if (seconds >= 3600) {										// checks for hours
			hours = (seconds / 3600);
			time += hours + " h, ";
			seconds = seconds % 3600;
		}
		if (seconds >= 60) {										// checks for minutes
			minutes = seconds / 60;
			time += minutes + " min, ";
			seconds = seconds % 60;
		}
		time += seconds + " sec.";					// add seconds that are left
//		return elapsedTime;
	}
	
//	public static void main(String[] args) {
	// start
//		TimeTracking test = new TimeTracking();
	// end
//		System.out.println(test.elapsedTime());
//		
//	}
	
}
