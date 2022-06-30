import java.util.Formatter;
import java.util.Scanner;
/**
 * Cheese class extends FoodItem class. Contains data and methods pertaining to cheese object.
 * @author Thomas Stanley
 *
 */
public class Cheese extends FoodItem{

	private float cheeseWeight;
	
	/**
	 * constructor
	 */
	public Cheese(){
		cheeseWeight=0;
	}
	/**
	 * Displays values from parent class, then value specific to child class
	 */
	@Override
	public String toString() {
		System.out.print(super.toString());
		return "Cheese Weight: "+cheeseWeight+"\n";
	}
	
	/**
	 * Takes user input for values from parent class, then from child class
	 */
	@Override
	public boolean addItem(Scanner keyboard, boolean fromFile) {
		if(fromFile==false) {
		if(super.addItem(keyboard,fromFile)==true) {
		System.out.println("Enter the weight of cheese in grams: ");
		cheeseWeight=keyboard.nextFloat();
		}
		return true;
		}else {
			super.addItem(keyboard, true);
			cheeseWeight=keyboard.nextFloat();
		}
		return true;
		
	}
	
	/**
	 * Overrides parent class, prints out object specific details, like object type, and cheese weight.
	 * @param writer Formatter object
	 */
	@Override
	public void outputItem(Formatter writer) {
		writer.format("c\n");
		super.outputItem(writer);
		writer.format("%.2f\n", cheeseWeight);
		
	}
	
}
