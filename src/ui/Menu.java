package ui;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.*;

public class Menu {

	private Scanner sc;
	private Node start;
	private Node end;

	// Game g;
	// BinaryTree scores;
	Matrix board;

	public Menu() {
		sc = new Scanner(System.in);

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
		// TODO Auto-generated method stub
		System.out.println(
				"please enter your nickname, rows, columns and amount of mirrors all in one line like this: \n>"
						+ "like this: `juan 3 5 9");
		String v = sc.nextLine();
		String[] values = v.split(" ");
		String name = values[0];
		int n = Integer.parseInt(values[1]);
		int m = Integer.parseInt(values[2]);
		int k = Integer.parseInt(values[3]);

		// g= new Game(name, n, m, k);
		board = new Matrix(n, m, k);

		shut();

	}

	public void shut() {

		/*
		 * una frase indicando el nickname del usuario seguido de cuántos espejos faltan
		 * por ubicar. Por ejemplo: seyerman: 4 mirrors remaining.
		 */
		System.out.println(board);
		
		// Reiniciar S y E
		if (start != null) {
			start.setLetter(' ');
		}
		if (end != null) {
			end.setLetter(' ');
		}
		
		String v = sc.nextLine();
		
		if (v.contains("menu")) {
			return;
		}

		int row = Integer.parseInt(String.valueOf(v.charAt(0)));
		int col = v.charAt(1) - 64;
		Character o = v.length() >= 3 ? v.charAt(2) : null;

		start = board.findByCol(board.getFirst(), col);
		start = board.findByRow(start, row);
		start.setLetter('S');

		if (row == 1 && col == 1) {
			if (o == null || o == 'H') {
				end = moveRight(start);
			} else if(o == 'V') {
				end = moveDown(start);
			} else {
				shut();
			}
		} else if (row == 1 && col == board.getNumCols()) {
			if (o == null || o == 'H') {
				end = moveLeft(start);
			} else if(o == 'V') {
				end = moveDown(start);
			} else {
				shut();
			}
		} else if (row == board.getNumRows() && col == 1) {
			if (o == null || o == 'H') {
				end = moveRight(start);
			} else if(o == 'V') {
				end = moveUp(start);
			} else {
				shut();
			}
		} else if (row == board.getNumRows() && col == board.getNumCols()) {
			if (o == null || o == 'H') {
				end = moveLeft(start);
			} else if(o == 'V') {
				end = moveUp(start);
			} else {
				shut();
			}
		} else if (row == 1) {
			end = moveDown(start);
		} else if(row == board.getNumRows()) {
			end = moveUp(start);
		} else if (col == 1) {
			end = moveRight(start);
		} else if(col == board.getNumCols()) {
			end = moveLeft(start);
		}		
		
		shut();
	}

	private Node moveLeft(Node from) {
		if (from.getLetter() == '/') {
			Node next = from.getDown();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveDown(next);
		}
		
		if (from.getLetter() == '\\') {
			Node next = from.getUp();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveUp(next);
		}
		
		Node next = from.getPrev();
		if (next == null) {
			from.setLetter('E');
			return from;
		}
		return moveLeft(next);
	}

	private Node moveRight(Node from) {
		if (from.getLetter() == '/') {
			Node next = from.getUp();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveUp(next);
		}
		
		if (from.getLetter() == '\\') {
			Node next = from.getDown();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveDown(next);
		}
		
		Node next = from.getNext();
		if (next == null) {
			from.setLetter('E');
			return from;
		}
		return moveRight(next);
	}

	private Node moveUp(Node from) {
		if (from.getLetter() == '/') {
			Node next = from.getNext();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveRight(next);
		}
		
		if (from.getLetter() == '\\') {
			Node next = from.getPrev();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveLeft(next);
		}
		
		Node next = from.getUp();
		if (next == null) {
			from.setLetter('E');
			return from;
		}
		return moveUp(next);
	}

	private Node moveDown(Node from) {
		if (from.getLetter() == '/') {
			Node next = from.getPrev();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveLeft(next);
		}
		
		if (from.getLetter() == '\\') {
			Node next = from.getNext();
			if (next == null) {
				from.setLetter('E');
				return from;
			}
			return moveRight(next);
		}
		
		Node next = from.getDown();
		if (next == null) {
			from.setLetter('E');
			return from;
		}
		return moveDown(next);
	}

	public int systemMenu() {
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
