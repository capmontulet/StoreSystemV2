import java.util.Formatter;
import java.util.Scanner;
/**
 * Parent object for store system.
 * @author Thomas Stanley
 */
public class FoodItem {

	protected int itemCode;
	private String itemName;
	private float itemPrice;
	private int itemQuantityInStock;
	private float itemCost;
	
/**
 * Constructor
 */
	public FoodItem() {
		
	}
	
/**
 * Overloaded Constructor
 * @param itemCode Code of item
 * @param itemName Name of item
 * @param itemPrice Price of item
 * @param itemQuantityInStock Quantity in stock
 * @param itemCost Cost of item
 */
	public FoodItem(int itemCode, String itemName, float itemPrice, int itemQuantityInStock, float itemCost) {
		this.itemCode=itemCode;
		this.itemName=itemName;
		this.itemPrice=itemPrice;
		this.itemQuantityInStock=itemQuantityInStock;
		this.itemCost=itemCost;	
	}
/**
 * Takes user input for parameters of the class.
 * @param keyboard Scanner object
 * @param fromFile Can be passed as either true or false, if false, user input
 *			       is required for values, if true, object is made from values
 *                 in the file.
 * @return Returns boolean value
 */
	public boolean addItem(Scanner keyboard, boolean fromFile) {
		//if fromFile is false, user entry in required to create FoodItem object
		if(fromFile==false) {
		try {
		keyboard.nextLine();
		System.out.println("Enter the name for the item: ");
		itemName=keyboard.nextLine();
		System.out.println("Enter the quantity for the item: ");
		itemQuantityInStock=keyboard.nextInt();
		System.out.println("Enter the cost of the item: ");
		itemCost=keyboard.nextFloat();
		System.out.println("Enter the sales price of the item: ");
		itemPrice=keyboard.nextFloat();
		keyboard.nextLine();
		}catch(Exception e) {
			System.out.println("Invalid entry");
			keyboard.nextLine();
			return false;
		}
		//if no errors are detected, method will return true
		return true;
		}else{
		//when fromFile is true, means info will come from a file, user entry not required.
		try {
		keyboard.nextLine();
		itemName=keyboard.nextLine();
		itemQuantityInStock=keyboard.nextInt();
		itemCost=keyboard.nextFloat();
		itemPrice=keyboard.nextFloat();
		keyboard.nextLine();
		}catch(Exception e) {
			System.out.println("Invalid entry");
			keyboard.nextLine();
			return false;
		}
		//if no errors are detected, method will return true
		return true;
		}
	}
	/**
	 * Displays parameters for parent class parameters
	 */
	@Override
	public String toString() {
		return"Item: "+itemCode+" "+itemName+" "+itemQuantityInStock+" Price: $"+itemPrice+" Cost: $"+itemCost+" .";
	}
	/**
	 * Checks to see if two item codes are the same
	 * @param item passed from inventory class.
	 * @return returns boolean value
	 */
	public boolean isEqual(FoodItem item) {
		
		
			if(item.itemCode==itemCode) {
				return true;
			}
			
		return false;
	}
	/**
	 * Reads a valid itemcode from scanner and returns true or false if successful
	 * @param keyboard Scanner object
	 * @param fromFile can be passed as either true or false.
	 * @return returns boolean object
	 */
	public int inputCode(Scanner keyboard, boolean fromFile) {
		
		System.out.println("Enter the code for the item: ");
		int itemCode=keyboard.nextInt();
		
		return itemCode;
	}
	/**
	 * Changes the item stock with amount value pass from inventory class
	 * @param amount value passed from inventory
	 * @param buyOrSell Adds or minuses amount based on option
	 * @return returns boolean value
	 */
	public boolean updateItem(int amount, boolean buyOrSell) {
		//if true, amount is added
		if(buyOrSell==true) {
		itemQuantityInStock+=amount;
		//else, it is subtracted
		}else{
			if(itemQuantityInStock-amount<0) {
				System.out.println("Insufficient Stock in inventory...\nError could not sell item");
			}else {
				itemQuantityInStock-=amount;
			}
		}
		return true;
	}
	
	/**
	 * Output data members from FoodItem class to a text file. Child classes override this method to add their own specific data member in front and behind these data members.
	 * @param writer Formatter object
	 */
	public void outputItem(Formatter writer) {

		writer.format("%d\n%s\n%.2f\n%d\n%.2f\n",itemCode,itemName,itemPrice,itemQuantityInStock,itemCost);
		
	}
}
