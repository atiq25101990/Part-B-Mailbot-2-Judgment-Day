/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Tracks time
 * Changes: Moved final mail item generation time to mail generator
 */

package automail;

public class Clock {
	
	// Current time step in simulation
    private static int Time = 0;

    // Get the time
    public static int Time() {
    	return Time;
    }
    
    // Increment the time
    public static void Tick() {
    	Time++;
    }
}
