import java.util.Formatter;
import java.util.Scanner;
/**
 * Preserve class extends FoodItem Class. Contains data and methods pertaining to preserve object.
 * @author Thomas Stanley
 *
 */
public class Preserve extends FoodItem {
	
	private int jarSize;
	
	/**
	 * constructor
	 */
	public Preserve() {
		jarSize=0;
	}
	/**
	 * Displays values from parent class, then value specific to child class
	 */
	@Override
	public String toString() {
		System.out.print(super.toString());
		return "Size: "+jarSize+"mL\n";
	}
	
	/**
	 * Takes user input for values from parent class, then from child class
	 */
	@Override
	public boolean addItem(Scanner keyboard, boolean fromFile) {
		if(fromFile==false) {
		if(super.addItem(keyboard,fromFile)==true) {
		System.out.println("Enter the size of the jar in mL: ");
		jarSize=keyboard.nextInt();
		}
		return true;
		}else {
			super.addItem(keyboard, true);
			jarSize=keyboard.nextInt();
		}
		return true;
		
	}
	/**
	 * Overrides parent class, prints out object specific details, like object type, and jar size.
	 * @param writer Formatter object
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("p\n");
		super.outputItem(writer);
		writer.format("%d\n", jarSize);
		
	}
	
}
