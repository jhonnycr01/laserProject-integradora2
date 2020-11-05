package model;

import java.util.Scanner;

public class Game {
	private Leaf p1;
	private BinaryTree scores;
	private Matrix board;
	private Node start;
	private Node end;
	
	public Game(String nick, int n, int m, int k) {
		scores = new BinaryTree();
		p1 = new Leaf(0, nick);
		board = new Matrix(n, m, k);
	}
	
	public Matrix getBoard() {
		return board;
	}
	
	public void play() {
	
		shut();
	}
	
	public void shut() {

		/*
		 * una frase indicando el nickname del usuario seguido de cuántos espejos faltan
		 * por ubicar. Por ejemplo: seyerman: 4 mirrors remaining.
		 */
		Scanner sc = new Scanner(System.in);
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
	
}
