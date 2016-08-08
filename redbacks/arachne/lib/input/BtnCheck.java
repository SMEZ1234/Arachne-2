package redbacks.arachne.lib.input;

import redbacks.arachne.lib.checks.Check;
import edu.wpi.first.wpilibj.buttons.Button;

/**
 * @author Sean Zammit
 */
public class BtnCheck extends Button
{
    Check check;
    boolean isStarted = false;
    boolean resetCheck = true;

    /**
     * JAVADOC
     */
    public BtnCheck(Check check) {
    	this.check = check;
    }

    public BtnCheck(Check check, boolean resetCheckOnCompletion) {
    	this(check);
    	this.resetCheck = resetCheckOnCompletion;
    }
    
    public boolean get() {
    	if(!isStarted) check.initialise(null, null);
    	check.onRun();
    	if(check.isFinished()) {
    		if(resetCheck) {
        		check.onFinish();
        		isStarted = false;
    		}
    		return true;
    	}
        return false;
    }
}
