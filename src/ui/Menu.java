package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import model.Game;
import model.Matrix;

public class Menu {

	// Game g;
	// BinaryTree scores;
	Matrix board;

	public Menu() {
		showWelcomeMsg();
		systemHandler();

		// scores = new BinaryTree();

	}

	// Method that displays a welcome message for the user
	public void showWelcomeMsg() {

		String msg = "";

		msg += "******************************************************************\n";
		msg += "****************** The laser game**********************\n";
		msg += "*************** developed by: Jhonny Cataño *****************\n";
		msg += "*****************************************************************\n";
		msg += "******************************************************************\n";

		// mensaje += mostrarBannerSeparacion();

		System.out.println();
		System.out.println(msg);
	}

	public void systemHandler() {

		boolean exit = false;

		while (!exit) {

			int userInput = systemMenu();

			switch (userInput) {
			case 1:
				play();
				break;

			case 2:
				checkScores();
				break;

			case 3:
				exit = true;

				break;

			default:
				System.out.println();

			}

		}
//	catch(InputMismatchException e) {
//		System.out.println("please type a number");
//		continue;
//	}
		System.out.println();
		System.out.println("******************************************************************");
		System.out.println("***************** Thanks for using the program *******************");
		System.out.println("******************************************************************");
	}

	private void checkScores() {
		// scores.printInorder();

	}

	private void play() {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"please enter your nickname, rows, columns and amount of mirrors all in one line like this: \n>"
						+ "like this: `juan 3 5 9");
		String v = sc.nextLine();
		String[] values = v.split(" ");
		String name = values[0];
		int n = Integer.parseInt(values[1]);
		int m = Integer.parseInt(values[2]);
		int k = Integer.parseInt(values[3]);

		Game game = new Game(name, n, m, k);
		game.play();
	}

	public int systemMenu() {
		Scanner sc = new Scanner(System.in);
		int value;
		while (!false) {
			try {
				System.out.println("What would you like to do?");

				System.out.println("1.play");
				System.out.println("2.highscores");
				System.out.println("3.exit");

				value = Integer.parseInt(sc.nextLine());

			} catch (InputMismatchException e) {
				System.out.println("please type a number");
				continue;
			}

			return value;
		}
	}

}
