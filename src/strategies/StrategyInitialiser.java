/* SWEN30006 Project: Part B
 * By: Sean O'Farrell, Shaikh Atiq, Abhishek Gupta
 * Controls initialisation of strategic aspects of the system
 * Changes: Incorporated properties file, replaced hardcode choice selection
 * Changed name from Automail to better fit updated purpose
 */

package strategies;

import automail.Robot;
import automail.StorageTube;
import automail.SystemProperties;

public class StrategyInitialiser {
    public Robot robot;
    public MailPool mailPool;

    
    public StrategyInitialiser() {
    	    	
    	// Initialise mail pool system
    	mailPool = new MailPool();
    	
        // Determine robot behaviour and variables
    	// Currently hardcoded in - lacks extensibility, but suitable for scope
    	String robotType = String.valueOf(SystemProperties.getRobotType());
    	IRobotBehaviour robotBehaviour = null;
    	int tubeSize = 0;
    	switch(robotType) {
    		// Original robot with old, simple behaviour
    		case "Small_Comms_Simple":
    			robotBehaviour = new SimpleCommsRobotBehaviour();
    			tubeSize = StorageTube.SMALL_TUBE_SIZE;
    			break;
    		// Original robot with new, smart behaviour
    		case "Small_Comms_Smart":
    			robotBehaviour = new SmartCommsRobotBehaviour();
    			tubeSize = StorageTube.SMALL_TUBE_SIZE;
    			break;
    		// Second-hand robot, smart behaviour, larger tube, no comms
    		case "Big_Smart":
    			robotBehaviour = new SmartRobotBehaviour();
    			tubeSize = StorageTube.BIG_TUBE_SIZE;    		
    			break;
    		// Something wrong
    		default :
    			System.out.println("Invalid Robot Type");
    			break;
    	}
    	
    	// Initialise robot control system
    	robot = new Robot(robotBehaviour, mailPool, tubeSize);
    }
}
