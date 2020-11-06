package model;

import java.util.Scanner;

public class Game {
	private Leaf p1;
	private BinaryTree scores;
	private Matrix board;

	private Node start;
	private Node end;
	private Node located;

	private Node mirrorEndBackup;
	private char mirrorCharEndBackup;

	private Node mirrorStartBackup;
	private char mirrorCharStartBackup;

	private char locatedCharBackup;

	private String nombre;
	private int score;

	public Game(String nick, int n, int m, int k) {
		nombre = nick;
		score = 0;
		scores = new BinaryTree();
		p1 = new Leaf(0, nick);
		board = new Matrix(n, m, k);
	}

	public Matrix getBoard() {
		return board;
	}

	public int play() {
		shut();
		return score;
	}

	public void shut() {

		/*
		 * una frase indicando el nickname del usuario seguido de cuántos espejos faltan
		 * por ubicar. Por ejemplo: seyerman: 4 mirrors remaining.
		 */
		System.out.println(nombre + ": " + (board.getMirrows() - score) + " mirrors remaining. Score: " + score);

		Scanner sc = new Scanner(System.in);
		System.out.println(board);

		// Reiniciar S, E and mirrors
		if (start != null) {
			start.setLetter(' ');
		}
		if (end != null) {
			end.setLetter(' ');
		}
		if (mirrorStartBackup != null) {
			mirrorStartBackup.setLetter(mirrorCharStartBackup);
		}
		if (mirrorEndBackup != null) {
			mirrorEndBackup.setLetter(mirrorCharEndBackup);
		}
		if (located != null) {
			located.setLetter(locatedCharBackup);
		}

		String input = sc.nextLine();

		if (input.contains("menu")) {
			return;
		}

		if (input.startsWith("L")) {
			locate(input);
			if (board.getMirrows() == score) {
				return;
			}
		} else {
			move(input);
		}

		shut();
	}

	private void locate(String input) {
		// L2CL
		int row = Integer.parseInt(String.valueOf(input.charAt(1)));
		int col = input.charAt(2) - 64;

		// Get character provided by user for the mirror direction
		Character o = null;
		if (input.length() >= 4) {
			if (input.charAt(3) == 'R') {
				o = '/';
			} else if (input.charAt(3) == 'L') {
				o = '\\';
			}
		}
		located = board.findByCol(board.getFirst(), col);
		located = board.findByRow(located, row);
		locatedCharBackup = located.getLetter();

		System.out.println(input + " Direction " + o + " located " + located);

		if (located.getLetter() == o) {
			if (!located.isMirrirLocated()) {
				located.setMirrirLocated(true);
				++score;
			}
		} else {
			located.setLetter('X');
		}
	}

	private void move(String input) {
		int row = Integer.parseInt(String.valueOf(input.charAt(0)));
		int col = input.charAt(1) - 64;
		Character o = input.length() >= 3 ? input.charAt(2) : null;

		start = board.findByCol(board.getFirst(), col);
		start = board.findByRow(start, row);

		mirrorStartBackup = start;
		mirrorCharStartBackup = start.getLetter();
		located = null;

		start.setLetter('S');

		if (row == 1 && col == 1) {
			if (o == null || o == 'H') {
				end = moveRight(start);
			} else if (o == 'V') {
				end = moveDown(start);
			} else {
				shut();
			}
		} else if (row == 1 && col == board.getNumCols()) {
			if (o == null || o == 'H') {
				end = moveLeft(start);
			} else if (o == 'V') {
				end = moveDown(start);
			} else {
				shut();
			}
		} else if (row == board.getNumRows() && col == 1) {
			if (o == null || o == 'H') {
				end = moveRight(start);
			} else if (o == 'V') {
				end = moveUp(start);
			} else {
				shut();
			}
		} else if (row == board.getNumRows() && col == board.getNumCols()) {
			if (o == null || o == 'H') {
				end = moveLeft(start);
			} else if (o == 'V') {
				end = moveUp(start);
			} else {
				shut();
			}
		} else if (row == 1) {
			end = moveDown(start);
		} else if (row == board.getNumRows()) {
			end = moveUp(start);
		} else if (col == 1) {
			end = moveRight(start);
		} else if (col == board.getNumCols()) {
			end = moveLeft(start);
		}
	}

	private Node moveLeft(Node from) {
		if (from.getLetter() == '/' || (from == mirrorStartBackup && mirrorCharStartBackup == '/')) {
			Node next = from.getDown();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveDown(next);
		}

		if (from.getLetter() == '\\' || (from == mirrorStartBackup && mirrorCharStartBackup == '\\')) {
			Node next = from.getUp();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveUp(next);
		}

		Node next = from.getPrev();
		if (next == null) {
			mirrorEndBackup = from;
			mirrorCharEndBackup = from.getLetter();
			from.setLetter('E');
			return from;
		}
		return moveLeft(next);
	}

	private Node moveRight(Node from) {
		if (from.getLetter() == '/' || (from == mirrorStartBackup && mirrorCharStartBackup == '/')) {
			Node next = from.getUp();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveUp(next);
		}

		if (from.getLetter() == '\\' || (from == mirrorStartBackup && mirrorCharStartBackup == '\\')) {
			Node next = from.getDown();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveDown(next);
		}

		Node next = from.getNext();
		if (next == null) {
			mirrorEndBackup = from;
			mirrorCharEndBackup = from.getLetter();
			from.setLetter('E');
			return from;
		}
		return moveRight(next);
	}

	private Node moveUp(Node from) {
		if (from.getLetter() == '/' || (from == mirrorStartBackup && mirrorCharStartBackup == '/')) {
			Node next = from.getNext();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveRight(next);
		}

		if (from.getLetter() == '\\' || (from == mirrorStartBackup && mirrorCharStartBackup == '\\')) {
			Node next = from.getPrev();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveLeft(next);
		}

		Node next = from.getUp();
		if (next == null) {
			mirrorEndBackup = from;
			mirrorCharEndBackup = from.getLetter();
			from.setLetter('E');
			return from;
		}
		return moveUp(next);
	}

	private Node moveDown(Node from) {
		if (from.getLetter() == '/' || (from == mirrorStartBackup && mirrorCharStartBackup == '/')) {
			Node next = from.getPrev();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveLeft(next);
		}

		if (from.getLetter() == '\\' || (from == mirrorStartBackup && mirrorCharStartBackup == '\\')) {
			Node next = from.getNext();
			if (next == null) {
				mirrorEndBackup = from;
				mirrorCharEndBackup = from.getLetter();
				from.setLetter('E');
				return from;
			}
			return moveRight(next);
		}

		Node next = from.getDown();
		if (next == null) {
			mirrorEndBackup = from;
			mirrorCharEndBackup = from.getLetter();
			from.setLetter('E');
			return from;
		}
		return moveDown(next);
	}

}
