package model;

public class Node {
	private int row;
	private int col;
	
	private Node next;
	private Node prev;
	private Node up;	
	private Node down;
	private char letter;
	
	public Node(int r, int c) {
		row = r;
		col = c;
		letter = ' ';
	}
	
	public int getRow() {
		return row;
	}
	
	public int getCol() {
		return col;
	}
	
	public char getNameCol() {
		return (char)('A'+col);
	}
	
	public Node getNext() {
		return next;
	}

	public Node getPrev() {
		return prev;
	}

	public Node getUp() {
		return up;
	}

	public Node getDown() {
		return down;
	}

	public void setPrev(Node p) {
		prev = p;
	}

	public void setNext(Node n) {
		next = n;
	}

	public void setUp(Node u) {
		up = u;
	}

	public void setDown(Node d) {
		down = d;
	}
	
	public String toString() {
		return "["+ letter+"]";// + row + "," + col;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}
	
	
}