import java.util.Scanner;

public class Assignment2 {
	/**
	 * This method displays a menu to be used in main
	 */
	static void displayMenu() {
		System.out.println("Please select one of the following: ");
		System.out.println("1: Add Item to Inventory: ");
		System.out.println("2: Display Current Inventory: ");
		System.out.println("3: Buy Item(s): ");
		System.out.println("4: Sell Item(s): ");
		System.out.println("5: Search for Item");
		System.out.println("6: Save Inventory to File");
		System.out.println("7: Read Inventory to File");
		System.out.println("8: To Exit");
	}
	/**
	 * Main displays a menu to the user and created a Scanner object for use in the project.
	 * @param args Are the Arguments used in main class
	 */
	public static void main(String[] args) {
		//creating Scanner object, Inventory object, and loop variables
		Scanner keyboard = new Scanner(System.in);
		boolean loop=true;
		Inventory inv = new Inventory();
		//start of loop
		while(loop==true) {
			displayMenu();
			//try catch for exception handling
			try {
				int option = keyboard.nextInt();
				//switch case for menu routing
				switch(option) {
				case 1:
					//adds new item with fromFile set to false, for user input
					inv.addItem(keyboard, false);
					break;
				case 2:
					//prints output of inventory array to console
					inv.toString();
					break;
				case 3:
					//adds item quantity to user selected item
					inv.updateQuantity(keyboard, true);
					break;
				case 4:
					//subtracts item quantity to user selected item
					inv.updateQuantity(keyboard, false);
					break;
				case 5:
					//searches for an itemcode in the inventory array
					System.out.print("Enter the code for the item: ");
					int itemcode = keyboard.nextInt();
					inv.searchForItem(itemcode);
					break;
				case 6:
					//writes the contents of the inventory array to a file
					inv.saveToFile(keyboard);
					break;
				case 7:
					//takes in data from a text file to enter into the inventory array
					inv.readFromFile(keyboard);
					break;
				case 8:
					//exits
					System.out.println("Exiting...");
					keyboard.close();
					loop=false;
					break;
				default:
					System.out.println("Must be a number between 1 - 8");
				}
			//invalid entries are met with a message
			}catch(Exception e) {
			System.out.println("*****Invalid Input*****");
			keyboard.nextLine();
		    }
		}

	}

}
