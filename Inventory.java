import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Formatter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
/**
 * Inventory class handles methods involving inventory mechanics, such as adding, buying, selling
 * and displaying items.
 * @author Thomas Stanley
 *
 */
public class Inventory{
	//creating arrayList of FoodItem objects
	private ArrayList<FoodItem> inventory;
	private int numItems;
	/**
	 * Constructor for Inventory class
	 */
	public Inventory() {
		inventory = new ArrayList<FoodItem>(20);
		numItems=0;
	}
	
	/**
	 * Adds an item to the array depending on the FoodItem type.
	 * @param keyboard is Scanner object
	 * @param fromFile Can be passed as either true or false, if false, user input
 *			           is required for values, if true, object is made from values
 *                     in the file.
	 * @return returns a boolean value
	 */
	public boolean addItem(Scanner keyboard, boolean fromFile) {
		//if fromFile is false, user is asked what kind of item they want to add
		if(fromFile==false) {
		System.out.print("Do you wish to add a fruit(f), vegetable(v), preserve(p), or cheese(c)?: ");}
		String option = keyboard.next();
		
		//start of menu
		switch(option) {
		case "f": 
			//new fruit
			inventory.add(numItems, new Fruit());
			break;
			
		case "v": 
			//new vegetable
			inventory.add(numItems, new Vegetable());	
			break;
			
		case "p":
			//new preserve
			inventory.add(numItems, new Preserve());	
			break;
		    
		case "c":
			//new cheese
			inventory.add(numItems, new Cheese());	
			break;
			//error message is displayed on incorrect entry
		    default:
		    	System.out.println("Please enter valid item type, (f), (v), (p), or (c): ");
			}
		//if fromFile is true, method adds item from a user defined file
		if(fromFile==true) {
			//check for duplicate item code
			int itemCode= keyboard.nextInt();
			if(alreadyExists(itemCode)!=-1) {
				System.out.println("Item code already exists");
			}else {
				//additem method with fromFile set to true is called
				inventory.get(numItems).itemCode = itemCode;
				inventory.get(numItems).addItem(keyboard, true);
				numItems++;
			}
		//if fromFile is false, additem method is called with fromFile set to false
		}else {
		int itemCode=inventory.get(numItems).inputCode(keyboard,false);
		//check for duplicate item code
		if(alreadyExists(itemCode)!=-1) {
			System.out.println("Item code already exists");
		}else {
		inventory.get(numItems).itemCode = itemCode;
		inventory.get(numItems).addItem(keyboard, false);
		numItems++;
		}
		}
		//sorting the array by itemCode
		Collections.sort(inventory, new sortByItemCode());
		return true;
	}
	/**
	 * Checks if the given item code already exists in the array. Prevents duplicate entries.
	 * @param itemCode is the item code of of the FoodItem
	 * @return returns the index of the array in which the item code matches.
	 */
	public int alreadyExists(int itemCode) {
		
		for(int i =0; i<inventory.size();i++) {
			try {
			if(inventory.get(i).itemCode==itemCode) {
				return i;
			}
			}catch(NullPointerException NPE) {
				
			}
		}
		return -1;
	}
	
	/**
	 * Displays each entered item in the array. Only prints entries with an item code
	 */
	@Override
	public String toString() {
		System.out.println("Inventory\n");
		try {
		for(int i =0; i<inventory.size();i++) {
			if(inventory.get(i).itemCode!=0) {
			System.out.print(inventory.get(i).toString());
				}
			}
			}catch(NullPointerException NPE) {
				
			}
		return "";
	}
	/**
	 * Raises and lowers the itemQuantityInStock value of any given element in array, given an item code.
	 * Checks to see if buy amount or sell amount is less than 0.
	 * @param keyboard Scanner object
	 * @param buyOrSell If true, item is bought, if false item is sold.
	 * @return returns boolean value
	 */
	public boolean updateQuantity(Scanner keyboard, boolean buyOrSell) {
		int codeInput;
		System.out.println("Enter valid item code: ");
		codeInput=keyboard.nextInt();
		alreadyExists(codeInput);
		//if true, item is bought
		if(buyOrSell==true) {
			if(alreadyExists(codeInput)!=-1) {
				System.out.println("Enter valid quantity to buy: ");
				int buyAmount = keyboard.nextInt();
					if(buyAmount<=0) {
						System.out.println("Invalid Quantity\nError could not buy item...");
					}
				inventory.get(alreadyExists(codeInput)).updateItem(buyAmount,true);
				}else {
					System.out.println("Code not found in inventory...\nError...Code could not buy item");
				}
			//if false, item is sold
		}else if(buyOrSell==false) {
			if(alreadyExists(codeInput)!=-1) {
				System.out.println("Enter valid quantity to sell: ");
				int sellAmount = keyboard.nextInt();
					if(sellAmount<=0) {
						System.out.println("Invalid Quantity...\nError could not sell item");
					}
				inventory.get(alreadyExists(codeInput)).updateItem(sellAmount,false);
				}else {
					System.out.println("Code not found in inventory...\nError...could not sell item");
				}
		}
		return false;
	}
	
	/**
	 * Searches for an item in the Inventory array. Uses non-recursive binary search.
	 * @param input The itemcode the user entered to search for
	 */
	public void searchForItem(int input) {
		int last = inventory.size();
		int first = 0;
		//starting search loop
		while(first<=last){
	
			//setting middle element position
			int middle=(first+last) /2;
			//if the middle of the array is the number the user entered, middle is returned and the loop ends.
			if(inventory.get(middle).itemCode==input) {
				System.out.println("Item: " + inventory.get(middle).toString());
				return;
			}
			//if the middle is lower than the input, the left side is cut
			if(inventory.get(middle).itemCode<input) {
				first=middle+1;
			}else {
			//else the right side is cut
				last=middle-1;
			}
		}
		//if the loop does not find the value, error message is displayed
		System.out.println("Code not found in inventory...");
	}
	
	/**
	 * Writes the contents of the array to a text file, structured in a way that it could be read back into the array if needed.
	 * @param keyboard Scanner object
	 */
	public void saveToFile(Scanner keyboard) {
		try {
		//getting user input
		System.out.print("Enter the filename to save to: ");
		String fileName = keyboard.next();
		//creating formatter object
		Formatter writerOutput = new Formatter(new FileOutputStream(fileName));
		//looping through array and calling each objects outputItem() method
		for(int i = 0; i< inventory.size(); i++) {
			inventory.get(i).outputItem(writerOutput);
		}
		//closing formatter and defining catch
		writerOutput.close();
		} catch (FileNotFoundException e) {
			System.out.println("savetofile error");
		}
	}
	
	/**
	 * Reads a text file and creates new objects in the inventory array 
	 * @param keyboard Scanner object
	 */
	public void readFromFile(Scanner keyboard) { 
		//asking user for text file
		System.out.print("Enter the filename to read from: ");
		String fileName = keyboard.next();
		try {
			//creating new scanner object
			Scanner reader = new Scanner(Paths.get(fileName));
			//while the file has characters in it, scanner will read. Calls add item with fromFile set to true.
			while(reader.hasNext()) {
				addItem(reader,true);
			}
			//closing scanner and defining catch
			reader.close();
			}catch (Exception e) {
			System.out.println("File not found, ignoring...");
			return;
		}
	}
	
	/**
	 * Method required by Comparator. Compares the itemcode of two objects. Used by cllection.sort method to sort the inventory array by object itemcode.
	 */
	public class sortByItemCode implements Comparator<FoodItem>{

		@Override
		public int compare(FoodItem o1, FoodItem o2) {
			return o1.itemCode - o2.itemCode;
		}
		
		
		
	}
}
