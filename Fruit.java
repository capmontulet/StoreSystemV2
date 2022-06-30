import java.util.Formatter;
import java.util.Scanner;

/**
 * Class Fruit Extends FoodItem. Contains data and methods pertaining to fruit object.
 * @author Thomas Stanley
 *
 */
public class Fruit extends FoodItem{
	
	private String orchardName;
	
	/**
	 * Constructor
	 */
	public Fruit() {
		orchardName="";
	}
	/**
	 * Takes user input for values from parent class, then from child class
	 */
	@Override
	public boolean addItem(Scanner keyboard, boolean fromFile) {
		if(fromFile==false) {
		if(super.addItem(keyboard,fromFile)==true) {
		System.out.println("Enter the name of the orchard supplier: ");
		orchardName=keyboard.nextLine();
		}
		return true;
		}else {
			super.addItem(keyboard, true);
			orchardName=keyboard.nextLine();
		}
		return true;
		
	}
	/**
	 * Displays values from parent class, then value specific to child class
	 */
	@Override
	public String toString() {
		System.out.print(super.toString());
		return "Orchard supplier: "+orchardName+"\n";
		
	}
	/**
	 * Overrides parent class, prints out object specific details, like object type, and orchard name.
	 * @param writer Formatter object
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("f\n");
		super.outputItem(writer);
		writer.format("%s\n", orchardName);
		
	}
}