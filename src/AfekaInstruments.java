import java.awt.Menu;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import javax.print.DocFlavor.STRING;

@SuppressWarnings("unused")
public class AfekaInstruments {

	public static void main(String[] args) {
		Scanner consoleScanner = new Scanner(System.in);
		ArrayList<MusicalInstrument> allInstruments = new ArrayList<>();
		File file = getInstrumentsFileFromUser(consoleScanner);

		loadInstrumentsFromFile(file, allInstruments);

		if (allInstruments.size() == 0) {
			System.out.println("There are no instruments in the store currently");
			return;
		}

		printInstruments(allInstruments);

		int different = getNumOfDifferentElements(allInstruments);

		System.out.println("\n\nDifferent Instruments: " + different);

		MusicalInstrument mostExpensive = getMostExpensiveInstrument(allInstruments);

		System.out.println("\n\nMost Expensive Instrument:\n" + mostExpensive);

		startInventoryMenu(allInstruments, consoleScanner);
		consoleScanner.close();
	}

	public static File getInstrumentsFileFromUser(Scanner consoleScanner) {
		boolean stopLoop = true;
		File file;

		do {
			System.out.println("Please enter instruments file name / path:");
			String filepath = consoleScanner.nextLine();
			file = new File(filepath);
			stopLoop = file.exists() && file.canRead();

			if (!stopLoop)
				System.out.println("\nFile Error! Please try again\n\n");
		} while (!stopLoop);

		return file;
	}

	public static void loadInstrumentsFromFile(File file, ArrayList<MusicalInstrument> allInstruments) {
		Scanner scanner = null;

		try {

			scanner = new Scanner(file);

			addAllInstruments(allInstruments, loadGuitars(scanner));

			addAllInstruments(allInstruments, loadBassGuitars(scanner));

			addAllInstruments(allInstruments, loadFlutes(scanner));

			addAllInstruments(allInstruments, loadSaxophones(scanner));

		} catch (InputMismatchException | IllegalArgumentException ex) {
			System.err.println("\n" + ex.getMessage());
			System.exit(1);
		} catch (FileNotFoundException ex) {
			System.err.println("\nFile Error! File was not found");
			System.exit(2);
		} finally {
			scanner.close();
		}
		System.out.println("\nInstruments loaded from file successfully!\n");

	}

	public static ArrayList<Guitar> loadGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Guitar> guitars = new ArrayList<Guitar>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			guitars.add(new Guitar(scanner));

		return guitars;
	}

	public static ArrayList<Bass> loadBassGuitars(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Bass> bassGuitars = new ArrayList<>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			bassGuitars.add(new Bass(scanner));

		return bassGuitars;
	}

	public static ArrayList<Flute> loadFlutes(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Flute> flutes = new ArrayList<>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			flutes.add(new Flute(scanner));

		return flutes;
	}

	public static ArrayList<Saxophone> loadSaxophones(Scanner scanner) {
		int numOfInstruments = scanner.nextInt();
		ArrayList<Saxophone> saxophones = new ArrayList<>(numOfInstruments);

		for (int i = 0; i < numOfInstruments; i++)
			saxophones.add(new Saxophone(scanner));

		return saxophones;
	}

	public static void addAllInstruments(ArrayList<? super MusicalInstrument> instruments,
			ArrayList<? extends MusicalInstrument> moreInstruments) {
		for (int i = 0; i < moreInstruments.size(); i++) {
			instruments.add(moreInstruments.get(i));
		}
	}

	public static void printInstruments(ArrayList<MusicalInstrument> instruments) {
		for (int i = 0; i < instruments.size(); i++)
			System.out.println(instruments.get(i));
	}

	public static int getNumOfDifferentElements(ArrayList<MusicalInstrument> instruments) {
		int numOfDifferentInstruments;
		ArrayList<MusicalInstrument> differentInstruments = new ArrayList<>();
		System.out.println();

		for (int i = 0; i < instruments.size(); i++) {
			if (!differentInstruments.contains((instruments.get(i)))) {
				differentInstruments.add(instruments.get(i));
			}
		}

		if (differentInstruments.size() == 1)
			numOfDifferentInstruments = 0;

		else
			numOfDifferentInstruments = differentInstruments.size();

		return numOfDifferentInstruments;
	}

	public static MusicalInstrument getMostExpensiveInstrument(ArrayList<MusicalInstrument> instruments) {
		double maxPrice = 0;
		MusicalInstrument mostExpensive = (MusicalInstrument) instruments.get(0);

		for (int i = 0; i < instruments.size(); i++) {
			MusicalInstrument temp = (MusicalInstrument) instruments.get(i);

			if (temp.getPrice() instanceof Integer) {
				if (temp.getPrice().intValue() > maxPrice) {
					maxPrice = temp.getPrice().intValue();
					mostExpensive = temp;
				}
			} else if (temp.getPrice().doubleValue() > maxPrice) {
				maxPrice = temp.getPrice().doubleValue();
				mostExpensive = temp;
			}
		}

		return mostExpensive;
	}

	public static void startInventoryMenu(ArrayList<MusicalInstrument> array, Scanner s) {

		AfekaInventory<MusicalInstrument> inventory = new AfekaInventory<MusicalInstrument>();
		String title = "------------------------------------------------------------------------- \n"
				+ "AFEKA MUSICAL INSTRUMENT INVENTORY MENU\n"
				+ " ------------------------------------------------------------------------- \n";

		String manu = "1. Copy All String Instruments To Inventory \n" + "2. Copy All Wind Instruments To Inventory \n"
				+ "3. Sort Instruments By Brand And Price \n" + "4. Search Instrument By Brand And Price \n"
				+ "5. Delete Instrument \n" + "6. Delete all Instruments \n"
				+ "7. Print Inventory Instruments Choose your option or any other key to EXIT";

		boolean ifContinue = false;

		do {
			int choose = 0;
			System.out.println(title + manu);
			System.out.print("\n Your Option:");
			while (true) {
				try {

					choose = s.nextInt();
					break;
				} catch (InputMismatchException e) {
					s.nextLine();
					System.err.println("please enter number");
				}
			}
			switch (choose) {
			case 1:
				inventory.addAllStringInstrument(array, inventory.getInstruments());
				break;
			case 2:
				inventory.addAllWindInstrument(array, inventory.getInstruments());
				break;
			case 3:
				if (!inventory.getInstruments().isEmpty()) {
					inventory.SortByBrandAndPrice(inventory.getInstruments());
					inventory.setIfSorted(true);
					System.out.println("Instruments Sorted Successfully!");
				}
				break;
			case 4:
				doSecificTask(s, "SEARCH INSTRUMENT: ", inventory);
				break;
			case 5:
				doSecificTask(s, "REMOVE INSTRUMENT: ", inventory);
				break;

			case 6:
				doSecificTask(s, "REMOVE ALL INSTRUMENTS: ", inventory);
				break;
			case 7:
				System.out.print(title);
				if (!inventory.getInstruments().isEmpty())
					System.out.println(inventory.toString());
				else
					System.out.println("There Is No Instruments To Show \n");
				System.out.println(String.format("Total Price: %4.2f sorted: %s", inventory.getTotalPrice(),
						inventory.isIfSorted()));
				break;

			case 8:
				System.out.println("Finished!");
				System.exit(0);

			default:
				ifContinue = true;

			}

		} while (!ifContinue);

	}

	public static void doSecificTask(Scanner s, String target, AfekaInventory<MusicalInstrument> array) {
		if (target.equals("REMOVE ALL INSTRUMENTS: ") == true) {
			System.out.println("REMOVE ALL INSTRUMENTS: ");
			System.out.println("Are You Sure?(Y/N) ");
			if (s.next().equalsIgnoreCase("y") == true) {
				array.removeAll(array.getInstruments());
				array.setIfSorted(false);
				array.setTotalPrice(0);
			}

		} else {
			System.out.println(target);
			System.out.print("BRAND:");
			String brand = s.next();
			System.out.print("PRICE:");
			double price;

			try {
				price = s.nextDouble();
				System.out.println();

			} catch (InputMismatchException e) {
				s.nextLine();
				System.out.println("instruments not found!");
				return;
			}

			int a = array.binnarySearchByBrandAndPrice(array.getInstruments(), brand, price);
			if (a == -1) {
				System.out.println("intstruments not found!");
				return;
			}
			System.out.println("RESULT: ");
			System.out.println(array.getInstruments().get(a).toString());
			if (target.equals("REMOVE INSTRUMENT: ") == true) {

				System.out.println("Are You Sure?(Y/N) ");
				if (s.next().equalsIgnoreCase("y") == true)
					array.removeInstrument(array.getInstruments(), array.getInstruments()
							.get(array.binnarySearchByBrandAndPrice(array.getInstruments(), brand, price)));
			}

		}
	}
}
