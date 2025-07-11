package theRiseOfITS.main;

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.databind.ObjectMapper;

import theRiseOfITS.concreto.entity.Player;
import theRiseOfITS.concreto.rooms.Floor;

public class Main {
	
	private static Player player = null;
	private static Floor floor = null;

	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {

		System.out.println("Benvenuto/a in The Rise of ITS"); // Frase di presentazione del gioco

		int option = firstMenu(); // Restituisce il valore del primo menu'

		switch (option) {

		case 1: // Carica una nuova partita
			// Richiamo sistema di tracciamento delle stanze
			newGame();
			break;

		case 2: // Carico una partita già esistente con i dati salvati in un file
			continueGame();
			break;

		case 3: // Esci dal gioco
			exitGame();
			
			return;

		default:
			System.out.println("Hai inserito un valore non valido...");
			// In teoria non dovrebbe mai essere stampato visto che ho fatto i controlli per
			// forzare ad inserire un valore valido

		}
	}

	private static void continueGame() {
		// TODO Auto-generated method stub

	}

	private static void newGame() {
		//Carica nuova partiata
		System.out.println("Come vuoi chiamarti?");
		String name = input.nextLine();
		Main.player = new Player(name); //Da salvare in file
		Main.floor = new Floor("Piano terra"); //Da salvare in file
		floor.generateMap();
		player.setCurrentFloor(floor);
		player.setCurrentRoom(floor.getInitialRoom());
		
		System.out.println("Ti trovi al Piano terra");
		int choice = 0;
		boolean runningGame = true;
		while(runningGame && !player.isDead()) {
			
			do {

				System.out.println("\n============ MENU ==========");
				System.out.println("1. Esamina stanza");
				System.out.println("2. Mostra inventario");
				System.out.println("3. Cambia stanza");
				System.out.println("4. Esci dal gioco");
				floor.printMap();
				
				try {
					choice = Integer.parseInt(input.nextLine());
					if (choice < 1 || choice > 4) {
						System.out.println("Hai inserito un valore non valido.");
					}

				} catch (Exception e) {
					System.out.println("Hai inserito un valore non valido.");
				}
			} while (choice < 1 || choice > 4); //Ciclo do while per forzare ad inserire un valore valido
			
			switch(choice) {
				case 1 : System.out.println(player.examineRoom(player.getCurrentRoom()));
					break;
				case 2 : player.showInventory();
					break;
				case 3 : player.chooseAndChangeRoom();
					break;
				case 4 : exitGame();
					runningGame = false;
					break;
					
				default:
					break;
				
			}
			
		}
		
	}
	

	public static int firstMenu() {
		int option = 0;
		do {

			System.out.println("Scegli un opzione:\n" + "1 - Carica una nuova partita\n"
					+ "2 - Continua da una partita già esistente\n" + "3 - Esci dal gioco"); // Vuoi salvare i progessi?
			try {
				option = Integer.parseInt(input.nextLine());
				if (option < 1 || option > 3) {
					System.out.println("Hai inserito un valore non valido.");
				}

			} catch (Exception e) {
				System.out.println("Hai inserito un valore non valido.");
				
			}
		} while (option < 1 || option > 3); // Ciclo do while per forzare ad inserire un valore valido

		return option;
	}

	public static void exitGame() {
		int option = 0;
		do {

			System.out.println("Vuoi salvare i progressi fatti fin'ora?\n" + "1 - Si'" + "2 - No");
			try {
				option = Integer.parseInt(input.nextLine());
				if (option < 1 || option > 2) {
					System.out.println("Hai inserito un valore non valido.");
				}

			} catch (Exception e) {
				System.out.println("Hai inserito un valore non valido.");
			}
		} while (option < 1 || option > 2);

		if (option == 1) {
			// Salva dati su file
			saveGame();
			
		}

	}

	private static void saveGame() {
		List<Object> data = new ArrayList<Object>();
		data.add(Main.player);
		data.add(Main.floor);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("salvataggio.json"), data);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}