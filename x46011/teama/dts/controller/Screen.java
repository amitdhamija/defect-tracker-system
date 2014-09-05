package x46011.teama.dts;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The abstract Screen class allows the subclass to show and interact with the current screen.
 * It provides abstract methods to implement.
 * 
 * @author Amit Dhamija
 * @version 1.0
 */
public abstract class Screen {

	/**
	 * Default constructor
	 */
	public Screen() {
		
	}
	
	/**
	 * Implement to display the screen.
	 */
	abstract void show();
	
	/**
	 * Implement to initialize the screen.
	 */
	abstract void initialize();
	
	/**
	 * Implement to destroy the screen.
	 */
	abstract void destroy();
}